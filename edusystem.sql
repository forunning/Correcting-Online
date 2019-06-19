/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50096
Source Host           : localhost:3306
Source Database       : edusystem

Target Server Type    : MYSQL
Target Server Version : 50096
File Encoding         : 65001

Date: 2019-06-19 17:35:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` int(11) NOT NULL auto_increment,
  `tea_id` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `tea_id` (`tea_id`),
  KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1024 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('1012', '1512045003', '软件项目管理');
INSERT INTO `course` VALUES ('1013', '1512045011', '软件测试');
INSERT INTO `course` VALUES ('1014', '1512045019', '数据挖掘');
INSERT INTO `course` VALUES ('1015', '1612045030', '计算机组成');
INSERT INTO `course` VALUES ('1016', '1612045032', '数据库管理');
INSERT INTO `course` VALUES ('1017', '1612045035', '算法设计与分析');
INSERT INTO `course` VALUES ('1018', '1712045007', 'Java2使用教程');
INSERT INTO `course` VALUES ('1019', '1512045003', '数据结构');
INSERT INTO `course` VALUES ('1020', '1612045035', '程序设计基础');
INSERT INTO `course` VALUES ('1021', '1712045052', 'Python开发');
INSERT INTO `course` VALUES ('1022', '1512045003', '数据结构');

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `id` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES ('666', '666666');

-- ----------------------------
-- Table structure for sc
-- ----------------------------
DROP TABLE IF EXISTS `sc`;
CREATE TABLE `sc` (
  `stu_id` int(11) NOT NULL,
  `cou_id` int(11) NOT NULL,
  PRIMARY KEY  (`stu_id`,`cou_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sc
-- ----------------------------
INSERT INTO `sc` VALUES ('1611205001', '1012');
INSERT INTO `sc` VALUES ('1611205001', '1019');
INSERT INTO `sc` VALUES ('1611205007', '1012');
INSERT INTO `sc` VALUES ('1611205007', '1019');
INSERT INTO `sc` VALUES ('1611205011', '1019');
INSERT INTO `sc` VALUES ('1611205020', '1012');
INSERT INTO `sc` VALUES ('1611205020', '1019');
INSERT INTO `sc` VALUES ('1611205027', '1012');
INSERT INTO `sc` VALUES ('1611205027', '1019');
INSERT INTO `sc` VALUES ('1611205029', '1012');
INSERT INTO `sc` VALUES ('1611205029', '1019');
INSERT INTO `sc` VALUES ('1611205044', '1012');
INSERT INTO `sc` VALUES ('1611205044', '1019');
INSERT INTO `sc` VALUES ('1611205045', '1012');
INSERT INTO `sc` VALUES ('1611205045', '1019');
INSERT INTO `sc` VALUES ('1611205072', '1019');
INSERT INTO `sc` VALUES ('1611205076', '1019');
INSERT INTO `sc` VALUES ('1611205078', '1019');
INSERT INTO `sc` VALUES ('1611205084', '1012');
INSERT INTO `sc` VALUES ('1611205090', '1019');

