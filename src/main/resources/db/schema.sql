create table `t_order_20220216`
(
    `test_id`   varchar(25)  not null comment 'test_id',
    `create_time` timestamp(3) not null comment '时间'
);


insert into `t_order_20220216` (test_id, create_time)
values (1, '2022-02-16 07:00:00.100'),
       (2, '2022-02-16 11:00:00.100'),
       (3, '2022-02-16 17:00:00.100');


create table `t_order_20220217`
(
    `test_id`   varchar(25)  not null comment 'test_id',
    `create_time` timestamp(3) not null comment '时间'
);

insert into `t_order_20220217` (test_id, create_time)
values (4, '2022-02-17 07:00:00.200'),
       (5, '2022-02-17 11:00:00.200'),
       (6, '2022-02-17 17:00:00.200');


create table `t_order_20220218`
(
    `test_id`   varchar(25)  not null comment 'test_id',
    `create_time` timestamp(3) not null comment '时间'
);

insert into `t_order_20220218` (test_id, create_time)
values (7, '2022-02-18 07:00:00.300'),
       (8, '2022-02-18 11:00:00.300'),
       (9, '2022-02-18 17:00:00.300');

create table `t_order_20220219`
(
    `test_id`   varchar(25)  not null comment 'test_id',
    `create_time` timestamp(3) not null comment '数据时间'
);

insert into `t_order_20220219` (test_id, create_time)
values (10, '2022-02-19 07:00:00.400'),
       (11, '2022-02-19 11:00:00.400'),
       (12, '2022-02-19 17:00:00.400');

create table `t_order_20220220`
(
    `test_id`   varchar(25)  not null comment 'test_id',
    `create_time` timestamp(3) not null comment '时间'
);

insert into `t_order_20220220` (test_id, create_time)
values (13, '2022-02-20 07:00:00.500'),
       (14, '2022-02-20 11:00:00.500'),
       (15, '2022-02-20 17:00:00.500');