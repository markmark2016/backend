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
   name                 varchar(30) comment '��Ⱥ��',
   status               char(1) comment '��Ⱥ״̬,0ͣ�ã�1����',
   user_id_fk           bigint(20) comment '��Ⱥӵ����,t_user����',
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
   association_id_fk    bigint(20) comment 't_association����',
   group_id_fk          bigint(20) comment 't_group����',
   create_time          datetime,
   update_time          datetime,
   status               char(1) comment '��Ⱥ��С��Ĺ�ϵ״̬��0���������ˣ�1������',
   primary key (id)
);

alter table t_association_group comment '��Ⱥ��С���ϵ��';

/*==============================================================*/
/* Table: t_book                                                */
/*==============================================================*/
create table t_book
(
   id                   bigint(20) not null auto_increment,
   create_time          datetime,
   update_time          datetime,
   title                varchar(128) comment '����',
   author               varchar(256) comment '����',
   pic_id_fk            varchar(1024) comment '���ͼƬ��ַ����Ӧt_pic�������',
   image                varchar(1024),
   status               char(1) comment '���״̬��0Ϊ������1Ϊ����',
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

alter table t_book comment '�����Ӣ����λ���ǿ���ԭ�汾���������Щ�����Ա�������������֪��ԭ�汾��ʲô����';

/*==============================================================*/
/* Table: t_group                                               */
/*==============================================================*/
create table t_group
(
   id                   bigint(20) not null auto_increment,
   create_time          datetime,
   update_time          datetime,
   begin_time           datetime comment 'С�鿪ʼʱ��',
   end_time             datetime comment 'С�����ʱ��',
   group_name           varchar(200),
   guarantee            integer comment '��֤��',
   frequency            varchar(20) comment '��Ƶ��',
   latest_time          datetime comment '�������ʱ��',
   book_id_fk           varchar(36) comment 'С������ͼ�飬�鼮�����',
   book_brief           varchar(200) comment 'ͼ����',
   book_name            varchar(50),
   read_slogan          varchar(2014) comment '�������',
   group_desc           varchar(144) comment 'С����',
   captain_brief        varchar(144) comment '����˼��',
   user_id_fk           bigint(20) comment '����˶�Ӧ���û����е�����',
   captain_email        varchar(250),
   captain_wecode       varchar(100),
   captain_phone        varchar(22),
   group_mode           varchar(50) comment 'С��ģʽ',
   status               char(1) comment 'С���״̬��0Ϊδ��ˣ�1Ϊ���ͨ����2Ϊδ��ʼ,3Ϊ������,4Ϊ�ѽ���',
   primary key (id)
);

alter table t_group comment 'С����Ϣ�������鼮Ϊ�鼮�������������Ϊ�û���������С��״̬��Ϊδ��ʼ��С��ģʽ��ԭ�����ݿ�ṹ�д��ڵ� ��֪��ʲô��';

/*==============================================================*/
/* Table: t_group_user                                          */
/*==============================================================*/
create table t_group_user
(
   id                   bigint(20) not null auto_increment,
   group_id_fk          bigint(20) comment 'С������',
   user_id_fk           bigint(20) comment '�û������',
   create_time          datetime,
   update_time          datetime,
   user_status          char(1) comment '��Ա��С���е�״̬��0Ϊ�˳�С��,1Ϊ��С����,2Ϊ�Ѷ���',
   user_class           char(1) comment '��Ա�����1Ϊ�����ˣ�0Ϊ�Ƿ�����',
   primary key (id)
);

alter table t_group_user comment '�ñ�ΪС����û��Ĺ�ϵ���û�״̬0Ϊ�˳���1Ϊ�����ڣ�2Ϊ�Ѿ����ꡣ���Ϊ1��Ϊ�����ˣ�0ΪС���Ա';

/*==============================================================*/
/* Table: t_interact                                            */
/*==============================================================*/
create table t_interact
(
   id                   bigint(20) not null auto_increment,
   create_time          datetime,
   update_time          datetime,
   content              varchar(1024) comment '����������Ϊ �� ������Ϊ��',
   user_id_fk           bigint(20) comment 't_user����',
   primary key (id)
);

alter table t_interact comment '��������Ļ�����Ϣ����Ϊ�ޣ�����������Ϊ��';

/*==============================================================*/
/* Table: t_picture                                             */
/*==============================================================*/
create table t_picture
(
   id                   int not null auto_increment,
   url                  varchar(1024),
   create_time          datetime,
   update_time          datetime,
   id_fk                bigint(20) comment '����type�������',
   type                 char(2) comment '1 Ϊ t_remak������2 Ϊt_book����(С���ͼƬ�������ͼƬ)��3 Ϊt_association����',
   primary key (id)
);

alter table t_picture comment '�ñ�Ŀǰ�洢����ͼƬ�ĵ�ַ��1����ͼƬ��2��/С��ͼƬ��3��ȺͼƬ';

/*==============================================================*/
/* Table: t_remark                                              */
/*==============================================================*/
create table t_remark
(
   id                   bigint(20) not null auto_increment,
   create_time          datetime,
   update_time          datetime,
   group_id_fk          bigint(20) comment 't_group����',
   book_id_fk           bigint(20) comment 't_book����',
   user_id_fk           bigint(20) comment 't_user����',
   start_page           integer comment '���δ�/������ʼҳ��',
   end_page             integer comment '���δ�/��������ҳ��',
   comment              Text comment '���δ�/���� ��������',
   primary key (id)
);

alter table t_remark comment '2016-03-10 ��ǰ��������ѯ����С������ѯ���Ժ���ֶ��С���һ�����ʱ����ϸ�����ñ�ͬʱ��Ϊ�򿨼�¼�Ĺ��ܱ�';

/*==============================================================*/
/* Table: t_remark_interact                                     */
/*==============================================================*/
create table t_remark_interact
(
   id                   bigint(20) not null auto_increment,
   remark_id_fk         bigint(20) comment 't_remark����',
   interact_id_fk       bigint(20) comment 't_interact����',
   type                 char(1) comment '�������ͣ�1Ϊ���ۣ�2Ϊ��',
   create_time          datetime,
   update_time          datetime,
   status               char(1) comment '�Ļ�����¼״̬��0Ϊδ����1Ϊ�Ѷ�',
   primary key (id)
);

alter table t_remark_interact comment '�������������� ��ϵ��';

/*==============================================================*/
/* Table: t_score                                               */
/*==============================================================*/
create table t_score
(
   id                   bigint(20) not null auto_increment,
   create_time          datetime,
   update_time          datetime,
   score                bigint(6) comment '����',
   user_id_fk           bigint(20) comment 't_user����',
   group_id_fk          bigint(20) comment 't_group����',
   primary key (id)
);

alter table t_score comment '���»���ʱ������ÿ�β���һ���»��֣�����Ψһ����ĳһС��Ļ�������';

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
   nickname             varchar(20) comment '�ǳ�',
   gender               char(1) comment '1������2����Ů',
   interests            varchar(24) comment '��Ȥ����',
   intro                varchar(144) comment '����
            ',
   city                 varchar(32) comment '����',
   province             varchar(32) comment 'ʡ��',
   school               varchar(22) comment 'ѧУ',
   occupation           varchar(20) comment 'ְҵ',
   constellation        char(4) comment '����',
   affective_status     char(1) comment '���״̬��0Ϊ����,1Ϊ����,2Ϊ�ѻ�',
   head_img_url         varchar(1024) comment 'ͷ���ַ��pic������',
   primary key (id)
);

