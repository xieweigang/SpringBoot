package com.ucmed.sxpt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Administrator
 * @version V1.3.1
 * @Description 这里使用@SpringBootApplication只是一个springBoot应用程序
 * @date 2017-4-21 下午9:08:54
 */
@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
@MapperScan("com.ucmed.sxpt.dao")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}