--删除数据库
DROP DATABASE IF EXISTS travel;
--创建数据库
CREATE DATABASE travel CHARACTER SET UTF8;
--使用数据库
USE travel;
--1.创建部门信息表
CREATE TABLE dept {
`did` bigint AUTO_INCREMENT COMMENT '部门编号',
`dname` varchar(50) COMMENT '部门名称',
`eid` varchar(50) COMMENT '领导编号',
CONSTRAINT pk_did1 PRIMARY KEY(did)
}ENGINE='innodb';
-- 增加部门信息
INSERT INTO dept (dname) VALUES ('管理部') ;
INSERT INTO dept (dname) VALUES ('人事部') ;
INSERT INTO dept (dname) VALUES ('财务部') ;
INSERT INTO dept (dname) VALUES ('市场部') ;
INSERT INTO dept (dname) VALUES ('开发一部') ;
INSERT INTO dept (dname) VALUES ('开发二部') ;
INSERT INTO dept (dname) VALUES ('开发三部') ;
--2.创建员工等级信息表
CREATE TABLE level{
`lid` varchar(50) COMMENT '岗级编号',
`title` varchar(50) COMMENT '岗级名称',
'level' int COMMENT '岗级级别',
CONSTRAINT pk_lid2 PRIMARY KEY(lid)
}ENGINE='innodb';
-- 增加员工等级信息
INSERT INTO level(lid,title,level) VALUES ('president','总裁',0) ;
INSERT INTO level(lid,title,level) VALUES ('chief','总监',1) ;
INSERT INTO level(lid,title,level) VALUES ('manager','部门经理',2) ;
INSERT INTO level(lid,title,level) VALUES ('staff','普通员工',3) ;
--3.创建员工信息表
CREATE TABLE emp{
`eid` varchar(50) NOT NULL COMMENT '员工编号',
`lid` varchar(50) COMMENT '岗级编号',
`did` bigint COMMENT '部门编号',
`ename` varchar(50) COMMENT '员工名称',
`sal` double COMMENT '员工薪资',
`phone` varchar(20) COMMENT '手机号',
`password` varchar(32) COMMENT '密码',
`photo` varchar(200) COMMENT '员工照片',
`note` text COMMENT '入职备注',
`hiredate` date COMMENT '入职日期',
`ineid` varchar(50) COMMENT '入职员工编号',
`locked` int default 0 COMMENT '锁定标记',
CONSTRAINT pk_eid3 PRIMARY KEY(eid),
CONSTRAINT fk_lid3 FOREIGN KEY(lid) REFERENCES level(lid),
CONSTRAINT fk_did3 FOREIGN KEY(did) REFERENCES dept(did)
}ENGINE='innodb';
--4.创建角色信息表
CREATE TABLE role(
`rid` varchar(50) COMMENT '角色编号',
`title` varchar(50) COMMENT '角色名称',
CONSTRAINT pk_rid4 PRIMARY KEY(rid)
)ENGINE='innodb';
-- 增加角色信息
INSERT INTO role(rid,title) VALUES ('emp','【人事部】雇员信息管理') ;
INSERT INTO role(rid,title) VALUES ('travel','【所有部门】差旅安排') ;
INSERT INTO role(rid,title) VALUES ('travelaudit','【人事部】差旅安排') ;
INSERT INTO role(rid,title) VALUES ('empshow','【管理部】雇员信息浏览') ;
--5.创建职位角色信息表
CREATE TABLE dept_role(
`did` bigint COMMENT '部门编号',
`rid` varchar(50) COMMENT '角色编号'
CONSTRAINT pk_did6 FOREIGN KEY dept(did),
CONSTRAINT pk_rid6 FOREIGN KEY role(rid)
)ENGINE='innodb';
-- 部门与角色关联
INSERT INTO dept_role(did,rid) VALUES (1,'travel') ;
INSERT INTO dept_role(did,rid) VALUES (2,'travel') ;
INSERT INTO dept_role(did,rid) VALUES (3,'travel') ;
INSERT INTO dept_role(did,rid) VALUES (4,'travel') ;
INSERT INTO dept_role(did,rid) VALUES (5,'travel') ;
INSERT INTO dept_role(did,rid) VALUES (6,'travel') ;
INSERT INTO dept_role(did,rid) VALUES (7,'travel') ;
INSERT INTO dept_role(did,rid) VALUES (1,'empshow') ;
INSERT INTO dept_role(did,rid) VALUES (2,'emp') ;
INSERT INTO dept_role(did,rid) VALUES (2,'travelaudit') ;
--6.创建权限信息表
CREATE TABLE action(
`actid` varchar(50) COMMENT '权限编号',
`rid`  varchar(50) COMMENT '角色编号',
`title` varchar(50) COMMENT '权限名称',
CONSTRAINT pk_actid5 PRIMARY KEY (actid) ,
CONSTRAINT fk_rid5 FOREIGN KEY(rid) REFERENCES role(rid)
)ENGINE='innodb';
-- 增加权限信息
INSERT INTO action(actid,rid,title) VALUES ('dept:list','emp','部门列表') ;
INSERT INTO action(actid,rid,title) VALUES ('dept:edit','emp','部门编辑') ;
INSERT INTO action(actid,rid,title) VALUES ('emp:list','emp','雇员列表') ;
INSERT INTO action(actid,rid,title) VALUES ('emp:add','emp','雇员增加') ;
INSERT INTO action(actid,rid,title) VALUES ('emp:edit','emp','雇员编辑') ;
INSERT INTO action(actid,rid,title) VALUES ('emp:delete','emp','雇员删除') ;
INSERT INTO action(actid,rid,title) VALUES ('emp:get','emp','雇员查看') ;
INSERT INTO action(actid,rid,title) VALUES ('travel:add','travel','出差申请') ;
INSERT INTO action(actid,rid,title) VALUES ('travel:edit','travel','出差申请修改') ;
INSERT INTO action(actid,rid,title) VALUES ('travel:self','travel','我的申请') ;
INSERT INTO action(actid,rid,title) VALUES ('travel:submit','travel','提交申请') ;
INSERT INTO action(actid,rid,title) VALUES ('travel:delete','travel','删除申请') ;
INSERT INTO action(actid,rid,title) VALUES ('travelaudit:list','travelaudit','差旅申请列表') ;
INSERT INTO action(actid,rid,title) VALUES ('travelaudit:prepare','travelaudit','待审核申请列表') ;
INSERT INTO action(actid,rid,title) VALUES ('travelaudit:handle','travelaudit','申请处理') ;

INSERT INTO action(actid,rid,title) VALUES ('deptshow:list','empshow','部门列表') ;
INSERT INTO action(actid,rid,title) VALUES ('empshow:list','empshow','雇员列表') ;
INSERT INTO action(actid,rid,title) VALUES ('empshow:get','empshow','雇员查看') ;
