/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/3/11 13:23:39                           */
/*==============================================================*/


drop table if exists t_applygroup;

drop table if exists t_association;

drop table if exists t_association_group;

drop table if exists t_book;

drop table if exists t_group;

drop table if exists t_group_user;

drop table if exists t_interact;

drop table if exists t_picture;

drop table if exists t_remark;

drop table if exists t_remark_interact;

drop table if exists t_score;

drop table if exists t_user;

drop table if exists t_user_like;

drop table if exists t_user_message;

/*==============================================================*/
/* Table: t_applygroup                                          */
/*==============================================================*/
create table t_applygroup
(
   id                   bigint(20) not null auto_increment,
   update_time          datetime,
   create_time          datetime,
   book_id_fk           bigint(20),
   book_name            varchar(100),
   user_id_fk           bigint(20),
   applicant_email      varchar(32),
   applicant_desc       varchar(1024),
   apply_group_name     varchar(32),
   read_slogan          varchar(2014),
   group_desc           varchar(2014),
   apply_state          char(1) comment '0为未审核，1为已通过。',
   primary key (id)
);

alter table t_applygroup comment '状态0为未审核通过，1为审核通过';

/*==============================================================*/
/* Table: t_association                                         */
/*==============================================================*/
create table t_association
(
   id                   bigint(20) not null auto_increment,
   create_time          datetime,
   update_time          datetime,
   name                 varchar(30),
   state                char(1) comment '0停用，1启用',
   user_id_fk           bigint(20),
   primary key (id)
);

alter table t_association comment 't_association';

/*==============================================================*/
/* Table: t_association_group                                   */
/*==============================================================*/
create table t_association_group
(
   id                   bigint(20) not null auto_increment,
   association_id_fk    bigint(20),
   group_id_fk          bigint(20),
   create_time          datetime,
   update_time          datetime,
   statie               char(1) comment '0停用，1启用',
   primary key (id)
);

/*==============================================================*/
/* Table: t_book                                                */
/*==============================================================*/
create table t_book
(
   id                   bigint(20) not null auto_increment,
   create_time          datetime,
   update_time          datetime,
   title                varchar(128),
   author               varchar(256),
   pic_id_fk            varchar(1024),
   state                char(1) comment '0为锁定，1为可用',
   origin_title         varchar(128),
   alt_title            varchar(128),
   subtitle             varchar(128),
   url                  varchar(1024),
   alt                  varchar(1024),
   translator           varchar(256),
   publiser             varchar(128),
   pubdate              varchar(64),
   rating_max           varchar(16),
   rating_average       varchar(16),
   rating_numRaters     varchar(16),
   rating_min           varchar(16),
   tags                 varchar(1024),
   binding              varchar(64),
   price                varchar(64),
   series               varchar(256),
   pages                varchar(16),
   author_intro         varchar(2048),
   summary              text,
   catalog              varchar(256),
   ebook_url            varchar(1024),
   ebook_price          varchar(16),
   primary key (id)
);

alter table t_book comment '后面的英文栏位，是看到原版本设计中有这些，所以保留了下来，不知道原版本是什么考虑';

/*==============================================================*/
/* Table: t_group                                               */
/*==============================================================*/
create table t_group
(
   id                   bigint(20) not null auto_increment,
   create_time          datetime,
   update_time          datetime,
   begin_time           datetime,
   end_time             datetime,
   guarantee            integer,
   frequency            varchar(20),
   latest_time          datetime,
   book_id_fk           varchar(36),
   read_slogan          varchar(2014),
   group_desc           varchar(144),
   captain_brief        varchar(144),
   user_id_fk           bigint(20) comment '用户表主键',
   book_brief           varchar(200),
   state                char(1) comment '0为已结束，1为进行中，2为未开始',
   group_mode           varchar(50),
   primary key (id)
);

alter table t_group comment '小组信息表，所读书籍为书籍表主键，领读人为用户表主键，小组状态分为未开始，小组模式是原来数据库结构中存在的 不知道什么用';

/*==============================================================*/
/* Table: t_group_user                                          */
/*==============================================================*/
create table t_group_user
(
   id                   bigint(20) not null auto_increment,
   group_id_fk          bigint(20),
   user_id_fk           bigint(20),
   create_time          datetime,
   update_time          datetime,
   user_state           char(1) comment '0为退出小组,1为在小组内,2为已读完',
   user_class           char(1) comment '1为发起人，0为非发起人',
   primary key (id)
);

