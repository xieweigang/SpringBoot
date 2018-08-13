package com.ucmed.sxpt.dao;

import com.ucmed.sxpt.entity.PaymentRefund;
import org.apache.ibatis.jdbc.SQL;

public class PaymentRefundSqlProvider {

    /**
     *
     * @mbg.generated
     */
    public String insertSelective(PaymentRefund record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("t_payment_refund");
        
        if (record.getRefundId() != null) {
            sql.VALUES("refund_id", "#{refundId,jdbcType=VARCHAR}");
        }
        
        if (record.getOrderId() != null) {
            sql.VALUES("order_id", "#{orderId,jdbcType=VARCHAR}");
        }
        
        if (record.getRefundType() != null) {
            sql.VALUES("refund_type", "#{refundType,jdbcType=VARCHAR}");
        }
        
        if (record.getRefundAmount() != null) {
            sql.VALUES("refund_amount", "#{refundAmount,jdbcType=VARCHAR}");
        }
        
        if (record.getRefundStatus() != null) {
            sql.VALUES("refund_status", "#{refundStatus,jdbcType=VARCHAR}");
        }
        
        if (record.getTradeType() != null) {
            sql.VALUES("trade_type", "#{tradeType,jdbcType=VARCHAR}");
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
     * @mbg.generated
     */
    public String updateByPrimaryKeySelective(PaymentRefund record) {
        SQL sql = new SQL();
        sql.UPDATE("t_payment_refund");
        
        if (record.getOrderId() != null) {
            sql.SET("order_id = #{orderId,jdbcType=VARCHAR}");
        }
        
        if (record.getRefundType() != null) {
            sql.SET("refund_type = #{refundType,jdbcType=VARCHAR}");
        }
        
        if (record.getRefundAmount() != null) {
            sql.SET("refund_amount = #{refundAmount,jdbcType=VARCHAR}");
        }
        
        if (record.getRefundStatus() != null) {
            sql.SET("refund_status = #{refundStatus,jdbcType=VARCHAR}");
        }
        
        if (record.getTradeType() != null) {
            sql.SET("trade_type = #{tradeType,jdbcType=VARCHAR}");
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
        
        sql.WHERE("refund_id = #{refundId,jdbcType=VARCHAR}");
        
        return sql.toString();
    }
}