-- ----------------------------
-- Table structure for st
-- ----------------------------
DROP TABLE IF EXISTS `st`;
CREATE TABLE `st` (
  `stu_id` int(11) NOT NULL,
  `tas_id` int(11) NOT NULL,
  `filepath` varchar(255) NOT NULL,
  `time` datetime NOT NULL,
  `score` int(11) default NULL,
  PRIMARY KEY  (`stu_id`,`tas_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of st
-- ----------------------------

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `phone` varchar(50) default NULL,
  `sex` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `enrolltime` varchar(50) NOT NULL,
  `department` varchar(50) NOT NULL,
  `specialty` varchar(50) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1611205000', '阿凡达', '', '男', '1611205000', '2010', '计算机', '软件');
INSERT INTO `student` VALUES ('1611205001', '鲍忠将', '', '男', '1611205001', '2016', '计算机与信息', '软件工程');
INSERT INTO `student` VALUES ('1611205007', '陈雨', '', '男', '1611205007', '2016', '计算机与信息', '软件工程');
INSERT INTO `student` VALUES ('1611205011', '程俭', '', '男', '1611205011', '2016', '计算机与信息', '软件工程');
INSERT INTO `student` VALUES ('1611205020', '董政', '', '男', '1611205020', '2016', '计算机与信息', '软件工程');
INSERT INTO `student` VALUES ('1611205027', '高倩', '', '女', '1611205027', '2016', '计算机与信息', '软件工程');
INSERT INTO `student` VALUES ('1611205029', '葛佳佳', '', '女', '1611205029', '2016', '计算机与信息', '软件工程');
INSERT INTO `student` VALUES ('1611205044', '蒋琪', '', '女', '1611205044', '2016', '计算机与信息', '软件工程');
INSERT INTO `student` VALUES ('1611205045', '蒋欣', '', '女', '1611205045', '2016', '计算机与信息', '软件工程');
INSERT INTO `student` VALUES ('1611205059', '刘慧', '', '女', '1611205059', '2016', '计算机与信息', '软件工程');
INSERT INTO `student` VALUES ('1611205060', '刘洋', '', '男', '1611205060', '2016', '计算机与信息', '软件工程');
INSERT INTO `student` VALUES ('1611205072', '钱茂', '', '男', '1611205072', '2016', '计算机与信息', '软件工程');
INSERT INTO `student` VALUES ('1611205076', '沈柳彤', '', '女', '1611205076', '2016', '计算机与信息', '软件工程');
INSERT INTO `student` VALUES ('1611205078', '时光', '', '男', '1611205078', '2016', '计算机与信息', '软件工程');
INSERT INTO `student` VALUES ('1611205084', '孙润', '', '男', '1611205084', '2016', '计算机与信息', '软件工程');
INSERT INTO `student` VALUES ('1611205090', '陶郑', '', '男', '1611205090', '2016', '计算机与信息', '软件工程');
INSERT INTO `student` VALUES ('1611205091', '田宇', '', '女', '1611205091', '2016', '计算机与信息', '软件工程');
INSERT INTO `student` VALUES ('1611205092', '屠西静', '', '女', '1611205092', '2016', '计算机与信息', '软件工程');
INSERT INTO `student` VALUES ('1611205102', '王磊', '', '男', '1611205102', '2016', '计算机与信息', '软件工程');
INSERT INTO `student` VALUES ('1611205113', '吴倩', '', '女', '1611205113', '2016', '计算机与信息', '软件工程');
INSERT INTO `student` VALUES ('1611205114', '吴霞', '', '女', '1611205114', '2016', '计算机与信息', '软件工程');
INSERT INTO `student` VALUES ('1611205117', '吴长河', '', '男', '1611205117', '2016', '计算机与信息', '软件工程');
INSERT INTO `student` VALUES ('1611205118', '吴正潇', '', '男', '1611205118', '2016', '计算机与信息', '软件工程');
INSERT INTO `student` VALUES ('1611205119', '伍方健', '', '男', '1611205119', '2016', '计算机与信息', '软件工程');
INSERT INTO `student` VALUES ('1611205120', '肖秋月', '', '女', '1611205120', '2016', '计算机与信息', '软件工程');
INSERT INTO `student` VALUES ('1611205121', '肖寅', '', '男', '1611205121', '2016', '计算机与信息', '软件工程');
INSERT INTO `student` VALUES ('1611205125', '徐京京', '', '男', '1611205125', '2016', '计算机与信息', '软件工程');
INSERT INTO `student` VALUES ('1611205128', '杨其梦', '', '女', '1611205128', '2016', '计算机与信息', '软件工程');
INSERT INTO `student` VALUES ('1611205133', '叶涛', '', '男', '1611205133', '2016', '计算机与信息', '软件工程');
INSERT INTO `student` VALUES ('1611205145', '张慧', '', '女', '1611205145', '2016', '计算机与信息', '软件工程');
INSERT INTO `student` VALUES ('1611205148', '张少聪', '', '男', '1611205148', '2016', '计算机与信息', '软件工程');
INSERT INTO `student` VALUES ('1611205153', '赵敏', '', '女', '1611205153', '2016', '计算机与信息', '软件工程');
INSERT INTO `student` VALUES ('1611205161', '周维', '', '男', '1611205161', '2016', '计算机与信息', '软件工程');
INSERT INTO `student` VALUES ('1611205165', '朱文娟', '', '女', '1611205165', '2016', '计算机与信息', '软件工程');
INSERT INTO `student` VALUES ('1611205169', '左帆', '', '女', '1611205169', '2016', '计算机与信息', '软件工程');
INSERT INTO `student` VALUES ('1611205175', '孙鉴', '', '男', '1611205175', '2016', '计算机与信息', '软件工程');

-- ----------------------------
-- Table structure for task
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `id` int(11) NOT NULL auto_increment,
  `s_time` datetime NOT NULL,
  `e_time` datetime NOT NULL,
  `file` varchar(255) default NULL,
  `c_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES ('3', '2019-06-09 00:00:00', '2019-06-10 00:00:00', '1560235158779-第6章作业.doc', '1012', '期中论文');
INSERT INTO `task` VALUES ('4', '2019-06-11 00:00:00', '2019-06-18 00:00:00', '1560184766780-第一章作业.doc', '1019', '作业一');
INSERT INTO `task` VALUES ('5', '2019-06-18 00:00:00', '2019-06-25 00:00:00', '1560184842639-计算题作业.docx', '1012', '实验一');
INSERT INTO `task` VALUES ('6', '2019-06-11 00:00:00', '2019-06-13 00:00:00', '1560184887748-案例分析.docx', '1012', '案例分析');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` varchar(50) NOT NULL,
  `phone` varchar(50) default NULL,
  `name` varchar(50) NOT NULL,
  `sex` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `worktime` varchar(50) NOT NULL,
  `department` varchar(50) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('1512045003', '', '左老师', '男', '1512045003', '2005', '计算机与信息');
INSERT INTO `teacher` VALUES ('1512045011', '', '薛老师', '男', '1512045011', '2005', '计算机与信息');
INSERT INTO `teacher` VALUES ('1512045019', '', '接老师', '男', '1512045019', '2005', '计算机与信息');
INSERT INTO `teacher` VALUES ('1612045001', '', '王老师', '男', '1612045001', '2006', '计算机与信息');
INSERT INTO `teacher` VALUES ('1612045030', '', '齐老师', '女', '1612045030', '2006', '计算机与信息');
INSERT INTO `teacher` VALUES ('1612045032', '', '桂老师', '女', '1612045032', '2006', '计算机与信息');
INSERT INTO `teacher` VALUES ('1612045035', '', '汪老师', '女', '1612045035', '2006', '计算机与信息');
INSERT INTO `teacher` VALUES ('1712045007', '', '董老师', '男', '1712045007', '2007', '计算机与信息');
INSERT INTO `teacher` VALUES ('1712045052', '', '章老师', '男', '1712045052', '2007', '计算机与信息');
