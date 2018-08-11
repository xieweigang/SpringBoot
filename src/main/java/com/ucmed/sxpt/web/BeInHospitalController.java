package com.ucmed.sxpt.web;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/beInHospital")
public class BeInHospitalController {
    public static final Logger LOG = Logger.getLogger(FPaymentController.class);

    // 住院入口页面
    @RequestMapping(method = RequestMethod.GET, value = "/index.htm")
    public String index(HttpServletRequest request, HttpSession session, ModelMap map) {
        map.put("kh", "H32334244");
        map.put("klx", "3");
        return "beInHospital/index.html";
    }

    // 住院列表页面
    @RequestMapping(method = RequestMethod.GET, value = "/beInHospitalList.htm")
    public String beInHospitalList(HttpServletRequest request, ModelMap map) {
        return "beInHospital/beInHospitalList.vm";
    }

    // 住院详情页面
    @RequestMapping(method = RequestMethod.GET, value = "/beInHospitalDetail.htm")
    public String beInHospitalDetail(HttpServletRequest request, ModelMap map) {
        return "beInHospital/beInHospitalDetail.vm";
    }

    // 日期清单列表
    @RequestMapping(method = RequestMethod.GET, value = "/beInHospitalDayList.htm")
    public String beInHospitalDayList(HttpServletRequest request, ModelMap map) {
        return "beInHospital/beInHospitalDayList.vm";
    }

    // 每日清单页面
    @RequestMapping(method = RequestMethod.GET, value = "/beInHospitalDayDetail.htm")
    public String beInHospitalDayDetail(HttpServletRequest request, ModelMap map) {
        return "beInHospital/beInHospitalDayDetail.vm";
    }
}
