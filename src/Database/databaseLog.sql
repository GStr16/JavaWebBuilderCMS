CREATE TABLE root_test (
ID integer auto_increment primary key,
email varchar(100) not null,
username varchar(100) not null,
number integer(6) not null,
verified Boolean not null,
password varchar(100) not null
)

--Inserting the test values
INSERT INTO root_test(email, username, number, verified, password) values ('test@gmailcom','test',961982, false, 'test')
-- SELECTING the test values
SELECT * FROM toot_test
-- Output
1 	test@gmailcom 	test 	961982 	0 	test
-- Update query
UPDATE root_test SET verified = 1 WHERE root_test.ID = 1;
-- SELECTING the modified test values
1 	test@gmailcom 	test 	961982 	1 	test