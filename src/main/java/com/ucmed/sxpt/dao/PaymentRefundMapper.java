package com.ucmed.sxpt.dao;

import com.ucmed.sxpt.entity.PaymentRefund;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface PaymentRefundMapper {
    /**
     *
     * @mbg.generated 2018-08-11
     */
    @Delete({
        "delete from payment_refund",
        "where refund_id = #{refundId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String refundId);

    /**
     *
     * @mbg.generated 2018-08-11
     */
    @Insert({
        "insert into payment_refund (refund_id, order_id, ",
        "refund_type, refund_amount, ",
        "refund_status, trade_type, ",
        "serial_id, serial_status, ",
        "serial_packet, create_time, ",
        "update_time)",
        "values (#{refundId,jdbcType=VARCHAR}, #{orderId,jdbcType=VARCHAR}, ",
        "#{refundType,jdbcType=VARCHAR}, #{refundAmount,jdbcType=VARCHAR}, ",
        "#{refundStatus,jdbcType=VARCHAR}, #{tradeType,jdbcType=VARCHAR}, ",
        "#{serialId,jdbcType=VARCHAR}, #{serialStatus,jdbcType=VARCHAR}, ",
        "#{serialPacket,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{updateTime,jdbcType=TIMESTAMP})"
    })
    int insert(PaymentRefund record);

    /**
     *
     * @mbg.generated 2018-08-11
     */
    @InsertProvider(type=PaymentRefundSqlProvider.class, method="insertSelective")
    int insertSelective(PaymentRefund record);

    /**
     *
     * @mbg.generated 2018-08-11
     */
    @Select({
        "select",
        "refund_id, order_id, refund_type, refund_amount, refund_status, trade_type, ",
        "serial_id, serial_status, serial_packet, create_time, update_time",
        "from payment_refund",
        "where refund_id = #{refundId,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="refund_id", property="refundId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="order_id", property="orderId", jdbcType=JdbcType.VARCHAR),
        @Result(column="refund_type", property="refundType", jdbcType=JdbcType.VARCHAR),
        @Result(column="refund_amount", property="refundAmount", jdbcType=JdbcType.VARCHAR),
        @Result(column="refund_status", property="refundStatus", jdbcType=JdbcType.VARCHAR),
        @Result(column="trade_type", property="tradeType", jdbcType=JdbcType.VARCHAR),
        @Result(column="serial_id", property="serialId", jdbcType=JdbcType.VARCHAR),
        @Result(column="serial_status", property="serialStatus", jdbcType=JdbcType.VARCHAR),
        @Result(column="serial_packet", property="serialPacket", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    PaymentRefund selectByPrimaryKey(String refundId);

    /**
     *
     * @mbg.generated 2018-08-11
     */
    @UpdateProvider(type=PaymentRefundSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(PaymentRefund record);

    /**
     *
     * @mbg.generated 2018-08-11
     */
    @Update({
        "update payment_refund",
        "set order_id = #{orderId,jdbcType=VARCHAR},",
          "refund_type = #{refundType,jdbcType=VARCHAR},",
          "refund_amount = #{refundAmount,jdbcType=VARCHAR},",
          "refund_status = #{refundStatus,jdbcType=VARCHAR},",
          "trade_type = #{tradeType,jdbcType=VARCHAR},",
          "serial_id = #{serialId,jdbcType=VARCHAR},",
          "serial_status = #{serialStatus,jdbcType=VARCHAR},",
          "serial_packet = #{serialPacket,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where refund_id = #{refundId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(PaymentRefund record);
}