package com.ucmed.sxpt.web;

import com.ucmed.sxpt.entity.dto.UserDto;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;

@Controller
@RequestMapping("/public")
public class PublicController {
    public static final Logger LOG = Logger.getLogger(PublicController.class);

    // 登录页面
    @RequestMapping(value = "/test.htm")
    public String test(HttpServletRequest request, ModelMap map) {
        Map<String, String> treeMap = new TreeMap<>();
        Enumeration<String> paraNames = request.getParameterNames();
        for (Enumeration<String> e = paraNames; e.hasMoreElements(); ) {
            String thisName = e.nextElement().toString();
            String thisValue = request.getParameter(thisName);
            treeMap.put(thisName, thisValue);
        }
        LOG.info(treeMap);
        return "public/login";
    }

    // 登录页面
    @RequestMapping(method = RequestMethod.GET, value = "/login.htm")
    public String login(HttpServletRequest request, ModelMap map) {
        map.put("kh", "010");
        map.put("klx", "3");
        return "public/login";
    }

    // 登录执行页面
    @RequestMapping(method = RequestMethod.GET, value = "/loginDo.htm")
    public String loginDo(HttpServletRequest request, HttpSession session) {
        UserDto userDto = new UserDto();
        userDto.setKh(request.getParameter("kh"));
        userDto.setKlx(request.getParameter("klx"));
        session.setAttribute("user", userDto);
        if (session.getAttribute("backUrl") == null) {
            return "redirect:" + "/payment/testPay.htm";
        }
        return "redirect:" + request.getSession().getAttribute("backUrl");
    }

    // 退出执行页面
    @RequestMapping(method = RequestMethod.GET, value = "/logoutDo.htm")
    public String logoutDo(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:" + "/public/login.htm";
    }
}
