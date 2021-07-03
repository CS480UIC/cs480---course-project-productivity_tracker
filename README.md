# Productivity DB ✏️

> **About our project**

Our application aims to be a tool for teams and individuals to go become more productive. User are able to create a task list and keep track of their progress. Teams are able to create a group of tasks and assign who needs to work on what! 


> **Database High Level Overview**

Our application allows teams and individual users to organize tasks and be able to track statistics. Our database will be comprised of three main entities, Users, Tasks/Teams, and Statistics. A team will have a many-to-many relationship with users where each team has its' own unique Team ID, Team Title, a list of users, and team task ID's. A user will be comprised of a unique user ID, task list IDs, Name, a list of Team ID's, and date joined. Individual users will be able to complete tasks on their individual task list or a team's task list. Teams and individuals can organize tasks by giving each task a category attribute as well as the due date. A Task List will have a one-to-one relationship with a given task. This list will hold ongoing tasks as well as completed tasks and will be able to be updated by a user. A task will include a unique task ID, list ID, task title, author, date created, due date, category, days-past-due-date, and a completed boolean state. The statistics entity will be able to use fields to generate meaningful statistics for the user. Users will be able to visualize data such as total tasks completed in a given week, and completion consistency.

> **Database ER diagram**

![drawSQL-export-2021-07-03_13_45](https://user-images.githubusercontent.com/54045615/124365881-a6be2a00-dc10-11eb-86cc-af58cae9d49c.png)
