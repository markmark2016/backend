/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/3/11 10:57:07                           */
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
   apply_state          char(1) comment '0Ϊδ��ˣ�1Ϊ��ͨ����',
   primary key (id)
);

/*==============================================================*/
/* Table: t_association                                         */
/*==============================================================*/
create table t_association
(
   id                   bigint(20) not null auto_increment,
   create_time          datetime,
   update_time          datetime,
   name                 varchar(30),
   state                char(1),
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
   state                char(1) comment '0Ϊ������1Ϊ����',
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

alter table t_book comment '�鼮��Ϣ��';

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
   user_id_fk           bigint(20) comment '�û�������',
   book_brief           varchar(200),
   state                char(1) comment '0Ϊ�ѽ�����1Ϊ�����У�2Ϊδ��ʼ',
   group_mode           varchar(50),
   primary key (id)
);

alter table t_group comment '����С���';

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
   user_state           char(1) comment '0Ϊ�˳�С��,1Ϊ��С����,2Ϊ�Ѷ���',
   user_class           char(1) comment '1Ϊ�����ˣ�0Ϊ�Ƿ�����',
   primary key (id)
);

/*==============================================================*/
/* Table: t_interact                                            */
/*==============================================================*/
create table t_interact
(
   id                   bigint(20) not null auto_increment,
   create_time          datetime,
   update_time          datetime,
   content              varchar(1024) comment '����������Ϊ �� ������Ϊ��',
   user_id_fk           bigint(20),
   primary key (id)
);

/*==============================================================*/
/* Table: t_picture                                             */
/*==============================================================*/
create table t_picture
(
   id                   int not null auto_increment,
   url                  varchar(1024),
   create_time          datetime,
   update_time          datetime,
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

alter table t_remark comment '2016-03-10 ��ǰ��������ѯ����С������ѯ���Ժ���ֶ��С���һ�����ʱ����ϸ��';

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
   primary key (id)
);

/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
create table t_user
(
   id                   bigint(20) not null auto_increment,
   create_time          datetime,
   update_time          datetime,
   user_id              bigint(20) comment '����΢������ʱ�����',
   openid               varchar(50) comment '΢��openid',
   nickname             varchar(20),
   gender               char(1) comment '1������2����Ů',
   interests            varchar(24),
   intro                varchar(144),
   city                 varchar(32),
   province             varchar(32),
   school               varchar(22),
   occupation           varchar(20),
   constellation        char(4),
   affective_state      char(1) comment '0Ϊ����,1Ϊ����,2Ϊ�ѻ�',
   pic_id_fk            bigint(20),
   primary key (id)
);

alter table t_user comment '�û���Ϣ��';

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
   state                char(1) comment '0ΪʧЧ��1Ϊ����',
   primary key (id)
);

alter table t_user_like comment '�û���ϲ�����飬���û����뿴����';

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

alter table t_user_message comment '���ںŶ����û�����';

