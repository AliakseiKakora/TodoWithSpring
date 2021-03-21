/*CREATE TABLE USERS*/
CREATE SEQUENCE USER_ID_SEQ_GEN START WITH 1 INCREMENT BY 1 CACHE 10 NOCYCLE;
CREATE TABLE USERS (ID LONG PRIMARY KEY, NAME VARCHAR(55), SURNAME VARCHAR(55), EMAIL VARCHAR(55) UNIQUE);

/*CREATE TABLE PROFILES*/
CREATE TABLE PROFILES (ID LONG PRIMARY KEY, LOGIN VARCHAR(55) NOT NULL UNIQUE, PASSWORD VARCHAR(55) NOT NULL, IS_ENABLE BOOLEAN, CONSTRAINT FK_PROFILE_ID FOREIGN KEY (ID) REFERENCES USERS(ID));

/*CREATE TABLE ROLES*/
CREATE SEQUENCE ROLE_ID_SEQ_GEN START WITH 1 INCREMENT BY 1 CACHE 10 NOCYCLE;
CREATE TABLE ROLES (ID LONG PRIMARY KEY, ROLE VARCHAR(55) NOT NULL UNIQUE);

/*CREATE TABLE USERS_ROLES*/

CREATE TABLE ROLE_USER (ROLE_ID LONG NOT NULL, USER_ID LONG NOT NULL, CONSTRAINT FK_USER_ID FOREIGN KEY (USER_ID) REFERENCES USERS (ID), CONSTRAINT FK_ROLE_ID FOREIGN KEY (ROLE_ID) REFERENCES ROLES (ID));

/*CREATE TABLE TASKS*/
CREATE SEQUENCE TASK_ID_SEQ_GEN START WITH 1 INCREMENT BY 1 CACHE 10 NOCYCLE;
CREATE TABLE TASKS (ID LONG PRIMARY KEY, NAME VARCHAR(55) NOT NULL, DESCRIPTION VARCHAR(255), DATE_ADDED TIMESTAMP NOT NULL, DATE_COMPLETION TIMESTAMP NOT NULL, USER_ID LONG NOT NULL, COMPLETED BOOLEAN, DELETED BOOLEAN, CONSTRAINT FK_TASKS_USER_ID  FOREIGN KEY (USER_ID) REFERENCES USERS (ID));

/*CREATE TABLE FILES*/

CREATE TABLE FILES_INFO(ID LONG PRIMARY KEY, NAME VARCHAR(255) NOT NULL, DIRECTORY VARCHAR(255) NOT NULL, PATH VARCHAR(255) NOT NULL UNIQUE, CONSTRAINT FK_FILE_ID FOREIGN KEY (ID) REFERENCES TASKS(ID));


/*CREATE TABLE MESSAGES*/
CREATE SEQUENCE MESSAGE_ID_SEQ_GEN START WITH 1 INCREMENT BY 1 CACHE 10 NOCYCLE;
CREATE TABLE MESSAGES (ID LONG PRIMARY KEY, MESSAGE VARCHAR(255) NOT NULL, DATE_ADDED TIMESTAMP NOT NULL, USER_ID LONG NOT NULL, CONSTRAINT FK_MESSAGE_USER_ID FOREIGN KEY (USER_ID) REFERENCES USERS (ID));