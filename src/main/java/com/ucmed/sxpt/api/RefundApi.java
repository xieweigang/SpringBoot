package com.ucmed.sxpt.api;

import com.alibaba.fastjson.JSONObject;
import com.ucmed.sxpt.dao.PaymentOrderMapper;
import com.ucmed.sxpt.dao.PaymentRefundMapper;
import com.ucmed.sxpt.entity.PaymentOrder;
import com.ucmed.sxpt.entity.PaymentRefund;
import com.ucmed.sxpt.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

@Api(tags = "退费相关接口")
@RestController
@RequestMapping("/api/refund")
public class RefundApi {
    public static final Logger LOG = Logger.getLogger(RefundApi.class);
    @Autowired
    private PaymentOrderMapper paymentOrderMapper;
    @Autowired
    private PaymentRefundMapper paymentRefundMapper;

    @ResponseBody
    @RequestMapping(value = "/payRefund")
    @ApiOperation(httpMethod = "POST", response = ApiResponse.class, value = "退款接口", notes = "返回退款订单")
    public ResponseEntity<ApiResponse> payRefund(@RequestBody JSONObject request) {
        Map<String, String> treeMap = PaymentConfig.getPocket(null, request);
        PaymentOrder paymentOrder = paymentOrderMapper.selectByPrimaryKey(treeMap.get("orderId"));
        if (paymentOrder == null) {
            LOG.info("订单不存在！");
            return ApiResponse.responseError("订单不存在！");
        }
        // 检查是否可以退款
        boolean mCheck = treeMap.get("refundType").equals("3") && paymentOrder.getOrderStatus().equals("1") || PaymentConfig.checkPocket(treeMap);
        // 支付成功尚未通知成功的订单才能自主退款
        if (!mCheck) {
            LOG.info("签名验证失败！");
            return ApiResponse.responseError("签名验证失败！");
        }
        String uniqueId = treeMap.get("uniqueId");
        String orderId = treeMap.get("orderId");
        String refundId = PaymentConfig.getRefundId();
        String refundAmount = treeMap.get("refundAmount");
        PaymentRefund paymentRefund = paymentRefundMapper.selectByPrimaryKey(uniqueId);
        if (paymentRefund != null && paymentRefund.getRefundStatus().equals("1")) {
            LOG.info("重复退款请求，已发原文！");
            return ApiResponse.responseSuccess(paymentRefund);
        }
        // 退款订单不存在存在
        if (paymentRefund == null) {
            // 记录退款订单
            paymentRefund = new PaymentRefund();
            paymentRefund.setRefundId(refundId);
            paymentRefund.setOrderId(orderId);
            paymentRefund.setRefundType(treeMap.get("refundType"));
            paymentRefund.setRefundStatus("0");
            paymentRefund.setRefundAmount(refundAmount);
            paymentRefund.setTradeType(paymentOrder.getTradeType());
            paymentRefund.setCreateTime(new Date());
            paymentRefund.setUpdateTime(new Date());
            paymentRefundMapper.insert(paymentRefund);
        }
        // 得到退款报文
        treeMap = PaymentConfig.getPayRefundPocket(orderId, refundId, refundAmount);
        JSONObject payRefundJson = PaymentConfig.getPayRefundJson(treeMap);
        String resString = HttpUtil.getInstance().POST(PaymentConfig.PAY_REFUND_URL, payRefundJson.toString());
        JSONObject res = JSONObject.parseObject(resString);
        // 验证签名
        treeMap = PaymentConfig.getPocket(null, res);
        if (!PaymentConfig.checkPocket(treeMap)) {
            LOG.info("退款异常！");
            return ApiResponse.responseError("退款异常！");
        }
        // TODO 校验退款成功的状态
        // 更新退款单
        if (res.getString("status").equals("TRADE_REFUND")) {
            paymentRefund.setRefundStatus("1");
            // 更新订单
            paymentOrder.setOrderStatus("3");
            paymentOrder.setRefundAmount(res.getString("refundAmount"));
            paymentOrder.setUpdateTime(new Date());
            paymentOrderMapper.updateByPrimaryKey(paymentOrder);
        }
        paymentRefund.setSerialId(res.getString("seqId"));
        paymentRefund.setSerialStatus(res.getString("status"));
        paymentRefund.setSerialPacket(GlobalConstants.MapToJson(treeMap).toString());
        paymentRefund.setUpdateTime(new Date());
        paymentRefundMapper.updateByPrimaryKey(paymentRefund);
        return ApiResponse.responseSuccess(paymentRefund);
    }
}
