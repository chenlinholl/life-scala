/*
 Navicat Premium Data Transfer

 Source Server         : 192.168.31.46
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : 192.168.31.46:3306
 Source Schema         : zt_test

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 17/05/2018 15:41:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` bigint(20) NOT NULL,
  `name` varchar(50) COLLATE utf8mb4_bin DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `address` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

SET FOREIGN_KEY_CHECKS = 1;
