#通用优化方案一：建表时为每张表创建主键索引，或者建表之后另外添加
ALTER TABLE class ADD PRIMARY KEY (id);

ALTER TABLE course ADD PRIMARY KEY (id);

ALTER TABLE grade ADD PRIMARY KEY (id);

ALTER TABLE student ADD PRIMARY KEY (id);

#通用优化方案二：为每张表查询时用到的属性列添加普通索引
ALTER TABLE class ADD INDEX idx_name (name);

ALTER TABLE course ADD INDEX idx_name (name);

ALTER TABLE grade ADD INDEX idx_grade (grade);

ALTER TABLE grade ADD INDEX idx_courseid (course_id);

ALTER TABLE grade ADD INDEX idx_studentid (student_id);

ALTER TABLE student ADD INDEX idx_classid (class_id);

ALTER TABLE student ADD INDEX idx_name (name);