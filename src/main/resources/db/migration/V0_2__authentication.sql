DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `persistent_logins`;

--
-- Table structure for table `authorities`
--

CREATE TABLE `authorities` (
      `username` varchar(50) NOT NULL,
      `authority` varchar(50) NOT NULL,
      CONSTRAINT `fk_authorities_users` FOREIGN KEY (`username`) REFERENCES `users` (`username`) ON DELETE CASCADE
)  DEFAULT CHARSET=utf8;
CREATE UNIQUE index `ix_auth_username` ON authorities (username,authority);

---
--- Table structure for table `persistent_logins`
---

CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) PRIMARY KEY,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL
) DEFAULT CHARSET=utf8;