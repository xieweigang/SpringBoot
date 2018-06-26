package cn.ucmed.springboot;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 出现404解决
 * 1、地址是否正确
 * 2、注解是否对了
 * 3、包路径是否正确，能否被扫描到加载到
 * 默认启动8080端口
 *
 * @author Administrator
 * @version V1.3.1
 * @Description 这里使用@SpringBootApplication只是一个springBoot应用程序
 * @date 2017-4-21 下午9:08:54
 */
@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
@MapperScan("cn.ucmed.springboot.dao")
public class Application
//extends WebMvcConfigurerAdapter
{
    /**
     * 添加第三方json工具
     * 1、需要再pom.xml加入相关以来
     * 2、需要再APP 继承 WeWebMvcConfigurerAdapter  重写configureMessageConverters
     * 3、或者使用bean注入fastJsonHttpMessageConverters
     * <p>
     * <p>
     * 配置fastjson支持两种方法
     * 一：1、启动继承 WebMvcConfigurerAdapter 2、覆盖方法configureMessageConverters
     * 二：使用bean注入fastJsonHttpMessageConverters
     * 这里使用@Bean注入 HttpMessageConverters
     *
     * @return
     * @Description
     * @author Administrator
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        //1、定义convert转换消息对象
        FastJsonHttpMessageConverter fasConverter = new FastJsonHttpMessageConverter();
        //2、添加fastJson的配置信息，比如：是否要格式化返回json数据
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        //3、再convert中添加配置信息
        fasConverter.setFastJsonConfig(fastJsonConfig);
        HttpMessageConverter<?> converter = fasConverter;
        return new HttpMessageConverters(converter);
    }

    //    1、需要先定义一个conver转换消息对象
    //    2、添加fastJson配置信息，比如：食肉需要格式化返回json数据
    //    3、再convert中添加配置信息
    //    4、讲convert添加到converters中
    /*@Override
    public void configureMessageConverters(
            List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(fastConverter);
    }*/

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}