DROP SCHEMA itracker;
CREATE SCHEMA itracker;
USE itracker;

CREATE TABLE ROLES
(
  ROLE_ID INT NOT NULL AUTO_INCREMENT
, ROLE_NAME VARCHAR(100) NOT NULL
, CONSTRAINT ROLES_PK PRIMARY KEY (ROLE_ID)
);

CREATE TABLE PRIVILEGES
(
  PRIVILEGE_ID INT NOT NULL AUTO_INCREMENT
, PRIVILEGE_NAME VARCHAR(150) NOT NULL
, ROLE_ID INT NOT NULL
, CONSTRAINT ROLES_PK PRIMARY KEY (PRIVILEGE_ID)
, CONSTRAINT ROLES_FK FOREIGN KEY (ROLE_ID) REFERENCES ROLES (ROLE_ID) ON DELETE CASCADE
);

CREATE TABLE USERS
(
  USER_ID INT NOT NULL AUTO_INCREMENT
, EMAIL VARCHAR(50) NOT NULL
, FIRST_NAME VARCHAR(45) NOT NULL
, LAST_NAME VARCHAR(45) NOT NULL
, ROLE_ID INT NOT NULL
, PASSWORD VARCHAR(50) NOT NULL
, CONSTRAINT USERS_PK PRIMARY KEY (USER_ID)
, CONSTRAINT USERS_UN UNIQUE (EMAIL)
, CONSTRAINT USERS_FK FOREIGN KEY (ROLE_ID) REFERENCES ROLES (ROLE_ID) ON DELETE CASCADE
);

-- ########################### STATUSES ###########################
CREATE TABLE STATUSES
(
  STATUS_ID INT NOT NULL AUTO_INCREMENT
, STATUS_NAME VARCHAR(30) NOT NULL
, CONSTRAINT STATUSES_PK PRIMARY KEY (STATUS_ID)
);

-- #################################################################

CREATE TABLE RESOLUTIONS
(
  RESOLUTION_ID INT NOT NULL AUTO_INCREMENT
, RESOLUTION_NAME VARCHAR(30) NOT NULL
, CONSTRAINT RESOLUTIONS_PK PRIMARY KEY (RESOLUTION_ID)
);

CREATE TABLE PRIORITIES
(
  PRIORITY_ID INT NOT NULL AUTO_INCREMENT
, PRIORITY_NAME VARCHAR(30) NOT NULL
, CONSTRAINT PRIORITIES_PK PRIMARY KEY (PRIORITY_ID)
);

CREATE TABLE TYPES
(
  TYPE_ID INT NOT NULL AUTO_INCREMENT
, TYPE_NAME VARCHAR(30) NOT NULL
, CONSTRAINT TYPES_PK PRIMARY KEY (TYPE_ID)
);

CREATE TABLE MANAGERS
(
  MANAGER_ID INT NOT NULL AUTO_INCREMENT
, USER_ID INT NOT NULL
, CONSTRAINT MANAGERS_PK PRIMARY KEY (MANAGER_ID)
, CONSTRAINT MANAGERS_FK FOREIGN KEY (USER_ID) REFERENCES USERS (USER_ID) ON DELETE CASCADE
);

CREATE TABLE PROJECTS
(
  PROJECT_ID INT NOT NULL AUTO_INCREMENT
, PROJECT_NAME VARCHAR(30) NOT NULL
, DESCRIPTION VARCHAR(100) NOT NULL
, MANAGER_ID INT NOT NULL
, CONSTRAINT PROJECTS_PK PRIMARY KEY (PROJECT_ID)
, CONSTRAINT PROJECTS_FK FOREIGN KEY (MANAGER_ID) REFERENCES MANAGERS (MANAGER_ID) ON DELETE CASCADE
);

CREATE TABLE BUILDS
(
  BUILD_ID INT NOT NULL AUTO_INCREMENT
, BUILD VARCHAR(20) NOT NULL
, PROJECT_ID INT NOT NULL
, CONSTRAINT BUILDS_PK PRIMARY KEY (BUILD_ID)
, CONSTRAINT BUILDS_FK FOREIGN KEY (PROJECT_ID) REFERENCES PROJECTS (PROJECT_ID) ON DELETE CASCADE
);

