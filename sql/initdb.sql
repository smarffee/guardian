CREATE TABLE heart_beat(
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `metric_key` VARCHAR(256) NOT NULL COMMENT '监控项key',
  `create_time` INT(11) NOT NULL COMMENT '创建时间',
  `update_time` INT(11) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `metric_key` (`metric_key`) USING BTREE
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='监控项心跳表';

CREATE TABLE metric_group (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `gid` char(36) NOT NULL COMMENT '组唯一标识',
  `create_time` int(11) NOT NULL,
  `update_time` int(11) NOT NULL,
  `group_name` varchar(32) NOT NULL COMMENT '组名称',
  `remark` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name_union` (`group_name`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='监控项所属组表';

CREATE TABLE metric_sub_group (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `gid` char(36) NOT NULL COMMENT '子组唯一标识',
  `group_gid` char(36) NOT NULL COMMENT '关联父gid',
  `create_time` int(11) NOT NULL,
  `update_time` int(11) NOT NULL,
  `sub_group_name` varchar(32) NOT NULL COMMENT '子组名称',
  `remark` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `sub_name_union` (`sub_group_name`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='监控项所属子组表';


CREATE TABLE metric_item (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `gid` char(36) NOT NULL COMMENT '预警项唯一标识',
  `group_gid` char(36) NOT NULL COMMENT '关联所属组表',
  `sub_group_gid` char(36) NOT NULL COMMENT '关联所属子组表',
  `create_time` int(11) NOT NULL,
  `update_time` int(11) NOT NULL,
  `metric_key` varchar(250) NOT NULL COMMENT '预警项key',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '0:关闭; 1：启用',
  `description` varchar(256) DEFAULT NULL,
  `remark` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `metric_key_union` (`metric_key`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='监控项表';



CREATE TABLE metric_emergency_event (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `gid` char(36) NOT NULL COMMENT '预警项唯一标识',
  `group_gid` char(36) NOT NULL COMMENT '关联所属组表',
  `sub_group_gid` char(36) NOT NULL COMMENT '关联所属子组表',
  `create_time` int(11) NOT NULL,
  `update_time` int(11) NOT NULL,
  `metric_key` varchar(250) NOT NULL COMMENT '预警项key',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '告警状态 0:未处理; 1:处理中; 2:关闭',
  `level` int(1) NOT NULL DEFAULT '-1' COMMENT '告警级别',
  `description` varchar(512) DEFAULT NULL COMMENT '告警描述',
  `abnormal_num` int(11) NOT NULL DEFAULT 0 COMMENT '告警异常数据值',
  `abnormal_data` varchar(8192) DEFAULT NULL COMMENT '告警异常数据',
  `remark` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='监控项告警事件表';



