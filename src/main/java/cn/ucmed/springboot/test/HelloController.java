package cn.ucmed.springboot.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.ucmed.springboot.dto.User;
import cn.ucmed.springboot.dto.User2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注解全名：
 *
 * @author Administrator
 * @version V1.3.1
 * @Description 使用RestController 相当于@Controller 和 @RequestBody
 * @date 2017-4-21 下午9:06:28
 */
// 相当于 @Controller + @ResponseBody
// 该注解 方法method 返回类型是String时候则返回string,返回对象时候则讲json_encode 该对象的json字符串
@RestController
// @EnableAutoConfiguration
@RequestMapping("/hello")
public class HelloController {

    /**
     * 请求： http://localhost:8800/test/ http方式：get 请求返回contentType: text/plain
     * 请求responseBody: "Hello page"
     *
     * @return
     * @Description
     * @author Administrator
     */
    @RequestMapping("/")
    public String index() {
        return "Hello page";
    }

    /**
     * 请求：http://localhost:8800/test/hello/
     * http方式：get 请求
     * 返回contentType: text/plain
     * 请求responseBody: "Hello 123 abc 123 123 !!!"
     *
     * @return
     * @Description
     * @author Administrator
     */
    @RequestMapping("/hello")
    public String hello() {
        return "Hello 123 abc 123 123 !!!";
    }

    /**
     * 请求：http://localhost:8800/test/hello/testname
     * 请求返回contentType:
     * responseBody: "Hello testname!!!"
     *
     * @param myName
     * @return
     * @Description
     * @author Administrator
     */
    @RequestMapping("/hello3/{myName}")
    public String hello3(@PathVariable String myName) {
        return "Hello " + myName + "!!!";
    }

    /**
     * 请求：http://localhost:8800/test/hello4?id=123&name=abc
     * http方式：get
     * 请求返回contentType: text/plain
     * 请求responseBody: ""id:123 name:abc""
     *
     * @param id
     * @param name
     * @return
     * @Description
     * @author Administrator
     */
    @RequestMapping("/hello4")
    public String hello3(int id, String name) {
        System.out.println("id:" + id + " name:" + name);
        return "id:" + id + " name:" + name;
    }

    /**
     * 请求：http://localhost:8800/test//getDemo/testname
     * http方式：get
     * 请求返回contentType: application/json
     * 请求responseBody: { "createTime": 1495640486000, "id": 123, "name": "good122223" } fastjson支持
     * 返回对象则自动解析为json字符串 因为spring boot
     * 默认使用json解析框架自动返回，返回头是Content-Type:application/json;charset=UTF-8
     *
     * @param myName
     * @return {"id":123,"name":"good","createTime":1492782569909}
     * @Description
     * @author Administrator
     */
    @RequestMapping("/getDemo/{myName}")
    User getDemo(@PathVariable String myName) {
        User user = new User();
        user.setId(123);
        user.setName("good122223");
        user.setCreateTime(new Date());
        System.out.println("Hello " + myName + "!!!");
        return user;
    }

    /**
     * 请求：http://localhost:8800/test//getDemo/testname
     * http方式：get
     * 请求返回contentType: application/json
     * 请求responseBody: { "createTime": "2017-05-24 23:43:02", "id": 123, "name": "sss" }
     * spring boot 默认使用jackjson来解析json 使用fastjson返回json 因为spring boot
     * 默认使用json解析框架自动返回， 返回头是Content-Type:application/json;charset=UTF-8
     *
     * @return {"createTime":"2017-05-20 13:23:57","id":123,"name":"sss"}
     * @Description
     * @author Administrator
     */
    @RequestMapping("/getJson")
    public User2 getJson() {
        User2 user = new User2();
        user.setId(123);
        user.setName("sss");
        user.setCreateTime(new Date());
        return user;
    }

