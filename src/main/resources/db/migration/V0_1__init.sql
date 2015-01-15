DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `commentaries`;
DROP TABLE IF EXISTS `posts`;
DROP TABLE IF EXISTS `walls`;

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
      `username` varchar(50) NOT NULL PRIMARY KEY,
      `password` varchar(50) NOT NULL,
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