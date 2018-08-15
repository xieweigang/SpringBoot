package com.ucmed.sxpt.dao;

import com.ucmed.sxpt.entity.PaymentOrder;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
import java.util.Map;

public interface PaymentOrderMapper {
    /**
     *
     * @mbg.generated
     */
    @Delete({
        "delete from t_payment_order",
        "where order_id = #{orderId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String orderId);

    /**
     *
     * @mbg.generated
     */
    @Insert({
        "insert into t_payment_order (order_id, order_type, ",
        "order_title, order_amount, ",
        "order_status, card_no, ",
        "card_type, goods_id, ",
        "goods_name, trade_type, ",
        "accept_url, notify_id, ",
        "serial_id, serial_status, ",
        "serial_packet, refund_amount, ",
        "create_time, update_time)",
        "values (#{orderId,jdbcType=VARCHAR}, #{orderType,jdbcType=VARCHAR}, ",
        "#{orderTitle,jdbcType=VARCHAR}, #{orderAmount,jdbcType=VARCHAR}, ",
        "#{orderStatus,jdbcType=VARCHAR}, #{cardNo,jdbcType=VARCHAR}, ",
        "#{cardType,jdbcType=VARCHAR}, #{goodsId,jdbcType=VARCHAR}, ",
        "#{goodsName,jdbcType=VARCHAR}, #{tradeType,jdbcType=VARCHAR}, ",
        "#{acceptUrl,jdbcType=VARCHAR}, #{notifyId,jdbcType=VARCHAR}, ",
        "#{serialId,jdbcType=VARCHAR}, #{serialStatus,jdbcType=VARCHAR}, ",
        "#{serialPacket,jdbcType=VARCHAR}, #{refundAmount,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP})"
    })
    int insert(PaymentOrder record);

    /**
     *
     * @mbg.generated
     */
    @InsertProvider(type=PaymentOrderSqlProvider.class, method="insertSelective")
    int insertSelective(PaymentOrder record);

    /**
     *
     * @mbg.generated
     */
    @Select({
        "select",
        "order_id, order_type, order_title, order_amount, order_status, card_no, card_type, ",
        "goods_id, goods_name, trade_type, accept_url, notify_id, serial_id, serial_status, ",
        "serial_packet, refund_amount, create_time, update_time",
        "from t_payment_order",
        "where order_id = #{orderId,jdbcType=VARCHAR}"
    })
    @Results({
        @Result(column="order_id", property="orderId", jdbcType=JdbcType.VARCHAR, id=true),
        @Result(column="order_type", property="orderType", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_title", property="orderTitle", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_amount", property="orderAmount", jdbcType=JdbcType.VARCHAR),
        @Result(column="order_status", property="orderStatus", jdbcType=JdbcType.VARCHAR),
        @Result(column="card_no", property="cardNo", jdbcType=JdbcType.VARCHAR),
        @Result(column="card_type", property="cardType", jdbcType=JdbcType.VARCHAR),
        @Result(column="goods_id", property="goodsId", jdbcType=JdbcType.VARCHAR),
        @Result(column="goods_name", property="goodsName", jdbcType=JdbcType.VARCHAR),
        @Result(column="trade_type", property="tradeType", jdbcType=JdbcType.VARCHAR),
        @Result(column="accept_url", property="acceptUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="notify_id", property="notifyId", jdbcType=JdbcType.VARCHAR),
        @Result(column="serial_id", property="serialId", jdbcType=JdbcType.VARCHAR),
        @Result(column="serial_status", property="serialStatus", jdbcType=JdbcType.VARCHAR),
        @Result(column="serial_packet", property="serialPacket", jdbcType=JdbcType.VARCHAR),
        @Result(column="refund_amount", property="refundAmount", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    PaymentOrder selectByPrimaryKey(String orderId);

    /**
     *
     * @mbg.generated
     */
    @UpdateProvider(type=PaymentOrderSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(PaymentOrder record);

    /**
     *
     * @mbg.generated
     */
    @Update({
        "update t_payment_order",
        "set order_type = #{orderType,jdbcType=VARCHAR},",
          "order_title = #{orderTitle,jdbcType=VARCHAR},",
          "order_amount = #{orderAmount,jdbcType=VARCHAR},",
          "order_status = #{orderStatus,jdbcType=VARCHAR},",
          "card_no = #{cardNo,jdbcType=VARCHAR},",
          "card_type = #{cardType,jdbcType=VARCHAR},",
          "goods_id = #{goodsId,jdbcType=VARCHAR},",
          "goods_name = #{goodsName,jdbcType=VARCHAR},",
          "trade_type = #{tradeType,jdbcType=VARCHAR},",
          "accept_url = #{acceptUrl,jdbcType=VARCHAR},",
          "notify_id = #{notifyId,jdbcType=VARCHAR},",
          "serial_id = #{serialId,jdbcType=VARCHAR},",
          "serial_status = #{serialStatus,jdbcType=VARCHAR},",
          "serial_packet = #{serialPacket,jdbcType=VARCHAR},",
          "refund_amount = #{refundAmount,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "update_time = #{updateTime,jdbcType=TIMESTAMP}",
        "where order_id = #{orderId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(PaymentOrder record);

    // 用户订单查询
    @Select({
            "select * from t_payment_order where card_no = #{cardNo} and card_type = #{cardType} "
    })
    @Results({
            @Result(column="order_id", property="orderId", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="order_type", property="orderType", jdbcType=JdbcType.VARCHAR),
            @Result(column="order_title", property="orderTitle", jdbcType=JdbcType.VARCHAR),
            @Result(column="order_amount", property="orderAmount", jdbcType=JdbcType.VARCHAR),
            @Result(column="order_status", property="orderStatus", jdbcType=JdbcType.VARCHAR),
            @Result(column="card_no", property="cardNo", jdbcType=JdbcType.VARCHAR),
            @Result(column="card_type", property="cardType", jdbcType=JdbcType.VARCHAR),
            @Result(column="goods_id", property="goodsId", jdbcType=JdbcType.VARCHAR),
            @Result(column="goods_name", property="goodsName", jdbcType=JdbcType.VARCHAR),
            @Result(column="trade_type", property="tradeType", jdbcType=JdbcType.VARCHAR),
            @Result(column="accept_url", property="acceptUrl", jdbcType=JdbcType.VARCHAR),
            @Result(column="notify_id", property="notifyId", jdbcType=JdbcType.VARCHAR),
            @Result(column="serial_id", property="serialId", jdbcType=JdbcType.VARCHAR),
            @Result(column="serial_status", property="serialStatus", jdbcType=JdbcType.VARCHAR),
            @Result(column="serial_packet", property="serialPacket", jdbcType=JdbcType.VARCHAR),
            @Result(column="refund_amount", property="refundAmount", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<PaymentOrder> selectByCard(Map<String, Object> hashMap);

    // 用户订单查询
    @Select({
            "select * from t_payment_order where goods_id = #{goodsId} LIMIT 1 "
    })
    @Results({
            @Result(column="order_id", property="orderId", jdbcType=JdbcType.VARCHAR, id=true),
            @Result(column="order_type", property="orderType", jdbcType=JdbcType.VARCHAR),
            @Result(column="order_title", property="orderTitle", jdbcType=JdbcType.VARCHAR),
            @Result(column="order_amount", property="orderAmount", jdbcType=JdbcType.VARCHAR),
            @Result(column="order_status", property="orderStatus", jdbcType=JdbcType.VARCHAR),
            @Result(column="card_no", property="cardNo", jdbcType=JdbcType.VARCHAR),
            @Result(column="card_type", property="cardType", jdbcType=JdbcType.VARCHAR),
            @Result(column="goods_id", property="goodsId", jdbcType=JdbcType.VARCHAR),
            @Result(column="goods_name", property="goodsName", jdbcType=JdbcType.VARCHAR),
            @Result(column="trade_type", property="tradeType", jdbcType=JdbcType.VARCHAR),
            @Result(column="accept_url", property="acceptUrl", jdbcType=JdbcType.VARCHAR),
            @Result(column="notify_id", property="notifyId", jdbcType=JdbcType.VARCHAR),
            @Result(column="serial_id", property="serialId", jdbcType=JdbcType.VARCHAR),
            @Result(column="serial_status", property="serialStatus", jdbcType=JdbcType.VARCHAR),
            @Result(column="serial_packet", property="serialPacket", jdbcType=JdbcType.VARCHAR),
            @Result(column="refund_amount", property="refundAmount", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.TIMESTAMP),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.TIMESTAMP)
    })
    PaymentOrder selectByGoodsId(String goodsId);
}