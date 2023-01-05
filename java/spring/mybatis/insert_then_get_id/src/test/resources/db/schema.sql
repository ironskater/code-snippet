DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `username` varchar(20) NOT NULL,
    `password` varchar(20) NOT NULL,

    PRIMARY KEY (`id`),
    INDEX `user_index`(`username`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb4;
