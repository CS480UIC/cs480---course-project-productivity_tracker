CREATE DATABASE ProductivityTracker;
USE ProductivityTracker;
DROP TABLE IF EXISTS TeamUser;
DROP TABLE IF EXISTS Task;
DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS Team;
DROP TABLE IF EXISTS PriorityDescription;
DROP TABLE IF EXISTS Category;

CREATE TABLE Team (
    team_id INTEGER NOT NULL AUTO_INCREMENT,
    team_name VARCHAR(50) NOT NULL,
    PRIMARY KEY (team_id)
);

CREATE TABLE TeamUser (
    team_id INTEGER NOT NULL,
    user_id INTEGER NOT NULL
);
CREATE TABLE User (
    user_id INTEGER NOT NULL AUTO_INCREMENT,
    user_name VARCHAR(20) UNIQUE NOT NULL,
    email TEXT NOT NULL,
    team_position VARCHAR(20),
    password TEXT NOT NULL,
    PRIMARY KEY (user_id)
);
CREATE TABLE Task (
    task_id INTEGER AUTO_INCREMENT NOT NULL,
    task_name VARCHAR(50) NOT NULL,
    task_description TEXT NOT NULL,
    user_id INTEGER NOT NULL,
    team_id INTEGER,
    creation_date date,
    dead_line_date date,
    completed_date date,
    priority INTEGER,
    is_completed boolean NOT NULL,
    category_id INTEGER,
    PRIMARY KEY (task_id)
);
CREATE TABLE PriorityDescription (
    priority INTEGER NOT NULL,
    description TEXT NOT NULL,
    PRIMARY KEY (priority)
);
CREATE TABLE Category (
    category_id INTEGER NOT NULL,
    name VARCHAR(20) NOT NULL,
    PRIMARY KEY (category_id)
);



