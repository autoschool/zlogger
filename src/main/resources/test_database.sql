DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `commentaries`;
DROP TABLE IF EXISTS `posts`;
DROP TABLE IF EXISTS `walls`;
DROP TABLE IF EXISTS `users`;

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`username`)
) DEFAULT CHARSET=utf8;

--
-- Table structure for table `walls`
--

CREATE TABLE `walls` (
  `wall_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `owner_name` varchar(50) NOT NULL,
  PRIMARY KEY (`wall_id`),
  CONSTRAINT `FK_walls_owner_name` FOREIGN KEY (`owner_name`) REFERENCES `users` (`username`)
) AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Table structure for table `authorities`
--

CREATE TABLE `authorities` (
  `authority` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  PRIMARY KEY (`username`),
  CONSTRAINT `FK_authorities_username` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) DEFAULT CHARSET=utf8;

--
-- Table structure for table `posts`
--

CREATE TABLE `posts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `creation_date` timestamp NOT NULL,
  `message` varchar(2000) NOT NULL,
  `title` varchar(255) DEFAULT NULL,
  `creator_name` varchar(50) NOT NULL,
  `wall_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_posts_creator_name` FOREIGN KEY (`creator_name`) REFERENCES `users` (`username`),
  CONSTRAINT `FK_posts_wall_id` FOREIGN KEY (`wall_id`) REFERENCES `walls` (`wall_id`)
) AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Table structure for table `commentaries`
--

CREATE TABLE `commentaries` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `creation_date` timestamp NOT NULL,
  `message` varchar(2000) NOT NULL,
  `creator_name` varchar(50) NOT NULL,
  `post_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK_commentaries_creator_name` FOREIGN KEY (`creator_name`) REFERENCES `users` (`username`),
  CONSTRAINT `FK_commentaries_post_id` FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`) ON DELETE CASCADE
) AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- data
--

INSERT INTO `users` VALUES ('testAdmin', 1, 'password');
INSERT INTO `users` VALUES ('testUser', 1, 'password');

INSERT INTO `authorities` VALUES ('ROLE_ADMIN', 'testAdmin');
INSERT INTO `authorities` VALUES ('ROLE_USER', 'testUser');

INSERT INTO `walls` VALUES (1,'testUser');

INSERT INTO `posts` VALUES (1,'2014-11-24 15:06:36','test message 1','test title','testUser', 1);
INSERT INTO `posts` VALUES (2,'2014-11-24 15:06:39','test message 2','test title','testAdmin', 1);
INSERT INTO `posts` VALUES (3,'2014-11-24 15:06:40','test message 3','test title','testUser', 1);

INSERT INTO `commentaries` VALUES (1,'2014-11-24 15:06:40','test message 1','testAdmin', 1);