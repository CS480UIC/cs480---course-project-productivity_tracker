DROP TABLE IF EXISTS Team;
DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS Task;
CREATE TABLE Team (
    team_id int NOT NULL,
    team_name TEXT NOT NULL,
    PRIMARY KEY (team_id),
    FOREIGN KEY (team_id) REFERENCES TeamUser(team_id) ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE TeamUser (
    team_id int NOT NULL,
    user_id int NOT NULL,
    FOREIGN KEY (team_id) REFERENCES Team(team_id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (team_id) REFERENCES User(user_id) ON UPDATE CASCADE ON DELETE CASCADE
); 

CREATE TABLE User (
    user_id int NOT NULL,
    user_name TEXT NOT NULL,
    email TEXT NOT NULL,
    team_position TEXT NOT NULL,
    password TEXT NOT NULL,
    PRIMARY KEY (user_id),
    FOREIGN KEY (user_id) REFERENCES Task(user_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Task (
    task_id int NOT NULL,
    task_name TEXT NOT NULL,
    task_description TEXT NOT NULL,
    user_id int NOT NULL,
    team_id int NOT NULL,
    creation_date date,
    dead_line_date date,
    completed_date date,
    priority int,
    is_completed boolean,
    category_id,
    PRIMARY KEY (task_id),
    FOREIGN KEY (user_id) REFERENCES User(user_id) ON UPDATE CASCADE ON DELETE CASCADE,
    FOREIGN KEY (team_id) REFERENCES Team(team_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE PriorityDescription (
    priority int NOT NULL,
    description TEXT NOT NULL,
    PRIMARY KEY (priority),
    FOREIGN KEY (priority) REFERENCES Task(priority) ON UPDATE CASCADE ON DELETE CASCADE
);


CREATE TABLE Category (
    category_id int NOT NULL,
    name TEXT NOT NULL,
    PRIMARY KEY (category_id),
    FOREIGN KEY (category_id) REFERENCES Task(category_id) ON UPDATE CASCADE ON DELETE CASCADE
);


INSERT INTO Task (
        task_id,
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
        40010,
        "Create Implementation tests",
        "make some test!",
        1,
        2,
        '2021-05-1',
        '2021-05-2',
        5,
        'false',
        1
    );
INSERT INTO Task (
        task_id,
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
        40020,
        "Buy cookie ingredients",
        "Buy flour, milk, chocolate chips",
        1,
        2,
        '2021-05-21',
        '2021-05-22',
        10,
        'false',
        1
    );
INSERT INTO Task (
        task_id,
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
        40030,
        "Deploy new feature!",
        "Go through the checklist and deploy!",
        1,
        2,
        '2021-05-21',
        '2021-05-22',
        '2021-05-22',
        1,
        'true',
        2
    );
INSERT INTO Task (
        task_id,
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
        40040,
        "Test changes",
        "Run tests with jenkins.",
        1,
        2,
        '2021-05-19',
        '2021-05-19',
        4,
        'false',
        2
    );
INSERT INTO Task (
        task_id,
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
        40050,
        "Run regression testing on PR-2021",
        "Go through checklist and run regression tests.",
        1,
        2,
        '2021-05-21',
        '2021-05-22',
        '2021-05-22',
        3,
        'true',
        3
    );