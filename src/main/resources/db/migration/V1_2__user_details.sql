DROP TABLE IF EXISTS `user_details`;

CREATE TABLE `user_details` (
    `username` varchar(50) NOT NULL,
    `email` varchar (254),
    `about` varchar (500),
    `site` varchar (100),
    PRIMARY KEY (`username`),
    CONSTRAINT `fk_username` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE CASCADE
) DEFAULT charset=utf8;