CREATE TABLE COMMENTS
(
  COMMENT_ID INT NOT NULL AUTO_INCREMENT
, ADDED_BY INT NOT NULL
, ADD_DATE DATE NOT NULL
, COMMENT VARCHAR(255) NOT NULL
, CONSTRAINT COMMENTS_PK PRIMARY KEY (COMMENT_ID)
, CONSTRAINT COMMENTS_ADDED_BY_FK FOREIGN KEY (ADDED_BY) REFERENCES USERS (USER_ID) ON DELETE CASCADE
);

CREATE TABLE FILES
(
  FILE_ID INT NOT NULL AUTO_INCREMENT
, ADDED_BY INT NOT NULL
, ADD_DATE DATE NOT NULL
, FILE_NAME VARCHAR(255) NOT NULL
, CONTENT_TYPE VARCHAR(255)
, FILE_SIZE INT
, DATA TINYBLOB NOT NULL
, CONSTRAINT FILES_PK PRIMARY KEY (FILE_ID)
, CONSTRAINT FILES_ADDED_BY_FK FOREIGN KEY (ADDED_BY) REFERENCES USERS (USER_ID) ON DELETE CASCADE
);

CREATE TABLE ISSUES
(
  ISSUE_ID INT NOT NULL AUTO_INCREMENT
, CREATE_DATE DATE NOT NULL
, CREATED_BY INT NOT NULL
, MODIFY_DATE DATE NOT NULL
, MODIFIED_BY INT NOT NULL
, SUMMARY VARCHAR(30) NOT NULL
, DESCRIPTION VARCHAR(255) NOT NULL
, STATUS_ID INT NOT NULL
, RESOLUTION_ID INT
, TYPE_ID INT NOT NULL
, PRIORITY_ID INT NOT NULL
, PROJECT_ID INT NOT NULL
, BUILD_ID INT NOT NULL
, ASSIGNEE_ID INT
, COMMENT_ID INT
, FILE_ID INT
, CONSTRAINT ISSUES_PK PRIMARY KEY (ISSUE_ID)
, CONSTRAINT ISSUES_CREATED_BY_FK FOREIGN KEY (CREATED_BY) REFERENCES USERS (USER_ID) ON DELETE CASCADE
, CONSTRAINT ISSUES_MODIFIED_BY_FK FOREIGN KEY (MODIFIED_BY) REFERENCES USERS (USER_ID) ON DELETE CASCADE
, CONSTRAINT ISSUES_STATUS_FK FOREIGN KEY (STATUS_ID) REFERENCES STATUSES (STATUS_ID) ON DELETE CASCADE
, CONSTRAINT ISSUES_RESOLUTION_FK FOREIGN KEY (RESOLUTION_ID) REFERENCES RESOLUTIONS (RESOLUTION_ID) ON DELETE CASCADE
, CONSTRAINT ISSUES_TYPE_FK FOREIGN KEY (TYPE_ID) REFERENCES TYPES (TYPE_ID) ON DELETE CASCADE
, CONSTRAINT ISSUES_PRIORITY_FK FOREIGN KEY (PRIORITY_ID) REFERENCES PRIORITIES (PRIORITY_ID) ON DELETE CASCADE
, CONSTRAINT ISSUES_PROJECT_FK FOREIGN KEY (PROJECT_ID) REFERENCES PROJECTS (PROJECT_ID) ON DELETE CASCADE
, CONSTRAINT ISSUES_BUILD_FK FOREIGN KEY (BUILD_ID) REFERENCES BUILDS (BUILD_ID) ON DELETE CASCADE
, CONSTRAINT ISSUES_ASSIGNEE_FK FOREIGN KEY (ASSIGNEE_ID) REFERENCES USERS (USER_ID) ON DELETE CASCADE
, CONSTRAINT ISSUES_COMMENT_FK FOREIGN KEY (COMMENT_ID) REFERENCES COMMENTS (COMMENT_ID) ON DELETE CASCADE
, CONSTRAINT ISSUES_FILE_FK FOREIGN KEY (FILE_ID) REFERENCES FILES (FILE_ID) ON DELETE CASCADE
);

-- insert into roles(ROLE_NAME) values('GUEST');
insert into roles(ROLE_NAME) values('USER');
insert into roles(ROLE_NAME) values('ADMINISTRATOR');
commit;

insert into statuses(STATUS_NAME) values('New');
insert into statuses(STATUS_NAME) values('Assigned');
insert into statuses(STATUS_NAME) values('In Progress');
insert into statuses(STATUS_NAME) values('Resolved');
insert into statuses(STATUS_NAME) values('Closed');
insert into statuses(STATUS_NAME) values('Reopened');
commit;

