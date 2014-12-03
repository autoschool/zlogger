DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `commentaries`;
DROP TABLE IF EXISTS `posts`;
DROP TABLE IF EXISTS `walls`;
DROP TABLE IF EXISTS `persistent_logins`;
DROP TABLE IF EXISTS `users`;
DROP TABLE IF EXISTS `acl_sid`;
DROP TABLE IF EXISTS `acl_class`;
DROP TABLE IF EXISTS `acl_object_identity`;
DROP TABLE IF EXISTS `acl_entry`;

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

---
--- ACL tables
---

CREATE TABLE acl_sid (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    principal BOOLEAN NOT NULL,
    sid VARCHAR(100) NOT NULL,
    UNIQUE KEY unique_acl_sid (sid, principal)
) DEFAULT CHARSET=utf8;

CREATE TABLE acl_class (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    class VARCHAR(100) NOT NULL,
    UNIQUE KEY uk_acl_class (class)
) DEFAULT CHARSET=utf8;

CREATE TABLE acl_object_identity (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    object_id_class BIGINT UNSIGNED NOT NULL,
    object_id_identity BIGINT NOT NULL,
    parent_object BIGINT UNSIGNED,
    owner_sid BIGINT UNSIGNED,
    entries_inheriting BOOLEAN NOT NULL,
    UNIQUE KEY uk_acl_object_identity (object_id_class, object_id_identity),
    CONSTRAINT fk_acl_object_identity_parent FOREIGN KEY (parent_object) REFERENCES acl_object_identity (id),
    CONSTRAINT fk_acl_object_identity_class FOREIGN KEY (object_id_class) REFERENCES acl_class (id),
    CONSTRAINT fk_acl_object_identity_owner FOREIGN KEY (owner_sid) REFERENCES acl_sid (id)
) DEFAULT CHARSET=utf8;

CREATE TABLE acl_entry (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
    acl_object_identity BIGINT UNSIGNED NOT NULL,
    ace_order INTEGER NOT NULL,
    sid BIGINT UNSIGNED NOT NULL,
    mask INTEGER UNSIGNED NOT NULL,
    granting BOOLEAN NOT NULL,
    audit_success BOOLEAN NOT NULL,
    audit_failure BOOLEAN NOT NULL,
    UNIQUE KEY unique_acl_entry (acl_object_identity, ace_order),
    CONSTRAINT fk_acl_entry_object FOREIGN KEY (acl_object_identity) REFERENCES acl_object_identity (id),
    CONSTRAINT fk_acl_entry_acl FOREIGN KEY (sid) REFERENCES acl_sid (id)
) DEFAULT CHARSET=utf8;

--
-- data
--

INSERT INTO `users` VALUES ('testAdmin', '5f4dcc3b5aa765d61d8327deb882cf99', 1);
INSERT INTO `users` VALUES ('testUser', '5f4dcc3b5aa765d61d8327deb882cf99', 1);

INSERT INTO `authorities` VALUES ('testAdmin', 'ROLE_ADMIN');
INSERT INTO `authorities` VALUES ('testUser', 'ROLE_USER');

INSERT INTO `walls` VALUES (1,'testAdmin');
INSERT INTO `walls` VALUES (2,'testUser');

INSERT INTO `posts` VALUES (1,'test title','test message 2','2014-11-24 15:06:39','testAdmin', 1);
INSERT INTO `posts` VALUES (2,'test title','test message 1','2014-11-24 15:06:36','testUser', 2);
INSERT INTO `posts` VALUES (3,'test title','test message 3','2014-11-24 15:06:40','testUser', 2);

INSERT INTO `commentaries` VALUES (1,'test message 1','2014-11-24 15:06:40','testAdmin', 1);
INSERT INTO `commentaries` VALUES (2,'test message 2','2014-11-24 15:06:40','testAdmin', 1);

INSERT INTO `acl_sid` VALUES (1, 0, 'ROLE_ADMIN'), (2, 0, 'ROLE_USER'), (3, 1, 'testAdmin'), (4, 1, 'testUser'),
    (5, 0, 'ROLE_ANONYMOUS');
INSERT INTO `acl_class` VALUES (1, 'zlogger.logic.models.Post'), (2, 'zlogger.logic.models.Commentary'),
(3, 'zlogger.logic.models.User'), (4, 'zlogger.logic.models.Wall');
INSERT INTO `acl_object_identity` VALUES (1, 1, 1, null, 3, 0), (2, 1, 2, null, 4, 0), (3, 1, 3, null, 4, 0),
  (4, 2, 1, null, 3, 0), (5, 2, 2, null, 3, 0), (6, 3, 1, null, 3, 0), (7, 3, 2, null, 4, 0),
  (8, 4, 1, null, 3, 0), (9, 4, 2, null, 4, 0);
INSERT INTO `acl_entry` VALUES
  (1, 1, 2, 1, 2, 1, 1, 1), (2, 4, 2, 1, 2, 1, 1, 1), (3, 6, 1, 1, 1, 1, 1, 1), (4, 6, 2, 1, 2, 1, 1, 1), (5, 8, 2, 1, 2, 1, 1, 1),
  (6, 2, 2, 1, 2, 1, 1, 1), (7, 5, 2, 1, 2, 1, 1, 1), (8, 7, 1, 1, 1, 1, 1, 1), (9, 7, 2, 1, 2, 1, 1, 1), (10, 9, 2, 1, 2, 1, 1, 1),
  (11, 3, 2, 1, 2, 1, 1, 1),
  (12, 1, 1, 2, 1, 1, 1, 1), (13, 4, 1, 2, 1, 1, 1, 1), (14, 8, 1, 2, 1, 1, 1, 1),
  (15, 2, 1, 2, 1, 1, 1, 1), (16, 5, 1, 2, 1, 1, 1, 1), (17, 9, 1, 2, 1, 1, 1, 1),
  (18, 3, 1, 2, 1, 1, 1, 1),
  (19, 1, 3, 3, 2, 1, 1, 1), (20, 4, 3, 3, 2, 1, 1, 1), (21, 6, 3, 3, 1, 1, 1, 1), (22, 6, 4, 3, 2, 1, 1, 1), (23, 8, 3, 3, 2, 1, 1, 1),
  (24, 2, 3, 4, 2, 1, 1, 1), (25, 5, 3, 4, 2, 1, 1, 1), (26, 7, 3, 3, 1, 1, 1, 1), (27, 7, 4, 4, 2, 1, 1, 1), (28, 9, 3, 4, 2, 1, 1, 1),
  (29, 3, 3, 4, 2, 1, 1, 1),
  (30, 1, 4, 5, 1, 1, 1, 1), (31, 8, 4, 5, 1, 1, 1, 1);