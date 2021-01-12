--Default Users and Accounts
INSERT INTO "USER"(username, password, firstname, lastname, role) VALUES 
('root', 'changeme', 'root', 'admin', 2);

--Dummy values
INSERT INTO "USER"(username, password, firstname, lastname, role) VALUES
('manager', 'changeme', 'default', 'manager', 1),
('user1', 'changeme', 'John', 'Doe', 0),
('user2', 'changeme', 'John', 'Smith', 0);