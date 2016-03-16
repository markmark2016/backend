/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/3/16 10:48:11                           */
/*==============================================================*/


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
/* Table: t_association                                         */
/*==============================================================*/
create table t_association
(
   id                   bigint(20) not null auto_increment,
   create_time          datetime,
   update_time          datetime,
   name                 varchar(30) comment '社群名',
   status               char(1) comment '社群状态,0停用，1启用',
   user_id_fk           bigint(20) comment '社群拥有者,t_user主键',
   association_desc     varchar(500),
   slogan               varchar(100),
   primary key (id)
);

alter table t_association comment 't_association';

/*==============================================================*/
/* Table: t_association_group                                   */
/*==============================================================*/
create table t_association_group
(
   id                   bigint(20) not null auto_increment,
   association_id_fk    bigint(20) comment 't_association主键',
   group_id_fk          bigint(20) comment 't_group主键',
   create_time          datetime,
   update_time          datetime,
   status               char(1) comment '社群与小组的关系状态，0不在组内了，1在组内',
   primary key (id)
);

alter table t_association_group comment '社群与小组关系表';

/*==============================================================*/
/* Table: t_book                                                */
/*==============================================================*/
create table t_book
(
   id                   bigint(20) not null auto_increment,
   create_time          datetime,
   update_time          datetime,
   title                varchar(128) comment '书名',
   author               varchar(256) comment '作者',
   pic_id_fk            varchar(1024) comment '书的图片地址，对应t_pic表的主键',
   image                varchar(1024),
   status               char(1) comment '书的状态，0为锁定，1为可用',
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
   begin_time           datetime comment '小组开始时间',
   end_time             datetime comment '小组结束时间',
   group_name           varchar(200),
   guarantee            integer comment '保证金',
   frequency            varchar(20) comment '打卡频率',
   latest_time          datetime comment '最晚加入时间',
   book_id_fk           varchar(36) comment '小组所读图书，书籍表外键',
   book_brief           varchar(200) comment '图书简介',
   book_name            varchar(50),
   read_slogan          varchar(2014) comment '读书标语',
   group_desc           varchar(144) comment '小组简介',
   captain_brief        varchar(144) comment '领读人简介',
   user_id_fk           bigint(20) comment '领读人对应在用户表中的主键',
   captain_email        varchar(250),
   captain_wecode       varchar(100),
   captain_phone        varchar(22),
   group_mode           varchar(50) comment '小组模式',
   status               char(1) comment '小组的状态。0为未审核，1为审核通过，2为未开始,3为进行中,4为已结束',
   primary key (id)
);

alter table t_group comment '小组信息表，所读书籍为书籍表主键，领读人为用户表主键，小组状态分为未开始，小组模式是原来数据库结构中存在的 不知道什么用';

/*==============================================================*/
/* Table: t_group_user                                          */
/*==============================================================*/
create table t_group_user
(
   id                   bigint(20) not null auto_increment,
   group_id_fk          bigint(20) comment '小组表外键',
   user_id_fk           bigint(20) comment '用户表外键',
   create_time          datetime,
   update_time          datetime,
   user_status          char(1) comment '成员在小组中的状态。0为退出小组,1为在小组内,2为已读完',
   user_class           char(1) comment '成员的类别。1为发起人，0为非发起人',
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
   user_id_fk           bigint(20) comment 't_user主键',
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
   id_fk                bigint(20) comment '根据type具体而定',
   type                 char(2) comment '1 为 t_remak主键，2 为t_book主键(小组的图片就是书的图片)，3 为t_association主键',
   primary key (id)
);

alter table t_picture comment '该表目前存储三类图片的地址，1书评图片，2书/小组图片，3社群图片';

/*==============================================================*/
/* Table: t_remark                                              */
/*==============================================================*/
create table t_remark
(
   id                   bigint(20) not null auto_increment,
   create_time          datetime,
   update_time          datetime,
   group_id_fk          bigint(20) comment 't_group主键',
   book_id_fk           bigint(20) comment 't_book主键',
   user_id_fk           bigint(20) comment 't_user主键',
   start_page           integer comment '本次打卡/书评开始页码',
   end_page             integer comment '本次打卡/书评结束页码',
   comment              Text comment '本次打卡/书评 具体内容',
   primary key (id)
);

alter table t_remark comment '2016-03-10 当前的书评查询按照小组来查询，以后出现多个小组读一本书的时候再细化。该表同时作为打卡记录的功能表。';

/*==============================================================*/
/* Table: t_remark_interact                                     */
/*==============================================================*/
create table t_remark_interact
(
   id                   bigint(20) not null auto_increment,
   remark_id_fk         bigint(20) comment 't_remark主键',
   interact_id_fk       bigint(20) comment 't_interact主键',
   type                 char(1) comment '互动类型，1为评论，2为赞',
   create_time          datetime,
   update_time          datetime,
   status               char(1) comment '改互动记录状态，0为未读，1为已读',
   primary key (id)
);

alter table t_remark_interact comment '书评与书评互动 关系表';

/*==============================================================*/
/* Table: t_score                                               */
/*==============================================================*/
create table t_score
(
   id                   bigint(20) not null auto_increment,
   create_time          datetime,
   update_time          datetime,
   score                bigint(6) comment '积分',
   user_id_fk           bigint(20) comment 't_user主键',
   group_id_fk          bigint(20) comment 't_group主键',
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
   nickname             varchar(20) comment '昵称',
   gender               char(1) comment '1代表男2代表女',
   interests            varchar(24) comment '兴趣领域',
   intro                varchar(144) comment '描述
            ',
   city                 varchar(32) comment '城市',
   province             varchar(32) comment '省份',
   school               varchar(22) comment '学校',
   occupation           varchar(20) comment '职业',
   constellation        char(4) comment '星座',
   affective_status     char(1) comment '情感状态。0为单身,1为恋爱,2为已婚',
   head_img_url         varchar(1024) comment '头像地址，pic表的外键',
   primary key (id)
);

alter table t_user comment '存储用户的个人信息';

/*==============================================================*/
/* Table: t_user_like                                           */
/*==============================================================*/
create table t_user_like
(
   id                   bigint(20) not null,
   user_id_fk           bigint(20) comment '用户表主键',
   book_id_fk           bigint(20) comment '书籍表主键',
   create_time          datetime,
   update_time          datetime,
   type                 char(1) comment '用户对书洗好类型，1为最喜欢的书，2为最想读的书',
   status               char(1) comment '用户读该书的操作，若加入为1，移除为0.0为失效，1为可用',
   primary key (id)
);

alter table t_user_like comment '用于存储用户个人信息中，最喜欢的三本书和最想看的三本书，状态为0代表用户移除了这本书';

/*==============================================================*/
/* Table: t_user_message                                        */
/*==============================================================*/
create table t_user_message
(
   id                   bigint(20) not null,
   user_id_fk           bigint(20) comment '推送到的用户，t_user主键',
   content              varchar(2014) comment '推送具体内容',
   url                  varchar(2014) comment '地址',
   is_check             char(1) comment '是否被查看',
   create_time          datetime,
   update_time          datetime,
   primary key (id)
);

alter table t_user_message comment '公众号订阅用户推送';

