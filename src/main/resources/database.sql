DROP DATABASE `sxpt-fybjy`;
CREATE DATABASE `sxpt-fybjy` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `sxpt-fybjy`;
SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(16) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `phone` varchar(32) COMMENT '手机号码',
  `password` varchar(64) COMMENT '密码',
  `hosp_id` varchar(64) COMMENT '医院编号',
  `open_id` varchar(64) COMMENT '微信号',
  `create_time` datetime COMMENT '创建时间',
  `update_time` datetime COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000000 DEFAULT CHARSET=utf8;
insert  into `t_user`(`phone`,`password`,`hosp_id`,`open_id`,`create_time`,`update_time`)
values ('18305755230','111111','33060247132306811G1001','tester',NOW(),NOW());

DROP TABLE IF EXISTS `t_patient`;
CREATE TABLE `t_patient` (
  `id` int(16) NOT NULL AUTO_INCREMENT COMMENT '患者id',
  `user_id` int(16) COMMENT '用户id',
  `card_no` varchar(32) COMMENT '卡号',
  `card_type` varchar(8) COMMENT '卡类型，0医保卡2健康卡3省内外地社保卡',
  `patient_id` varchar(32) COMMENT '患者编号',
  `patient_name` varchar(32) COMMENT '患者姓名',
  `patient_phone` varchar(32) COMMENT '患者手机',
  `patient_idcard` varchar(32) COMMENT '身份证证号',
  `create_time` datetime COMMENT '创建时间',
  `update_time` datetime COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000000 DEFAULT CHARSET=utf8;
insert  into `t_patient`(`user_id`,`card_no`,`card_type`,`patient_id`,`patient_name`,`patient_phone`,`patient_idcard`,`create_time`,`update_time`)
values (1000001,'A00349765','0','F87326657','谢伟刚','18305755230','330621198903256371',NOW(),NOW());

DROP TABLE IF EXISTS `t_payment_order`;
CREATE TABLE `t_payment_order` (
  `order_id` varchar(64) COMMENT '订单号',
  `order_type` varchar(32) COMMENT '订单类型，0测试缴费1诊间支付2住院缴费3挂号费4卡充值5其他',
  `order_title` varchar(64) COMMENT '订单类型名称',
  `order_amount` varchar(32) COMMENT '订单金额，单位：分',
  `order_status` varchar(8) COMMENT '订单状态，0未支付1已支付2支付已通知3已退款4订单已失效',
  `card_no` varchar(32) COMMENT '卡号',
  `card_type` varchar(8) COMMENT '卡类型，0医保卡2健康卡3省内外地社保卡',
  `goods_id` varchar(128) COMMENT '商品单号，格式：1,2,3',
  `goods_name` varchar(512) COMMENT '商品名称，格式：处方,处置,检验',
  `trade_type` varchar(32) COMMENT '交易类型，1支付宝2微信3银行',
  `accept_url` varchar(128) COMMENT '结果处理地址',
  `notify_id` varchar(64) COMMENT '通知唯一id',
  `serial_id` varchar(64) COMMENT '流水号',
  `serial_status` varchar(32) COMMENT '流水状态',
  `serial_packet` varchar(2048) COMMENT '流水报文',
  `refund_amount` varchar(32) COMMENT '退款金额，单位：分',
  `create_time` datetime COMMENT '创建时间',
  `update_time` datetime COMMENT '修改时间',
  PRIMARY KEY (`order_id`),
  UNIQUE KEY (`goods_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_payment_refund`;
CREATE TABLE `t_payment_refund` (
  `refund_id` varchar(64) COMMENT '退款订单号',
  `order_id` varchar(64) COMMENT '订单号',
  `unique_id` varchar(64) COMMENT '唯一号',
  `refund_type` varchar(8) COMMENT '退款类型，1自动退款2院方退款3异常退款',
  `refund_amount` varchar(64) COMMENT '退款金额，单位：分',
  `refund_status` varchar(8) COMMENT '退款状态，0未退款1已退款',
  `trade_type` varchar(32) COMMENT '交易类型，1支付宝2微信3银行',
  `serial_id` varchar(64) COMMENT '流水号',
  `serial_status` varchar(32) COMMENT '流水状态',
  `serial_packet` varchar(2048) COMMENT '流水报文',
  `create_time` datetime COMMENT '创建时间',
  `update_time` datetime COMMENT '修改时间',
  PRIMARY KEY (`refund_id`),
  UNIQUE KEY (`unique_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;