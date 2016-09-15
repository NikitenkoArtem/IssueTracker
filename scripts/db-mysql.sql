use test;

CREATE TABLE ROLES
(
  ROLE_NAME VARCHAR(50) NOT NULL
, CONSTRAINT ROLES_PK PRIMARY KEY (ROLE_NAME)
);

CREATE TABLE USERS
(
  EMAIL VARCHAR(120) NOT NULL
, FIRST_NAME VARCHAR(45) NOT NULL
, LAST_NAME VARCHAR(45) NOT NULL
, ROLE VARCHAR(50) NOT NULL
, PASSWORD VARCHAR(50) NOT NULL
, CONSTRAINT USERS_PK PRIMARY KEY (EMAIL)
, CONSTRAINT USERS_FK FOREIGN KEY (ROLE) REFERENCES ROLES (ROLE_NAME)
);

-- ########################### STATUSES ###########################
CREATE TABLE STATUSES
(
  STATUS_NAME VARCHAR(30) NOT NULL
, CONSTRAINT STATUSES_PK PRIMARY KEY (STATUS_NAME)
);

-- #################################################################

CREATE TABLE RESOLUTIONS
(
  RESOLUTION_NAME VARCHAR(30) NOT NULL
, CONSTRAINT RESOLUTIONS_PK PRIMARY KEY (RESOLUTION_NAME)
);

CREATE TABLE PRIORITIES
(
  PRIORITY_NAME VARCHAR(30) NOT NULL
, CONSTRAINT PRIORITIES_PK PRIMARY KEY (PRIORITY_NAME)
);

CREATE TABLE TYPES
(
  TYPE_NAME VARCHAR(30) NOT NULL
, CONSTRAINT TYPES_PK PRIMARY KEY (TYPE_NAME)
);


CREATE TABLE MANAGERS
(
  MANAGER_NAME VARCHAR(30) NOT NULL
, CONSTRAINT MANAGERS_PK PRIMARY KEY (MANAGER_NAME)
);

CREATE TABLE PROJECTS
(
  PROJECT_NAME VARCHAR(30) NOT NULL
, DESCRIPTION VARCHAR(100) NOT NULL
, BUILD VARCHAR(20) NOT NULL
, MANAGER VARCHAR(30) NOT NULL
, CONSTRAINT PROJECTS_PK PRIMARY KEY (PROJECT_NAME)
, CONSTRAINT PROJECTS_FK FOREIGN KEY (MANAGER) REFERENCES MANAGERS (MANAGER_NAME)
);

CREATE TABLE ISSUES
(
  ISSUE_ID INT NOT NULL AUTO_INCREMENT
, CREATE_DATE DATE NOT NULL
, CREATED_BY VARCHAR(120) NOT NULL
, MODIFY_DATE DATE NOT NULL
, MODIFYED_BY VARCHAR(20) NOT NULL
, SUMMARY VARCHAR(30) NOT NULL
, DESCRIPTION VARCHAR(255) NOT NULL
, STATUS VARCHAR(30) NOT NULL
, RESOLUTION VARCHAR(30)
, TYPE VARCHAR(30) NOT NULL
, PRIORITY VARCHAR(30) NOT NULL
, PROJECT VARCHAR(30) NOT NULL
, BUILD_FOUND VARCHAR(15) NOT NULL
, ASSIGNEE VARCHAR(20)
, CONSTRAINT ISSUES_PK PRIMARY KEY (ISSUE_ID)
, CONSTRAINT ISSUES_CREATED_BY_FK FOREIGN KEY (CREATED_BY) REFERENCES USERS (EMAIL)
, CONSTRAINT ISSUES_MODIFYED_BY_FK FOREIGN KEY (MODIFYED_BY) REFERENCES USERS (EMAIL)
, CONSTRAINT ISSUES_PRIORITY_FK FOREIGN KEY (PRIORITY) REFERENCES PRIORITIES (PRIORITY_NAME)
, CONSTRAINT ISSUES_PROJECT_FK FOREIGN KEY (PROJECT) REFERENCES PROJECTS (PROJECT_NAME)
, CONSTRAINT ISSUES_RESOLUTION_FK FOREIGN KEY (RESOLUTION) REFERENCES RESOLUTIONS (RESOLUTION_NAME)
, CONSTRAINT ISSUES_STATUS_FK FOREIGN KEY (STATUS) REFERENCES STATUSES (STATUS_NAME)
, CONSTRAINT ISSUES_TYPE_FK FOREIGN KEY (TYPE) REFERENCES TYPES (TYPE_NAME)
);

insert into roles values('GUEST');
insert into roles values('USER');
insert into roles values('ADMINISTRATOR');
commit;

insert into statuses values('New');
insert into statuses values('Assigned');
insert into statuses values('In Progress');
insert into statuses values('Resolved');
insert into statuses values('Closed');
insert into statuses values('Reopened');
commit;

insert into priorities values('Critical');
insert into priorities values('Major');
insert into priorities values('Important');
insert into priorities values('Minor');
commit;

insert into types values('Cosmetic');
insert into types values('Bug');
insert into types values('Feature');
insert into types values('Performance');
commit;

insert into resolutions values('Fixed');
insert into resolutions values('Invalid');
insert into resolutions values('Wontfix');
insert into resolutions values('Worksforme');
commit;

INSERT INTO managers VALUES ('epamer');
INSERT INTO managers VALUES ('team lead');
commit;

INSERT INTO projects VALUES ('another one', 'some text', '1.0', 'epamer');
INSERT INTO projects VALUES ('issue tracker', 'info', '1.0', 'team lead');
commit;

INSERT INTO users VALUES ('test@epam.ceh', 'test', 'test account', 'guest', 'pass');
INSERT INTO users VALUES ('user@epam.ceh', 'user', 'user account', 'administrator', 'pass');
commit;

INSERT INTO issues
(`CREATE_DATE`, `CREATED_BY`, `MODIFY_DATE`, `MODIFYED_BY`, `SUMMARY`, `DESCRIPTION`, `STATUS`, `RESOLUTION`, `TYPE`, `PRIORITY`, `PROJECT`, `BUILD_FOUND`, `ASSIGNEE`)
 VALUES ('2016-09-10', 'test@epam.ceh', '2016-09-10', 'test@epam.ceh', 'info', 'first record', 'New', 'Invalid', 'Cosmetic', 'Critical', 'issue tracker', '1.0', 'test');
INSERT INTO issues
(`CREATE_DATE`, `CREATED_BY`, `MODIFY_DATE`, `MODIFYED_BY`, `SUMMARY`, `DESCRIPTION`, `STATUS`, `RESOLUTION`, `TYPE`, `PRIORITY`, `PROJECT`, `BUILD_FOUND`, `ASSIGNEE`)
VALUES ('2016-09-09', 'user@epam.ceh', '2016-09-10', 'user@epam.ceh', 'information', 'second', 'New', 'Fixed', 'Bug', 'Major', 'another one', '1.0.1', 'user');
commit;