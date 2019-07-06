/*
Navicat MySQL Data Transfer

Source Server         : localhostMysql
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : quartzdb

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2019-07-06 21:25:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for birthday
-- ----------------------------
DROP TABLE IF EXISTS `birthday`;
CREATE TABLE `birthday` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `birth_type` varchar(255) DEFAULT NULL COMMENT '1阳历 2农历',
  `birth_dt` timestamp NULL DEFAULT NULL,
  `create_dt` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `birth_counts` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of birthday
-- ----------------------------
INSERT INTO `birthday` VALUES ('1', '张盼', '2', '1992-02-19 12:12:12', '2019-07-06 21:12:08', '0');
INSERT INTO `birthday` VALUES ('2', '肖珍珍', '2', '1990-01-11 11:11:10', '2019-07-06 21:12:43', '0');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `ID` varchar(20) NOT NULL,
  `NAME` varchar(50) NOT NULL,
  `DESCRIPTION` varchar(5000) NOT NULL,
  `DATE` datetime NOT NULL DEFAULT '1000-01-01 00:00:00',
  `USER_ID` varchar(20) NOT NULL,
  `CHANNEL_COUNT` int(11) NOT NULL DEFAULT '0',
  `PRODUCT_KEY` varchar(50) DEFAULT NULL,
  `PRODUCT_PLATFORM` varchar(20) NOT NULL,
  `CATEGORY` varchar(20) NOT NULL,
  `ACTIVE` int(11) NOT NULL DEFAULT '1',
  `PACKAGE_NAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', 'hello', 'test for user', '2017-09-06 20:29:10', '1', '4', '121212345', 'app', '1212312', '1', 'userpack');

-- ----------------------------
-- Table structure for sys_event_defination
-- ----------------------------
DROP TABLE IF EXISTS `sys_event_defination`;
CREATE TABLE `sys_event_defination` (
  `EVENT_ID` varchar(20) NOT NULL,
  `EVENT_IDENTIFIER` varchar(50) NOT NULL,
  `PRODUCTKEY` varchar(50) NOT NULL DEFAULT '',
  `EVENT_NAME` varchar(50) NOT NULL,
  `CHANNEL_ID` varchar(20) NOT NULL,
  `PRODUCT_ID` varchar(20) NOT NULL,
  `USER_ID` varchar(20) NOT NULL,
  `CREATE_DATE` datetime NOT NULL DEFAULT '1000-01-01 00:00:00',
  `ACTIVE` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`EVENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_event_defination
-- ----------------------------

-- ----------------------------
-- Table structure for sys_event_track
-- ----------------------------
DROP TABLE IF EXISTS `sys_event_track`;
CREATE TABLE `sys_event_track` (
  `ER_ID` varchar(40) NOT NULL,
  `EVENT_ID` varchar(40) DEFAULT NULL,
  `EVENT_PATH` varchar(512) DEFAULT NULL,
  `ACTIVITY` varchar(512) DEFAULT NULL,
  `VERSION` varchar(20) DEFAULT NULL,
  `APPKEY` varchar(40) DEFAULT NULL,
  `PRODUCT_ID` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_event_track
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `menu_id` int(11) NOT NULL,
  `menu_name` varchar(255) DEFAULT NULL,
  `menu_url` varchar(255) DEFAULT NULL,
  `is_active` varchar(255) DEFAULT NULL,
  `is_delete` varchar(255) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '应用管理', null, '1', '0', '0');
INSERT INTO `sys_menu` VALUES ('2', '图表统计', null, '1', '0', '0');
INSERT INTO `sys_menu` VALUES ('3', '系统管理', null, '1', '0', '0');
INSERT INTO `sys_menu` VALUES ('4', '个人中心', null, '1', '0', '0');
INSERT INTO `sys_menu` VALUES ('5', '关于系统', null, '1', '0', '0');
INSERT INTO `sys_menu` VALUES ('10001', '我的应用', null, '1', '0', '1');
INSERT INTO `sys_menu` VALUES ('10002', '用户管理', null, '1', '0', '3');
INSERT INTO `sys_menu` VALUES ('10003', '菜单管理', null, '1', '0', '3');
INSERT INTO `sys_menu` VALUES ('10004', '角色管理', null, '1', '0', '3');
INSERT INTO `sys_menu` VALUES ('10005', '个人中心', null, '1', '0', '4');
INSERT INTO `sys_menu` VALUES ('10006', '修改密码', null, '1', '0', '4');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  `is_active` varchar(255) DEFAULT NULL,
  `is_delete` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '系统管理员', '1', '0');
INSERT INTO `sys_role` VALUES ('2', '人事管理员', '1', '0');
INSERT INTO `sys_role` VALUES ('3', '经理', '1', '0');
INSERT INTO `sys_role` VALUES ('4', '员工', '1', '0');

-- ----------------------------
-- Table structure for sys_session
-- ----------------------------
DROP TABLE IF EXISTS `sys_session`;
CREATE TABLE `sys_session` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `session_id` varchar(255) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `ip_address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_session
-- ----------------------------
INSERT INTO `sys_session` VALUES ('25', '3F63739B57ADC2D3D0E80FEA37E82370', '1', '张三', 'http://192.168.0.106/user/login.do');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` int(11) NOT NULL,
  `login_name` varchar(40) DEFAULT NULL,
  `login_pwd` varchar(100) DEFAULT NULL,
  `name` varchar(40) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `is_active` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '123456', '张三', '2', '2');
INSERT INTO `sys_user` VALUES ('2', 'zhangsan', '111111', 'yarcl', '1', '1');

-- ----------------------------
-- Table structure for sys_user2product
-- ----------------------------
DROP TABLE IF EXISTS `sys_user2product`;
CREATE TABLE `sys_user2product` (
  `ID` varchar(20) NOT NULL,
  `USER_ID` varchar(20) NOT NULL,
  `PRODUCT_ID` varchar(20) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user2product
-- ----------------------------
SET FOREIGN_KEY_CHECKS=1;
