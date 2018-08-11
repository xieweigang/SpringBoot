package com.ucmed.sxpt.entity;

import java.util.Date;

public class PaymentRefund {
    /**
     * 退款订单号
     */
    private String refundId;

    /**
     * 订单号
     */
    private String orderId;

    /**
     * 退款类型，1异常退款2院方退款
     */
    private String refundType;

    /**
     * 退款金额，单位：分
     */
    private String refundAmount;

    /**
     * 退款状态，0未通知1通知成功2通知失败
     */
    private String refundStatus;

    /**
     * 交易类型，1支付宝2微信3银行
     */
    private String tradeType;

    /**
     * 流水号
     */
    private String serialId;

    /**
     * 流水状态
     */
    private String serialStatus;

    /**
     * 流水报文
     */
    private String serialPacket;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 退款订单号
     * @return refund_id 退款订单号
     */
    public String getRefundId() {
        return refundId;
    }

    /**
     * 退款订单号
     * @param refundId 退款订单号
     */
    public void setRefundId(String refundId) {
        this.refundId = refundId == null ? null : refundId.trim();
    }

    /**
     * 订单号
     * @return order_id 订单号
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 订单号
     * @param orderId 订单号
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    /**
     * 退款类型，1异常退款2院方退款
     * @return refund_type 退款类型，1异常退款2院方退款
     */
    public String getRefundType() {
        return refundType;
    }

    /**
     * 退款类型，1异常退款2院方退款
     * @param refundType 退款类型，1异常退款2院方退款
     */
    public void setRefundType(String refundType) {
        this.refundType = refundType == null ? null : refundType.trim();
    }

    /**
     * 退款金额，单位：分
     * @return refund_amount 退款金额，单位：分
     */
    public String getRefundAmount() {
        return refundAmount;
    }

    /**
     * 退款金额，单位：分
     * @param refundAmount 退款金额，单位：分
     */
    public void setRefundAmount(String refundAmount) {
        this.refundAmount = refundAmount == null ? null : refundAmount.trim();
    }

    /**
     * 退款状态，0未通知1通知成功2通知失败
     * @return refund_status 退款状态，0未通知1通知成功2通知失败
     */
    public String getRefundStatus() {
        return refundStatus;
    }

    /**
     * 退款状态，0未通知1通知成功2通知失败
     * @param refundStatus 退款状态，0未通知1通知成功2通知失败
     */
    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus == null ? null : refundStatus.trim();
    }

    /**
     * 交易类型，1支付宝2微信3银行
     * @return trade_type 交易类型，1支付宝2微信3银行
     */
    public String getTradeType() {
        return tradeType;
    }

    /**
     * 交易类型，1支付宝2微信3银行
     * @param tradeType 交易类型，1支付宝2微信3银行
     */
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType == null ? null : tradeType.trim();
    }

    /**
     * 流水号
     * @return serial_id 流水号
     */
    public String getSerialId() {
        return serialId;
    }

    /**
     * 流水号
     * @param serialId 流水号
     */
    public void setSerialId(String serialId) {
        this.serialId = serialId == null ? null : serialId.trim();
    }

    /**
     * 流水状态
     * @return serial_status 流水状态
     */
    public String getSerialStatus() {
        return serialStatus;
    }

    /**
     * 流水状态
     * @param serialStatus 流水状态
     */
    public void setSerialStatus(String serialStatus) {
        this.serialStatus = serialStatus == null ? null : serialStatus.trim();
    }

    /**
     * 流水报文
     * @return serial_packet 流水报文
     */
    public String getSerialPacket() {
        return serialPacket;
    }

    /**
     * 流水报文
     * @param serialPacket 流水报文
     */
    public void setSerialPacket(String serialPacket) {
        this.serialPacket = serialPacket == null ? null : serialPacket.trim();
    }

    /**
     * 创建时间
     * @return create_time 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 修改时间
     * @return update_time 修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 修改时间
     * @param updateTime 修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}