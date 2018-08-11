package com.ucmed.sxpt.dao;

import com.ucmed.sxpt.entity.PaymentOrder;
import org.apache.ibatis.jdbc.SQL;

public class PaymentOrderSqlProvider {

    /**
     *
     * @mbg.generated 2018-08-11
     */
    public String insertSelective(PaymentOrder record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("payment_order");
        
        if (record.getOrderId() != null) {
            sql.VALUES("order_id", "#{orderId,jdbcType=VARCHAR}");
        }
        
        if (record.getOrderType() != null) {
            sql.VALUES("order_type", "#{orderType,jdbcType=VARCHAR}");
        }
        
        if (record.getOrderTitle() != null) {
            sql.VALUES("order_title", "#{orderTitle,jdbcType=VARCHAR}");
        }
        
        if (record.getOrderAmount() != null) {
            sql.VALUES("order_amount", "#{orderAmount,jdbcType=VARCHAR}");
        }
        
        if (record.getOrderStatus() != null) {
            sql.VALUES("order_status", "#{orderStatus,jdbcType=VARCHAR}");
        }
        
        if (record.getCardNo() != null) {
            sql.VALUES("card_no", "#{cardNo,jdbcType=VARCHAR}");
        }
        
        if (record.getCardType() != null) {
            sql.VALUES("card_type", "#{cardType,jdbcType=VARCHAR}");
        }
        
        if (record.getGoodsId() != null) {
            sql.VALUES("goods_id", "#{goodsId,jdbcType=VARCHAR}");
        }
        
        if (record.getGoodsName() != null) {
            sql.VALUES("goods_name", "#{goodsName,jdbcType=VARCHAR}");
        }
        
        if (record.getTradeType() != null) {
            sql.VALUES("trade_type", "#{tradeType,jdbcType=VARCHAR}");
        }
        
        if (record.getAcceptUrl() != null) {
            sql.VALUES("accept_url", "#{acceptUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getNotifyId() != null) {
            sql.VALUES("notify_id", "#{notifyId,jdbcType=VARCHAR}");
        }
        
        if (record.getSerialId() != null) {
            sql.VALUES("serial_id", "#{serialId,jdbcType=VARCHAR}");
        }
        
        if (record.getSerialStatus() != null) {
            sql.VALUES("serial_status", "#{serialStatus,jdbcType=VARCHAR}");
        }
        
        if (record.getSerialPacket() != null) {
            sql.VALUES("serial_packet", "#{serialPacket,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    /**
     *
     * @mbg.generated 2018-08-11
     */
    public String updateByPrimaryKeySelective(PaymentOrder record) {
        SQL sql = new SQL();
        sql.UPDATE("payment_order");
        
        if (record.getOrderType() != null) {
            sql.SET("order_type = #{orderType,jdbcType=VARCHAR}");
        }
        
        if (record.getOrderTitle() != null) {
            sql.SET("order_title = #{orderTitle,jdbcType=VARCHAR}");
        }
        
        if (record.getOrderAmount() != null) {
            sql.SET("order_amount = #{orderAmount,jdbcType=VARCHAR}");
        }
        
        if (record.getOrderStatus() != null) {
            sql.SET("order_status = #{orderStatus,jdbcType=VARCHAR}");
        }
        
        if (record.getCardNo() != null) {
            sql.SET("card_no = #{cardNo,jdbcType=VARCHAR}");
        }
        
        if (record.getCardType() != null) {
            sql.SET("card_type = #{cardType,jdbcType=VARCHAR}");
        }
        
        if (record.getGoodsId() != null) {
            sql.SET("goods_id = #{goodsId,jdbcType=VARCHAR}");
        }
        
        if (record.getGoodsName() != null) {
            sql.SET("goods_name = #{goodsName,jdbcType=VARCHAR}");
        }
        
        if (record.getTradeType() != null) {
            sql.SET("trade_type = #{tradeType,jdbcType=VARCHAR}");
        }
        
        if (record.getAcceptUrl() != null) {
            sql.SET("accept_url = #{acceptUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getNotifyId() != null) {
            sql.SET("notify_id = #{notifyId,jdbcType=VARCHAR}");
        }
        
        if (record.getSerialId() != null) {
            sql.SET("serial_id = #{serialId,jdbcType=VARCHAR}");
        }
        
        if (record.getSerialStatus() != null) {
            sql.SET("serial_status = #{serialStatus,jdbcType=VARCHAR}");
        }
        
        if (record.getSerialPacket() != null) {
            sql.SET("serial_packet = #{serialPacket,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("order_id = #{orderId,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}