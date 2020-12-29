#显示数据库
SHOW DATABASES;

#判断是否存在数据库student,有的话先删除
DROP DATABASE
  IF EXISTS student;

#创建数据库
CREATE DATABASE student;

#使用该数据库
USE student;

#显示数据库中的表
SHOW TABLES;

#先判断表是否存在,存在先删除
DROP TABLE
  IF EXISTS student;

#创建表
CREATE TABLE student
(
  id      INT         NOT NULL auto_increment PRIMARY KEY,
  #设定主键
  NAME    VARCHAR(20) NOT NULL DEFAULT 'NoName',
  #设定默认值
  sex     VARCHAR(20),
  date    VARCHAR(50),
  content VARCHAR(100),
  PRIMARY KEY (`id`, `name`) #联合主键
) DEFAULT charset = utf8;

#查看表的结构
DESCRIBE student;

#可以简写为desc student;
#插入数据
INSERT INTO student
VALUES (NULL,
        'aa',
        '男',
        '1988-10-2',
        '......');

INSERT INTO student
VALUES (NULL,
        'bb',
        '女',
        '1889-03-6',
        '......');

INSERT INTO student
VALUES (NULL,
        'cc',
        '男',
        '1889-08-8',
        '......');

INSERT INTO student
VALUES (NULL,
        'dd',
        '女',
        '1889-12-8',
        '......');

INSERT INTO student
VALUES (NULL,
        'ee',
        '女',
        '1889-09-6',
        '......');

INSERT INTO student
VALUES (NULL,
        'ff',
        'null',
        '1889-09-6',
        '......');

#查询表中的数据
SELECT *
FROM student;

SELECT id,
       NAME
FROM student;

#修改某一条数据
UPDATE student
SET sex = '男'
WHERE id = 4;

#删除数据
DELETE
FROM student
WHERE id = 5;

# and 且
SELECT *
FROM student
WHERE date > '1988-1-2'
  AND date < '1988-12-1';

# or 或
SELECT *
FROM student
WHERE date < '1988-11-2'
   OR date > '1988-12-1';

#between
SELECT *
FROM student
WHERE date BETWEEN '1988-1-2'
        AND '1988-12-1';

#排序 asc 升序  desc 降序
SELECT *
FROM student
ORDER BY id ASC;

#分组查询 #聚合函数
SELECT max(id),
       NAME,
       sex
FROM student
GROUP BY sex;

SELECT min(date)
FROM student;

SELECT avg(id) AS '求平均'
FROM student;

SELECT count(*)
FROM student;

#统计表中总数
SELECT count(sex)
FROM student;

#统计表中性别总数  若有一条数据中sex为空的话,就不予以统计~
SELECT sum(id)
FROM student;

#修改表的名字
#格式:alter table tbl_name rename to new_name
ALTER TABLE test RENAME TO test_rename;

#向表中增加一个字段(列)
#格式:alter table tablename add columnname type;/alter table tablename add(columnname type);
ALTER TABLE test
  ADD columnname VARCHAR(20);

#修改表中某个字段的名字
ALTER TABLE tablename
  CHANGE columnname newcolumnname type;

#修改一个表的字段名
ALTER TABLE test
  CHANGE NAME uname VARCHAR(50);

#表position 增加列test
ALTER TABLE position
  ADD (test CHAR(10));

#表position 修改列test
ALTER TABLE position
  MODIFY test CHAR(20) NOT NULL;

#表position 修改列test 默认值
ALTER TABLE position
  ALTER test
    SET DEFAULT 'system';

#表position 去掉test 默认值
ALTER TABLE position
  ALTER test DROP DEFAULT;

#表position 去掉列test
ALTER TABLE position
  DROP COLUMN test;

#表depart_pos 删除主键
ALTER TABLE depart_pos
  DROP PRIMARY KEY;

#表depart_pos 增加主键
ALTER TABLE depart_pos
  ADD PRIMARY KEY PK_depart_pos (department_id, position_id);

创建索引
CREATE INDEX indexName ON mytable (username(length) );

CREATE INDEX stu_id ON student (id) 显示索引信息 SHOW INDEX
FROM
	table_name;

修改表结构
  (添加索引)
ALTER TABLE tableName
  ADD INDEX indexName (columnName) 重建索引 索引是不可更改的，想更改必须删除重新建。 DBCC REINDEX DBCC INDEXDEFRAG 创建表的时候直接指定 CREATE TABLE mytable (
	ID INT NOT NULL,
	username VARCHAR (16) NOT NULL,
	INDEX [ indexName ] (username(length))
);

删除索引的语法
DROP INDEX [ indexName ] ON mytable;

删除索引：
drop INDEX idxname SELECT
  `sname`
  FROM
  `stu`
  WHERE
  `age` + 10 = 30;

-- 不会使用索引，因为所有索引列参与了计算
SELECT `sname`
FROM `stu`
WHERE LEFT(`date`, 4) < 1990;

-- 不会使用索引，因为使用了函数运算，原理与上面相同
SELECT *
FROM `houdunwang`
WHERE `uname` LIKE '后盾%' -- 走索引
SELECT *
FROM `houdunwang`
WHERE `uname` LIKE "%后盾%" -- 不走索引
  正则表达式不使用索引，这应该很好理解，所以这就是为什么在SQL中很难看到regexp关键字的原因 字符串与数字比较不使用索引;

CREATE TABLE `a`
(
  `a` CHAR(10)
);

EXPLAIN SELECT *
        FROM `a`
        WHERE `a` = "1" -- 走索引
EXPLAIN SELECT *
        FROM `a`
        WHERE `a` = 1 -- 不走索引，同样也是使用了函数运算
SELECT *
FROM dept
WHERE dname = 'xxx'
   OR loc = 'xx'
   OR deptno = 45 -- 如果条件中有or， 即使其中有条件带索引也不会使用。换言之，就是要求使用的所有字段，都必须建立索引，我们建议大家尽量避免使用or 关键字 MySQL内部优化器会对SQL语句进行优化，如果优化器估计使用全表扫描要比使用索引快，则不使用索引 创建视图：create VIEW viewname AS SELECT
  statement 删除视图：
drop VIEW viewname 修改数据库的名称 : sp_renamedb 'old_name',
			'new_name' 删除重复记录 1
),
DELETE
FROM tablename
WHERE id NOT IN (
  SELECT max(id)
  FROM tablename
  GROUP BY col1,
           col2, ...) 2
)
,
SELECT DISTINCT * INTO temp
FROM tablename DELETE
FROM
  tablename
INSERT INTO tablename
SELECT *
FROM temp 评价： 这种操作牵连大量的数据的移动，这种做法不适合大容量但数据操作