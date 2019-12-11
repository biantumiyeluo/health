/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.5.40 : Database - health
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`health` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `health`;

/*Table structure for table `t_checkgroup` */

DROP TABLE IF EXISTS `t_checkgroup`;

CREATE TABLE `t_checkgroup` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(32) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `helpCode` varchar(32) DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `remark` varchar(128) DEFAULT NULL,
  `attention` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `t_checkgroup` */

insert  into `t_checkgroup`(`id`,`code`,`name`,`helpCode`,`sex`,`remark`,`attention`) values (5,'0001','一般检查','YBJC','0','一般检查','无'),(6,'0002','视力色觉','SLSJ','0','视力色觉',NULL),(7,'0003','血常规','XCG','0','血常规',NULL),(8,'0004','尿常规','NCG','0','尿常规',NULL),(9,'0005','肝功三项','GGSX','0','肝功三项',NULL),(10,'0006','肾功三项','NGSX','0','肾功三项',NULL),(11,'0007','血脂四项','XZSX','0','血脂四项',NULL),(12,'0008','心肌酶三项','XJMSX','0','心肌酶三项',NULL),(13,'0009','甲功三项','JGSX','0','甲功三项',NULL),(14,'0010','子宫附件彩超','ZGFJCC','2','子宫附件彩超',NULL),(15,'0011','胆红素三项','DHSSX','1','胆红素三项',NULL);

/*Table structure for table `t_checkgroup_checkitem` */

DROP TABLE IF EXISTS `t_checkgroup_checkitem`;

