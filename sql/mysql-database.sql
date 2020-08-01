/*
 Navicat Premium Data Transfer

 Source Server         : 我的香港服务器
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : 128.1.131.111:3306
 Source Schema         : windypath

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 01/08/2020 10:59:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for WP_ARTICLE
-- ----------------------------
DROP TABLE IF EXISTS `WP_ARTICLE`;
CREATE TABLE `WP_ARTICLE`  (
  `ID` int(11) NOT NULL COMMENT 'ID',
  `TITLE_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '博客中文标题',
  `CONTENT_CN` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '博客中文正文',
  `UPDATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '编辑时间',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `TITLE_EN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '博客英文标题',
  `CONTENT_EN` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '博客英文正文',
  `USER_ID` int(11) NULL DEFAULT NULL COMMENT '作者用户ID',
  `HIDDEN` int(11) NULL DEFAULT NULL COMMENT '是否可见(0可见，1不可见)',
  `COMMENT_NUM` int(11) NULL DEFAULT NULL COMMENT '评论数',
  `CATEGORY_ID` int(11) NULL DEFAULT NULL COMMENT '分类ID',
  `PICTURE` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文章图',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '博客文章表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for WP_ARTICLE_TAG_RELATION
-- ----------------------------
DROP TABLE IF EXISTS `WP_ARTICLE_TAG_RELATION`;
CREATE TABLE `WP_ARTICLE_TAG_RELATION`  (
  `ID` int(11) NOT NULL COMMENT 'ID',
  `ARTICLE_ID` int(11) NULL DEFAULT NULL COMMENT '文章id',
  `TAG_ID` int(11) NULL DEFAULT NULL COMMENT '标签id',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章标签关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for WP_CATRGORY
-- ----------------------------
DROP TABLE IF EXISTS `WP_CATRGORY`;
CREATE TABLE `WP_CATRGORY`  (
  `ID` int(11) NOT NULL COMMENT 'ID',
  `NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类名称',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '编辑时间',
  `HIDDEN` int(11) NULL DEFAULT NULL COMMENT '是否隐藏',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for WP_COMMENT
-- ----------------------------
DROP TABLE IF EXISTS `WP_COMMENT`;
CREATE TABLE `WP_COMMENT`  (
  `ID` int(11) NOT NULL COMMENT 'ID',
  `USER_ID` int(11) NULL DEFAULT NULL COMMENT '评论人id',
  `CONTENT` varchar(800) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '评论内容',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '编辑时间',
  `ARTICLE_ID` int(11) NULL DEFAULT NULL COMMENT '文章id',
  `HIDDEN` int(11) NULL DEFAULT NULL COMMENT '是否隐藏',
  `REPLY_ID` int(11) NULL DEFAULT NULL COMMENT '回复评论id',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '评论表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for WP_MENU
-- ----------------------------
DROP TABLE IF EXISTS `WP_MENU`;
CREATE TABLE `WP_MENU`  (
  `ID` int(11) NOT NULL COMMENT 'ID',
  `NAME_CN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '中文菜单名称',
  `NAME_EN` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '英文菜单名称',
  `SEQ` int(11) NULL DEFAULT NULL COMMENT '排序',
  `PATH` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '跳转路径',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '编辑时间',
  `HIDDEN` int(11) NULL DEFAULT NULL COMMENT '是否隐藏',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for WP_PERMISSION
-- ----------------------------
DROP TABLE IF EXISTS `WP_PERMISSION`;
CREATE TABLE `WP_PERMISSION`  (
  `ID` int(11) NOT NULL COMMENT 'ID',
  `NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '编辑时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for WP_ROLE
-- ----------------------------
DROP TABLE IF EXISTS `WP_ROLE`;
CREATE TABLE `WP_ROLE`  (
  `ID` int(11) NOT NULL COMMENT 'ID',
  `NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名称',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '编辑时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for WP_ROLE_PERMISSION_RELATION
-- ----------------------------
DROP TABLE IF EXISTS `WP_ROLE_PERMISSION_RELATION`;
CREATE TABLE `WP_ROLE_PERMISSION_RELATION`  (
  `ID` int(11) NOT NULL COMMENT 'ID',
  `NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限名称',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '编辑时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色与权限关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for WP_SETTING
-- ----------------------------
DROP TABLE IF EXISTS `WP_SETTING`;
CREATE TABLE `WP_SETTING`  (
  `ID` int(11) NOT NULL COMMENT 'ID',
  `ITEM_KEY` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '键',
  `ITEM_VALUE` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '值',
  PRIMARY KEY (`ID`, `ITEM_KEY`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '博客设置' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for WP_TAG
-- ----------------------------
DROP TABLE IF EXISTS `WP_TAG`;
CREATE TABLE `WP_TAG`  (
  `ID` int(11) NOT NULL COMMENT 'ID',
  `NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '标签名称',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '编辑时间',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '标签表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for WP_USER
-- ----------------------------
DROP TABLE IF EXISTS `WP_USER`;
CREATE TABLE `WP_USER`  (
  `ID` int(11) NOT NULL COMMENT 'ID',
  `USERNAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `PASSWORD` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `NICKNAME` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '昵称',
  `AVATAR` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `EMAIL` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'EMAIL',
  `MOBILE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机',
  `SIGNATURE` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '签名',
  `CREATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` datetime(0) NULL DEFAULT NULL COMMENT '编辑时间',
  `DISABLED` int(11) NULL DEFAULT NULL COMMENT '禁用',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for WP_USER_ROLE_RELATION
-- ----------------------------
DROP TABLE IF EXISTS `WP_USER_ROLE_RELATION`;
CREATE TABLE `WP_USER_ROLE_RELATION`  (
  `ID` int(11) NOT NULL COMMENT 'ID',
  `USER_ID` int(11) NULL DEFAULT NULL COMMENT '用户ID',
  `ROLE_ID` int(11) NULL DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色关联表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
