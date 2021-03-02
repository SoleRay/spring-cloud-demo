/*
 Navicat Premium Data Transfer

 Source Server         : 本地数据库-10
 Source Server Type    : MySQL
 Source Server Version : 100508
 Source Host           : 192.168.0.10:3306
 Source Schema         : consumer

 Target Server Type    : MySQL
 Target Server Version : 100508
 File Encoding         : 65001

 Date: 01/03/2021 09:52:50
*/

CREATE DATABASE IF NOT EXISTS `tx_consumer`
DEFAULT CHARACTER SET utf8mb4
DEFAULT COLLATE utf8mb4_general_ci;

USE `tx_consumer`;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_consumer
-- ----------------------------
DROP TABLE IF EXISTS `consumer`;
CREATE TABLE `consumer` (
  `id` int(11) NOT NULL,
  `c_name` varchar(32) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `ismale` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `c_name_age` (`c_name`,`age`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

SET FOREIGN_KEY_CHECKS = 1;
