-- DML
-- DML 查询相关操作
-- 查询语法
-- SELECT [ALL | DISTINCT] select_expr, select_expr, ...
-- FROM table_reference
-- [WHERE where_condition]
-- [GROUP BY col_list]
-- [ORDER BY col_list]
-- [CLUSTER BY col_list
-- | [DISTRIBUTE BY col_list] [SORT BY col_list]
-- ]
-- [LIMIT number]

-- 一 准备测试数据
create table if not exists dept(
    deptno int,
    dname string,
    loc int
) row format delimited
fields terminated by ',';
load data local inpath '/root/data/dept.txt' into table dept;

create table if not exists loc(
    loc int,
    loc_name string
) row format delimited
fields terminated by ',';
load data local inpath '/root/data/loc.txt' into table loc;

create table if not exists emp(
    empno int,
    ename string,
    job string,
    mgr int,
    hiredate string,
    sal double,
    comm double,
    deptno int
) row format delimited
fields terminated by ',';
load data local inpath '/root/data/emp.txt' into table emp;

-- 二 Join语句
-- 1 内连接
-- 只有进行连接的两个表中都存在与连接条件相匹配的数据才会被保留下来
select
    e.empno,
    e.ename,
    d.dname
from emp e
inner join dept d
on e.deptno = d.deptno;
-- 2 左外连接
-- JOIN 操作符左边表中符合 WHERE 子句的所有记录将会被返回
select
    e.empno,
    e.ename,
    d.dname
from emp e
left outer join dept d
on e.deptno = d.deptno;
-- 3 右外连接
-- JOIN 操作符右边表中符合 WHERE 子句的所有记录将会被返回
select
    e.empno,
    e.ename,
    d.dname
from emp e
right outer join dept d
on e.deptno = d.deptno;
-- 4 满外连接
select
    e.empno,
    e.ename,
    nvl(e.deptno,d.deptno),
    d.dname
from emp e
full outer join dept d
on e.deptno = d.deptno;
-- 5 多表连接
select
    e.ename,
    d.dname,
    l.loc_name
from emp e
inner join dept d on e.deptno = d.deptno
inner join loc l on d.loc = l.loc;
-- 6 获取左表独有的数据
-- 方法一：先左外连接，然后选择右表数据为null的就是左表独有的
select
    e.empno,
    e.ename,
    e.deptno
from emp e
left outer join dept d on e.deptno = d.deptno
where d.deptno is null;
-- 方法二：where筛选未在右表出现的数据，即左表独有
set hive.mapred.mode = nonstrict;
select
    e.empno,
    e.ename,
    e.deptno
from emp e
where e.deptno not in (
    select d.deptno from dept d
);
-- 7 获取右表独有的数据
-- 方法一：先右外连接，然后选择左表数据为null的就是右表独有的
select
    d.deptno,
    d.dname
from emp e
right outer join dept d on e.deptno = d.deptno
where e.deptno is null;
-- 方法二：where筛选未在左表出现的数据，即右表独有
-- distinct 先去重可以加快mr执行速度
set hive.mapred.mode = nonstrict;
select
    d.deptno,
    d.dname
from dept d
where d.deptno not in (
    select distinct e.deptno from emp e
);
-- 8 获取左右两表独有的部分
-- 方法一：满外连接后，通过条件筛选出左表独有和右表独有的数据
select
    e.empno,
    e.ename,
    e.deptno as edeptno,
    d.deptno as ddeptno,
    d.dname
from emp e
full outer join dept d on e.deptno = d.deptno
where d.deptno is null or e.deptno is null;
-- 方法二：通过分别获取左表独立数据和右表独立数据，联合起来就是全部独立数据
-- union：去重，union all：不去重，所以会效率更高
select
    e.empno,
    e.ename,
    e.deptno as edeptno,
    d.deptno as ddeptno,
    d.dname
from emp e
left outer join dept d on e.deptno = d.deptno
where d.deptno is null
union
select
    e.empno,
    e.ename,
    e.deptno as edeptno,
    d.deptno as ddeptno,
    d.dname
from emp e
right outer join dept d on e.deptno = d.deptno
where e.deptno is null;

-- 三 排序
-- order by / sort by / distribute by / cluster by
-- order by:全局排序，只有一个reducer
-- sort by:为每个 reducer 产生一个排序文件。每个 Reducer 内部进行排序，对全局结果集 来说不是排序
-- distribute by:类似 MR 中 partition(自定义分区)，进行分区，结合 sort by 使用
-- cluster by:当 distribute by 和 sorts by 字段相同时，可以使用 cluster by 方式。只支持升序