INSERT INTO users(username, password, enabled) VALUES ('testUser', 'password', TRUE);
INSERT INTO walls(owner_name) VALUES ('testUser');
INSERT INTO posts(title, message, creation_date, creator_name, wall_id) VALUES ('testTitle', 'testMessage', CURRENT_TIMESTAMP, 'testUser', 1);
INSERT INTO posts(title, message, creation_date, creator_name, wall_id) VALUES ('testTitle1', 'testMessage1', CURRENT_TIMESTAMP, 'testUser', 1);