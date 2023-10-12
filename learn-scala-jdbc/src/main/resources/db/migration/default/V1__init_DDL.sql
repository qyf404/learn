CREATE TABLE `t_user`(
`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
`full_name` varchar(200) NOT NULL  COMMENT '姓名',
`alias` JSON COMMENT '别名,例如:["张三","李四"]',
`citizenship` JSON  COMMENT '国籍,例如"["",""]',
`state` tinyint(2) NOT NULL DEFAULT 1 COMMENT '状态，1-有效，0-无效',
`gmt_created` datetime NOT NULL  COMMENT '入库时间',
`gmt_modified` datetime NOT NULL  COMMENT '更新时间',
PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='AML 名单表';