CREATE TABLE `t_checkgroup_checkitem` (
  `checkgroup_id` int(11) NOT NULL DEFAULT '0',
  `checkitem_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`checkgroup_id`,`checkitem_id`),
  KEY `item_id` (`checkitem_id`),
  CONSTRAINT `group_id` FOREIGN KEY (`checkgroup_id`) REFERENCES `t_checkgroup` (`id`),
  CONSTRAINT `item_id` FOREIGN KEY (`checkitem_id`) REFERENCES `t_checkitem` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_checkgroup_checkitem` */

insert  into `t_checkgroup_checkitem`(`checkgroup_id`,`checkitem_id`) values (5,28),(5,29),(5,30),(5,31),(5,32),(6,33),(6,34),(6,35),(6,36),(6,37),(7,38),(7,39),(7,40),(7,41),(7,42),(7,43),(7,44),(7,45),(7,46),(7,47),(7,48),(7,49),(7,50),(7,51),(7,52),(7,53),(7,54),(7,55),(7,56),(8,57),(8,58),(8,59),(8,60),(8,61),(8,62),(8,63),(8,64),(8,65),(8,66),(8,67),(8,68),(8,69),(8,70),(8,71),(9,72),(9,73),(9,74),(10,75),(10,76),(10,77),(11,78),(11,79),(11,80),(11,81),(12,82),(12,83),(12,84),(13,85),(13,86),(13,87),(14,88),(14,89),(15,90),(15,91),(15,92);

/*Table structure for table `t_checkitem` */

DROP TABLE IF EXISTS `t_checkitem`;

CREATE TABLE `t_checkitem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(16) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `age` varchar(32) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `type` char(1) DEFAULT NULL COMMENT '查检项类型,分为检查和检验两种',
  `attention` varchar(128) DEFAULT NULL,
  `remark` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8;

/*Data for the table `t_checkitem` */

insert  into `t_checkitem`(`id`,`code`,`name`,`sex`,`age`,`price`,`type`,`attention`,`remark`) values (28,'0001','身高','0','0-100',5,'1','无','身高'),(29,'0002','体重','0','0-100',5,'1','无','体重'),(30,'0003','体重指数','0','0-100',5,'1','无','体重指数'),(31,'0004','收缩压','0','0-100',5,'1','无','收缩压'),(32,'0005','舒张压','0','0-100',5,'1','无','舒张压'),(33,'0006','裸视力（右）','0','0-100',5,'1','无','裸视力（右）'),(34,'0007','裸视力（左）','0','0-100',5,'1','无','裸视力（左）'),(35,'0008','矫正视力（右）','0','0-100',5,'1','无','矫正视力（右）'),(36,'0009','矫正视力（左）','0','0-100',5,'1','无','矫正视力（左）'),(37,'0010','色觉','0','0-100',5,'1','无','色觉'),(38,'0011','白细胞计数','0','0-100',10,'2','无','白细胞计数'),(39,'0012','红细胞计数','0','0-100',10,'2',NULL,'红细胞计数'),(40,'0013','血红蛋白','0','0-100',10,'2',NULL,'血红蛋白'),(41,'0014','红细胞压积','0','0-100',10,'2',NULL,'红细胞压积'),(42,'0015','平均红细胞体积','0','0-100',10,'2',NULL,'平均红细胞体积'),(43,'0016','平均红细胞血红蛋白含量','0','0-100',10,'2',NULL,'平均红细胞血红蛋白含量'),(44,'0017','平均红细胞血红蛋白浓度','0','0-100',10,'2',NULL,'平均红细胞血红蛋白浓度'),(45,'0018','红细胞分布宽度-变异系数','0','0-100',10,'2',NULL,'红细胞分布宽度-变异系数'),(46,'0019','血小板计数','0','0-100',10,'2',NULL,'血小板计数'),(47,'0020','平均血小板体积','0','0-100',10,'2',NULL,'平均血小板体积'),(48,'0021','血小板分布宽度','0','0-100',10,'2',NULL,'血小板分布宽度'),(49,'0022','淋巴细胞百分比','0','0-100',10,'2',NULL,'淋巴细胞百分比'),(50,'0023','中间细胞百分比','0','0-100',10,'2',NULL,'中间细胞百分比'),(51,'0024','中性粒细胞百分比','0','0-100',10,'2',NULL,'中性粒细胞百分比'),(52,'0025','淋巴细胞绝对值','0','0-100',10,'2',NULL,'淋巴细胞绝对值'),(53,'0026','中间细胞绝对值','0','0-100',10,'2',NULL,'中间细胞绝对值'),(54,'0027','中性粒细胞绝对值','0','0-100',10,'2',NULL,'中性粒细胞绝对值'),(55,'0028','红细胞分布宽度-标准差','0','0-100',10,'2',NULL,'红细胞分布宽度-标准差'),(56,'0029','血小板压积','0','0-100',10,'2',NULL,'血小板压积'),(57,'0030','尿比重','0','0-100',10,'2',NULL,'尿比重'),(58,'0031','尿酸碱度','0','0-100',10,'2',NULL,'尿酸碱度'),(59,'0032','尿白细胞','0','0-100',10,'2',NULL,'尿白细胞'),(60,'0033','尿亚硝酸盐','0','0-100',10,'2',NULL,'尿亚硝酸盐'),(61,'0034','尿蛋白质','0','0-100',10,'2',NULL,'尿蛋白质'),(62,'0035','尿糖','0','0-100',10,'2',NULL,'尿糖'),(63,'0036','尿酮体','0','0-100',10,'2',NULL,'尿酮体'),(64,'0037','尿胆原','0','0-100',10,'2',NULL,'尿胆原'),(65,'0038','尿胆红素','0','0-100',10,'2',NULL,'尿胆红素'),(66,'0039','尿隐血','0','0-100',10,'2',NULL,'尿隐血'),(67,'0040','尿镜检红细胞','0','0-100',10,'2',NULL,'尿镜检红细胞'),(68,'0041','尿镜检白细胞','0','0-100',10,'2',NULL,'尿镜检白细胞'),(69,'0042','上皮细胞','0','0-100',10,'2',NULL,'上皮细胞'),(70,'0043','无机盐类','0','0-100',10,'2',NULL,'无机盐类'),(71,'0044','尿镜检蛋白定性','0','0-100',10,'2',NULL,'尿镜检蛋白定性'),(72,'0045','丙氨酸氨基转移酶','0','0-100',10,'2',NULL,'丙氨酸氨基转移酶'),(73,'0046','天门冬氨酸氨基转移酶','0','0-100',10,'2',NULL,'天门冬氨酸氨基转移酶'),(74,'0047','Y-谷氨酰转移酶','0','0-100',10,'2',NULL,'Y-谷氨酰转移酶'),(75,'0048','尿素','0','0-100',10,'2',NULL,'尿素'),(76,'0049','肌酐','0','0-100',10,'2',NULL,'肌酐'),(77,'0050','尿酸','0','0-100',10,'2',NULL,'尿酸'),(78,'0051','总胆固醇','0','0-100',10,'2',NULL,'总胆固醇'),(79,'0052','甘油三酯','0','0-100',10,'2',NULL,'甘油三酯'),(80,'0053','高密度脂蛋白胆固醇','0','0-100',10,'2',NULL,'高密度脂蛋白胆固醇'),(81,'0054','低密度脂蛋白胆固醇','0','0-100',10,'2',NULL,'低密度脂蛋白胆固醇'),(82,'0055','磷酸肌酸激酶','0','0-100',10,'2',NULL,'磷酸肌酸激酶'),(83,'0056','磷酸肌酸激酶同工酶','0','0-100',10,'2',NULL,'磷酸肌酸激酶同工酶'),(84,'0057','乳酸脱氢酶','0','0-100',10,'2',NULL,'乳酸脱氢酶'),(85,'0058','三碘甲状腺原氨酸','0','0-100',10,'2',NULL,'三碘甲状腺原氨酸'),(86,'0059','甲状腺素','0','0-100',10,'2',NULL,'甲状腺素'),(87,'0060','促甲状腺激素','0','0-100',10,'2',NULL,'促甲状腺激素'),(88,'0061','子宫','2','0-100',10,'2',NULL,'子宫'),(89,'0062','附件','2','0-100',10,'2',NULL,'附件'),(90,'0063','总胆红素','0','0-100',10,'2',NULL,'总胆红素'),(91,'0064','直接胆红素','0','0-100',10,'2',NULL,'直接胆红素'),(92,'0065','间接胆红素','0','0-100',10,'2',NULL,'间接胆红素');

/*Table structure for table `t_member` */

DROP TABLE IF EXISTS `t_member`;

CREATE TABLE `t_member` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fileNumber` varchar(32) DEFAULT NULL,
  `name` varchar(32) DEFAULT NULL,
  `sex` varchar(8) DEFAULT NULL,
  `idCard` varchar(18) DEFAULT NULL,
  `phoneNumber` varchar(11) DEFAULT NULL,
  `regTime` date DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `email` varchar(32) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `remark` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10034 DEFAULT CHARSET=utf8;

/*Data for the table `t_member` */

insert  into `t_member`(`id`,`fileNumber`,`name`,`sex`,`idCard`,`phoneNumber`,`regTime`,`password`,`email`,`birthday`,`remark`) values (82,NULL,'小明','1','123456789000999999','18511279942','2019-03-08',NULL,NULL,NULL,NULL),(83,NULL,'王美丽','1','132333333333333','13412345678','2019-03-11',NULL,NULL,NULL,NULL),(84,NULL,'test',NULL,NULL,'18511279942','2019-03-13',NULL,NULL,NULL,NULL),(85,NULL,NULL,NULL,NULL,NULL,'2019-03-06',NULL,NULL,NULL,NULL),(86,NULL,NULL,NULL,NULL,NULL,'2019-04-04',NULL,NULL,NULL,NULL),(87,NULL,NULL,NULL,NULL,NULL,'2019-02-06',NULL,NULL,NULL,NULL),(88,NULL,NULL,NULL,NULL,NULL,'2019-04-10',NULL,NULL,NULL,NULL),(89,NULL,NULL,NULL,NULL,NULL,'2018-12-01',NULL,NULL,NULL,NULL),(90,NULL,NULL,NULL,NULL,NULL,'2018-12-02',NULL,NULL,NULL,NULL),(91,NULL,NULL,NULL,NULL,NULL,'2018-02-01',NULL,NULL,NULL,NULL),(92,NULL,'333','2','234234145432121345','18019286521','2019-04-19',NULL,NULL,NULL,NULL),(93,NULL,'war','2','123456789123456','15877685922','2019-12-07',NULL,NULL,NULL,NULL),(1001,NULL,'test','1','111111111111111','','2019-05-16',NULL,NULL,NULL,NULL),(1002,NULL,NULL,NULL,NULL,NULL,'2019-05-03',NULL,NULL,NULL,NULL),(1003,NULL,NULL,NULL,NULL,NULL,'2019-04-04',NULL,NULL,NULL,NULL),(1004,NULL,NULL,NULL,NULL,NULL,'2019-05-08',NULL,NULL,NULL,NULL),(1005,NULL,NULL,NULL,NULL,NULL,'2019-04-05',NULL,NULL,NULL,NULL),(1006,NULL,NULL,NULL,NULL,NULL,'2019-04-10',NULL,NULL,NULL,NULL),(1007,NULL,NULL,NULL,NULL,NULL,'2019-03-08',NULL,NULL,NULL,NULL),(1008,NULL,NULL,NULL,NULL,NULL,'2019-03-09',NULL,NULL,NULL,NULL),(1009,NULL,NULL,NULL,NULL,NULL,'2019-03-15',NULL,NULL,NULL,NULL),(10010,NULL,NULL,NULL,NULL,NULL,'2019-03-15',NULL,NULL,NULL,NULL),(10011,NULL,NULL,NULL,NULL,NULL,'2019-03-15',NULL,NULL,NULL,NULL),(10012,NULL,NULL,NULL,NULL,NULL,'2019-02-15',NULL,NULL,NULL,NULL),(10013,NULL,NULL,NULL,NULL,NULL,'2019-02-15',NULL,NULL,NULL,NULL),(10014,NULL,NULL,NULL,NULL,NULL,'2019-02-15',NULL,NULL,NULL,NULL),(10015,NULL,NULL,NULL,NULL,NULL,'2019-02-15',NULL,NULL,NULL,NULL),(10016,NULL,NULL,NULL,NULL,NULL,'2019-01-15',NULL,NULL,NULL,NULL),(10017,NULL,NULL,NULL,NULL,NULL,'2019-01-15',NULL,NULL,NULL,NULL),(10018,NULL,NULL,NULL,NULL,NULL,'2019-01-15',NULL,NULL,NULL,NULL),(10019,NULL,NULL,NULL,NULL,NULL,'2019-01-15',NULL,NULL,NULL,NULL),(10020,NULL,NULL,NULL,NULL,NULL,'2018-12-08',NULL,NULL,NULL,NULL),(10021,NULL,NULL,NULL,NULL,NULL,'2018-12-08',NULL,NULL,NULL,NULL),(10022,NULL,NULL,NULL,NULL,NULL,'2018-11-08',NULL,NULL,NULL,NULL),(10023,NULL,NULL,NULL,NULL,NULL,'2018-11-08',NULL,NULL,NULL,NULL),(10024,NULL,NULL,NULL,NULL,NULL,'2018-10-08',NULL,NULL,NULL,NULL),(10025,NULL,NULL,NULL,NULL,NULL,'2018-10-08',NULL,NULL,NULL,NULL),(10026,NULL,NULL,NULL,NULL,NULL,'2018-09-08',NULL,NULL,NULL,NULL),(10027,NULL,NULL,NULL,NULL,NULL,'2018-09-08',NULL,NULL,NULL,NULL),(10028,NULL,NULL,NULL,NULL,NULL,'2018-08-08',NULL,NULL,NULL,NULL),(10029,NULL,NULL,NULL,NULL,NULL,'2018-07-08',NULL,NULL,NULL,NULL),(10030,NULL,NULL,NULL,NULL,NULL,'2018-06-08',NULL,NULL,NULL,NULL),(10031,NULL,NULL,NULL,NULL,NULL,'2018-05-08',NULL,NULL,NULL,NULL),(10032,NULL,NULL,NULL,NULL,NULL,'2018-05-08',NULL,NULL,NULL,NULL),(10033,NULL,NULL,NULL,NULL,NULL,'2018-05-08',NULL,NULL,NULL,NULL);

/*Table structure for table `t_menu` */

DROP TABLE IF EXISTS `t_menu`;

CREATE TABLE `t_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `linkUrl` varchar(128) DEFAULT NULL,
  `path` varchar(128) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `icon` varchar(64) DEFAULT NULL,
  `description` varchar(128) DEFAULT NULL,
  `parentMenuId` int(11) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_13` (`parentMenuId`),
  CONSTRAINT `FK_Reference_13` FOREIGN KEY (`parentMenuId`) REFERENCES `t_menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

/*Data for the table `t_menu` */

insert  into `t_menu`(`id`,`name`,`linkUrl`,`path`,`priority`,`icon`,`description`,`parentMenuId`,`level`) values (1,'会员管理',NULL,'2',1,'fa-user-md',NULL,NULL,1),(2,'会员档案','member.html','/2-1',1,NULL,NULL,1,2),(3,'体检上传',NULL,'/2-2',2,NULL,NULL,1,2),(4,'会员统计',NULL,'/2-3',3,NULL,NULL,1,2),(5,'预约管理',NULL,'3',2,'fa-tty',NULL,NULL,1),(6,'预约列表','ordersettinglist.html','/3-1',1,NULL,NULL,5,2),(7,'预约设置','ordersetting.html','/3-2',2,NULL,NULL,5,2),(8,'套餐管理','setmeal.html','/3-3',3,NULL,NULL,5,2),(9,'检查组管理','checkgroup.html','/3-4',4,NULL,NULL,5,2),(10,'检查项管理','checkitem.html','/3-5',5,NULL,NULL,5,2),(11,'健康评估',NULL,'4',3,'fa-stethoscope',NULL,NULL,1),(12,'中医体质辨识',NULL,'/4-1',1,NULL,NULL,11,2),(13,'统计分析',NULL,'5',4,'fa-heartbeat',NULL,NULL,1),(14,'会员数量','report_member.html','/5-1',1,NULL,NULL,13,2),(15,'系统设置',NULL,'6',5,'fa-users',NULL,NULL,1),(16,'菜单管理','menu.html','/6-1',1,NULL,NULL,15,2),(17,'权限管理','permission.html','/6-2',2,NULL,NULL,15,2),(18,'角色管理','role.html','/6-3',3,NULL,NULL,15,2),(19,'用户管理','user.html','/6-4',4,NULL,NULL,15,2),(20,'套餐占比','report_setmeal.html','/5-2',2,NULL,NULL,13,2),(21,'运营数据','report_business.html','/5-3',3,NULL,NULL,13,2);

/*Table structure for table `t_order` */

DROP TABLE IF EXISTS `t_order`;

CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `member_id` int(11) DEFAULT NULL COMMENT '员会id',
  `orderDate` date DEFAULT NULL COMMENT '约预日期',
  `orderType` varchar(8) DEFAULT NULL COMMENT '约预类型 电话预约/微信预约',
  `orderStatus` varchar(8) DEFAULT NULL COMMENT '预约状态（是否到诊）',
  `setmeal_id` int(11) DEFAULT NULL COMMENT '餐套id',
  PRIMARY KEY (`id`),
  KEY `key_member_id` (`member_id`),
  KEY `key_setmeal_id` (`setmeal_id`),
  CONSTRAINT `key_member_id` FOREIGN KEY (`member_id`) REFERENCES `t_member` (`id`),
  CONSTRAINT `key_setmeal_id` FOREIGN KEY (`setmeal_id`) REFERENCES `t_setmeal` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

/*Data for the table `t_order` */

insert  into `t_order`(`id`,`member_id`,`orderDate`,`orderType`,`orderStatus`,`setmeal_id`) values (17,84,'2019-04-28','微信预约','未到诊',12),(26,93,'2019-12-16','微信预约','未到诊',54),(27,93,'2019-12-10','微信预约','未到诊',32),(28,93,'2019-12-14','微信预约','未到诊',32);

/*Table structure for table `t_ordersetting` */

DROP TABLE IF EXISTS `t_ordersetting`;

CREATE TABLE `t_ordersetting` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `orderDate` date DEFAULT NULL COMMENT '约预日期',
  `number` int(11) DEFAULT NULL COMMENT '可预约人数',
  `reservations` int(11) DEFAULT NULL COMMENT '已预约人数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8;

/*Data for the table `t_ordersetting` */

insert  into `t_ordersetting`(`id`,`orderDate`,`number`,`reservations`) values (13,'2019-03-04',100,100),(14,'2019-03-05',200,0),(15,'2019-03-06',300,0),(16,'2019-03-07',200,0),(17,'2019-03-08',200,1),(18,'2019-03-09',200,0),(19,'2019-03-10',200,0),(20,'2019-03-11',200,3),(21,'2019-03-13',300,1),(22,'2019-03-14',600,0),(23,'2019-03-15',500,1),(24,'2019-03-16',500,0),(25,'2019-03-17',400,0),(26,'2019-03-19',300,1),(27,'2019-04-01',300,0),(28,'2019-04-02',300,0),(29,'2019-04-19',300,0),(30,'2019-03-20',200,1),(31,'2019-05-01',300,0),(32,'2019-03-28',200,1),(33,'2019-04-03',400,0),(34,'2019-09-30',800,0),(35,'2019-04-04',400,0),(36,'2019-04-05',300,0),(37,'2019-04-14',200,1),(38,'2019-04-13',200,0),(39,'2019-04-17',400,0),(40,'2019-04-18',1,1),(41,'2019-04-20',300,1),(42,'2019-04-21',300,1),(43,'2019-04-22',300,1),(44,'2019-04-23',300,0),(45,'2019-04-24',300,0),(46,'2019-04-25',400,0),(47,'2019-04-26',300,0),(48,'2019-04-27',300,0),(49,'2019-04-28',300,2),(50,'2019-04-29',300,0),(51,'2019-04-30',300,0),(52,'2019-05-02',300,0),(53,'2019-05-03',300,0),(54,'2019-05-04',300,0),(55,'2019-05-05',300,0),(56,'2019-05-06',300,0),(57,'2019-05-07',300,0),(58,'2019-05-08',300,0),(59,'2019-05-09',300,0),(60,'2019-05-10',300,0),(61,'2019-05-11',300,0),(62,'2019-05-12',300,0),(63,'2019-05-13',301,0),(64,'2019-05-14',302,0),(65,'2019-05-15',300,0),(66,'2019-05-16',300,0),(67,'2019-12-01',10,0),(68,'2019-12-02',11,0),(69,'2019-12-03',12,0),(70,'2019-12-04',13,0),(71,'2019-12-05',14,0),(72,'2019-12-06',15,0),(73,'2019-12-07',16,0),(74,'2019-12-08',17,17),(75,'2019-12-09',18,1),(76,'2019-12-10',19,2),(77,'2019-12-11',20,0),(78,'2019-12-12',21,1),(79,'2019-12-13',22,0),(80,'2019-12-14',23,2),(81,'2019-12-15',24,0),(82,'2019-12-16',10,2),(83,'2019-12-17',11,0),(84,'2019-12-18',12,1),(85,'2019-12-19',13,0),(86,'2019-12-20',14,1),(87,'2019-12-21',15,0),(88,'2019-12-22',16,0),(89,'2019-12-23',17,0),(90,'2019-12-24',12,12),(91,'2019-12-25',13,13),(92,'2019-12-26',20,0),(93,'2019-12-27',21,0),(94,'2019-12-28',22,0),(95,'2019-12-29',23,0),(96,'2019-12-30',24,0);

/*Table structure for table `t_permission` */

DROP TABLE IF EXISTS `t_permission`;

CREATE TABLE `t_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `keyword` varchar(64) DEFAULT NULL,
  `description` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

/*Data for the table `t_permission` */

insert  into `t_permission`(`id`,`name`,`keyword`,`description`) values (1,'新增检查项','CHECKITEM_ADD',NULL),(2,'删除检查项','CHECKITEM_DELETE',NULL),(3,'编辑检查项','CHECKITEM_EDIT',NULL),(4,'查询检查项','CHECKITEM_QUERY',NULL),(5,'新增检查组','CHECKGROUP_ADD',NULL),(6,'删除检查组','CHECKGROUP_DELETE',NULL),(7,'编辑检查组','CHECKGROUP_EDIT',NULL),(8,'查询检查组','CHECKGROUP_QUERY',NULL),(9,'新增套餐','SETMEAL_ADD',NULL),(10,'删除套餐','SETMEAL_DELETE',NULL),(11,'编辑套餐','SETMEAL_EDIT',NULL),(12,'查询套餐','SETMEAL_QUERY',NULL),(13,'预约设置','ORDERSETTING',NULL),(14,'查看统计报表','REPORT_VIEW',NULL),(15,'新增菜单','MENU_ADD',NULL),(16,'删除菜单','MENU_DELETE',NULL),(17,'编辑菜单','MENU_EDIT',NULL),(18,'查询菜单','MENU_QUERY',NULL),(19,'新增角色','ROLE_ADD',NULL),(20,'删除角色','ROLE_DELETE',NULL),(21,'编辑角色','ROLE_EDIT',NULL),(22,'查询角色','ROLE_QUERY',NULL),(23,'新增用户','USER_ADD',NULL),(24,'删除用户','USER_DELETE',NULL),(25,'编辑用户','USER_EDIT',NULL),(26,'查询用户','USER_QUERY',NULL);

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `keyword` varchar(64) DEFAULT NULL,
  `description` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `t_role` */

insert  into `t_role`(`id`,`name`,`keyword`,`description`) values (1,'系统管理员','ROLE_ADMIN',NULL),(2,'健康管理师','ROLE_HEALTH_MANAGER',NULL);

/*Table structure for table `t_role_menu` */

DROP TABLE IF EXISTS `t_role_menu`;

CREATE TABLE `t_role_menu` (
  `role_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`menu_id`),
  KEY `FK_Reference_10` (`menu_id`),
  CONSTRAINT `FK_Reference_10` FOREIGN KEY (`menu_id`) REFERENCES `t_menu` (`id`),
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_role_menu` */

insert  into `t_role_menu`(`role_id`,`menu_id`) values (1,1),(2,1),(1,2),(2,2),(1,3),(2,3),(1,4),(2,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,10),(1,11),(1,12),(1,13),(1,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20),(1,21);

/*Table structure for table `t_role_permission` */

DROP TABLE IF EXISTS `t_role_permission`;

CREATE TABLE `t_role_permission` (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`),
  KEY `FK_Reference_12` (`permission_id`),
  CONSTRAINT `FK_Reference_11` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`),
  CONSTRAINT `FK_Reference_12` FOREIGN KEY (`permission_id`) REFERENCES `t_permission` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_role_permission` */

insert  into `t_role_permission`(`role_id`,`permission_id`) values (1,1),(2,1),(1,2),(2,2),(1,3),(2,3),(1,4),(2,4),(1,5),(2,5),(1,6),(2,6),(1,7),(2,7),(1,8),(2,8),(1,9),(2,9),(1,10),(2,10),(1,11),(2,11),(1,12),(2,12),(1,13),(2,13),(1,14),(2,14),(1,15),(1,16),(1,17),(1,18),(1,19),(1,20),(1,21),(1,22),(1,23),(1,24),(1,25),(1,26);

/*Table structure for table `t_setmeal` */

DROP TABLE IF EXISTS `t_setmeal`;

CREATE TABLE `t_setmeal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) DEFAULT NULL,
  `code` varchar(8) DEFAULT NULL,
  `helpCode` varchar(16) DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `age` varchar(32) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `remark` varchar(128) DEFAULT NULL,
  `attention` varchar(128) DEFAULT NULL,
  `img` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8;

/*Data for the table `t_setmeal` */

insert  into `t_setmeal`(`id`,`name`,`code`,`helpCode`,`sex`,`age`,`price`,`remark`,`attention`,`img`) values (12,'入职无忧体检套餐（男女通用）','0001','RZTJ','0','18-60',300,'入职体检套餐',NULL,'ee1e5306-feaa-4def-af4d-55a7a29a1b49.jpg'),(13,'粉红珍爱(女)升级TM12项筛查体检套餐','0002','FHZA','2','18-60',1200,'本套餐针对宫颈(TCT检查、HPV乳头瘤病毒筛查）、乳腺（彩超，癌抗125），甲状腺（彩超，甲功验血）以及胸片，血常规肝功等有全面检查，非常适合女性全面疾病筛查使用。',NULL,'cc551b98-b620-430e-97dd-3962fc0a54f0.jpg'),(14,'阳光爸妈升级肿瘤12项筛查（男女单人）体检套餐','0003','YGBM','0','55-100',1400,'本套餐主要针对常见肿瘤筛查，肝肾、颈动脉、脑血栓、颅内血流筛查，以及风湿、颈椎、骨密度检查',NULL,'1054bfe2-1ba3-40a5-b6c8-329951fc2463.jpg'),(15,'珍爱高端升级肿瘤12项筛查（男女单人）','0004','ZAGD','0','14-21',2400,'本套餐是一款针对生化五项检查，心，肝，胆，胃，甲状腺，颈椎，肺功能，脑部检查（经颅多普勒）以及癌症筛查，适合大众人群体检的套餐',NULL,'c2989f00-b7cb-4b8e-9118-17cab4854107.jpg'),(32,'111111','111111','111111','1','111111',111111,'111111','111111','5fbb0548-a2f4-4d76-957d-a1c5e6166639.gif'),(54,'12312','111','213','1','213',213,'132','213','3c1c2563-9155-4127-99c3-8489d0c53c64.gif');

/*Table structure for table `t_setmeal_checkgroup` */

DROP TABLE IF EXISTS `t_setmeal_checkgroup`;

CREATE TABLE `t_setmeal_checkgroup` (
  `setmeal_id` int(11) NOT NULL DEFAULT '0',
  `checkgroup_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`setmeal_id`,`checkgroup_id`),
  KEY `checkgroup_key` (`checkgroup_id`),
  CONSTRAINT `checkgroup_key` FOREIGN KEY (`checkgroup_id`) REFERENCES `t_checkgroup` (`id`),
  CONSTRAINT `setmeal_key` FOREIGN KEY (`setmeal_id`) REFERENCES `t_setmeal` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_setmeal_checkgroup` */

insert  into `t_setmeal_checkgroup`(`setmeal_id`,`checkgroup_id`) values (12,5),(32,5),(54,5),(12,6),(32,6),(54,6),(12,7),(12,8),(54,8),(12,9),(12,10),(14,10),(15,10),(12,11),(14,11),(15,11),(14,12),(14,13),(15,13),(13,14),(15,14),(13,15);

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `birthday` date DEFAULT NULL,
  `gender` varchar(1) DEFAULT NULL,
  `username` varchar(32) DEFAULT NULL,
  `password` varchar(256) DEFAULT NULL,
  `remark` varchar(32) DEFAULT NULL,
  `station` varchar(1) DEFAULT NULL,
  `telephone` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`birthday`,`gender`,`username`,`password`,`remark`,`station`,`telephone`) values (1,NULL,NULL,'admin','$2a$10$3xW2nBjwBM3rx1LoYprVsemNri5bvxeOd/QfmO7UDFQhW2HRHLi.C',NULL,NULL,NULL),(2,NULL,NULL,'xiaoming','$2a$10$3xW2nBjwBM3rx1LoYprVsemNri5bvxeOd/QfmO7UDFQhW2HRHLi.C',NULL,NULL,NULL),(3,NULL,NULL,'test','$2a$10$zYJRscVUgHX1wqwu90WereuTmIg6h/JGirGG4SWBsZ60wVPCgtF8W',NULL,NULL,NULL);

/*Table structure for table `t_user_role` */

DROP TABLE IF EXISTS `t_user_role`;

CREATE TABLE `t_user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FK_Reference_8` (`role_id`),
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_user_role` */

insert  into `t_user_role`(`user_id`,`role_id`) values (1,1),(2,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
