
CREATE TABLE `sys_menu` (
  `id` bigint(19) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL,
  `url` varchar(100) DEFAULT NULL,
  `open_mode` varchar(32) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `icon` varchar(32) DEFAULT NULL,
  `pid` bigint(19) DEFAULT NULL,
  `seq` tinyint(2) NOT NULL DEFAULT '0',
  `status` tinyint(2) NOT NULL DEFAULT '0' ,
  `menu_type` tinyint(2) NOT NULL DEFAULT '0',
  `is_leaf` tinyint(1) DEFAULT '0',
  `resource_id` bigint(20) DEFAULT NULL ,
  `del_flag` tinyint(1) DEFAULT '0',
  `update_time` datetime DEFAULT NULL,
  `create_time` datetime NOT NULL ,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=250 DEFAULT CHARSET=utf8 COMMENT='资源';