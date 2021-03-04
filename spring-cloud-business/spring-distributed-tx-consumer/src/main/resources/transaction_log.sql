/*
 Navicat Premium Data Transfer

 Source Server         : 本地数据库-10
 Source Server Type    : MySQL
 Source Server Version : 100508
 Source Host           : 192.168.0.10:3306
 Source Schema         : mq-producer

 Target Server Type    : MySQL
 Target Server Version : 100508
 File Encoding         : 65001

 Date: 03/03/2021 21:36:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for transaction_log
-- ----------------------------
DROP TABLE IF EXISTS `transaction_log`;
CREATE TABLE `transaction_log` (
  `id` varchar(64) NOT NULL,
  `business` varchar(64) DEFAULT NULL,
  `foreign_key` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
