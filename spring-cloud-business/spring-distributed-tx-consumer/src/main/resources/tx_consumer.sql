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

DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE `undo_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `branch_id` bigint(20) NOT NULL,
  `xid` varchar(100) NOT NULL,
  `context` varchar(128) NOT NULL,
  `rollback_info` longblob NOT NULL,
  `log_status` int(11) NOT NULL,
  `log_created` datetime NOT NULL,
  `log_modified` datetime NOT NULL,
  `ext` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_undo_log` (`xid`,`branch_id`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4;


SET FOREIGN_KEY_CHECKS = 1;
