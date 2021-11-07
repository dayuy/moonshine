-- 查找每个部门工资最高的人的详细信息
select ename, sal, temp.max_sal, emp.deptno
    from emp, (
        select deptno, max(sal) as max_sal
        from emp
        group by deptno
    ) temp
    where emp.deptno = temp.deptno and emp.sql = temp.max_sal;


-- 查找每个部门大于平均工资的人信息
select ename, sal, temp.avg_sal, emp.deptno
    from emp, (
        select deptno, avg(sal) as avg_sal
        from emp
        group by deptno
    ) temp
    where emp.deptno = temp.deptno and emp.sal > temp.avg_sal


--- 查询每个部门的信息（部门名、编号、地址）和人员数量
--- 分析：1、部门名、编号、地址 来自dept表
---      2、各个部门的人员数量 -- 构建一个临时表
select dname, dept.deptno, loc, tmp.per_num  -- select temp.*, dname, loc //也可以这么写
    from dept, (
        select count(*) as per_num, deptno
        from emp
        group by deptno
    ) tmp
    where tmp.deptno = dept.deptno;

--- 自我复制
create table my_tab01 (
    id INT unsigned AUTO_INCREMENT PRIMARY KEY,
    a_name VARCHAR(32),
    sal DOUBLE,
    job varchar(32),
    deptno INT
)engine=InnoDB AUTO_INCREMENT=2, default charset = utf8;

--- 1、先把emp表的记录复制到my_tab01
insert into my_tab01
    (id, a_name, sal, job, deptno)
    select empno, ename, sal, job, deptno from emp;
--- 2、自我复制
insert into my_tab01
    select * from my_tab01;


----- 创建一张表，并有重复数据，去重
create table temp like tab02;
insert into temp select distinct * from tab02;
select * from temp;
delete from tab02;
insert into tab02 select * from temp;
drop table temp;


--- union all 将两个查询结果合并，不会去重
select ename, sal, job from emp where sal > 2500
union all
select ename, sal, job from emp where job='MANAGE'

--- union 将两个查询结果合并，会去重
select ename, sal, job from emp where sal > 2500
union
select ename, sal, job from emp where job='MANAGE'


--- 外连接
--- 例：列出部门名称和这些部门的员工名称和工作（要求显示出没有员工的部门）
--- 分析：一个部门没有员工，也就没有与员工表关联的点，也就查不出这个部门
select dname, ename, job
    from emp, dept
    where emp.deptno = dept.deptno
    order by dname;

--- 左外连接：左侧的表完全显示
select uname, grade
    from stu left join exam
    on stu.id = exam.id

--- 右外连接：右侧的表完全显示
select uname, grade
    from stu right join exam
    on stu.id = exam.id;


--- 主键
create table t17(
    id int primary key,
    uname varchar(32)
);

--- 复合主键
create table t18(
    id int unique,
    uname varchar(32),
    email varchar(32),
    primary key (id, uname));


--- 外键使用
--- 创建主表 my_class
create table my_class(
    id int primary key,
    uname varchar(32) not null default '');
