-- DML
-- DML 函数

-- 一 查看系统内置函数
-- 1 查看系统自带的函数
show functions;
-- 2 详细显示自带函数用法(upper函数为例)
desc function extended upper;

-- 二 常用内置函数使用
-- 1 nvl 空字段赋值
-- 如果员工的 comm 为 NULL，则用 -1 代替
select ename,nvl(comm,-1) from emp;

-- 2 case when then else end 条件分支语句
-- 案例：求出不同部门男女各多少人
-- 创建测试表并导入数据
create table emp_sex(
    name string,
    dept_id string,
    sex string
) row format delimited
fields terminated by '\t';
load data local inpath '/root/data/emp_sex.txt' into table emp_sex;
-- 测试
select
    dept_id,
    sum(case sex when '男' then 1 else 0 end) as male_count,
    sum(case sex when '女' then 1 else 0 end) as female_count
from emp_sex
group by dept_id;

-- 3.行转列
    -- 1 concat 将多个字符串或列连接后返回字符串拼接结果
select concat(deptno,'-',dname) from dept;
    -- 2 concat_ws 特殊形式的concat。第一个参数为分隔符,其余为字符串或字符串列表。
-- 注：测试结果发现concat可以拼接int和string，但concat_ws拼接int报错
-- select concat_ws('-',deptno,dname) from dept;  -- error
select concat_ws('-',ename,job) from emp;
    -- 3 collect_set 将某字段的值进行去重汇总，产生 Array 类型字段
select collect_set(dname) from dept;
-- 案例：把星座和血型一样的人归类到一起
-- 创建测试数据表并导入
create table person_info(
    name string,
    constellation string,
    blood_type string
) row format delimited
fields terminated by ',';
load data local inpath '/root/data/person_info.txt' into table person_info;
-- 测试
select
    c_b,
    concat_ws('|',collect_set(name)) as name_list
from (select
          concat_ws(',',constellation,blood_type) as c_b,
          name
      from person_info) t
group by c_b;

-- 4 列转行
-- explode 将 hive 一列中复杂的 Array 或者 Map 结构拆分成多行
-- lateral view
-- lateral view udtf(expression) tableAlias AS columnAlias
-- 案例：将电影分类中的数组数据展开
-- 创建测试数据表并导入
create table movie_info(
    movie string,
    category string
) row format delimited
fields terminated by '\t';
load data local inpath '/root/data/movie_info.txt' into table movie_info;
-- 测试
select
    movie,
    movie_category
from movie_info
lateral view
    explode(split(category,',')) category_tmp as movie_category;

-- 5 窗口函数
-- OVER()：指定分析函数工作的数据窗口大小，这个数据窗口大小可能会随着行的变而变化。
-- CURRENT ROW：当前行
-- n PRECEDING：往前 n 行数据
-- n FOLLOWING：往后 n 行数据
-- UNBOUNDED：起点，
    -- UNBOUNDED PRECEDING  表示从前面的起点，
    -- UNBOUNDED FOLLOWING 表示到后面的终点
-- lag(col,n,default_val)：往前第 n 行数据
-- lead(col,n, default_val)：往后第 n 行数据
-- ntile(n)：把有序窗口的行分发到指定数据的组中，各个组有编号，编号从 1 开始，对 于每一行，NTILE 返回此行所属的组的编号

-- 案例集合
-- 创建测试数据表并导入
create table business(
    name string,
    orderdate string,
    cost int
) row format delimited
fields terminated by ',';
load data local inpath '/root/data/business.txt' into table business;
-- 需求一：查询在 2017 年 4 月份购买过的顾客及总人数
select
    name,
    count(*) over() as cn
from business
where substring(orderdate,1,7) = '2017-04'
group by name;
-- 需求二：查询顾客的购买明细及所有顾客月购买总额
select
    name,
    orderdate,
    cost,
    sum(cost) over(partition by month(orderdate))
from business;
-- 需求三：将每个顾客的 cost 按照日期进行累加
select
    name,
    orderdate,
    cost,
    sum(cost) over() as sample1,
    sum(cost) over(partition by name) as sample2,
    sum(cost) over(partition by name order by orderdate) as sample3,
    sum(cost) over(partition by name order by orderdate rows between UNBOUNDED PRECEDING and current row) as sample4,
    sum(cost) over(partition by name order by orderdate rows between 1 PRECEDING and current row) as sample5,
    sum(cost) over(partition by name order by orderdate rows between 1 PRECEDING and 1 FOLLOWING) as sample6,
    sum(cost) over(partition by name order by orderdate rows between current row and UNBOUNDED FOLLOWING) as sample7
from business;
-- 需求四：查看顾客上次的购买时间
select
    name,
    orderdate,
    cost,
    lag(orderdate,1,'1900-01-01') over(partition by name order by orderdate) as time1
from business;
-- 需求五：查询前20%时间的订单信息
select * from (
      select
          name,
          orderdate,
          cost,
          ntile(5) over(order by orderdate) as sorted
      from business) t
where sorted = 1;

-- rank
-- rank 排序相同时会重复，总数不会变
-- dense_rank 排序相同时会重复，总数会减少
-- row_number 会根据顺序计算

-- 创建测试数据表并导入
create table score(
    name string,
    subject string,
    score int
) row format delimited
fields terminated by '\t';
load data local inpath '/root/data/score.txt' into table score;
-- 案例:计算每门学科成绩排名
select
    name,
    subject,
    score,
    rank() over(partition by subject order by score desc) as rk,
    dense_rank() over (partition by subject order by score desc) as rk2,
    row_number() over (partition by subject order by score desc) as rk3
from score;
