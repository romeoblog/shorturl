/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : db_shorturl

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 26/11/2019 09:54:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_short_url
-- ----------------------------
DROP TABLE IF EXISTS `tb_short_url`;
CREATE TABLE `tb_short_url`
(
    `id`        int(11)      NOT NULL AUTO_INCREMENT COMMENT '主键',
    `long_url`  varchar(240) NOT NULL COMMENT '长码',
    `short_url` varchar(120) NOT NULL COMMENT '短码',
    `type`      int(11)      NOT NULL DEFAULT '0' COMMENT '类型',
    `status`    int(11)      NOT NULL DEFAULT '0' COMMENT '状态',
    `create_at` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_at` datetime              DEFAULT NULL COMMENT '更改时间',
    `deleted`   int(11)               DEFAULT '0' COMMENT '0-未删除 1已删除',
    `version`   int(11)               DEFAULT '0' COMMENT '版本号',
    PRIMARY KEY (`id`),
    KEY `long_url` (`long_url`) USING BTREE,
    KEY `short_url` (`short_url`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 47
  DEFAULT CHARSET = utf8 COMMENT ='短网址（Short URL）映射关系';

SET FOREIGN_KEY_CHECKS = 1;


-- ----------------------------
-- Table structure for tb_report_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_report_log`;
CREATE TABLE `tb_report_log`
(
    `id`         int(11)      NOT NULL AUTO_INCREMENT,
    `ip`         varchar(30)  NOT NULL,
    `url`        varchar(150) NOT NULL,
    `pv`         int(11)      NOT NULL,
    `uv`         int(11)      NOT NULL,
    `create_at`  timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_at`  datetime              DEFAULT NULL COMMENT '更改时间',
    `deleted`    int(11)               DEFAULT '0' COMMENT '0-未删除 1已删除',
    `version`    int(11)               DEFAULT '0' COMMENT '版本号',
    `user_agent` varchar(200)          NOT NULL,
    `method`     varchar(10)           NOT NULL,
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = utf8;

SET FOREIGN_KEY_CHECKS = 1;
