DROP DATABASE IF EXISTS xiaomissm;
CREATE DATABASE xiaomissm DEFAULT CHARSET utf8;

/*打开DB*/
USE xiaomissm;
DROP TABLE IF EXISTS `orderdetail`;
DROP TABLE IF EXISTS `xmorder`;
DROP TABLE IF EXISTS `carshop`;
DROP TABLE IF EXISTS `address`;
DROP TABLE IF EXISTS `users`;
drop table if exists product_info;
drop table if exists product_type;
drop table if exists admin;


#DROP TABLE xiaomi_admin;
#################################管理员表
CREATE TABLE admin(
a_id INT AUTO_INCREMENT PRIMARY KEY,
a_name VARCHAR(20),
a_pass VARCHAR(20)
);
INSERT INTO admin(a_id,a_name,a_pass) VALUES(1,'admin','c984aed014aec7623a54f0591da07a85fd4b762d');

##########################商品类型表
CREATE TABLE product_type
(
type_id int auto_increment PRIMARY KEY,
type_name varchar(20)
);

####################添加数据
insert into product_type(type_name) values('Mobile Phone');
insert into product_type(type_name) values('Computer');
insert into product_type(type_name) values('TV');


#############################商品表
create table product_info
(
p_id int auto_increment primary key,
p_name varchar(20),
p_content varchar(200), ##############33商品规格/简介
p_price int, ###############价格
p_image varchar(200), #############图片
p_number int, ########数量
type_id int,
p_date date,
FOREIGN KEY(type_id) REFERENCES product_type(type_id)
);
##添加
insert into product_info(p_name,p_content,p_price,p_image,p_number,type_id,p_date) values('Xiaomi Note2','Black;6GB of memory;64GB of flash',2899,'xmNote2.jpg',500,1,'2018-01-04');
insert into product_info(p_name,p_content,p_price,p_image,p_number,type_id,p_date) values('Xiaomi Pad3','Gold;4GB of memory;64GB of flash',1499,'xmPad3.jpg',500,2,'2018-01-09');
insert into product_info(p_name,p_content,p_price,p_image,p_number,type_id,p_date) values('Xiaomi TV4','LG screen;3840×2160;4K',3299,'xmTV4-49.jpg',500,3,'2018-01-15');

#创建前台用户表

CREATE TABLE `users` (
                       `uid` int(11) NOT NULL auto_increment,
                       `uname` varchar(50) default NULL,
                       `upass` varbinary(50) default NULL,
                       `ustatus` int(11) default NULL,
                       `ulevel` int(11) default NULL,
                       `score` int(11) default NULL,
                       PRIMARY KEY  (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
#增加用户数据
INSERT INTO `users` VALUES ('1', 'zar', 0x313233343536, '0', '0', '0');
INSERT INTO `users` VALUES ('2', 'zhangsan', 0x313233343536, '1', '0', '0');
#创建地址表

CREATE TABLE `address` (
                         `addressId` int(11) NOT NULL auto_increment,
                         `uid` int(11) default NULL,
                         `cnee` varchar(50) default NULL,
                         `phone` varchar(11) default NULL,
                         `address` varchar(100) default NULL,
                         PRIMARY KEY  (`addressId`),
                         KEY `FK_Reference_1` (`uid`),
                         CONSTRAINT `FK_Reference_1` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
#增加地址表数据
INSERT INTO `address` VALUES ('1', '1', 'zar', '15266676667', 'Beijing');
INSERT INTO `address` VALUES ('2', '1', 'oracle', '15266678888', 'Shanghai');
INSERT INTO `address` VALUES ('3', '2', 'zhangsan', '15290888162', 'Chengdu');

#创建购物车表
CREATE TABLE `carshop` (
                         `cid` int(11) NOT NULL auto_increment,
                         `uid` int(11) default NULL,
                         `pid` int(11) default NULL,
                         `numbers` int(11) default NULL,
                         PRIMARY KEY  (`cid`),
                         KEY `FK_Reference_3` (`uid`),
                         KEY `FK_Reference_4` (`pid`),
                         CONSTRAINT `FK_Reference_4` FOREIGN KEY (`pid`) REFERENCES `product_info` (`p_id`),
                         CONSTRAINT `FK_Reference_3` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
#增加购物车数据
insert into carshop (uid,pid,numbers) values (1,1,2);
#创建订单表
CREATE TABLE `xmorder` (
                         `oid` char(32) NOT NULL ,
                         `uid` int(11) default NULL,
                         `addressId` int(11) default NULL,
                         `totalprice` double(10,2) default NULL,
                         `remarks` varchar(200) default NULL,
                         `status` varchar(6) default NULL,
                         `odate` TIMESTAMP  DEFAULT CURRENT_TIMESTAMP ,
                         PRIMARY KEY  (`oid`),
                         KEY `FK_Reference_5` (`uid`),
                         KEY `FK_Reference_6` (`addressId`),
                         CONSTRAINT `FK_Reference_6` FOREIGN KEY (`addressId`) REFERENCES `address` (`addressId`),
                         CONSTRAINT `FK_Reference_5` FOREIGN KEY (`uid`) REFERENCES `users` (`uid`)

) ENGINE=InnoDB DEFAULT CHARSET=utf8;
#订单表增加数据
insert into xmorder(oid,uid,addressid,totalprice,remarks,status,odate) values('abcd111222333444777888999000wwww',1,1,9996,'尽快送到','待发货',default);
#创建订单明细表

CREATE TABLE `orderdetail` (
                             `odid` int(11) NOT NULL auto_increment,
                             `oid` char(32) default NULL,
                             `pid` int(11) default NULL,
                             `pnumber` int(11) default NULL,
                             `ptotal` double(10,2) default NULL,
                             PRIMARY KEY  (`odid`),
                             KEY `FK_Reference_7` (`oid`),
                             KEY `FK_Reference_8` (`pid`),
                             CONSTRAINT `FK_Reference_8` FOREIGN KEY (`pid`) REFERENCES `product_info` (`p_id`),
                             CONSTRAINT `FK_Reference_9` FOREIGN KEY (`oid`) REFERENCES `xmorder` (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
insert into orderdetail(oid,pid,pnumber,ptotal) values ('abcd111222333444777888999000wwww',1,2,9996);


select * from admin;
select * from users;
select * from product_type;
select * from product_info ;
select * from orderdetail;
select * from xmorder;
select * from carshop;
select * from address;