--- 创建从表 my_stu
create table my_stu(
    id int primary key,
    uname varchar(32) not null default '',
    class_id int,
    foreign key (class_id) references my_class(id);


--- 自增长
create table t24
    (id int primary key auto_increment,
    email varchar(32) not null default '',
    uname varchar(32) not null default '');
----插入
insert into t24 values(null, 'jack@qq.com', 'jack');
----插入
insert into t24 (email, uname) values('hsp@qq.com', 'hasp');
----修改默认的自增长
alter table t24 auto_increment = 100;

----创建索引（普通）
create index empno_index on t24 (uname);
alter table t25 add index id_index (id);
--- 查看索引
show indexes from t25;
--- 添加唯一索引 （如果列的值是不重复的，优先考虑unique，否则使用普通索引）
create unique index id_index on t25 (id);
--- 删除索引
drop index uname_index on t24;
--- 查看索引
show index from t24;
show indexes from t24;
show keys from t24;


--- 事务：保证一组操作成功或失败，保证数据的一致性
-- 开始事务
start transaction;
-- 保存点a
savepoint a;
insert into t27 values (100, 'tom');
savepoint b;
insert into t27 values (200, 'yom');
--- 回退至保存点a
rollback to a;
--- 提交事务
commit;


---- 事务隔离级别
-- 查看
select @@transaction_isolation;
select @@global.transaction_isolation;
-- 设置
set session transaction_isolation = 'REPEATABLE-READ';
set global transaction_isolation = 'REPEATABLE-READ';

--- 引擎
show engines;

--- 视图
--- 创建
create view act_view01
    as
    select user_name, sex from actress;
--- 查看
select * from act_view01;
desc act_view01;
--- 查看创建时
show create view act_view0i;
drop view act_view01;
---- 例：使用三表联合查询，将得到的结果，构建成视图
create view emp_view03
    as
    select empno, ename, dname, grade
    from emp, dept, salgrade
    where emp.deptno = dept.deptno and (sal between losal and hisal);
desc emp_view03;


--- 用户管理
create user 'test01'@'localhost' identified by '123456';  --- test01是用户名，localhost为登录IP
select host, user, authentication_string from mysql.user;
drop user 'test01'@'localhost';
set password for 'test01'@'localhost' = 'abcd';
--- 授予权限 给test01分配查看和添加 testdb库.news表的权限
grant select,update,delete on test.actress to 'test01'@'localhost';
--- 收回权限
revoke all on test.actress from 'test01'@'localhost';
revoke select,update,delete on test.actress from 'test01'@'localhost';

--- 练习
select ename, (sal + nullif(comm, 0)) * 13 as "年收入" from emp；
select 700 + null from dual;
--- 找出部门10中所有经理MANAGER，部门20中所有办事员（CLERK），还有既不是经理也不是办事员但薪资大于或等于2000的所有员工。
select * from emp
    where (deptno = 10 and job='MANAGER')
    or (deptno = 20 and job = 'CLERK')
    or (job != 'MANAGER' and job != 'CLERK' and sal >= 2000);

select * from emp where comm is null or nullif(comm, 0) < 100;
select last_day(now()) - 2 from dual; //倒数第3天
--- 入职超过2年的员工
select * from emp where date_add(hiredate, interval 12 year ) < now();
--- 以首字母小写的方式显示所有员工姓名
select concat(lcast(substr(ename, 1, 1)), substr(ename, 2) ) from emp;
select * from emp where length(ename) = 5;
select * from emp where ename not like '%R%';
select * left(ename, 3) from emp;
select * replace(ename, 'A', 'a') from emp;
select ename, datediff(now(), hiredate) from emp;
select count(*) as c, deptno
    from emp
    group by deptno
    having c > 1;
--- 列出受雇日期晚于其直接上级的所有员工: 把emp表做两个表
select worker.ename as "员工名", worker.hiredate as "入职时间", leader.ename as "上级名"
    from emp worker, emp leader
    where worker.hiredate > leader.hiredate
    and worker.mgr = leader.empno;
--- 列出在每个部门工作的员工数量、平均工资和平均服务期限（时间单位）。
select count(*) as "部门员工数", deptno, avg(sal) as "部门平均工资", format(avg(datediff(now(), hiredate) / 365), 2) as "平均服务期限"
    from emp
    group by deptno;
--- 列出所有部门的详细信息和部门人数
select dept.*, tmp.c
    from dept, (
        select count(*) as c, deptno
            from emp
            group by deptno
    ) tmp
    where dept.deptno = tmp.deptno;

select tmp.*, department.departmentId
    from department, (
        select SUM(num) as nums, deptname
        from class
        group by deptname
        having nums >= 30
    ) tmp
    where department.deptname = tmp.deptname;

update aclass set num = num - 1
    where classId = (
        select classId
            from student
            where aname = "张三"
    );
delete
    from student
    where aname = "张三";








