insert into priorities(PRIORITY_NAME) values('Critical');
insert into priorities(PRIORITY_NAME) values('Major');
insert into priorities(PRIORITY_NAME) values('Important');
insert into priorities(PRIORITY_NAME) values('Minor');
commit;

insert into types(TYPE_NAME) values('Cosmetic');
insert into types(TYPE_NAME) values('Bug');
insert into types(TYPE_NAME) values('Feature');
insert into types(TYPE_NAME) values('Performance');
commit;

insert into resolutions(RESOLUTION_NAME) values('Fixed');
insert into resolutions(RESOLUTION_NAME) values('Invalid');
insert into resolutions(RESOLUTION_NAME) values('Wontfix');
insert into resolutions(RESOLUTION_NAME) values('Worksforme');
commit;

-- INSERT INTO users(EMAIL, FIRST_NAME, LAST_NAME, ROLE, PASSWORD) VALUES ('anonymous', '', '', 1, '');
INSERT INTO users(EMAIL, FIRST_NAME, LAST_NAME, ROLE_ID, PASSWORD) VALUES ('epamer@epam.ceh', 'epamer', 'epamer account', 1, 'pass');
INSERT INTO users(EMAIL, FIRST_NAME, LAST_NAME, ROLE_ID, PASSWORD) VALUES ('teamlead@epam.ceh', 'team lead', 'team lead account', 1, 'pass');
INSERT INTO users(EMAIL, FIRST_NAME, LAST_NAME, ROLE_ID, PASSWORD) VALUES ('admin@epam.ceh', 'admin', 'admin account', 2, 'password');
INSERT INTO users(EMAIL, FIRST_NAME, LAST_NAME, ROLE_ID, PASSWORD) VALUES ('admin2@epam.ceh', 'admin2', 'admin2 account', 2, 'Passw0rd');
INSERT INTO users(EMAIL, FIRST_NAME, LAST_NAME, ROLE_ID, PASSWORD) VALUES ('test1@epam.ceh', 'test1', 'test1 account', 1, 'pass');
INSERT INTO users(EMAIL, FIRST_NAME, LAST_NAME, ROLE_ID, PASSWORD) VALUES ('test2@epam.ceh', 'test2', 'test2 account', 1, 'pass');
INSERT INTO users(EMAIL, FIRST_NAME, LAST_NAME, ROLE_ID, PASSWORD) VALUES ('test3@epam.ceh', 'test3', 'test3 account', 1, 'pass');
INSERT INTO users(EMAIL, FIRST_NAME, LAST_NAME, ROLE_ID, PASSWORD) VALUES ('test4@epam.ceh', 'test4', 'test4 account', 1, 'pass');
INSERT INTO users(EMAIL, FIRST_NAME, LAST_NAME, ROLE_ID, PASSWORD) VALUES ('test5@epam.ceh', 'test5', 'test5 account', 1, 'pass');
INSERT INTO users(EMAIL, FIRST_NAME, LAST_NAME, ROLE_ID, PASSWORD) VALUES ('test6@epam.ceh', 'test6', 'test6 account', 1, 'pass');
INSERT INTO users(EMAIL, FIRST_NAME, LAST_NAME, ROLE_ID, PASSWORD) VALUES ('test7@epam.ceh', 'test7', 'test7 account', 1, 'pass');
INSERT INTO users(EMAIL, FIRST_NAME, LAST_NAME, ROLE_ID, PASSWORD) VALUES ('test8@epam.ceh', 'test8', 'test8 account', 1, 'pass');
INSERT INTO users(EMAIL, FIRST_NAME, LAST_NAME, ROLE_ID, PASSWORD) VALUES ('test9@epam.ceh', 'test9', 'test9 account', 1, 'pass');
INSERT INTO users(EMAIL, FIRST_NAME, LAST_NAME, ROLE_ID, PASSWORD) VALUES ('test10@epam.ceh', 'test10', 'test10 account', 1, 'pass');
INSERT INTO users(EMAIL, FIRST_NAME, LAST_NAME, ROLE_ID, PASSWORD) VALUES ('test11@epam.ceh', 'test11', 'test11 account', 1, 'pass');
INSERT INTO users(EMAIL, FIRST_NAME, LAST_NAME, ROLE_ID, PASSWORD) VALUES ('test12@epam.ceh', 'test12', 'test12 account', 1, 'pass');
INSERT INTO users(EMAIL, FIRST_NAME, LAST_NAME, ROLE_ID, PASSWORD) VALUES ('test13@epam.ceh', 'test13', 'test13 account', 1, 'pass');
INSERT INTO users(EMAIL, FIRST_NAME, LAST_NAME, ROLE_ID, PASSWORD) VALUES ('test14@epam.ceh', 'test14', 'test14 account', 1, 'pass');
INSERT INTO users(EMAIL, FIRST_NAME, LAST_NAME, ROLE_ID, PASSWORD) VALUES ('test15@epam.ceh', 'test15', 'test15 account', 1, 'pass');
commit;

