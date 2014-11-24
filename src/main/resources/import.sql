INSERT INTO Users(login) VALUES ('testUser1');
INSERT INTO Walls(owner_id) VALUES (1);
INSERT INTO Posts(title, message, creation_date, creator_id, wall_id) VALUES ('testTitle', 'testMessage', CURRENT_TIMESTAMP, 1, 1);