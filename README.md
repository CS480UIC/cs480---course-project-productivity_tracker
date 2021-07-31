## Table of Contents
1. [Database](#database)
2. [Author(s)](#author)
3. [Database description](#description)
 
# Database
Productivity Tracker

# Author(s)
- Jacob Diaz
- Yash Kurkure

# Database description

The aim of this application aims to be a tool for teams and individuals to go become more productive. User are able to create a task list and keep track of their progress. Teams are able to create a group of tasks and assign who needs to work on what!

Our application allows teams and individual users to organize tasks and be able to track statistics. Our database will be comprised of three main entitie: Users, Tasks and Teams. A user can be part of 0 or more teams, where 0 means the user intends personal use. The user will have their own unique id, user name, email, team position is any and a login password. A team will have a many-to-many relationship with users where each team has its' own unique Team ID, Team Name. Individual users will be able to complete tasks on their individual task list or a team's task list. Tasks in general for all teams and users will be in one single table.  Teams and individuals can organize tasks by giving each task a category attribute/category as well as a priority level. Tasks can be ongoing tasks or even completed tasks and this will be able to be updated by a user. A task will include a unique task ID, task name, author, description, creation date, due date, actual completion date, category, and a completed boolean state indicating if the task is still active. Users will be able to visualize data such as total tasks completed in a given week, and completion consistency, and other stats from the task table.


- Database ER diagram:

![drawSQL-export-2021-07-03_13_45](https://github.com/diamalab/cs480---course-project-productivity_tracker/blob/main/documentation/ER_diagram.png)

Alternatively you can view the ER diagram in the documentaion folder or [here](https://drawsql.app/uic-1/diagrams/copy-of-cs-480-project#)
