package com.ucmed.sxpt.util;

import com.ucmed.sxpt.web.FPaymentController;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class WebResponse {
    public static final Logger LOG = Logger.getLogger(FPaymentController.class);

    // Json响应
    public static void JsonResponse(HttpServletResponse response, String resString) {
        Response(response, resString, "application/json", "UTF-8");
    }

    // Post响应
    public static void PostResponse(HttpServletResponse response, String resString) {
        Response(response, resString, "multipart/form-data", "UTF-8");
    }

    // XML响应
    public static void XMLResponse(HttpServletResponse response, String resString) {
        Response(response, resString, "text/xml", "UTF-8");
    }

    // 自定义响应
    public static void Response(HttpServletResponse response, String resString, String contentType, String charset) {
        response.setContentType(contentType);
        response.setCharacterEncoding(charset);
        Response(response, resString);
    }

    // 默认响应
    // Content-Type: application/x-www-form-urlencoded;charset=utf-8
    public static void Response(HttpServletResponse response, String resString) {
        try {
            PrintWriter writer = response.getWriter();
            writer.print(resString);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
