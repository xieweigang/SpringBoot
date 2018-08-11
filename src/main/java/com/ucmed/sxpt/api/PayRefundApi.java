package com.ucmed.sxpt.api;

import com.alibaba.fastjson.JSONObject;
import com.ucmed.sxpt.dao.PaymentOrderMapper;
import com.ucmed.sxpt.dao.PaymentRefundMapper;
import com.ucmed.sxpt.entity.PaymentRefund;
import com.ucmed.sxpt.util.ApiResponse;
import com.ucmed.sxpt.entity.PaymentOrder;
import com.ucmed.sxpt.util.GlobalConstants;
import com.ucmed.sxpt.util.HttpUtil;
import com.ucmed.sxpt.util.PaymentConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

@Api(tags = "退款接口")
@RestController
@RequestMapping("/refund")
public class PayRefundApi {
    @Autowired
    private PaymentOrderMapper paymentOrderMapper;
    @Autowired
    private PaymentRefundMapper paymentRefundMapper;

    @ResponseBody
    @RequestMapping(value = "/api.htm")
    @ApiOperation(value = "退款接口", httpMethod = "POST", notes = "退款接口", response = ApiResponse.class)
    public ResponseEntity<ApiResponse> patientRefund(
            @ApiParam(name = "orderId", value = "订单编号", required = true) @RequestParam(value = "orderId") String orderId,
            @ApiParam(name = "refundType", value = "退款类型", required = true) @RequestParam(value = "refundType") String refundType,
            @ApiParam(name = "refundAmount", value = "退款金额（单位分）", required = true) @RequestParam(value = "refundAmount") String refundAmount,
            @ApiParam(name = "sign", value = "签名", required = true) @RequestParam(value = "sign") String sign) {
        PaymentOrder paymentOrder = paymentOrderMapper.selectByPrimaryKey(orderId);
        if (paymentOrder == null) {
            return ApiResponse.responseError("订单不存在");
        }
        // 1异常退款，验证订单是否异常
        if (refundType.equals("1")) {
            // 支付成功，并且通知成功，不允许用户自己退款
            if (paymentOrder.getSerialStatus().equals("TRADE_SUCCESS") && !paymentOrder.getOrderStatus().equals("1")) {
                return ApiResponse.responseError("该订单不是异常订单，不能退款");
            }
        }
        // 2院方退款，验证签名是否正确
        if (refundType.equals("2")) {
            // 签名验证
            Map<String, String> treeMap = new TreeMap<>();
            treeMap.put("orderId", orderId); // 订单号
            treeMap.put("refundType", refundType); // 退款类型，1异常退款2院方退款
            treeMap.put("refundAmount", refundAmount); // 退款金额，单位：分
            if (!PaymentConfig.getSign(treeMap).equals(sign)) {
                return ApiResponse.responseError("签名验证失败");
            }
        }
        // 生成退款订单
        String refundId = PaymentConfig.getRefundId();
        JSONObject payRefundJson = PaymentConfig.getPayRefundJson(orderId, refundId, refundAmount);
        // 发起退款
        String reqString = payRefundJson.toString();
        String resString = HttpUtil.getInstance().POST(PaymentConfig.PAY_REFUND_URL, reqString);
        // TODO 验证报文结果
        JSONObject res = JSONObject.parseObject(resString);
        // 记录退款订单
        PaymentRefund paymentRefund = new PaymentRefund();
        paymentRefund.setRefundId(refundId); // 退款订单号
        paymentRefund.setOrderId(orderId); // 订单号
        paymentRefund.setRefundType(refundType); // 退款类型，1异常退款2院方退款
        paymentRefund.setRefundStatus("0"); // 退款状态，0未通知1通知成功2通知失败
        paymentRefund.setRefundAmount(refundAmount); // 退款金额，单位：分
        paymentRefund.setTradeType(paymentOrder.getTradeType()); // 交易类型，1支付宝2微信3银行
        paymentRefund.setSerialId(res.getString("seqId")); // 流水号
        paymentRefund.setSerialStatus(res.getString("status")); // 流水状态
        paymentRefund.setSerialPacket(res.getString("")); // 流水报文
        paymentRefund.setCreateTime(new Date()); // 创建时间
        paymentRefund.setUpdateTime(new Date()); // 修改时间
        // TODO 通知医院
        return ApiResponse.responseSuccess("退款成功");
    }
}
