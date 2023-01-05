DROP TABLE IF EXISTS `user_info`;

CREATE TABLE `user_info`
(
    `id` bigint NOT NULL AUTO_INCREMENT,
    `name` varchar(50) NOT NULL DEFAULT '',
    `age` int(11) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `name_index`(`name`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4 COMMENT = 'User Info';
