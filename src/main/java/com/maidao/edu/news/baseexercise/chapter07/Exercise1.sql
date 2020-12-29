CREATE DATABASE student_grade;

CREATE TABLE student
(
  id           INT         NOT NULL auto_increment PRIMARY KEY,
  #设定主键
  name         VARCHAR(20) NOT NULL,
  #设定默认值
  sex          TINYINT(1) DEFAULT 1,
  class_id     INT         NOT NULL,
  `created_at` BIGINT(20) DEFAULT NULL
) DEFAULT charset = utf8;

CREATE TABLE class
(
  id           INT NOT NULL auto_increment PRIMARY KEY,
  #设定主键
  name         VARCHAR(10),
  teacher      VARCHAR(10),
  `created_at` BIGINT(20) DEFAULT NULL
) DEFAULT charset = utf8;

CREATE TABLE course
(
  id           INT         NOT NULL auto_increment PRIMARY KEY,
  #设定主键
  `name`       VARCHAR(20) NOT NULL,
  type         VARCHAR(10),
  `created_at` BIGINT(20) DEFAULT NULL
) DEFAULT charset = utf8;

CREATE TABLE grade
(
  id           INT NOT NULL auto_increment PRIMARY KEY,
  student_id   INT NOT NULL,
  course_id    INT NOT NULL,
  grade        INT NOT NULL,
  `created_at` BIGINT(20) DEFAULT NULL
) DEFAULT charset = utf8;

INSERT INTO class
VALUES (1, '软件工程1601', '张耀武');

INSERT INTO class
VALUES (2, '软件工程1602', '张天伍');

INSERT INTO student
VALUES (1, '一号学生', 1, 1);

INSERT INTO student
VALUES (2, '二号学生', 1, 1);

INSERT INTO student
VALUES (3, '三号学生', 1, 1);

INSERT INTO student
VALUES (4, '四号学生', 1, 1);

INSERT INTO student
VALUES (5, '五号学生', 1, 1);

INSERT INTO student
VALUES (6, '六号学生', 0, 2);

INSERT INTO student
VALUES (7, '七号学生', 0, 2);

INSERT INTO student
VALUES (8, '八号学生', 0, 2);

INSERT INTO student
VALUES (9, '九号学生', 0, 2);

INSERT INTO student
VALUES (10, '十号学生', 0, 2);

INSERT INTO course
VALUES (1, '数据库', 1);

INSERT INTO course
VALUES (2, '数据结构', 1);

INSERT INTO course
VALUES (3, '离散数学', 0);

INSERT INTO grade
VALUES (1, 1, 1, 10);

INSERT INTO grade
VALUES (2, 2, 1, 20);

INSERT INTO grade
VALUES (3, 3, 1, 30);

INSERT INTO grade
VALUES (4, 4, 1, 40);

INSERT INTO grade
VALUES (5, 5, 1, 50);

INSERT INTO grade
VALUES (6, 6, 1, 60);

INSERT INTO grade
VALUES (7, 7, 1, 70);

INSERT INTO grade
VALUES (8, 8, 1, 80);

INSERT INTO grade
VALUES (9, 9, 1, 90);

INSERT INTO grade
VALUES (10, 10, 1, 100);


