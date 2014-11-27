DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `commentaries`;
DROP TABLE IF EXISTS `posts`;
DROP TABLE IF EXISTS `walls`;
DROP TABLE IF EXISTS `persistent_logins`;
DROP TABLE IF EXISTS `users`;

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
      `username` varchar_ignorecase(50) NOT NULL PRIMARY KEY,
      `password` varchar_ignorecase(50) NOT NULL,
      `enabled` boolean NOT NULL
) DEFAULT CHARSET=utf8;

--
-- Table structure for table `walls`
--

CREATE TABLE `walls` (
  `wall_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `owner_name` varchar(50) NOT NULL,
  PRIMARY KEY (`wall_id`),
  CONSTRAINT `fk_walls_owner_name` FOREIGN KEY (`owner_name`) REFERENCES `users` (`username`) ON DELETE CASCADE
) AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Table structure for table `authorities`
--

CREATE TABLE `authorities` (
      `username` varchar_ignorecase(50) NOT NULL,
      `authority` varchar_ignorecase(50) NOT NULL,
      CONSTRAINT `fk_authorities_users` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE CASCADE
)  DEFAULT CHARSET=utf8;
CREATE UNIQUE index `ix_auth_username` ON authorities (username,authority);

--
-- Table structure for table `posts`
--

CREATE TABLE `posts` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `message` varchar(2000) NOT NULL,
  `creation_date` timestamp NOT NULL,
  `creator_name` varchar(50) NOT NULL,
  `wall_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_posts_creator_name` FOREIGN KEY (`creator_name`) REFERENCES `users` (`username`) ON DELETE CASCADE,
  CONSTRAINT `fk_posts_wall_id` FOREIGN KEY (`wall_id`) REFERENCES `walls` (`wall_id`) ON DELETE CASCADE
) AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

--
-- Table structure for table `commentaries`
--

CREATE TABLE `commentaries` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `message` varchar(2000) NOT NULL,
  `creation_date` timestamp NOT NULL,
  `creator_name` varchar(50) NOT NULL,
  `post_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_commentaries_creator_name` FOREIGN KEY (`creator_name`) REFERENCES `users` (`username`) ON DELETE CASCADE,
  CONSTRAINT `fk_commentaries_post_id` FOREIGN KEY (`post_id`) REFERENCES `posts` (`id`) ON DELETE CASCADE
) AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

---
--- Table structure for table `persistent_logins`
---

CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) PRIMARY KEY,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL
) DEFAULT CHARSET=utf8;

--
-- data
--

INSERT INTO `users` VALUES ('testAdmin', 'password', 1);
INSERT INTO `users` VALUES ('testUser', 'password', 1);

INSERT INTO `authorities` VALUES ('testAdmin', 'ROLE_ADMIN');
INSERT INTO `authorities` VALUES ('testUser', 'ROLE_USER');

INSERT INTO `walls` VALUES (1,'testUser');

INSERT INTO `posts` VALUES (1,'test title','test message 1','2014-11-24 15:06:36','testUser', 1);
INSERT INTO `posts` VALUES (2,'test title','test message 2','2014-11-24 15:06:39','testAdmin', 1);
INSERT INTO `posts` VALUES (3,'test title','test message 3','2014-11-24 15:06:40','testUser', 1);

INSERT INTO `commentaries` VALUES (1,'test message 1','2014-11-24 15:06:40','testAdmin', 1);