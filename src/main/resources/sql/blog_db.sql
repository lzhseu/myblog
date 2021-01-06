/*
Navicat MySQL Data Transfer

Source Server         : 本地
Source Server Version : 80015
Source Host           : localhost:3306
Source Database       : blog_db

Target Server Type    : MYSQL
Target Server Version : 80015
File Encoding         : 65001

Date: 2020-06-05 20:18:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `blog`
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `blogger_id` int(11) unsigned NOT NULL COMMENT '用户id',
  `category_id` int(11) unsigned NOT NULL COMMENT '博文类别id',
  `state` int(11) NOT NULL COMMENT '博文状态',
  `title` varchar(80) NOT NULL COMMENT '博文标题',
  `content` longtext NOT NULL COMMENT '博文主体 html 格式',
  `content_md` longtext NOT NULL COMMENT '博文主体 markdown 格式',
  `intro` varchar(400) NOT NULL COMMENT '博文摘要',
  `date` datetime NOT NULL COMMENT '首次发布日期',
  `modify_date` datetime NOT NULL COMMENT '最后一次修改时间',
  `word_count` int(11) unsigned DEFAULT '0' COMMENT '鍗氭枃瀛楁暟',
  PRIMARY KEY (`id`),
  KEY `FK_Baccount_blog` (`blogger_id`),
  KEY `FK_category_blog` (`category_id`),
  CONSTRAINT `FK_Baccount_blog` FOREIGN KEY (`blogger_id`) REFERENCES `blogger_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_category_blog` FOREIGN KEY (`category_id`) REFERENCES `blog_category` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8;



-- ----------------------------
-- Table structure for `blogger_account`
-- ----------------------------
DROP TABLE IF EXISTS `blogger_account`;
CREATE TABLE `blogger_account` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(50) NOT NULL COMMENT '账号',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册日期',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blogger_account
-- ----------------------------
INSERT INTO `blogger_account` VALUES ('1', 'lzhseu@163.com', 'sy091314', '2020-05-26 10:11:49');

-- ----------------------------
-- Table structure for `blogger_profile`
-- ----------------------------
DROP TABLE IF EXISTS `blogger_profile`;
CREATE TABLE `blogger_profile` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `blogger_id` int(11) unsigned NOT NULL COMMENT '用户id',
  `nickname` varchar(20) NOT NULL COMMENT '用户昵称',
  `label` varchar(50) DEFAULT NULL COMMENT '个人标签',
  `intro` varchar(50) DEFAULT NULL COMMENT '一句话简介',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`),
  UNIQUE KEY `blogger_id` (`blogger_id`),
  UNIQUE KEY `email` (`email`),
  CONSTRAINT `FK_BAccount_BProfile` FOREIGN KEY (`blogger_id`) REFERENCES `blogger_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blogger_profile
-- ----------------------------
INSERT INTO `blogger_profile` VALUES ('1', '1', '牛奋', '学生,Java 后端,SEU', '日拱一卒，功不唐捐', 'lzhseu@163.com');

-- ----------------------------
-- Table structure for `blog_category`
-- ----------------------------
DROP TABLE IF EXISTS `blog_category`;
CREATE TABLE `blog_category` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(50) NOT NULL COMMENT '类别名称',
  `desc` text COMMENT '类别描述',
  `blogger_id` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `title` (`title`),
  KEY `blogger_id` (`blogger_id`),
  CONSTRAINT `FK_BAccount_category` FOREIGN KEY (`blogger_id`) REFERENCES `blogger_account` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog_category
-- ----------------------------
INSERT INTO `blog_category` VALUES ('1', '关于本站', '', '1');
INSERT INTO `blog_category` VALUES ('2', 'Java 基础', '这是一个 Java 基础的类别，再加点测试文字。再加一点点。', '1');
INSERT INTO `blog_category` VALUES ('3', 'Java 容器', '这是 Java 容器，再加一段测试文字吧', '1');
INSERT INTO `blog_category` VALUES ('4', 'Java 并发', '这是 Java 并发的测试', '1');
INSERT INTO `blog_category` VALUES ('5', 'JVM', '这是 JVM 并发的测试', '1');

-- ----------------------------
-- Table structure for `blog_image`
-- ----------------------------
DROP TABLE IF EXISTS `blog_image`;
CREATE TABLE `blog_image` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL COMMENT '图片名称',
  `desc` varchar(100) DEFAULT NULL COMMENT '图片描述',
  `url` varchar(200) DEFAULT NULL COMMENT '图片地址',
  PRIMARY KEY (`id`),
  UNIQUE KEY `title` (`title`),
  UNIQUE KEY `url` (`url`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;


