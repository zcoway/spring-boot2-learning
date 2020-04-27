CREATE DATABASE `bank1` CHARACTER SET 'utf8' COLLATE 'utf8_general_ci';
DROP TABLE IF EXISTS `account_info`;
CREATE TABLE `account_info`  (
                                 `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                 `account_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '户主姓名',
                                 `account_no` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '银行卡号',
                                 `account_password` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '帐户密码',
                                 `account_balance` double NULL DEFAULT NULL COMMENT '帐户余额',
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin ;
INSERT INTO `account_info` VALUES (2, '张三的账户', '1', '', 10000);


CREATE TABLE `de_duplication` (
`tx_no` varchar(64) COLLATE utf8_bin NOT NULL, `create_time` datetime(0) NULL DEFAULT NULL, PRIMARY KEY (`tx_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ;


CREATE TABLE `account_info2`(
                                 `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                 `account_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '户主姓名',
                                 `account_no` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '银行卡号',
                                 `account_password` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT '帐户密码',
                                 `account_balance` double NULL DEFAULT NULL COMMENT '帐户余额',
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_bin ;




CREATE TABLE `de_duplication2` (
`tx_no` varchar(64) COLLATE utf8_bin NOT NULL, `create_time` datetime(0) NULL DEFAULT NULL, PRIMARY KEY (`tx_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin ;

