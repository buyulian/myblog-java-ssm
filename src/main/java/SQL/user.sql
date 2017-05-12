

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(255) default NULL COMMENT '用户名',
  `name` varchar(255) default NULL COMMENT '用户名',
  `role` varchar(25) default NULL COMMENT '角色',
  `password` varchar(32) default NULL COMMENT '加盐后用户密码',
  `salt` varchar(6) default NULL COMMENT 'MD5盐',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户登录表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '赵大宝','admin', '05126a423a9379d529e4ee61a212fa55', 'KJUYT5');
INSERT INTO `user` VALUES ('2', '张三丰','admin', '98bd3a1bebde01ad363d3c5a0d1e56da', '656JHU');
INSERT INTO `user` VALUES ('3', '王尼玛','admin', '5470db9b63c354f6c8d628b80ae2f3c3', '89UIKQ');
