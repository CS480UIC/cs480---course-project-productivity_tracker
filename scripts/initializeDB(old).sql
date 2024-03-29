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

INSERT INTO User(user_id, user_name, email, team_position, password)
VALUES (
        1001,
        'DanielRiccardo',
        'danric@gmail.com',
        'Project Manager',
        'Mydoglovescheese123!'
    );
INSERT INTO User(user_id, user_name, email, team_position, password)
VALUES (
        1002,
        'LewisHamiltton',
        'louis@gmail.com',
        'Project Manager',
        'jablsdfjkb2!'
    );
INSERT INTO User(user_id, user_name, email, team_position, password)
VALUES (
        1003,
        'CharlesLeclerc',
        'CharlesLec@gmail.com',
        'QA',
        'hotdogsforlunch'
    );
INSERT INTO User(user_id, user_name, email, team_position, password)
VALUES (
        1004,
        'LandoNorris',
        'lando@gmail.com',
        'QA',
        'CantSwim!'
    );
INSERT INTO User(user_id, user_name, email, team_position, password)
VALUES (
        1005,
        'PierreGastly',
        'gas123@gmail.com',
        'Software Engineer',
        'CheeseyBread824901'
    );
INSERT INTO User(user_id, user_name, email, team_position, password)
VALUES (
        1006,
        'SergioPerrez',
        'sergi01@gmail.com',
        'Software Engineer',
        'toyotacorrolla'
    );
INSERT INTO User(user_id, user_name, email, team_position, password)
VALUES (
        1007,
        'MaxVerstappen',
        'stapp@gmail.com',
        'Software Engineer',
        'Ihave11ToEs'
    );
INSERT INTO User(user_id, user_name, email, team_position, password)
VALUES (
        1008,
        'ValtteriBottas',
        'bottas41@gmail.com',
        'Software Engineer',
        'dontRunAfterSwimming'
    );

INSERT INTO User(user_id, user_name, email, team_position, password)
VALUES (
        0,
        'admin',
        'admin@admin.com',
        'admin',
        'admin'
    );
-- test query SELECT * FROM User;

INSERT INTO Team(team_id, team_name)
VALUES (1, "Cereal Killers");
INSERT INTO Team(team_id, team_name)
VALUES (2, "Hungry Hippos");
INSERT INTO Team(team_id, team_name)
VALUES (3, "Dill with it");
INSERT INTO Team(team_id, team_name)
VALUES (4, "Chicken Noodle Hoop");
INSERT INTO Team(team_id, team_name)
VALUES (5, "Cereal Killers");
-- test query SELECT * FROM Team;

INSERT INTO TeamUser(team_id, user_id)
VALUES (1, 1001);
INSERT INTO TeamUser(team_id, user_id)
VALUES (2, 1002);
INSERT INTO TeamUser(team_id, user_id)
VALUES (1, 1003);
INSERT INTO TeamUser(team_id, user_id)
VALUES (3, 1004);
INSERT INTO TeamUser(team_id, user_id)
VALUES (4, 1005);
INSERT INTO TeamUser(team_id, user_id)
VALUES (2, 1006);
INSERT INTO TeamUser(team_id, user_id)
VALUES (1, 1007);
INSERT INTO TeamUser(team_id, user_id)
VALUES (5, 1008);
-- test query SELECT * FROM TeamUser WHERE team_id = 1;


INSERT INTO PriorityDescription(priority, description)
VALUES (1, "Immediate Priority");
INSERT INTO PriorityDescription(priority, description)
VALUES (2, "High Priority");
INSERT INTO PriorityDescription(priority, description)
VALUES (3, "Moderate Priority");
INSERT INTO PriorityDescription(priority, description)
VALUES (4, "Low Priority");
-- test query SELECT * FROM PriorityDescription;
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
        
        "Create Implementation tests",
        "make some test!",
        1001,
        1,
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
        completed_date,
        priority,
        is_completed,
        category_id
    )
VALUES (
        
        "Deploy new feature!",
        "Go through the checklist and deploy!",
        1005,
        4,
        '2021-05-21',
        '2021-05-22',
        '2021-05-22',
        1,
        1,
        20
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
        
        "Test changes",
        "Run tests with jenkins.",
        1002,
        2,
        '2021-05-19',
        '2021-05-19',
        3,
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
        completed_date,
        priority, 
        is_completed,
        category_id
    )
VALUES (
        
        "Run regression testing on PR-2021",
        "Go through checklist and run regression tests.",
        1007,
        1,
        '2021-05-21',
        '2021-05-22',
        '2021-05-22',
        3,
        1,
        50
    );
-- test query SELECT * FROM Task;