alter table t_group_user comment '该表为小组和用户的关系表，用户状态0为退出，1为在组内，2为已经读完。类别为1的为发起人，0为小组成员';

/*==============================================================*/
/* Table: t_interact                                            */
/*==============================================================*/
create table t_interact
(
   id                   bigint(20) not null auto_increment,
   create_time          datetime,
   update_time          datetime,
   content              varchar(1024) comment '若该项类型为 赞 则这里为空',
   user_id_fk           bigint(20),
   primary key (id)
);

alter table t_interact comment '书评具体的互动信息，若为赞，则评论内容为空';

/*==============================================================*/
/* Table: t_picture                                             */
/*==============================================================*/
create table t_picture
(
   id                   int not null auto_increment,
   url                  varchar(1024),
   create_time          datetime,
   update_time          datetime,
   id_fk                bigint(20),
   type                 char(2) comment '1 为 书评图片，对应外键为书评外键，2 为 书/小组图片 ，3 为社群图片',
   primary key (id)
);

/*==============================================================*/
/* Table: t_remark                                              */
/*==============================================================*/
create table t_remark
(
   id                   bigint(20) not null auto_increment,
   create_time          datetime,
   update_time          datetime,
   group_id_fk          bigint(20),
   book_id_fk           bigint(20),
   user_id_fk           bigint(20),
   start_page           integer,
   end_page             integer,
   comment              Text,
   primary key (id)
);

alter table t_remark comment '2016-03-10 当前的书评查询按照小组来查询，以后出现多个小组读一本书的时候再细化。该表同时作为打卡记录的功能表。';

/*==============================================================*/
/* Table: t_remark_interact                                     */
/*==============================================================*/
create table t_remark_interact
(
   id                   bigint(20) not null auto_increment,
   remark_id_fk         bigint(20),
   interact_id_fk       bigint(20),
   type                 char(1),
   create_time          datetime,
   update_time          datetime,
   state                char(1) comment '0为未读，1为已读',
   primary key (id)
);

/*==============================================================*/
/* Table: t_score                                               */
/*==============================================================*/
create table t_score
(
   id                   bigint(20) not null auto_increment,
   create_time          datetime,
   update_time          datetime,
   score                bigint(6),
   user_id_fk           bigint(20),
   group_id_fk          bigint(20),
   primary key (id)
);

alter table t_score comment '更新积分时，可以每次插入一条新积分，或者唯一更新某一小组的积分数据';

/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
create table t_user
(
   id                   bigint(20) not null auto_increment,
   create_time          datetime,
   update_time          datetime,
   user_id              bigint(20) comment '不走微信渠道时候可用',
   openid               varchar(50) comment '微信openid',
   nickname             varchar(20),
   gender               char(1) comment '1代表男2代表女',
   interests            varchar(24),
   intro                varchar(144),
   city                 varchar(32),
   province             varchar(32),
   school               varchar(22),
   occupation           varchar(20),
   constellation        char(4),
   affective_state      char(1) comment '0为单身,1为恋爱,2为已婚',
   pic_id_fk            bigint(20),
   primary key (id)
);

alter table t_user comment '存储用户的个人信息';

/*==============================================================*/
/* Table: t_user_like                                           */
/*==============================================================*/
create table t_user_like
(
   id                   bigint(20) not null,
   user_id_fk           bigint(20),
   book_id_fk           bigint(20),
   create_time          datetime,
   update_time          datetime,
   state                char(1) comment '0为失效，1为可用',
   primary key (id)
);

alter table t_user_like comment '用于存储用户个人信息中，最喜欢的三本书和最想看的三本书，状态为0代表用户移除了这本书';

/*==============================================================*/
/* Table: t_user_message                                        */
/*==============================================================*/
create table t_user_message
(
   id                   bigint(20) not null,
   user_id_fk           bigint(20),
   content              varchar(2014),
   url                  varchar(2014),
   is_check             char(1),
   create_time          datetime,
   update_time          datetime,
   primary key (id)
);

alter table t_user_message comment '公众号订阅用户推送';

