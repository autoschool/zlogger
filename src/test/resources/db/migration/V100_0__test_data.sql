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