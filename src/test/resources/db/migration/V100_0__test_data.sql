--
-- data
--

INSERT INTO `users` VALUES ('testAdmin', '5f4dcc3b5aa765d61d8327deb882cf99', 1);
INSERT INTO `users` VALUES ('testUser', '5f4dcc3b5aa765d61d8327deb882cf99', 1);

INSERT INTO `authorities` VALUES ('testAdmin', 'ROLE_ADMIN');
INSERT INTO `authorities` VALUES ('testUser', 'ROLE_USER');

INSERT INTO `walls` VALUES (2,'testAdmin');
INSERT INTO `walls` VALUES (3,'testUser');

INSERT INTO `posts` VALUES (2,'test title','test message 2','2014-11-24 15:06:39','testAdmin', 1);
INSERT INTO `posts` VALUES (3,'test title','test message 1','2014-11-24 15:06:36','testUser', 2);
INSERT INTO `posts` VALUES (4,'test title','test message 3','2014-11-24 15:06:40','testUser', 2);

INSERT INTO `commentaries` VALUES (1,'test message 1','2014-11-24 15:06:40','testAdmin', 1);
INSERT INTO `commentaries` VALUES (2,'test message 2','2014-11-24 15:06:40','testAdmin', 1);