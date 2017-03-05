/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50537
Source Host           : localhost:3306
Source Database       : ttms

Target Server Type    : MYSQL
Target Server Version : 50537
File Encoding         : 65001

Date: 2016-10-05 21:17:33
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for data_dict
-- ----------------------------
DROP TABLE IF EXISTS `data_dict`;
CREATE TABLE `data_dict` (
  `dict_id` int(11) NOT NULL AUTO_INCREMENT,
  `dict_parent_id` int(11) DEFAULT NULL,
  `dict_index` int(11) DEFAULT NULL,
  `dict_name` varchar(200) DEFAULT NULL,
  `dict_value` varchar(100) NOT NULL,
  PRIMARY KEY (`dict_id`),
  KEY `FK_super_child_dict` (`dict_parent_id`),
  CONSTRAINT `FK_super_child_dict` FOREIGN KEY (`dict_parent_id`) REFERENCES `data_dict` (`dict_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of data_dict
-- ----------------------------
INSERT INTO `data_dict` VALUES ('1', null, '1', '数据字典', '根');
INSERT INTO `data_dict` VALUES ('2', '1', '2', 'PLAYTYPE', '影片类型');
INSERT INTO `data_dict` VALUES ('3', '1', '3', 'PLAYLANG', '影片语言');
INSERT INTO `data_dict` VALUES ('4', '1', '4', '锁失效时间', '5');
INSERT INTO `data_dict` VALUES ('5', '2', '1', 'Costume Drama', '古装剧');
INSERT INTO `data_dict` VALUES ('6', '2', '2', 'Anime Piece', '动漫片');
INSERT INTO `data_dict` VALUES ('7', '2', '3', 'Life Drama', '生活剧');
INSERT INTO `data_dict` VALUES ('8', '2', '4', 'Horror Movies', '恐怖片');
INSERT INTO `data_dict` VALUES ('9', '2', '5', 'War Movies', '战争片');
INSERT INTO `data_dict` VALUES ('10', '2', '6', 'Science Fiction', '科幻片');
INSERT INTO `data_dict` VALUES ('11', '3', '1', 'Chinese', '国语');
INSERT INTO `data_dict` VALUES ('12', '3', '2', 'English', '英语');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `emp_id` int(11) NOT NULL AUTO_INCREMENT,
  `emp_no` varchar(20) NOT NULL,
  `emp_name` varchar(100) NOT NULL,
  `emp_tel_num` varchar(20) DEFAULT NULL,
  `emp_addr` varchar(200) DEFAULT NULL,
  `emp_email` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`emp_id`),
  UNIQUE KEY `emp_no` (`emp_no`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('1', '001', '张三', '11111', '长安南路563号', '111@163.com');
INSERT INTO `employee` VALUES ('2', '002', '李四', '22222', '莲湖路22号', '222@21cn.com');
INSERT INTO `employee` VALUES ('3', '003', '王五', '33333', '东五路33号', '333@yeah.net');
INSERT INTO `employee` VALUES ('4', '004', '刘六', '44444', '西一路44号', '444@sina.com.cn');
INSERT INTO `employee` VALUES ('5', '005', '郑七', '55555', '南稍门55号', '555@tom.com');
INSERT INTO `employee` VALUES ('6', '006', '李念', '66666', '大溪地66号', '666@xiyou.edu.cn');
INSERT INTO `employee` VALUES ('7', '007', '凌霜华', '77777', '大溪地77号', '777@xiyou.edu.cn');
INSERT INTO `employee` VALUES ('8', '008', '景茵梦', '88888', '大溪地88号', '888@xiyou.edu.cn');
INSERT INTO `employee` VALUES ('9', '009', '任水寒', '99999', '大溪地99号', '999@xiyou.edu.cn');
INSERT INTO `employee` VALUES ('10', '010', '丁玲珑', '10101', '大溪地10号', '100@xiyou.edu.cn');
INSERT INTO `employee` VALUES ('11', '011', '柳兰歌', '11111', '大溪地11号', '111@xiyou.edu.cn');
INSERT INTO `employee` VALUES ('12', '012', '燕七', '12121', '大溪地12号', '121@xiyou.edu.cn');
INSERT INTO `employee` VALUES ('13', '013', '丁典', '13131', '大溪地13号', '131@xiyou.edu.cn');
INSERT INTO `employee` VALUES ('14', '014', '袁冠南', '14141', '大溪地14号', '141@xiyou.edu.cn');
INSERT INTO `employee` VALUES ('15', 'guest04', '老胡', '17812345678', '吉祥村004号', 'laohu@tom.com');
INSERT INTO `employee` VALUES ('16', 'guest05', '老钱', '15612345678', '西斜七路005号', 'laoqian@yahoo.com.cn');
INSERT INTO `employee` VALUES ('17', 'guest06', '老章', '18710905922', '西长安街121号', 'laozhang@sohu.com');
-- ----------------------------
-- Table structure for play
-- ----------------------------
DROP TABLE IF EXISTS `play`;
CREATE TABLE `play` (
  `play_id` int(11) NOT NULL AUTO_INCREMENT,
  `play_type_id` int(11) DEFAULT NULL,
  `play_lang_id` int(11) DEFAULT NULL,
  `play_name` varchar(200) DEFAULT NULL,
  `play_introduction` varchar(2000) DEFAULT NULL,
  `play_image` longblob,
  `play_length` int(11) DEFAULT NULL,
  `play_ticket_price` decimal(10,2) DEFAULT NULL,
  `play_status` smallint(6) DEFAULT NULL COMMENT '取值含义：\r\n            0：待安排演出\r\n            1：已安排演出\r\n            -1：下线',
  PRIMARY KEY (`play_id`),
  KEY `FK_dict_lan_play` (`play_lang_id`),
  KEY `FK_dict_type_play` (`play_type_id`),
  CONSTRAINT `FK_dict_lan_play` FOREIGN KEY (`play_lang_id`) REFERENCES `data_dict` (`dict_id`),
  CONSTRAINT `FK_dict_type_play` FOREIGN KEY (`play_type_id`) REFERENCES `data_dict` (`dict_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of play
-- ----------------------------

-- ----------------------------
-- Table structure for sale
-- ----------------------------
DROP TABLE IF EXISTS `sale`;
CREATE TABLE `sale` (
  `sale_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `emp_id` int(11) DEFAULT NULL,
  `sale_time` datetime DEFAULT NULL,
  `sale_payment` decimal(10,2) DEFAULT NULL,
  `sale_change` decimal(10,2) DEFAULT NULL,
  `sale_type` smallint(6) DEFAULT NULL COMMENT '类别取值含义：\r\n            1：销售单\r\n            -1：退款单',
  `sale_status` smallint(6) DEFAULT NULL COMMENT '销售单状态如下：\r\n            0：代付款\r\n            1：已付款',
  PRIMARY KEY (`sale_ID`),
  KEY `FK_employee_sale` (`emp_id`),
  CONSTRAINT `FK_employee_sale` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`emp_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sale
-- ----------------------------

-- ----------------------------
-- Table structure for sale_item
-- ----------------------------
DROP TABLE IF EXISTS `sale_item`;
CREATE TABLE `sale_item` (
  `sale_item_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ticket_id` bigint(20) DEFAULT NULL,
  `sale_ID` bigint(20) DEFAULT NULL,
  `sale_item_price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`sale_item_id`),
  KEY `FK_sale_sale_item` (`sale_ID`),
  KEY `FK_ticket_sale_item` (`ticket_id`),
  CONSTRAINT `FK_sale_sale_item` FOREIGN KEY (`sale_ID`) REFERENCES `sale` (`sale_ID`),
  CONSTRAINT `FK_ticket_sale_item` FOREIGN KEY (`ticket_id`) REFERENCES `ticket` (`ticket_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sale_item
-- ----------------------------

-- ----------------------------
-- Table structure for schedule
-- ----------------------------
DROP TABLE IF EXISTS `schedule`;
CREATE TABLE `schedule` (
  `sched_id` int(11) NOT NULL AUTO_INCREMENT,
  `studio_id` int(11) DEFAULT NULL,
  `play_id` int(11) DEFAULT NULL,
  `sched_time` datetime NOT NULL,
  `sched_ticket_price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`sched_id`),
  KEY `FK_play_sched` (`play_id`),
  KEY `FK_studio_sched` (`studio_id`),
  CONSTRAINT `FK_play_sched` FOREIGN KEY (`play_id`) REFERENCES `play` (`play_id`),
  CONSTRAINT `FK_studio_sched` FOREIGN KEY (`studio_id`) REFERENCES `studio` (`studio_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of schedule
-- ----------------------------

-- ----------------------------
-- Table structure for seat
-- ----------------------------
DROP TABLE IF EXISTS `seat`;
CREATE TABLE `seat` (
  `seat_id` int(11) NOT NULL AUTO_INCREMENT,
  `studio_id` int(11) DEFAULT NULL,
  `seat_row` int(11) DEFAULT NULL,
  `seat_column` int(11) DEFAULT NULL,
  `seat_status` smallint(6) DEFAULT NULL COMMENT '取值含义：\r\n                       0：此处是空位，没有安排座椅\r\n                       1：完好的座位，可以使用\r\n                       -1：座位损坏，不能安排座位',
  PRIMARY KEY (`seat_id`),
  KEY `FK_studio_seat` (`studio_id`),
  CONSTRAINT `FK_studio_seat` FOREIGN KEY (`studio_id`) REFERENCES `studio` (`studio_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seat
-- ----------------------------

-- ----------------------------
-- Table structure for studio
-- ----------------------------
DROP TABLE IF EXISTS `studio`;
CREATE TABLE `studio` (
  `studio_id` int(11) NOT NULL AUTO_INCREMENT,
  `studio_name` varchar(100) NOT NULL,
  `studio_row_count` int(11) DEFAULT NULL,
  `studio_col_count` int(11) DEFAULT NULL,
  `studio_introduction` varchar(2000) DEFAULT NULL,
  `studio_flag` smallint(6) DEFAULT NULL COMMENT '取值含义：\r\n                        0：尚未生成座位，可以根据行列信息生成座位\r\n                        1：已经根据影厅的座位信息安排了座位，不能再安排座位；\r\n                        -1：影厅损坏或者废弃，不能使用',
  PRIMARY KEY (`studio_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of studio
-- ----------------------------

-- ----------------------------
-- Table structure for ticket
-- ----------------------------
DROP TABLE IF EXISTS `ticket`;
CREATE TABLE `ticket` (
  `ticket_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `seat_id` int(11) DEFAULT NULL,
  `sched_id` int(11) DEFAULT NULL,
  `ticket_price` decimal(10,2) DEFAULT NULL,
  `ticket_status` smallint(6) DEFAULT NULL COMMENT '含义如下：\r\n            0：待销售\r\n            1：锁定\r\n            9：卖出',
  `ticket_locked_time` datetime DEFAULT NULL,
  PRIMARY KEY (`ticket_id`),
  KEY `FK_sched_ticket` (`sched_id`),
  KEY `FK_seat_ticket` (`seat_id`),
  CONSTRAINT `FK_sched_ticket` FOREIGN KEY (`sched_id`) REFERENCES `schedule` (`sched_id`),
  CONSTRAINT `FK_seat_ticket` FOREIGN KEY (`seat_id`) REFERENCES `seat` (`seat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ticket
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `emp_no` varchar(20) NOT NULL COMMENT '用户名',
  `emp_pass` varchar(20) NOT NULL COMMENT '密码',
  `type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '用户类型：0为普通用户，1是管理员',
  PRIMARY KEY (`emp_no`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`emp_no`) REFERENCES `employee` (`emp_no`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('001', '123456', '1');
INSERT INTO `user` VALUES ('002', '123456', '0');
INSERT INTO `user` VALUES ('003', '123456', '0');
INSERT INTO `user` VALUES ('004', '123456', '0');
INSERT INTO `user` VALUES ('005', '123456', '0');
INSERT INTO `user` VALUES ('006', '123456', '0');
