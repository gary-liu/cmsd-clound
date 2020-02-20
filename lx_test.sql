/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : localhost:3306
 Source Schema         : lx_test

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 20/02/2020 16:41:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_route
-- ----------------------------
DROP TABLE IF EXISTS `sys_route`;
CREATE TABLE `sys_route` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `route_id` varchar(255) NOT NULL DEFAULT '' COMMENT '路由ID',
  `route_name` varchar(255) NOT NULL DEFAULT '' COMMENT '路由名称',
  `predicates` varchar(255) NOT NULL DEFAULT '' COMMENT '断言',
  `filters` varchar(255) NOT NULL DEFAULT '' COMMENT '过滤器',
  `uri` varchar(255) NOT NULL DEFAULT '' COMMENT 'URI',
  `sort` int(11) NOT NULL COMMENT '排序',
  `status` tinyint(4) NOT NULL COMMENT '启用禁用',
  `creator` varchar(128) NOT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` varchar(128) NOT NULL COMMENT '修改人',
  `modify_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_flag` tinyint(4) NOT NULL DEFAULT '0' COMMENT '删除标记',
  `application_code` varchar(128) NOT NULL DEFAULT '' COMMENT '系统编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of sys_route
-- ----------------------------
BEGIN;
INSERT INTO `sys_route` VALUES (607150228717572096, 'msc-service', '消息中心', '[\n  {\n    \"name\": \"Path\",\n    \"args\": {\n      \"_genkey_0\": \"/api/msc/**\"\n    }\n  }\n]', '[\n  {\n    \"name\": \"StripPrefix\",\n    \"args\": {\n      \"_genkey_0\": \"2\"\n    }\n  },\n  {\n    \"name\": \"RemoveRequestHeader\",\n    \"args\": {\n      \"_genkey_0\": \"Cookie\",\n      \"_genkey_1\": \"Set-Cookie\"\n    }\n  }\n]', 'lb://msc-service', 4, 0, '', '2019-10-07 15:04:11', '', '2019-10-07 14:55:44', 0, 'EXAM');
INSERT INTO `sys_route` VALUES (607150228717572097, 'user-service', '用户服务', '[\n  {\n    \"name\": \"Path\",\n    \"args\": {\n      \"_genkey_0\": \"/api/user/**\"\n    }\n  }\n]', '[\n  {\n    \"name\": \"StripPrefix\",\n    \"args\": {\n      \"_genkey_0\": \"2\"\n    }\n  },\n  {\n    \"name\": \"RemoveRequestHeader\",\n    \"args\": {\n      \"_genkey_0\": \"Cookie\",\n      \"_genkey_1\": \"Set-Cookie\"\n    }\n  }\n]', 'lb://user-service', 2, 0, 'admin', '2019-04-07 11:22:05', '', '2019-08-03 09:58:35', 0, 'EXAM');
INSERT INTO `sys_route` VALUES (607150228717572098, 'exam-service', '考试服务', '[\n  {\n    \"name\": \"Path\",\n    \"args\": {\n      \"_genkey_0\": \"/api/exam/**\"\n    }\n  }\n]', '[\n  {\n    \"name\": \"StripPrefix\",\n    \"args\": {\n      \"_genkey_0\": \"2\"\n    }\n  },\n  {\n    \"name\": \"RemoveRequestHeader\",\n    \"args\": {\n      \"_genkey_0\": \"Cookie\",\n      \"_genkey_1\": \"Set-Cookie\"\n    }\n  }\n]', 'lb://exam-service', 3, 0, 'admin', '2019-04-02 21:39:30', '', '2019-08-03 09:58:54', 0, 'EXAM');
INSERT INTO `sys_route` VALUES (607150228717572099, 'auth-service', '认证授权服务', '[\n  {\n    \"name\": \"Path\",\n    \"args\": {\n      \"_genkey_0\": \"/api/auth/**\"\n    }\n  }\n]', '[\n  {\n    \"name\": \"StripPrefix\",\n    \"args\": {\n      \"_genkey_0\": \"2\"\n    }\n  },\n  {\n    \"name\": \"RemoveRequestHeader\",\n    \"args\": {\n      \"_genkey_0\": \"Cookie\",\n      \"_genkey_1\": \"Set-Cookie\"\n    }\n  }\n]', 'lb://auth-service', 1, 0, 'admin', '2019-04-07 11:20:55', '', '2019-08-03 09:58:48', 0, 'EXAM');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'name');
INSERT INTO `user` VALUES (2, 'user');
INSERT INTO `user` VALUES (3, 'sdfsd');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
