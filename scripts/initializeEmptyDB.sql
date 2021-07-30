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

-- ADD Foreign Keys for all tables

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
-- test query SELECT * FROM Category;

INSERT INTO User(user_name, email, team_position, password)
VALUES (
        'admin',
        'admin@admin.com',
        'admin',
        'admin'
    );


INSERT INTO PriorityDescription(priority, description)
VALUES (1, "Immediate Priority");
INSERT INTO PriorityDescription(priority, description)
VALUES (2, "High Priority");
INSERT INTO PriorityDescription(priority, description)
VALUES (3, "Moderate Priority");
INSERT INTO PriorityDescription(priority, description)
VALUES (4, "Low Priority");