ALTER TABLE TeamUser
ADD CONSTRAINT FK_TeamUser_user_id
FOREIGN KEY (user_id) REFERENCES User(user_id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE TeamUser
ADD CONSTRAINT FK_TeamUser_team_id
FOREIGN KEY (team_id) REFERENCES Team(team_id) ON UPDATE CASCADE ON DELETE CASCADE; 

ALTER TABLE Task
ADD CONSTRAINT FK_Task_user_id
FOREIGN KEY (user_id) REFERENCES User(user_id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE Task
ADD CONSTRAINT FK_Task_team_id
FOREIGN KEY (team_id) REFERENCES Team(team_id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE Task
ADD CONSTRAINT FK_PriorityDescription_priority
FOREIGN KEY (priority) REFERENCES PriorityDescription(priority) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE Task
ADD CONSTRAINT FK_Category_category_id
FOREIGN KEY (category_id) REFERENCES Category(category_id) ON UPDATE CASCADE ON DELETE CASCADE;



INSERT INTO User(user_name, email, team_position, password)
VALUES (
        'ykurku2',
        'ykurku2@uic.edu',
        'Administrator',
        'admin'
);
   
INSERT INTO User(user_name, email, team_position, password)
VALUES (
        'jdiaz88',
        'jdiaz88@uic.edu',
        'Administrator',
        'admin'
);

INSERT INTO User(user_name, email, team_position, password)
VALUES (
        'user1',
        'user1@uic.edu',
        'Project Manager',
        'user'
);

INSERT INTO User(user_name, email, team_position, password)
VALUES (
        'user2',
        'user2@uic.edu',
        'Project Manager',
        'user'
);

INSERT INTO User(user_name, email, team_position, password)
VALUES (
        'user3',
        'user3@uic.edu',
        'Software Engineer',
        'user'
);

INSERT INTO User(user_name, email, team_position, password)
VALUES (
        'user4',
        'user4@uic.edu',
        'Software Engineer',
        'user'
);

INSERT INTO User(user_name, email, team_position, password)
VALUES (
        'user5',
        'user5@uic.edu',
        'Software Engineer',
        'user'
);

INSERT INTO User(user_name, email, team_position, password)
VALUES (
        'user6',
        'user6@uic.edu',
        'Software Engineer',
        'user'
);

INSERT INTO User(user_name, email, team_position, password)
VALUES (
        'user7',
        'user7@uic.edu',
        'Buisness Analyst',
        'user'
);

INSERT INTO User(user_name, email, team_position, password)
VALUES (
        'user8',
        'user8@uic.edu',
        'Buisness Analyst',
        'user'
);

INSERT INTO User(user_name, email, team_position, password)
VALUES (
        'user9',
        'user7@uic.edu',
        'System Administrator',
        'user'
);

INSERT INTO User(user_name, email, team_position, password)
VALUES (
        'user10',
        'user8@uic.edu',
        'Software Architect',
        'user'
);

INSERT INTO User(user_name, email, team_position, password)
VALUES (
        'user11',
        'user11@uic.edu',
        'IT Infra Manager',
        'user'
);

INSERT INTO User(user_name, email, team_position, password)
VALUES (
        'user12',
        'user12@uic.edu',
        'IT Infra Manager',
        'user'
);

INSERT INTO User(user_name, email, team_position, password)
VALUES (
        'user13',
        'user13@uic.edu',
        'Software Intern',
        'user'
);

INSERT INTO User(user_name, email, team_position, password)
VALUES (
        'user14',
        'user14@uic.edu',
        'Software Intern',
        'user'
);

INSERT INTO User(user_name, email, team_position, password)
VALUES (
        'user15',
        'user13@uic.edu',
        'Project Manager',
        'user'
);

INSERT INTO User(user_name, email, team_position, password)
VALUES (
        'user16',
        'user14@uic.edu',
        'HR Manager',
        'user'
);

INSERT INTO User(user_name, email, team_position, password)
VALUES (
        'user17',
        'user14@uic.edu',
        'Project Manager',
        'user'
);



INSERT INTO Team(team_name)
VALUES ('Team Admins');

INSERT INTO Team(team_name)
VALUES ('Team Evens');

INSERT INTO Team(team_name)
VALUES ('Team Odds');

INSERT INTO Team(team_name)
VALUES ('Team 3K');

INSERT INTO Team(team_name)
VALUES ('Team Primes');


INSERT INTO TeamUser(team_id, user_id)
VALUES ( (SELECT team_id FROM Team WHERE team_name = 'Team Admins')
	,(SELECT user_id FROM User WHERE user_name = 'ykurku2')
);

INSERT INTO TeamUser(team_id, user_id)
VALUES ( (SELECT team_id FROM Team WHERE team_name = 'Team Admins')
	,(SELECT user_id FROM User WHERE user_name = 'jdiaz88')
);


INSERT INTO TeamUser(team_id, user_id)
VALUES ( (SELECT team_id FROM Team WHERE team_name = 'Team Evens')
	,(SELECT user_id FROM User WHERE user_name = 'user2')
);

INSERT INTO TeamUser(team_id, user_id)
VALUES ( (SELECT team_id FROM Team WHERE team_name = 'Team Evens')
	,(SELECT user_id FROM User WHERE user_name = 'user4')
);

INSERT INTO TeamUser(team_id, user_id)
VALUES ( (SELECT team_id FROM Team WHERE team_name = 'Team Evens')
	,(SELECT user_id FROM User WHERE user_name = 'user6')
);

INSERT INTO TeamUser(team_id, user_id)
VALUES ( (SELECT team_id FROM Team WHERE team_name = 'Team Evens')
	,(SELECT user_id FROM User WHERE user_name = 'user8')
);


INSERT INTO TeamUser(team_id, user_id)
VALUES ( (SELECT team_id FROM Team WHERE team_name = 'Team Evens')
	,(SELECT user_id FROM User WHERE user_name = 'user10')
);

INSERT INTO TeamUser(team_id, user_id)
VALUES ( (SELECT team_id FROM Team WHERE team_name = 'Team Evens')
	,(SELECT user_id FROM User WHERE user_name = 'user12')
);

INSERT INTO TeamUser(team_id, user_id)
VALUES ( (SELECT team_id FROM Team WHERE team_name = 'Team Evens')
	,(SELECT user_id FROM User WHERE user_name = 'user14')
);

INSERT INTO TeamUser(team_id, user_id)
VALUES ( (SELECT team_id FROM Team WHERE team_name = 'Team Evens')
	,(SELECT user_id FROM User WHERE user_name = 'user14')
);

INSERT INTO TeamUser(team_id, user_id)
VALUES ( (SELECT team_id FROM Team WHERE team_name = 'Team Odds')
	,(SELECT user_id FROM User WHERE user_name = 'user1')
);

INSERT INTO TeamUser(team_id, user_id)
VALUES ( (SELECT team_id FROM Team WHERE team_name = 'Team Odds')
	,(SELECT user_id FROM User WHERE user_name = 'user3')
);

INSERT INTO TeamUser(team_id, user_id)
VALUES ( (SELECT team_id FROM Team WHERE team_name = 'Team Odds')
	,(SELECT user_id FROM User WHERE user_name = 'user5')
);

INSERT INTO TeamUser(team_id, user_id)
VALUES ( (SELECT team_id FROM Team WHERE team_name = 'Team Odds')
	,(SELECT user_id FROM User WHERE user_name = 'user7')
);

INSERT INTO TeamUser(team_id, user_id)
VALUES ( (SELECT team_id FROM Team WHERE team_name = 'Team Odds')
	,(SELECT user_id FROM User WHERE user_name = 'user9')
);

INSERT INTO TeamUser(team_id, user_id)
VALUES ( (SELECT team_id FROM Team WHERE team_name = 'Team Odds')
	,(SELECT user_id FROM User WHERE user_name = 'user11')
);

INSERT INTO TeamUser(team_id, user_id)
VALUES ( (SELECT team_id FROM Team WHERE team_name = 'Team Odds')
	,(SELECT user_id FROM User WHERE user_name = 'user13')
);

INSERT INTO TeamUser(team_id, user_id)
VALUES ( (SELECT team_id FROM Team WHERE team_name = 'Team Odds')
	,(SELECT user_id FROM User WHERE user_name = 'user15')
);

INSERT INTO TeamUser(team_id, user_id)
VALUES ( (SELECT team_id FROM Team WHERE team_name = 'Team Odds')
	,(SELECT user_id FROM User WHERE user_name = 'user17')
);

INSERT INTO TeamUser(team_id, user_id)
VALUES ( (SELECT team_id FROM Team WHERE team_name = 'Team 3K')
	,(SELECT user_id FROM User WHERE user_name = 'user3')
);

INSERT INTO TeamUser(team_id, user_id)
VALUES ( (SELECT team_id FROM Team WHERE team_name = 'Team 3K')
	,(SELECT user_id FROM User WHERE user_name = 'user6')
);

INSERT INTO TeamUser(team_id, user_id)
VALUES ( (SELECT team_id FROM Team WHERE team_name = 'Team 3K')
	,(SELECT user_id FROM User WHERE user_name = 'user9')
);

INSERT INTO TeamUser(team_id, user_id)
VALUES ( (SELECT team_id FROM Team WHERE team_name = 'Team 3K')
	,(SELECT user_id FROM User WHERE user_name = 'user12')
);

INSERT INTO TeamUser(team_id, user_id)
VALUES ( (SELECT team_id FROM Team WHERE team_name = 'Team 3K')
	,(SELECT user_id FROM User WHERE user_name = 'user15')
);

INSERT INTO TeamUser(team_id, user_id)
VALUES ( (SELECT team_id FROM Team WHERE team_name = 'Team Primes')
	,(SELECT user_id FROM User WHERE user_name = 'user2')
);

INSERT INTO TeamUser(team_id, user_id)
VALUES ( (SELECT team_id FROM Team WHERE team_name = 'Team Primes')
	,(SELECT user_id FROM User WHERE user_name = 'user3')
);

INSERT INTO TeamUser(team_id, user_id)
VALUES ( (SELECT team_id FROM Team WHERE team_name = 'Team Primes')
	,(SELECT user_id FROM User WHERE user_name = 'user5')
);

INSERT INTO TeamUser(team_id, user_id)
VALUES ( (SELECT team_id FROM Team WHERE team_name = 'Team Primes')
	,(SELECT user_id FROM User WHERE user_name = 'user7')
);

INSERT INTO TeamUser(team_id, user_id)
VALUES ( (SELECT team_id FROM Team WHERE team_name = 'Team Primes')
	,(SELECT user_id FROM User WHERE user_name = 'user11')
);


INSERT INTO TeamUser(team_id, user_id)
VALUES ( (SELECT team_id FROM Team WHERE team_name = 'Team Primes')
	,(SELECT user_id FROM User WHERE user_name = 'user13')
);


INSERT INTO TeamUser(team_id, user_id)
VALUES ( (SELECT team_id FROM Team WHERE team_name = 'Team Primes')
	,(SELECT user_id FROM User WHERE user_name = 'user17')
);


INSERT INTO Category(category_id, name)
VALUES (10, 'Documentation');
INSERT INTO Category(category_id, name)
VALUES (20, 'Merged to package');
INSERT INTO Category(category_id, name)
VALUES (30, 'Merged to branch');
INSERT INTO Category(category_id, name)
VALUES (40, 'Issue/Bug');
INSERT INTO Category(category_id, name)
VALUES (50, 'Test Case');


INSERT INTO PriorityDescription(priority, description)
VALUES (1, 'Immediate Priority');
INSERT INTO PriorityDescription(priority, description)
VALUES (2, 'High Priority');
INSERT INTO PriorityDescription(priority, description)
VALUES (3, 'Moderate Priority');
INSERT INTO PriorityDescription(priority, description)
VALUES (4, 'Low Priority');


INSERT INTO Task (
        
        task_name,
        task_description,
        user_id,
        team_id,
        creation_date,
        dead_line_date,
        priority,
        is_completed,
        category_id
    )
VALUES (
        
        'Test initializeDB.sql',
        'make some test!',
        (SELECT user_id FROM User WHERE user_name = 'user4'),
        (SELECT team_id FROM Team WHERE team_name = 'Team Evens'),
        '2021-05-1',
        '2021-05-2',
        1,
        0,
        50
);

INSERT INTO Task (
        
        task_name,
        task_description,
        user_id,
        team_id,
        creation_date,
        dead_line_date,
        priority,
        is_completed,
        category_id
    )
VALUES (
        
        'Test HTTP server',
        'make some test!',
        (SELECT user_id FROM User WHERE user_name = 'user4'),
        (SELECT team_id FROM Team WHERE team_name = 'Team Evens'),
        '2021-05-1',
        '2021-05-2',
        2,
        0,
        50
);

INSERT INTO Task (
        
        task_name,
        task_description,
        user_id,
        team_id,
        creation_date,
        dead_line_date,
        priority,
        is_completed,
        category_id
    )
VALUES (
        
        'Test Add User App Framework',
        'make some test!',
        (SELECT user_id FROM User WHERE user_name = 'user6'),
        (SELECT team_id FROM Team WHERE team_name = 'Team Evens'),
        '2021-05-1',
        '2021-05-2',
        1,
        0,
        50
);

INSERT INTO Task (
        
        task_name,
        task_description,
        user_id,
        team_id,
        creation_date,
        dead_line_date,
        priority,
        is_completed,
        category_id
    )
VALUES (
        
        'Test Task Creation App Framework',
        'make some test!',
        (SELECT user_id FROM User WHERE user_name = 'user6'),
        (SELECT team_id FROM Team WHERE team_name = 'Team Evens'),
        '2021-05-1',
        '2021-05-2',
        1,
        0,
        50
);

INSERT INTO Task (
        
        task_name,
        task_description,
        user_id,
        team_id,
        creation_date,
        dead_line_date,
        priority,
        is_completed,
        category_id
    )
VALUES (
        
        'Test create user GUI',
        'make some test!',
        (SELECT user_id FROM User WHERE user_name = 'user3'),
        (SELECT team_id FROM Team WHERE team_name = 'Team Odds'),
        '2021-05-1',
        '2021-05-2',
        2,
        0,
        50
);

INSERT INTO Task (
        
        task_name,
        task_description,
        user_id,
        team_id,
        creation_date,
        dead_line_date,
        priority,
        is_completed,
        category_id
    )
VALUES (
        
        'Test login activity',
        'make some test!',
        (SELECT user_id FROM User WHERE user_name = 'user3'),
        (SELECT team_id FROM Team WHERE team_name = 'Team Odds'),
        '2021-05-1',
        '2021-05-2',
        1,
        0,
        50
);

INSERT INTO Task (
        
        task_name,
        task_description,
        user_id,
        team_id,
        creation_date,
        dead_line_date,
        priority,
        is_completed,
        category_id
    )
VALUES (
        
        'Test login activity response to DB',
        'Test if logging in query reaches server',
        (SELECT user_id FROM User WHERE user_name = 'user3'),
        (SELECT team_id FROM Team WHERE team_name = 'Team 3K'),
        '2021-05-1',
        '2021-05-2',
        1,
        0,
        50
);

INSERT INTO Task (
        
        task_name,
        task_description,
        user_id,
        team_id,
        creation_date,
        dead_line_date,
        priority,
        is_completed,
        category_id
    )
VALUES (
        
        'Test remote connection with DB',
        'make some test!',
        (SELECT user_id FROM User WHERE user_name = 'user5'),
        (SELECT team_id FROM Team WHERE team_name = 'Team Odds'),
        '2021-05-1',
        '2021-05-2',
        2,
        0,
        50
);

INSERT INTO Task (
        
        task_name,
        task_description,
        user_id,
        team_id,
        creation_date,
        dead_line_date,
        priority,
        is_completed,
        category_id
    )
VALUES (
        
        'Test Complex Queries',
        'make some test!',
        (SELECT user_id FROM User WHERE user_name = 'user5'),
        (SELECT team_id FROM Team WHERE team_name = 'Team Odds'),
        '2021-05-1',
        '2021-05-2',
        2,
        0,
        50
);

INSERT INTO Task (
        
        task_name,
        task_description,
        user_id,
        team_id,
        creation_date,
        dead_line_date,
        priority,
        is_completed,
        category_id
    )
VALUES (
        
        'Fix switch case no break',
        'Bug Fix',
        (SELECT user_id FROM User WHERE user_name = 'user4'),
        (SELECT team_id FROM Team WHERE team_name = 'Team Evens'),
        '2021-05-1',
        '2021-05-2',
        1,
        0,
        40
);

INSERT INTO Task (
        
        task_name,
        task_description,
        user_id,
        team_id,
        creation_date,
        dead_line_date,
        priority,
        is_completed,
        category_id
    )
VALUES (
        
        'Fix function param order',
        'Bug Fix',
        (SELECT user_id FROM User WHERE user_name = 'user4'),
        (SELECT team_id FROM Team WHERE team_name = 'Team Evens'),
        '2021-05-1',
        '2021-05-2',
        3,
        0,
        40
);

INSERT INTO Task (
        
        task_name,
        task_description,
        user_id,
        team_id,
        creation_date,
        dead_line_date,
        priority,
        is_completed,
        category_id
    )
VALUES (
        
        'Make user id auto increment',
        'New Feature',
        (SELECT user_id FROM User WHERE user_name = 'user6'),
        (SELECT team_id FROM Team WHERE team_name = 'Team 3K'),
        '2021-05-1',
        '2021-05-2',
        4,
        0,
        40
);

INSERT INTO Task (
        
        task_name,
        task_description,
        user_id,
        team_id,
        creation_date,
        dead_line_date,
        priority,
        is_completed,
        category_id
    )
VALUES (
        
        'Documentation: Update ERD',
        'make some test!',
        (SELECT user_id FROM User WHERE user_name = 'user6'),
        (SELECT team_id FROM Team WHERE team_name = 'Team 3K'),
        '2021-05-1',
        '2021-05-2',
        3,
        0,
        10
);

INSERT INTO Task (
        
        task_name,
        task_description,
        user_id,
        team_id,
        creation_date,
        dead_line_date,
        priority,
        is_completed,
        category_id
    )
VALUES (
        
        'Fix wild thread on reload',
        'Thread stays alive after app closes',
        (SELECT user_id FROM User WHERE user_name = 'user3'),
        (SELECT team_id FROM Team WHERE team_name = 'Team Odds'),
        '2021-05-1',
        '2021-05-2',
        2,
        0,
        40
);

INSERT INTO Task (
        
        task_name,
        task_description,
        user_id,
        team_id,
        creation_date,
        dead_line_date,
        priority,
        is_completed,
        category_id
    )
VALUES (
        
        'Resolve tested pull requests',
        'Resolve pull requests related to server side',
        (SELECT user_id FROM User WHERE user_name = 'user3'),
        (SELECT team_id FROM Team WHERE team_name = 'Team Primes'),
        '2021-05-1',
        '2021-05-2',
        1,
        0,
        30
);

INSERT INTO Task (
        
        task_name,
        task_description,
        user_id,
        team_id,
        creation_date,
        dead_line_date,
        priority,
        is_completed,
        category_id
    )
VALUES (
        
        'Resolve pull requests about GUI',
        'Resolve tested GUI pull requests',
        (SELECT user_id FROM User WHERE user_name = 'user5'),
        (SELECT team_id FROM Team WHERE team_name = 'Team Primes'),
        '2021-05-1',
        '2021-05-2',
        2,
        0,
        30
);

INSERT INTO Task (
        
        task_name,
        task_description,
        user_id,
        team_id,
        creation_date,
        dead_line_date,
        priority,
        is_completed,
        category_id
    )
VALUES (
        
        'Create User Guide',
        'User guide to run project',
        (SELECT user_id FROM User WHERE user_name = 'user5'),
        (SELECT team_id FROM Team WHERE team_name = 'Team Odds'),
        '2021-05-1',
        '2021-05-2',
        4,
        0,
        10
);






