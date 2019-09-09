/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-08-08 16:23:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` varchar(36) NOT NULL,
  `permission_name` varchar(50) DEFAULT NULL,
  `permission_code` varchar(100) DEFAULT NULL,
  `permission_type` char(1) DEFAULT NULL COMMENT '1-菜单 2-按钮',
  `permission_order` int(11) DEFAULT NULL,
  `parent_id` varchar(36) DEFAULT NULL,
  `url` varchar(100) DEFAULT NULL COMMENT '菜单url',
  `is_delete` char(1) DEFAULT NULL COMMENT '0-未删除，1-删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建者',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modifyUser` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('001', '系统管理', 'sys:*:*', '1', '1', '0', '', '1', null, null, '2018-12-16 18:05:01', null);
INSERT INTO `sys_permission` VALUES ('002', '用户管理', 'sys:user:view', '1', '1', '001', '/user/index.html', '0', null, null, '2018-12-16 23:21:38', null);
INSERT INTO `sys_permission` VALUES ('0021', '新增', 'sys:user:add', '2', '1', '002', null, '1', null, null, '2018-12-16 23:21:40', null);
INSERT INTO `sys_permission` VALUES ('0022', '编辑', 'sys:user:update', '2', '2', '002', null, '0', null, null, '2018-12-16 23:21:41', null);
INSERT INTO `sys_permission` VALUES ('0023', '冻结', 'sys:user:lock', '2', '3', '002', null, '0', null, null, '2018-12-16 23:21:43', null);
INSERT INTO `sys_permission` VALUES ('003', '角色管理', 'sys:role:view', '1', '2', '001', '/role/index.html', '0', null, null, '2018-12-16 23:21:45', null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` varchar(36) NOT NULL,
  `role_name` varchar(50) DEFAULT NULL,
  `role_code` varchar(50) DEFAULT NULL,
  `is_delete` char(1) DEFAULT NULL COMMENT '0-未删除，1-删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建者',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modifyUser` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('000000', '超级管理员', 'super_admin', '0', null, null, '2018-12-09 16:35:45', null);
INSERT INTO `sys_role` VALUES ('100000', '游客', 'visitor', '0', null, null, '2018-12-09 16:36:28', null);

-- ----------------------------
-- Table structure for sys_role_permission_ref
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission_ref`;
CREATE TABLE `sys_role_permission_ref` (
  `id` varchar(36) NOT NULL,
  `role_id` varchar(36) DEFAULT NULL,
  `permission_id` varchar(36) DEFAULT NULL,
  `is_delete` char(1) DEFAULT NULL COMMENT '0-未删除，1-删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建者',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modifyUser` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_role_permission_ref
-- ----------------------------
INSERT INTO `sys_role_permission_ref` VALUES ('97967221665431558', '000000', '001', '0', null, null, '2018-12-09 18:10:37', null);
INSERT INTO `sys_role_permission_ref` VALUES ('97967221665431559', '000000', '002', '0', null, null, '2018-12-09 18:10:37', null);
INSERT INTO `sys_role_permission_ref` VALUES ('97967221665431560', '000000', '0021', '0', null, null, '2018-12-09 18:10:37', null);
INSERT INTO `sys_role_permission_ref` VALUES ('97967221665431561', '000000', '0022', '0', null, null, '2018-12-09 18:10:37', null);
INSERT INTO `sys_role_permission_ref` VALUES ('97967221665431562', '000000', '0023', '0', null, null, '2018-12-09 18:10:37', null);
INSERT INTO `sys_role_permission_ref` VALUES ('97967221665431563', '000000', '003', '0', null, null, '2018-12-09 18:10:37', null);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` varchar(36) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `user_type` char(1) DEFAULT NULL COMMENT '保留字段 用户类型',
  `is_lock` char(1) DEFAULT NULL COMMENT '0-未锁定，1-锁定',
  `is_delete` char(1) DEFAULT NULL COMMENT '0-未删除，1-删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建者',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modifyUser` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'taven', '$2a$10$j07GzkILzeMa00QPEAnZv.6/uFppV9WPQJaWWmrvcP9KQyr9reVxG', null, '0', '0', null, null, '2019-08-06 17:31:06', null);
INSERT INTO `sys_user` VALUES ('2', 'hmj', 'xxx', null, '0', '0', null, null, '2018-12-09 16:33:35', null);

-- ----------------------------
-- Table structure for sys_user_role_ref
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role_ref`;
CREATE TABLE `sys_user_role_ref` (
  `id` varchar(36) NOT NULL,
  `user_id` varchar(36) DEFAULT NULL,
  `role_id` varchar(36) DEFAULT NULL,
  `is_delete` char(1) DEFAULT NULL COMMENT '0-未删除，1-删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '创建者',
  `modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `modifyUser` varchar(32) CHARACTER SET utf8 DEFAULT NULL COMMENT '修改者',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of sys_user_role_ref
-- ----------------------------
INSERT INTO `sys_user_role_ref` VALUES ('1', '1', '000000', '0', null, null, '2018-12-16 14:31:15', null);
