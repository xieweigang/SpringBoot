CREATE DATABASE `student_test` ;
USE `student_test`;

DROP TABLE IF EXISTS `klass`;
CREATE TABLE `klass` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '班级编号',
  `name` varchar(50) DEFAULT NULL COMMENT '班级名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;
insert  into `klass`(`id`,`name`) values (1,'一年一班');
insert  into `klass`(`id`,`name`) values (2,'一年二班');
insert  into `klass`(`id`,`name`) values (3,'一年三班');
insert  into `klass`(`id`,`name`) values (16,'四年五班');
insert  into `klass`(`id`,`name`) values (18,'二年一班1');
insert  into `klass`(`id`,`name`) values (19,'二年一班2');
insert  into `klass`(`id`,`name`) values (20,'二年一班3');
insert  into `klass`(`id`,`name`) values (21,'二年一班4');
insert  into `klass`(`id`,`name`) values (22,'二年一班5');
insert  into `klass`(`id`,`name`) values (23,'二年一班6');
insert  into `klass`(`id`,`name`) values (24,'二年一班7');
insert  into `klass`(`id`,`name`) values (25,'二年一班8');
insert  into `klass`(`id`,`name`) values (26,'二年一班9');
insert  into `klass`(`id`,`name`) values (27,'二年一班0');
insert  into `klass`(`id`,`name`) values (28,'二年一班1');
insert  into `klass`(`id`,`name`) values (29,'二年一班2');
insert  into `klass`(`id`,`name`) values (30,'二年一班3');
insert  into `klass`(`id`,`name`) values (31,'二年一班4');
insert  into `klass`(`id`,`name`) values (32,'二年一班5');
insert  into `klass`(`id`,`name`) values (33,'二年一班6');

DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '学生编号',
  `name` varchar(50) DEFAULT NULL COMMENT '学生姓名',
  `k_id` int(10) DEFAULT NULL COMMENT '班级编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
insert  into `student`(`id`,`name`,`k_id`) values (1,'jack',1);
insert  into `student`(`id`,`name`,`k_id`) values (2,'allen',1);
insert  into `student`(`id`,`name`,`k_id`) values (3,'marry',2);
insert  into `student`(`id`,`name`,`k_id`) values (4,'john',2);
insert  into `student`(`id`,`name`,`k_id`) values (5,'gumble',2);
insert  into `student`(`id`,`name`,`k_id`) values (6,'forest',2);
insert  into `student`(`id`,`name`,`k_id`) values (7,'tailer',3);
insert  into `student`(`id`,`name`,`k_id`) values (8,'smith',3);
insert  into `student`(`id`,`name`,`k_id`) values (9,'hebe',1);
insert  into `student`(`id`,`name`,`k_id`) values (10,'julia',3);
insert  into `student`(`id`,`name`,`k_id`) values (11,'mason',2);
insert  into `student`(`id`,`name`,`k_id`) values (12,'joe',2);
insert  into `student`(`id`,`name`,`k_id`) values (14,'angel',2);
insert  into `student`(`id`,`name`,`k_id`) values (15,'turky',2);
insert  into `student`(`id`,`name`,`k_id`) values (16,'eve',2);