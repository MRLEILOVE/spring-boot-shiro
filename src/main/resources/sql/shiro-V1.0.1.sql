/*
SQLyog Ultimate v12.5.0 (64 bit)
MySQL - 5.7.21-log : Database - shiro
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`shiro` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `shiro`;

/*Table structure for table `sys_permission` */

DROP TABLE IF EXISTS `sys_permission`;

CREATE TABLE `sys_permission` (
  `permissionId` int(11) NOT NULL AUTO_INCREMENT,
  `available` bit(1) DEFAULT NULL,
  `parentId` bigint(20) DEFAULT NULL,
  `parentIds` varchar(255) DEFAULT NULL,
  `permission` varchar(255) DEFAULT NULL,
  `permissionName` varchar(255) NOT NULL,
  `resourceType` enum('menu','button') DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`permissionId`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `sys_permission` */

insert  into `sys_permission`(`permissionId`,`available`,`parentId`,`parentIds`,`permission`,`permissionName`,`resourceType`,`url`) values 
(1,'\0',0,'0/','user:view','用户管理','menu','user/userList'),
(2,'\0',1,'0/1','user:add','用户添加','button','user/userAdd'),
(3,'\0',1,'0/1','user:del','用户删除','button','user/userDel');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `available` bit(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`roleId`),
  UNIQUE KEY `UK_8sggqkp1sv8guimk979mf6anf` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

insert  into `sys_role`(`roleId`,`available`,`description`,`role`) values 
(1,'\0','管理员','admin'),
(2,'\0','VIP会员','vip'),
(3,'','test','test');

/*Table structure for table `sys_role_permission` */

DROP TABLE IF EXISTS `sys_role_permission`;

CREATE TABLE `sys_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `permissionId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpuhqkr403td1v28c3e71cgm4b` (`roleId`),
  KEY `FKjwye79px7p33gsqu4kftj0ua1` (`permissionId`),
  CONSTRAINT `FKjwye79px7p33gsqu4kftj0ua1` FOREIGN KEY (`permissionId`) REFERENCES `sys_permission` (`permissionId`),
  CONSTRAINT `FKpuhqkr403td1v28c3e71cgm4b` FOREIGN KEY (`roleId`) REFERENCES `sys_role` (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role_permission` */

insert  into `sys_role_permission`(`id`,`permissionId`,`roleId`) values 
(1,1,1),
(2,2,1);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `createTime` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `expiredDate` date DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `state` tinyint(4) NOT NULL,
  `userName` varchar(255) NOT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE KEY `UK_hl8fftx66p59oqgkkcfit3eay` (`userName`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

insert  into `sys_user`(`userId`,`createTime`,`email`,`expiredDate`,`name`,`password`,`salt`,`state`,`userName`) values 
(1,'2019-06-28 18:00:31',NULL,NULL,'管理员','d3c59d25033dbf980d29554025c23a75','8d78869f470951332959580424d4bf4f',0,'admin');

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgnn5rpnbwhx9fu93b19daiwbt` (`roleId`),
  KEY `FKsaymuhj4r4qr22w2q1e2oewx` (`userId`),
  CONSTRAINT `FKgnn5rpnbwhx9fu93b19daiwbt` FOREIGN KEY (`roleId`) REFERENCES `sys_role` (`roleId`),
  CONSTRAINT `FKsaymuhj4r4qr22w2q1e2oewx` FOREIGN KEY (`userId`) REFERENCES `sys_user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`id`,`userId`,`roleId`) values 
(1,1,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
