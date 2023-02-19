-- DDL 语句
-- 主要包括对数据库的增删改查以及对表的增删改查操作。

-- 一、增删改查数据库
-- 1（增）创建数据库
-- 建库语法:
-- CREATE DATABASE [IF NOT EXISTS] database_name
--      [COMMENT database_comment]
--      [LOCATION hdfs_path]
--      [WITH DBPROPERTIES (property_name=property_value, ...)];

-- 1 创建db_hive数据库
create database db_hive;
-- 2 创建db_hive数据库，避免已经存在该库创建报错
create database if not exists db_hive;
-- 3 创建db_hive数据库，并指定在HDFS存放的位置，默认是/user/hive/warehouse/*.db
-- 该数据库会在HDFS上建立目录/test/db_hive2.db
create database db_hive2 location '/test/db_hive2.db';

-- 2 （查）查询与切换数据库
-- 1 显示所有数据库
show databases;
-- 2 过滤查询数据库，仅查询匹配的数据库
show databases like 'db_hive*';
-- 3 显示数据库详情
desc database extended db_hive;
-- 4 切换当前数据库
use db_hive;

-- 3 （改）修改数据库
-- 数据库的 DBPROPERTIES 添加键-值对属性值
alter database db_hive set dbproperties ('creatTime'='20230219');

-- 4 （删）删除数据库
-- 1 删除空库（没有表）
drop database db_hive2;
-- 2 避免删除数据库不存在报错
drop database if exists db_hive2;
-- 3 删除非空库（有表）
-- 注：即使在被删除库里也可以执行删库命令
use db_hive;
create table psn (id string,name string);
drop database db_hive cascade;

-- 二、增删改查表
-- 建表语法:
-- CREATE [EXTERNAL] TABLE [IF NOT EXISTS] table_name
--      [(col_name data_type [COMMENT col_comment], ...)] [COMMENT table_comment]
--      [PARTITIONED BY (col_name data_type [COMMENT col_comment], ...)]
--      [CLUSTERED BY (col_name, col_name, ...)
--      [SORTED BY (col_name [ASC|DESC], ...)] INTO num_buckets BUCKETS]
--      [ROW FORMAT row_format]
--      [STORED AS file_format]
--      [LOCATION hdfs_path]
--      [TBLPROPERTIES (property_name=property_value, ...)]
--      [AS select_statement]

-- 2.1 创建表（增）
-- 2.1.1 内部表创建（管理表）
-- 1 普通方式创建内表
create table student(
    id int,
    name string
) row format delimited
fields terminated by ',';
-- 导入数据
load data local inpath '/root/data/student.txt' into table student;
-- 2 根据查询结果创建内部表
create table if not exists student2
as select id,name from student;
-- 3 根据已经存在的表结构创建内部表
create table if not exists student3 like student;

-- 2.1.2 外部表创建
-- HDFS的方式导入数据
dfs -mkdir -p /test/student_ext;
dfs -put /root/data/student.txt /test/student_ext/student.txt;
-- 创建外部表
create external table student_ext(
    id int,
    name string
) row format delimited
fields terminated by ','
location '/test/student_ext';

-- 2.2 查询表（查）
-- 1 查询全部表
show tables;
-- 2 查询内部表
desc formatted student; -- MANAGED_TABLE
-- 3 查询外部表
desc formatted student_ext; -- EXTERNAL_TABLE

-- 2.3 修改表（改）
-- 1 重命名表
alter table student2 rename to student2_new;
-- 2 增加/修改/替换列 (replace可以完成全部操作)
alter table student2_new replace columns (stu_id string,stu_name string,stu_loc string);
-- 3 增加/修改/删除表分区
-- pass
-- 4 修改表属性(通过修改表属性，实现内部表与外部表之间的转换)
alter table student2_new set tblproperties ('EXTERNAL'='TRUE');

-- 2.4 删除表（删）
-- 1 删除内部表(表的元数据与HDFS数据一并删除)
drop table student;
-- 2 删除外部表(仅删除表的元数据，不删除HDFS数据，当恢复表的元数据时可恢复对该表的操作)
drop table student_ext;