INSERT INTO managers(USER_ID) VALUES (1);
INSERT INTO managers(USER_ID) VALUES (2);
commit;

INSERT INTO projects(PROJECT_NAME, DESCRIPTION, MANAGER_ID) VALUES ('issue tracker', 'info', 2);
INSERT INTO projects(PROJECT_NAME, DESCRIPTION, MANAGER_ID) VALUES ('another one', 'some text', 1);
INSERT INTO projects(PROJECT_NAME, DESCRIPTION, MANAGER_ID) VALUES ('test 1', 'test 1', 2);
INSERT INTO projects(PROJECT_NAME, DESCRIPTION, MANAGER_ID) VALUES ('test 2', 'test 2', 2);
INSERT INTO projects(PROJECT_NAME, DESCRIPTION, MANAGER_ID) VALUES ('test 3', 'test 3', 1);
INSERT INTO projects(PROJECT_NAME, DESCRIPTION, MANAGER_ID) VALUES ('test 4', 'test 4', 1);
commit;

INSERT INTO builds (BUILD, PROJECT_ID) VALUES ('1.0', 1);
INSERT INTO builds (BUILD, PROJECT_ID) VALUES ('1.5', 1);
INSERT INTO builds (BUILD, PROJECT_ID) VALUES ('2.0', 1);
INSERT INTO builds (BUILD, PROJECT_ID) VALUES ('1.0', 2);
INSERT INTO builds (BUILD, PROJECT_ID) VALUES ('1.5', 2);
INSERT INTO builds (BUILD, PROJECT_ID) VALUES ('2.0', 2);
INSERT INTO builds (BUILD, PROJECT_ID) VALUES ('1.0', 3);
INSERT INTO builds (BUILD, PROJECT_ID) VALUES ('1.5', 3);
INSERT INTO builds (BUILD, PROJECT_ID) VALUES ('1.0', 4);
INSERT INTO builds (BUILD, PROJECT_ID) VALUES ('1.5', 4);
INSERT INTO builds (BUILD, PROJECT_ID) VALUES ('2.0', 4);
INSERT INTO builds (BUILD, PROJECT_ID) VALUES ('1.0', 5);
INSERT INTO builds (BUILD, PROJECT_ID) VALUES ('1.5', 5);
INSERT INTO builds (BUILD, PROJECT_ID) VALUES ('2.0', 5);
INSERT INTO builds (BUILD, PROJECT_ID) VALUES ('1.0', 6);
INSERT INTO builds (BUILD, PROJECT_ID) VALUES ('1.5', 6);
commit;

INSERT INTO issues(CREATE_DATE, CREATED_BY, MODIFY_DATE, MODIFIED_BY, SUMMARY, DESCRIPTION, STATUS_ID, RESOLUTION_ID,
                   TYPE_ID, PRIORITY_ID, PROJECT_ID, BUILD_ID, ASSIGNEE_ID)
VALUES ('2016-09-10', 1, '2016-09-10', 1, 'info', 'first record', 1, 1, 1, 1, 1, 1, 1);
INSERT INTO issues(CREATE_DATE, CREATED_BY, MODIFY_DATE, MODIFIED_BY, SUMMARY, DESCRIPTION, STATUS_ID, RESOLUTION_ID,
                   TYPE_ID, PRIORITY_ID, PROJECT_ID, BUILD_ID, ASSIGNEE_ID)
VALUES ('2016-09-09', 5, '2016-09-10', 2, 'information', 'second', 2, 1, 2, 2, 2, 4, 5);
INSERT INTO issues(CREATE_DATE, CREATED_BY, MODIFY_DATE, MODIFIED_BY, SUMMARY, DESCRIPTION, STATUS_ID, RESOLUTION_ID,
                   TYPE_ID, PRIORITY_ID, PROJECT_ID, BUILD_ID, ASSIGNEE_ID)
