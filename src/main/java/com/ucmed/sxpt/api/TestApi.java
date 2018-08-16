package com.ucmed.sxpt.api;

import com.alibaba.fastjson.JSONObject;
import com.ucmed.sxpt.dao.PaymentOrderMapper;
import com.ucmed.sxpt.dao.PaymentRefundMapper;
import com.ucmed.sxpt.entity.PaymentOrder;
import com.ucmed.sxpt.entity.PaymentRefund;
import com.ucmed.sxpt.util.ApiResponse;
import com.ucmed.sxpt.util.GlobalConstants;
import com.ucmed.sxpt.util.HttpUtil;
import com.ucmed.sxpt.util.PaymentConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "测试接口")
@RestController
@RequestMapping("/api/test")
public class TestApi {
    public static final Logger LOG = Logger.getLogger(TestApi.class);
    @Autowired
    private PaymentOrderMapper paymentOrderMapper;

    @ResponseBody
    @RequestMapping(value = "/getOrderList")
    @ApiOperation(httpMethod = "POST", response = ApiResponse.class, value = "测试接口", notes = "返回指定用户的所有支付订单")
    public ResponseEntity<ApiResponse> getOrderList(
            @ApiParam(name = "cardNo", value = "卡号", required = true) @RequestParam(value = "cardNo") String cardNo,
            @ApiParam(name = "cardType", value = "卡类型", required = true) @RequestParam(value = "cardType") String cardType) {
        Map<String, Object> map = new HashMap<>();
        map.put("cardNo", cardNo);
        map.put("cardType", cardType);
        List<PaymentOrder> paymentOrderList = paymentOrderMapper.selectByCard(map);
        return ApiResponse.responseSuccess(paymentOrderList);
    }
}
