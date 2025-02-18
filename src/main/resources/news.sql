/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:news
 * 类描述:创建数据库的sql元语句
 **/

create
database if not exists news default charset utf8 collate utf8_general_ci;

CREATE TABLE `news`
(
    `id`         BIGINT(20)   NOT NULL AUTO_INCREMENT,
    `title`      VARCHAR(100) NOT NULL,
    `summary`    VARCHAR(500) DEFAULT NULL,
    `content`    TEXT         NOT NULL,
    `status`     TINYINT(1)   NOT NULL,
    `created_at` BIGINT(20)   NOT NULL,
    `click_num`  INT(11)      NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10001
  DEFAULT CHARSET = utf8;

/* article */
create table article
(
    id         int primary key auto_increment,
    title      varchar(512),
    img        varchar(1024), /* oss img */
    intro      varchar(512),
    content    longtext,
    created_at bigint(20),
    visit_num  int,
    status     tinyint(4) /* 1.正常 2.停用 */
)DEFAULT CHARSET = utf8;

/* 管理员角色 */
create table role
(
    id          int primary key auto_increment,
    name        varchar(16), /* 角色名称 */
    permissions varchar(1024) /* 角色权限 */
);

insert into role(name, permissions)
values ('超级管理员', 'ROLE_EDIT,ADMIN_LIST,ADMIN_EDIT');

/* 管理员信息表 */
create table admin
(
    id        int primary key auto_increment, /* 主键 */
    username  varchar(16) not null, /* username */
    name      varchar(16), /* 用户名称/备注 */
    password  varchar(32) not null, /* 密码 */
    role_id   int, /* 角色 */
    status    tinyint(4), /* 1.正常 2.停用 */
    signin_at bigint /* 最后登录时间 */
);

/* password A111111+salt */
insert into admin(username, name, password, role_id, status, signin_at)
values ('admin', '超级管理员', 'c4723c465300cf66978f39ccf83f0adc', 1, 1, null);

/* 管理员登录日志 & token 持久化 */
create table admin_session
(
    id        int primary key auto_increment, /* 主键 */
    admin_id  int, /* 关联admin */
    role      varchar(128), /* 管理组名称 */
    name      varchar(128), /* 管理员名称 */
    token     varchar(64), /* token */
    signin_at bigint, /* 登录时间 */
    expire_at bigint, /* 过期时间 */
    ip        varchar(32), /* 登录ip */
    location  varchar(128), /* 登录地址 */
    UNIQUE KEY `token` (`token`)
);

/* banner */
create table banner
(
    id       int primary key auto_increment,
    title    varchar(64), /* 标题 */
    url      varchar(512), /* 跳转链接 */
    status   tinyint(4), /* 1.显示 2.隐藏 */
    priority int, /* 权重 */
    type     tinyint(4), /* 1.PC 2.手机 */
    img      varchar(1024) /* oss img */
);

/* article */
create table article
(
    id         int primary key auto_increment,
    title      varchar(512),
    img        varchar(1024), /* oss img */
    intro      varchar(512),
    content    longtext,
    created_at bigint(20),
    visit      int,
    status     int /* 1.正常 2.停用 */
);

/* 客户案例 */
create table custcase
(
    id         int primary key auto_increment,
    img        varchar(1024), /* oss img */
    banners    varchar(5000), /* oss img */
    title      varchar(512),
    subtitle   varchar(512),
    context    varchar(512),/* 项目背景 */
    customer   varchar(128),
    industry   varchar(128),
    service    varchar(256),
    content    longtext,
    settop     tinyint(4) default 2,/* 1.置顶 2.普通 */
    top_intro  varchar(500),/* 置顶简介 */
    top_img    varchar(1024),/* 置顶列表图片 */
    priority   int default 0, /* 权重 */
    created_at bigint(20),
    status     int /* 1.正常 2.停用 */
);

/* 客户评价 */
create table custeval
(
    id         int primary key auto_increment,
    title      varchar(512),
    customer   varchar(128),
    img        varchar(1024), /* oss img */
    created_at bigint(20),
    status     int /* 1.正常 2.停用 */
);

/*用户*/
CREATE TABLE user
(
    id           INT PRIMARY KEY auto_increment,
    `mobile`     varchar(20)  NOT NULL,
    `email`      varchar(20),
    `nick`       varchar(20),
    `password`   varchar(100) NOT NULL,
    `created_at` bigint(20) NOT NULL,
    address_id   varchar(100),
    img          VARCHAR(1024)
) DEFAULT CHARSET = utf8;

/*用户会话*/
CREATE TABLE `usersession`
(
    `id`        int(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,
    `user_id`   int(11) NOT NULL,
    `token`     varchar(100) NOT NULL,
    `signin_at` bigint(20) NOT NULL,
    `expire_at` bigint(20) NOT NULL,
    `ip`        varchar(100) DEFAULT NULL,
    `location`  varchar(20)  DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8;