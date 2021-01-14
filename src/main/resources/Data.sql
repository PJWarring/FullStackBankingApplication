--Default Users and Accounts
INSERT INTO "USER"(username, password, firstname, lastname, email, role) VALUES 
('root', 'changeme', 'root', 'admin', 'n/a:root', 2);

--Dummy values
INSERT INTO "USER"(username, password, firstname, lastname, email, role) VALUES
('manager', 'changeme', 'default', 'manager', 'n/a:manager', 1),
('user1', 'changeme', 'John', 'Doe', 'user1@gmail.com', 0),
('user2', 'changeme', 'John', 'Smith', 'user2@gmail.com', 0);

INSERT INTO ACCOUNT(balance, status, type) VALUES
(10.00, 1, 1),
(12.00, 2, 1);

INSERT INTO USER_ACCOUNT(users_id, accounts_id) VALUES
(3, 1),
(3, 2),
(4, 2);