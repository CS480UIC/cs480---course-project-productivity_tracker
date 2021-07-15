CREATE DATABASE ProductivityTracker;
USE ProductivityTracker;

CREATE TABLE Team
(
	team_id INTEGER NOT NULL AUTO_INCREMENT,
	team_name VARCHAR(50) NOT NULL,
    	PRIMARY KEY(team_id)
);

CREATE TABLE User
(
	user_id INTEGER NOT NULL AUTO_INCREMENT,
       	user_name VARCHAR(50) NOT NULL,
    	email VARCHAR(50) NOT NULL,
    	team_position VARCHAR(50),
   	 password VARCHAR(50),
   	 PRIMARY KEY (user_id)
);

CREATE TABLE TeamUser
(
	#TODO:
	#Need to add constraints for foreign keys
   	 #team_id and user_id are auto_increment in their tables, how does that play here?
	team_id INTEGER NOT NULL,
   	 user_id INTEGER NOT NULL,
    	PRIMARY KEY(team_id,user_id)
);

CREATE TABLE Task
(
	#TODO:
       	#Add foreign keys  and constraints: user_id, team_id, category_id, priority
	task_id INTEGER NOT NULL AUTO_INCREMENT,
       	task_name VARCHAR(50),
    	task_description VARCHAR(200),
    	user_id INTEGER NOT NULL,
    	team_id INTEGER,
    	creation_date DATE NOT NULL,
    	completed_date DATE,
    	dead_line_date DATE,
    	proirity INTEGER NOT NULL,
    	is_completed TINYINT(1) NOT NULL,
    	category_id INTEGER,
    	PRIMARY KEY (task_id)
);

CREATE TABLE Category
(
	category_id INTEGER NOT NULL,
       	name VARCHAR(50),
    	PRIMARY KEY (category_id)
);

INSERT INTO Category(category_id, name) VALUES
(100, 'Bug'),
(101, 'Documentation'),
(102, 'Test Case');

CREATE TABLE PriorityDescription
(
	priority INTEGER NOT NULL,
    	description VARCHAR(50),
    	PRIMARY KEY (priority)
);

INSERT INTO Priority(priority, description) VALUES
(1, 'High'),
(2, 'Medium'),
(3, 'Low'),
(100,'Pushed Forward'),
(101,'Reassign Priority'),
(102,'Inactive');
