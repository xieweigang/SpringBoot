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

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Api(tags = "支付相关接口")
@RestController
@RequestMapping("/api-payment")
public class PaymentApi {
    public static final Logger LOG = Logger.getLogger(PaymentApi.class);
    @Autowired
    private PaymentOrderMapper paymentOrderMapper;
    @Autowired
    private PaymentRefundMapper paymentRefundMapper;

    @ResponseBody
    @RequestMapping(value = "/refund")
    @ApiOperation(httpMethod = "POST", response = ApiResponse.class, value = "退款接口", notes = "根据订单号退款")
    public ResponseEntity<ApiResponse> refund(@ApiParam(name = "requestJson", value = "退款报文", required = true) @RequestBody JSONObject req) {
        LOG.info("调用退款接口");
        LOG.info(req.toString());
        PaymentOrder paymentOrder = paymentOrderMapper.selectByPrimaryKey(req.getString("orderId"));
        if (paymentOrder == null) {
            LOG.info("订单不存在！");
            return ApiResponse.responseError("订单不存在！");
        }
        // 是否需要验证签名（支付成功，但尚未通知成功的订单免验证）
        if (!req.getString("refundType").equals("3") || !paymentOrder.getOrderStatus().equals("1")) {
            // 签名验证
            String sign1 = req.getString("sign");
            req.remove("sign");
            String sign2 = PaymentConfig.getSign(req);
            if (!sign1.equalsIgnoreCase(sign2)) {
                LOG.info("签名验证失败！");
                return ApiResponse.responseError("签名验证失败！");
            }
        }
        PaymentRefund paymentRefund = paymentRefundMapper.selectByUniqueId(req.getString("uniqueId"));
        // 重复退款并且已经成功退款，返回成功的结果
        if (paymentRefund != null && paymentRefund.getRefundStatus().equals("1")) {
            return ApiResponse.responseSuccess(paymentRefund);
        }
        // 退款订单不存在，新建退款单
        if (paymentRefund == null) {
            // 记录退款订单
            paymentRefund = new PaymentRefund();
            paymentRefund.setRefundId(PaymentConfig.getRefundId());
            paymentRefund.setUniqueId(req.getString("uniqueId"));
            paymentRefund.setOrderId(req.getString("orderId"));
            paymentRefund.setRefundType(req.getString("refundType"));
            paymentRefund.setRefundAmount(req.getString("refundAmount"));
            paymentRefund.setRefundStatus("0");
            paymentRefund.setTradeType(paymentOrder.getTradeType());
            paymentRefund.setCreateTime(new Date());
            paymentRefund.setUpdateTime(new Date());
            paymentRefundMapper.insert(paymentRefund);
        }
        // 得到退款报文
        JSONObject refundRequest = PaymentConfig.getRefundRequest(paymentRefund.getOrderId(), paymentRefund.getRefundId(), paymentRefund.getRefundAmount());
        // 请求退款
        String resString = HttpUtil.getInstance().POST(PaymentConfig.PAY_REFUND_URL, refundRequest.toString());
        JSONObject res = JSONObject.parseObject(resString);
        // 退款交易失败
        if (!res.getString("status").equals("TRADE_SUCCESS")) {
            return ApiResponse.responseError(res.getString("errMsg"), paymentRefund);
        }
        // 更新订单
        paymentOrder.setOrderStatus("3");
        double refundAmount = GlobalConstants.DoubleValueOf(paymentOrder.getRefundAmount());
        refundAmount += GlobalConstants.DoubleValueOf(res.getString("totalAmount"));
        paymentOrder.setRefundAmount(GlobalConstants.DF0.format(refundAmount));
        paymentOrder.setUpdateTime(new Date());
        paymentOrderMapper.updateByPrimaryKey(paymentOrder);
        // 更新退款单
        paymentRefund.setRefundStatus("1");
        paymentRefund.setSerialId(res.getString("seqId"));
        paymentRefund.setSerialStatus(res.getString("status"));
        paymentRefund.setSerialPacket(resString);
        paymentRefund.setUpdateTime(new Date());
        paymentRefundMapper.updateByPrimaryKey(paymentRefund);
        return ApiResponse.responseSuccess(paymentRefund);
    }
}
