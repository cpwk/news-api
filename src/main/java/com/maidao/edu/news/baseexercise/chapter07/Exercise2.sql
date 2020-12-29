a:修改录入错误的学生姓名，学生成绩。（至少三条）
UPDATE student
SET NAME = '1号学生'
WHERE id = 1;

UPDATE student
SET NAME = '2号学生'
WHERE id = 2;

UPDATE student
SET NAME = '3号学生'
WHERE id = 3;

UPDATE grade
SET grade = 100
WHERE id = 1;

UPDATE grade
SET grade = 100
WHERE id = 2;

UPDATE grade
SET grade = 100
WHERE id = 2;

b:统计每门课的学生人数
SELECT count(*) AS student_number
FROM grade
GROUP BY course_id
  c.查询某一门课程不及格的学生信息
SELECT *
FROM student a,
     (SELECT * FROM grade WHERE grade < 60) b
WHERE a.id = b.student_id
  d.查询某一个学生的各科成绩
SELECT course_id,
       grade
FROM grade
WHERE student_id = 1
  e.查询一个班级每个学生的总成绩，并按总成绩由高到低排序;

SELECT *
FROM class c,
     student s,
     (
       SELECT student_id,
              SUM(grade) AS total
       FROM grade
       GROUP BY student_id
     ) g
WHERE c.id = s.class_id
  AND s.id = g.student_id
ORDER BY g.total DESC
  f.查询每门课都大于80分的学生信息;

SELECT *
FROM student a,
     (
       SELECT student_id
       FROM grade
       WHERE grade > 80
     ) b
WHERE a.id = b.student_id
  g.查询至少有一门课程不及格的学生信息
SELECT *
FROM student a,
     (
       SELECT student_id
       FROM grade
       WHERE grade < 60
     ) b
WHERE a.id = b.student_id
  h.查询某一个课各个班级的平均学生成绩，并按成绩由高到低排序;
SELECT class_id,
       AVG(grade)
FROM student s,
     (
       SELECT student_id,
              grade
       FROM grade
       WHERE course_id = 1
     ) g
WHERE s.id = g.student_id
GROUP BY class_id
  i.查询各科成绩最高分、最低分和平均分
SELECT MAX(grade) AS "最高分",
       min(grade) AS "最低分",
       AVG(grade) AS "平均分"
FROM grade
GROUP BY course_id
