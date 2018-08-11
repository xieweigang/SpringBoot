package com.ucmed.sxpt.entity;

import java.util.Date;

public class PaymentOrder {
    /**
     * 订单号
     */
    private String orderId;

    /**
     * 订单类型，1诊间支付2住院缴费3测试缴费
     */
    private String orderType;

    /**
     * 订单类型名称
     */
    private String orderTitle;

    /**
     * 订单金额，单位：分
     */
    private String orderAmount;

    /**
     * 订单状态，0未通知1通知成功2通知失败
     */
    private String orderStatus;

    /**
     * 卡号
     */
    private String cardNo;

    /**
     * 卡类型，0医保卡2健康卡3省内外地社保卡
     */
    private String cardType;

    /**
     * 商品单号，格式：1,2,3
     */
    private String goodsId;

    /**
     * 商品名称，格式：处方,处置,检验
     */
    private String goodsName;

    /**
     * 交易类型，1支付宝2微信3银行
     */
    private String tradeType;

    /**
     * 结果处理地址
     */
    private String acceptUrl;

    /**
     * 通知唯一id
     */
    private String notifyId;

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
     * 订单类型，1诊间支付2住院缴费3测试缴费
     * @return order_type 订单类型，1诊间支付2住院缴费3测试缴费
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * 订单类型，1诊间支付2住院缴费3测试缴费
     * @param orderType 订单类型，1诊间支付2住院缴费3测试缴费
     */
    public void setOrderType(String orderType) {
        this.orderType = orderType == null ? null : orderType.trim();
    }

    /**
     * 订单类型名称
     * @return order_title 订单类型名称
     */
    public String getOrderTitle() {
        return orderTitle;
    }

    /**
     * 订单类型名称
     * @param orderTitle 订单类型名称
     */
    public void setOrderTitle(String orderTitle) {
        this.orderTitle = orderTitle == null ? null : orderTitle.trim();
    }

    /**
     * 订单金额，单位：分
     * @return order_amount 订单金额，单位：分
     */
    public String getOrderAmount() {
        return orderAmount;
    }

    /**
     * 订单金额，单位：分
     * @param orderAmount 订单金额，单位：分
     */
    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount == null ? null : orderAmount.trim();
    }

    /**
     * 订单状态，0未通知1通知成功2通知失败
     * @return order_status 订单状态，0未通知1通知成功2通知失败
     */
    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * 订单状态，0未通知1通知成功2通知失败
     * @param orderStatus 订单状态，0未通知1通知成功2通知失败
     */
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus == null ? null : orderStatus.trim();
    }

    /**
     * 卡号
     * @return card_no 卡号
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * 卡号
     * @param cardNo 卡号
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    /**
     * 卡类型，0医保卡2健康卡3省内外地社保卡
     * @return card_type 卡类型，0医保卡2健康卡3省内外地社保卡
     */
    public String getCardType() {
        return cardType;
    }

    /**
     * 卡类型，0医保卡2健康卡3省内外地社保卡
     * @param cardType 卡类型，0医保卡2健康卡3省内外地社保卡
     */
    public void setCardType(String cardType) {
        this.cardType = cardType == null ? null : cardType.trim();
    }

    /**
     * 商品单号，格式：1,2,3
     * @return goods_id 商品单号，格式：1,2,3
     */
    public String getGoodsId() {
        return goodsId;
    }

    /**
     * 商品单号，格式：1,2,3
     * @param goodsId 商品单号，格式：1,2,3
     */
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
    }

    /**
     * 商品名称，格式：处方,处置,检验
     * @return goods_name 商品名称，格式：处方,处置,检验
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 商品名称，格式：处方,处置,检验
     * @param goodsName 商品名称，格式：处方,处置,检验
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName == null ? null : goodsName.trim();
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
     * 结果处理地址
     * @return accept_url 结果处理地址
     */
    public String getAcceptUrl() {
        return acceptUrl;
    }

    /**
     * 结果处理地址
     * @param acceptUrl 结果处理地址
     */
    public void setAcceptUrl(String acceptUrl) {
        this.acceptUrl = acceptUrl == null ? null : acceptUrl.trim();
    }

    /**
     * 通知唯一id
     * @return notify_id 通知唯一id
     */
    public String getNotifyId() {
        return notifyId;
    }

    /**
     * 通知唯一id
     * @param notifyId 通知唯一id
     */
    public void setNotifyId(String notifyId) {
        this.notifyId = notifyId == null ? null : notifyId.trim();
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