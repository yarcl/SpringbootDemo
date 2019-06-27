/*
Navicat MySQL Data Transfer
Source Server         : centos
Source Server Version : 50557
Source Host           : 192.168.0.100:3306
Source Database       : test
Target Server Type    : MYSQL
Target Server Version : 50557
File Encoding         : 65001
Date: 2017-10-23 22:00:00
*/
CREATE TABLE `RAZOR_EVENT_DEFINATION` (
  `EVENT_ID` varchar(20) CHARACTER SET utf8 NOT NULL,
  `EVENT_IDENTIFIER` varchar(50) CHARACTER SET utf8 NOT NULL,
  `PRODUCTKEY` varchar(50) CHARACTER SET utf8 NOT NULL DEFAULT '',
  `EVENT_NAME` varchar(50) CHARACTER SET utf8 NOT NULL,
  `CHANNEL_ID` varchar(20) CHARACTER SET utf8 NOT NULL,
  `PRODUCT_ID` varchar(20) CHARACTER SET utf8 NOT NULL,
  `USER_ID` varchar(20) CHARACTER SET utf8 NOT NULL,
  `CREATE_DATE` datetime NOT NULL DEFAULT '1000-01-01 00:00:00',
  `ACTIVE` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`EVENT_ID`)
);

CREATE TABLE `RAZOR_EVENT_TRACK` (
  `ER_ID` varchar(40) CHARACTER SET utf8 NOT NULL,
  `EVENT_ID` varchar(40) CHARACTER SET utf8 DEFAULT NULL,
  `EVENT_PATH` varchar(512) CHARACTER SET utf8 DEFAULT NULL,
  `ACTIVITY` varchar(512) CHARACTER SET utf8 DEFAULT NULL,
  `VERSION` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `APPKEY` varchar(40) CHARACTER SET utf8 DEFAULT NULL,
  `PRODUCT_ID` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`ER_ID`)
);


CREATE TABLE `RAZOR_MENU` (
  `menu_id` int(11) NOT NULL,
  `menu_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `menu_url` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `is_active` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `is_delete` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`menu_id`)
);

CREATE TABLE `RAZOR_PRODUCT` (
  `ID` varchar(20) CHARACTER SET utf8 NOT NULL,
  `NAME` varchar(50) CHARACTER SET utf8 NOT NULL,
  `DESCRIPTION` varchar(5000) CHARACTER SET utf8 NOT NULL,
  `DATE` datetime NOT NULL DEFAULT '1000-01-01 00:00:00',
  `USER_ID` varchar(20) CHARACTER SET utf8 NOT NULL,
  `CHANNEL_COUNT` int(11) NOT NULL DEFAULT '0',
  `PRODUCT_KEY` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `PRODUCT_PLATFORM` varchar(20) CHARACTER SET utf8 NOT NULL,
  `CATEGORY` varchar(20) CHARACTER SET utf8 NOT NULL,
  `ACTIVE` int(11) NOT NULL DEFAULT '1',
  `PACKAGE_NAME` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`ID`)
);

CREATE TABLE `RAZOR_ROLE` (
  `role_id` int(11) NOT NULL,
  `role_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `is_active` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `is_delete` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`role_id`)
);

CREATE TABLE `RAZOR_USER` (
  `user_id` int(11) NOT NULL,
  `login_name` varchar(40) CHARACTER SET utf8 DEFAULT NULL,
  `login_pwd` varchar(100) CHARACTER SET utf8 DEFAULT NULL,
  `name` varchar(40) CHARACTER SET utf8 DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `is_active` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
);

CREATE TABLE `RAZOR_USER2PRODUCT` (
  `ID` varchar(20) CHARACTER SET utf8 NOT NULL,
  `USER_ID` varchar(20) CHARACTER SET utf8 NOT NULL,
  `PRODUCT_ID` varchar(20) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`ID`)
);

-- ----------------------------
-- Records of RAZOR_MENU
-- ----------------------------
INSERT INTO `RAZOR_MENU` VALUES ('1', '应用管理', null, '1', '0', '0');
INSERT INTO `RAZOR_MENU` VALUES ('2', '图表统计', null, '1', '0', '0');
INSERT INTO `RAZOR_MENU` VALUES ('3', '系统管理', null, '1', '0', '0');
INSERT INTO `RAZOR_MENU` VALUES ('4', '个人中心', null, '1', '0', '0');
INSERT INTO `RAZOR_MENU` VALUES ('5', '关于系统', null, '1', '0', '0');
INSERT INTO `RAZOR_MENU` VALUES ('10001', '我的应用', null, '1', '0', '1');
INSERT INTO `RAZOR_MENU` VALUES ('10002', '用户管理', null, '1', '0', '3');
INSERT INTO `RAZOR_MENU` VALUES ('10003', '菜单管理', null, '1', '0', '3');
INSERT INTO `RAZOR_MENU` VALUES ('10004', '角色管理', null, '1', '0', '3');
INSERT INTO `RAZOR_MENU` VALUES ('10005', '个人中心', null, '1', '0', '4');
INSERT INTO `RAZOR_MENU` VALUES ('10006', '修改密码', null, '1', '0', '4');

-- ----------------------------
-- Records of RAZOR_PRODUCT
-- ----------------------------
INSERT INTO `RAZOR_PRODUCT` VALUES ('1', 'hello', 'test for user', '2017-09-06 20:29:10', '1', '4', '121212345', 'app', '1212312', '1', 'userpack');


-- ----------------------------
-- Records of RAZOR_ROLE
-- ----------------------------
INSERT INTO `RAZOR_ROLE` VALUES ('1', '系统管理员', '1', '0');
INSERT INTO `RAZOR_ROLE` VALUES ('2', '人事管理员', '1', '0');
INSERT INTO `RAZOR_ROLE` VALUES ('3', '经理', '1', '0');
INSERT INTO `RAZOR_ROLE` VALUES ('4', '员工', '1', '0');



INSERT INTO `RAZOR_USER` VALUES ('1', 'admin', '123456', '张三', '2', '2');
INSERT INTO `RAZOR_USER` VALUES ('2', 'zhangsan', '111111', 'yarcl', '1', '1');