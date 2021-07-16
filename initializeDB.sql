DROP TABLE IF EXISTS Team;
DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS Task;

CREATE TABLE Team (
  team_id int NOT NULL,
  team_members int NOT NULL,
  task_list int NOT NULL,
  PRIMARY KEY (team_id),
  FOREIGN KEY (team_members) REFERENCES User(user_id) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (task_list) REFERENCES Task(task_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE User (
  user_id int NOT NULL ,
  user_name text NOT NULL,
  email text NOT NULL,
  team_position text NOT NULL,
  task_list int,
  completed_tasks int,
  incompleted_tasks int,
  PRIMARY KEY (user_id),
  FOREIGN KEY (task_list) REFERENCES Task(task_id) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (completed_tasks) REFERENCES Task(task_id) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (incompleted_tasks) REFERENCES Task(task_id) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE Task (
  task_id int NOT NULL ,
  task_name text NOT NULL,
  task_description text NOT NULL,
  user_id int NOT NULL,
  team_id int NOT NULL,
  creation_date date,
  dead_line_date date, 
  completed_date date, 
  priority int,
  is_completed boolean,
  PRIMARY KEY (task_id),
  FOREIGN KEY (user_id) REFERENCES User(user_id) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (team_id) REFERENCES Team(team_id) ON UPDATE CASCADE ON DELETE CASCADE
);

-- Populate some tasks
INSERT INTO Task (task_id,task_name,task_description,user_id,team_id,creation_date,dead_line_date,priority,is_completed)
VALUES (40010,"Create Implementation tests", "make some test!", 1, 2,'2021-05-1','2021-05-2',5,'false');
INSERT INTO Task (task_id,task_name,task_description,user_id,team_id,creation_date,dead_line_date,priority,is_completed)
VALUES (40020,"Buy cookie ingredients", "Buy flour, milk, chocolate chips", 1, 2,'2021-05-21','2021-05-22',10,'false');
INSERT INTO Task (task_id,task_name,task_description,user_id,team_id,creation_date,dead_line_date,completed_date,priority,is_completed)
VALUES (40030,"Deploy new feature!", "Go through the checklist and deploy!", 1, 2,'2021-05-21','2021-05-22','2021-05-22',1,'true');
INSERT INTO Task (task_id,task_name,task_description,user_id,team_id,creation_date,dead_line_date,priority,is_completed)
VALUES (40040,"Test changes", "Run tests with jenkins.", 1, 2,'2021-05-19','2021-05-19',4,'false');
INSERT INTO Task (task_id,task_name,task_description,user_id,team_id,creation_date,dead_line_date,completed_date,priority,is_completed)
VALUES (40050,"Run regression testing on PR-2021", "Go through checklist and run regression tests.", 1, 2,'2021-05-21','2021-05-22','2021-05-22',3,'true');

