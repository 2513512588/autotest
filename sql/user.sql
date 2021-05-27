/*
 Navicat Premium Data Transfer

 Source Server         : mysql_localhost
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3306
 Source Schema         : code_management

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 09/04/2021 15:27:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
-- auto-generated definition
create table user
(
    id          int auto_increment
        primary key,
    password    varchar(60)  not null,
    create_time datetime     not null,
    username    varchar(50)  not null,
    phone       varchar(20)  null,
    real_name   varchar(30)  null,
    role        varchar(255) null
);



SET FOREIGN_KEY_CHECKS = 1;
