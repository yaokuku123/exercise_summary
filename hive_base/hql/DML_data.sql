-- DML
-- DML的数据操作部分。包括：数据的导入和导出操作。

-- 一、数据导入

-- 1.1 向表中装载数据 load
-- load data [local] inpath '数据的 path' [overwrite] into table
-- table_name [partition (partcol1=val1,…)];

-- 1 加载本地文件
-- 创建表
create table student(
    id int,
    name string
) row format delimited
fields terminated by ',';
-- 加载本地数据（复制本地数据到目标表的目录）
load data local inpath '/root/data/student.txt' into table student;

-- 2 加载HDFS文件（移动原数据到目标表的目录）
-- 上传文件到HDFS
dfs -put /root/data/student.txt /test;
-- 加载HDFS数据插入到表数据
load data inpath '/test/student.txt' into table student;
-- 加载HDFS数据覆盖到表数据
load data inpath '/test/student.txt' overwrite into table student_res;

-- 1.2 通过查询语句向表中插入数据 insert
-- 创建结果表
create table student_res(
    id int,
    name string
) row format delimited
fields terminated by ',';
-- 1 直接插入数据
insert into table student_res(id, name) values (1,'doris'),(2,'july');
-- 2 根据单张表查询结果插入数据
insert into table student_res
select id,name from student;
-- 3 根据单张表查询结果覆盖插入数据
insert overwrite table student_res
select id,name from student;

-- 二、数据导出
-- 2.1 insert 导出
-- 注：覆盖该目录中原内容
-- 1 将查询的结果格式化导出到本地
insert overwrite local directory '/root/tmp_data'
row format delimited fields terminated by '\t'
select id,name from student_res;
-- 2 将查询的结果导出到HDFS
insert overwrite directory '/test'
row format delimited fields terminated by '\t'
select id,name from student_res;

-- 2.2 Hadoop 命令导出到本地（常用）
dfs -get /user/hive/warehouse/student /root/tmp_data;

-- 2.3 Hive Shell 命令导出
-- hive -e 'select * from default.student;' > /root/tmp_data/student.txt

-- 2.4 Sqoop 导出
-- pass