    /**
     * 请求：http://localhost:8800/test//getMapping?id=123&name=abc
     * http方式：get
     * 请求返回contentType: application/json
     * 请求responseBody: { "id": 123, "name": "abc" }
     *
     * @param user
     * @return
     * @Description
     */
    // 只有get方式能成功,发送post会报错。@GetMapping = @RequestMapping(method = { RequestMethod.GET }
    @GetMapping("/getMapping")
    public User getMapping(User user) {
        return user;
    }

    /**
     * 请求：http://localhost:8800/test//getMapping2?id=123&name=abc
     * http方式：get
     * 请求返回contentType: application/json
     * 请求responseBody: { "id": 123, "name": "abc" }
     * 请求boty设置为 application/x-www-form-urlencoded 也可以接受参数
     * body : id=123&name=abc
     *
     * @param user
     * @return
     * @RequestMapping(value="/getMapping" )
     * 等于 @RequestMapping(value="/getMapping" , method={RequestMethod.GET,RequestMethod.POST})
     * @Description
     */
    @RequestMapping(value = "/getMapping2", method = {RequestMethod.GET, RequestMethod.POST})
    public User getMapping2(User user) {
        return user;
    }

    /**
     * @param user
     * @return
     * @RequestBody String 直接获取请求体， 不封装
     * 请求：http://localhost:8800/test/get2
     * http方式：post
     * {"createTime":1495640486000,"name":"good122223","id":123}
     * 请求返回contentType: application/json 请求responseBody:
     * {"createTime":1495640486000,"name":"good122223","id":123}
     * @Description
     */
    @RequestMapping("/get2")
    public User get2(@RequestBody String reqContent, @RequestBody User user) {
        // {"name":"abc","id":123}
        System.out.println("reqContent:" + reqContent);
        System.out.println(user.toString());
        return user;
    }

    /**
     * 对json数据进行实体类封装 请求：http://localhost:8800/test/user
     * http方式：post
     * 请求requestBody:
     * {"createTime":1495640486000,"name":"good122223","id":123}
     * 请求返回contentType: application/json
     * 请求responseBody: {"createTime":1495640486000,"name":"good122223","id":123}
     *
     * @param user
     * @return
     * @Description
     */
    // {"id":2,"username":"user2","name":"李四","age":20,"balance":100.00}
    // 发送格式没application/json
    @PostMapping("/user")
    public User postUser(@RequestBody User user) {
        System.out.println(user);
        return user;
    }

    /**
     * 请求：http://localhost:8800/test/list-all
     * http方式：get
     * 请求返回contentType:
     * 请求responseBody: [ { "createTime": 1495641815658, "id": 1, "name":
     * "zhangsan" }, { "createTime": 1495641815658, "id": 1, "name": "zhangsan"
     * }, { "createTime": 1495641815658, "id": 1, "name": "zhangsan" } ]
     *
     * @return
     * @Description
     */
    @GetMapping("list-all")
    public List<User> listAll() {
        ArrayList<User> list = new ArrayList<User>();
        User user = new User(1, "zhangsan", new Date());
        User user2 = new User(1, "zhangsan", new Date());
        User user3 = new User(1, "zhangsan", new Date());
        list.add(user);
        list.add(user2);
        list.add(user3);
        return list;

    }

    /**
     * 会自动使用fastjson进行数据解析
     * 请求：http://localhost:8800/test/list-all2
     * http方式：get
     * 请求responseBody: [ { "createTime": "2017-05-25 00:11:11",
     * "id": 1, "name": "zhangsan" }, { "createTime": "2017-05-25 00:11:11",
     * "id": 1, "name": "zhangsan" } ]
     *
     * @return
     * @Description
     */
    @GetMapping("list-all2")
    public List<User2> listAll2() {
        ArrayList<User2> list = new ArrayList<User2>();
        User2 user = new User2(1, "zhangsan", new Date(), "beijing");
        User2 user2 = new User2(1, "zhangsan", new Date(), "sh");
        list.add(user);
        list.add(user2);
        return list;

    }
}