alter table t_user comment '�洢�û��ĸ�����Ϣ';

/*==============================================================*/
/* Table: t_user_like                                           */
/*==============================================================*/
create table t_user_like
(
   id                   bigint(20) not null,
   user_id_fk           bigint(20) comment '�û�������',
   book_id_fk           bigint(20) comment '�鼮������',
   create_time          datetime,
   update_time          datetime,
   type                 char(1) comment '�û�����ϴ�����ͣ�1Ϊ��ϲ�����飬2Ϊ���������',
   status               char(1) comment '�û�������Ĳ�����������Ϊ1���Ƴ�Ϊ0.0ΪʧЧ��1Ϊ����',
   primary key (id)
);

alter table t_user_like comment '���ڴ洢�û�������Ϣ�У���ϲ��������������뿴�������飬״̬Ϊ0�����û��Ƴ����Ȿ��';

/*==============================================================*/
/* Table: t_user_message                                        */
/*==============================================================*/
create table t_user_message
(
   id                   bigint(20) not null,
   user_id_fk           bigint(20) comment '���͵����û���t_user����',
   content              varchar(2014) comment '���;�������',
   url                  varchar(2014) comment '��ַ',
   is_check             char(1) comment '�Ƿ񱻲鿴',
   create_time          datetime,
   update_time          datetime,
   primary key (id)
);

alter table t_user_message comment '���ںŶ����û�����';

