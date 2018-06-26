package cn.ucmed.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/HelloSpringBoot")
public class HelloSpringBoot {
    // thymeleaf 模板
    @RequestMapping("/thymeleaf")
    public String thymeleaf(Map<String, Object> map) {
        map.put("name", "Andy");
        return "thymeleaf";
    }

    // freemarker 模板
    @RequestMapping("/freemarker")
    public String freemarker(Map<String, Object> map) {
        map.put("name", "Andy");
        return "freemarker";
    }

    // vue 测试
    @RequestMapping("/vue1")
    public String vue1() {
        return "vue";
    }

    // vue 测试
    @RequestMapping("/vue2")
    public String vue2() {
        return "index2";
    }
}