VALUES ('2016-09-11', 5, '2016-09-20', 1, 'info 1', 'test 1', 1, 2, 1, 3, 2, 5, 11);
INSERT INTO issues(CREATE_DATE, CREATED_BY, MODIFY_DATE, MODIFIED_BY, SUMMARY, DESCRIPTION, STATUS_ID, RESOLUTION_ID,
                   TYPE_ID, PRIORITY_ID, PROJECT_ID, BUILD_ID, ASSIGNEE_ID)
VALUES ('2016-09-12', 5, '2016-09-20', 2, 'info 2', 'test 2', 1, 3, 4, 4, 3, 7, 12);
INSERT INTO issues(CREATE_DATE, CREATED_BY, MODIFY_DATE, MODIFIED_BY, SUMMARY, DESCRIPTION, STATUS_ID, RESOLUTION_ID,
                   TYPE_ID, PRIORITY_ID, PROJECT_ID, BUILD_ID, ASSIGNEE_ID)
VALUES ('2016-09-13', 6, '2016-09-20', 1, 'info 3', 'test 3', 3, 4, 3, 1, 3, 8, 12);
INSERT INTO issues(CREATE_DATE, CREATED_BY, MODIFY_DATE, MODIFIED_BY, SUMMARY, DESCRIPTION, STATUS_ID, RESOLUTION_ID,
                   TYPE_ID, PRIORITY_ID, PROJECT_ID, BUILD_ID, ASSIGNEE_ID)
VALUES ('2016-09-14', 6, '2016-09-20', 2, 'info 4', 'test 4', 4, 1, 2, 2, 5, 12, 13);
INSERT INTO issues(CREATE_DATE, CREATED_BY, MODIFY_DATE, MODIFIED_BY, SUMMARY, DESCRIPTION, STATUS_ID, RESOLUTION_ID,
                   TYPE_ID, PRIORITY_ID, PROJECT_ID, BUILD_ID, ASSIGNEE_ID)
VALUES ('2016-09-15', 7, '2016-09-20', 1, 'info 5', 'test 5', 5, 3, 4, 4, 4, 11, 14);
INSERT INTO issues(CREATE_DATE, CREATED_BY, MODIFY_DATE, MODIFIED_BY, SUMMARY, DESCRIPTION, STATUS_ID, RESOLUTION_ID,
                   TYPE_ID, PRIORITY_ID, PROJECT_ID, BUILD_ID, ASSIGNEE_ID)
VALUES ('2016-09-15', 8, '2016-09-20', 2, 'info 6', 'test 6', 6, 4, 3, 3, 6, 16, 15);
commit;

SELECT
  issue_id
  , create_date
  , created_by.first_name AS created_by
  , modify_date
  , modified_by.first_name AS modified_by
  , summary
  , i.description AS description
  , s.status_name AS status
  , r.resolution_name AS resolution
  , t.type_name AS type
  , p.priority_name AS priority
  , proj.project_name AS project
  , b.build
  , assignee_id.first_name as assignee
  , c.comment
  , i.file_id
FROM issues i
  INNER JOIN users created_by ON i.CREATED_BY = created_by.USER_ID
  INNER JOIN users modified_by ON i.MODIFIED_BY = modified_by.USER_ID
  INNER JOIN statuses s ON i.STATUS_ID = s.STATUS_ID
  INNER JOIN resolutions r ON i.RESOLUTION_ID = r.RESOLUTION_ID
  INNER JOIN types t ON i.TYPE_ID = t.TYPE_ID
  INNER JOIN priorities p ON i.PRIORITY_ID = p.PRIORITY_ID
  INNER JOIN projects proj ON i.PROJECT_ID = proj.PROJECT_ID
  INNER JOIN builds b ON i.BUILD_ID = b.BUILD_ID
  INNER JOIN users assignee_id ON i.ASSIGNEE_ID = assignee_id.USER_ID
  LEFT JOIN comments c ON i.COMMENT_ID = c.COMMENT_ID;

SELECT project_id, project_name, description, u.first_name AS manager
FROM projects
  INNER JOIN managers mgr ON projects.MANAGER_ID = mgr.MANAGER_ID
  INNER JOIN users u ON mgr.USER_ID = u.USER_ID;

SELECT user_id, email, first_name, last_name, r.role_name AS role, password
FROM users u
  INNER JOIN roles r ON u.ROLE_ID = r.ROLE_ID;