/*
 Navicat Premium Data Transfer

 Source Server         : mysql_pacificrack_us
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : 155.94.201.186:3306
 Source Schema         : bugs

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 24/05/2021 19:13:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for acceptance
-- ----------------------------
DROP TABLE IF EXISTS `acceptance`;
CREATE TABLE `acceptance` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(255) NOT NULL COMMENT '项目题目',
  `content` varchar(255) NOT NULL COMMENT '测试内容',
  `user_id` int DEFAULT NULL COMMENT '测试用户',
  `defect` varchar(255) DEFAULT NULL COMMENT '测试缺陷',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `test_time` datetime DEFAULT NULL COMMENT '测试时间',
  `status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '状态 1-未测试 2-已测试',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3;

-- ----------------------------
-- Records of acceptance
-- ----------------------------
BEGIN;
INSERT INTO `acceptance` VALUES (2, '测试', '测试123', 2, '123', '2021-05-24 17:49:30', NULL, 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
