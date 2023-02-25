-- DDL
-- DDL 分区&分桶

-- 一 分区
-- Hive 中的分区就是分目录，把一个大的数据集根据业务需要分割成小的数据 集
-- 1 单分区表
-- 创建单表(增)
create table dept_partition(
    deptno int,
    dname string,
    loc int
) partitioned by (day string)
row format delimited
fields terminated by ',';
-- 分区表加载数据，必须指定分区
load data local inpath '/root/data/dept1.txt' into table dept_partition partition (day='20230218');
load data local inpath '/root/data/dept2.txt' into table dept_partition partition (day='20230219');
load data local inpath '/root/data/dept3.txt' into table dept_partition partition (day='20230220');
-- 查看分区数据（查）
select * from dept_partition where day = '20230218';
-- 增加分区（增）
alter table dept_partition add partition(day='20230221') partition(day='20230222');
-- 删除分区（删）
alter table dept_partition drop partition(day='20230221'),partition(day='20230222');

-- 2 二级分区表
-- 创建二级分区表
create table dept_partition2(
    deptno int,
    dname string,
    loc int
) partitioned by (day string,hour string)
row format delimited
fields terminated by ',';
-- 加载数据
load data local inpath '/root/data/dept1.txt' into table dept_partition2 partition(day='20230218',hour='12');
load data local inpath '/root/data/dept2.txt' into table dept_partition2 partition(day='20230218',hour='13');
load data local inpath '/root/data/dept3.txt' into table dept_partition2 partition(day='20230219',hour='12');
-- 查看分区数据
select * from dept_partition2 where day = '20230218' and hour = '12';

-- 3 分区表与数据产生关联的三种方式
-- 创建分区表
create table dept_partition3(
   deptno int,
   dname string,
   loc int
) partitioned by (day string)
row format delimited
fields terminated by ',';
-- 方法一：load 数据到分区
load data local inpath '/root/data/dept1.txt' into table dept_partition3 partition(day='20230218');
-- 方法二：上传数据后修复分区
dfs -mkdir -p /user/hive/warehouse/dept_partition3/day=20230219;
dfs -put /root/data/dept1.txt /user/hive/warehouse/dept_partition3/day=20230219;
select * from dept_partition3 where day = '20230219'; -- 无数据
msck repair table dept_partition3;
select * from dept_partition3 where day = '20230219'; -- 有数据
-- 方法三：上传数据后添加分区
dfs -mkdir -p /user/hive/warehouse/dept_partition3/day=20230220;
dfs -put /root/data/dept1.txt /user/hive/warehouse/dept_partition3/day=20230220;
select * from dept_partition3 where day = '20230220'; -- 无数据
alter table dept_partition3 add partition(day = '20230220');
select * from dept_partition3 where day = '20230219'; -- 有数据

-- 4 动态分区
-- 4.1 动态分区参数设置
    -- 1 开启动态分区功能（默认：true）
set hive.exec.dynamic.partition=true;
    -- 2 设置为非严格模式（默认：strict，表示必须指定至少一个分区为 静态分区，
    --  nonstrict 模式表示允许所有的分区字段都可以使用动态分区）
set hive.exec.dynamic.partition.mode=nonstrict;
    -- 3 在所有执行 MR 的节点上，最大一共可以创建多少个动态分区（默认：1000）
set hive.exec.max.dynamic.partitions=1000;
    -- 4 在每个执行 MR 的节点上，最大可以创建多少个动态分区（默认：100）
set hive.exec.max.dynamic.partitions.pernode=100;
    -- 5 整个 MR Job 中，最大可以创建多少个 HDFS 文件（默认：100000）
set hive.exec.max.created.files=100000;
    -- 6 当有空分区生成时，是否抛出异常（默认：false）
set hive.error.on.empty.partition=false;

-- 4.2 设置动态分区
-- 1 创建目标分区表
create table dept_partition_dy(
    id int,
    name string
) partitioned by (loc int)
row format delimited
fields terminated by ',';
-- 动态插入到目标分区表分区(自动会根据分区字段的值，将数据插入到相应的分区中)
-- 注：插入字段的顺序与目标表顺序一致，否则会导致数据错位
set hive.exec.dynamic.partition.mode=nonstrict;
insert overwrite table dept_partition_dy partition (loc)
select
    deptno,
    dname,
    loc
from dept;
select * from dept_partition_dy;

-- 二 分桶
-- 分区针对的是数据的存储路径；分桶针对的是数据文件，更为细粒度的数据范围划分
-- 1 创建分桶表
create table student_buck(
    id int,
    name string
) clustered by (id) into 4 buckets
row format delimited
fields terminated by ',';
-- 2 导入数据到分桶表(insert插入)
create table student_insert(
    id int,
    name string
) row format delimited
fields terminated by ',';
load data local inpath '/root/data/student.txt' into table student_insert;
-- 插入数据到分桶表
insert into table student_buck
select id,name from student_insert;