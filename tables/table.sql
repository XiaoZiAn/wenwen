create table wenwen.email
(
    id           int auto_increment
    comment '自增id'
        primary key,
    sendTo       char(30)         not null
    comment '邮件收件人地址',
    sendDate     char(15)         not null
    comment '发送时间',
    emailContent varchar(1000)    not null
    comment '邮件内容',
    status       char default '0' not null
    comment '发送状态(1:已发送 0：未发送 2：发送失败 3：发送成功）',
    emailType    char(40)         not null
    comment '邮件类型',
    isBatch      char default '1' not null
    comment '是否参与批量发送（1：是 0：否）',
    emailTitle   char(50)         not null
    comment '邮件主题'
)
    comment '邮件表';
	
	
create table wenwen.emailtemplate
(
    id           int auto_increment
    comment '模板id'
        primary key,
    emailType    char(40)         not null
    comment '模板类型',
    emailContent varchar(1000)    not null
    comment '模板内容',
    status       char default '1' not null
    comment '是否启用（1：是 0：否）',
    emailTitle   char(30)         not null
    comment '邮件标题'
)
    comment '邮件模板表';

	
create table wenwen.`lock`
(
    id         int auto_increment
    comment '自增id'
        primary key,
    value      char(25)                            not null
    comment '加锁标志',
    sight      char(30)                            not null
    comment '加锁场景',
    createtime timestamp default CURRENT_TIMESTAMP not null
)
    comment '加锁表';
	
	
create table wenwen.person
(
    personId     varchar(20) default '0'             not null
    comment '用户ID'
        primary key,
    personName   varchar(20) default '0'             not null
    comment '用户姓名',
    sex          varchar(20)                         null
    comment '用户性别',
    birthday     varchar(20)                         null
    comment '用户生日',
    email        varchar(100)                        null
    comment '用户邮箱',
    password     varchar(60)                         not null
    comment '用户密码',
    createtime   timestamp default CURRENT_TIMESTAMP not null
    comment '注册日期',
    status       char default '0'                    not null
    comment '是否激活（1：是 0：否 2：账号被封)',
    sealedDays   int default '0'                     null
    comment '账号被封天数',
    activateCode char(50)                            not null
    comment '账户激活标识'
);
	comment '用户表';
	
	
create table wenwen.table_maxid
(
    id         int auto_increment
    comment '序号'
        primary key,
    tableName  char(15) null
    comment '表名',
    columnName char(20) null
    comment '列名',
    maxId      char(15) null
    comment '最大ID'
);
	comment '最大id表';