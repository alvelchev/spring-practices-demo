DROP TABLE IF EXISTS quartz_fired_triggers;
DROP TABLE IF EXISTS quartz_paused_trigger_grps;
DROP TABLE IF EXISTS quartz_scheduler_state;
DROP TABLE IF EXISTS quartz_locks;
DROP TABLE IF EXISTS quartz_simple_triggers;
DROP TABLE IF EXISTS quartz_simprop_triggers;
DROP TABLE IF EXISTS quartz_cron_triggers;
DROP TABLE IF EXISTS quartz_bytea_triggers;
DROP TABLE IF EXISTS quartz_triggers;
DROP TABLE IF EXISTS quartz_job_details;
DROP TABLE IF EXISTS quartz_calendars;

create table quartz_job_details
(
    sched_name        varchar(120) not null,
    job_name          varchar(200) not null,
    job_group         varchar(200) not null,
    description       varchar(250) null,
    job_class_name    varchar(250) not null,
    is_durable        varchar(250)   not null,
    is_nonconcurrent  varchar(250)   not null,
    is_update_data    varchar(250)   not null,
    requests_recovery varchar(250)   not null,
    job_data          bytea        null,
    primary key (sched_name, job_name, job_group)
);

create table quartz_triggers
(
    sched_name     varchar(120) not null,
    trigger_name   varchar(200) not null,
    trigger_group  varchar(200) not null,
    job_name       varchar(200) not null,
    job_group      varchar(200) not null,
    description    varchar(250) null,
    next_fire_time bigint null,
    prev_fire_time bigint null,
    priority       integer      null,
    trigger_state  varchar(250)  not null,
    trigger_type   varchar(250)   not null,
    start_time     bigint not null,
    end_time       bigint null,
    calendar_name  varchar(200) null,
    misfire_instr  smallint null,
    job_data       bytea        null,
    primary key (sched_name, trigger_name, trigger_group),
    foreign key (sched_name, job_name, job_group)
        references quartz_job_details (sched_name, job_name, job_group)
);

create table quartz_simple_triggers
(
    sched_name      varchar(120) not null,
    trigger_name    varchar(200) not null,
    trigger_group   varchar(200) not null,
    repeat_count    bigint not null,
    repeat_interval bigint not null,
    times_triggered bigint not null,
    primary key (sched_name, trigger_name, trigger_group),
    foreign key (sched_name, trigger_name, trigger_group)
        references quartz_triggers (sched_name, trigger_name, trigger_group)
);

create table quartz_cron_triggers
(
    sched_name      varchar(120) not null,
    trigger_name    varchar(200) not null,
    trigger_group   varchar(200) not null,
    cron_expression varchar(200) not null,
    time_zone_id    varchar(80),
    primary key (sched_name, trigger_name, trigger_group),
    foreign key (sched_name, trigger_name, trigger_group)
        references quartz_triggers (sched_name, trigger_name, trigger_group)
);

create table quartz_simprop_triggers
(
    sched_name    varchar(120)   not null,
    trigger_name  varchar(200)   not null,
    trigger_group varchar(200)   not null,
    str_prop_1    varchar(512)   null,
    str_prop_2    varchar(512)   null,
    str_prop_3    varchar(512)   null,
    int_prop_1    int            null,
    int_prop_2    int            null,
    long_prop_1   bigint         null,
    long_prop_2   bigint         null,
    dec_prop_1    numeric(13, 4) null,
    dec_prop_2    numeric(13, 4) null,
    bool_prop_1   varchar(250)     null,
    bool_prop_2   varchar(250)     null,
    primary key (sched_name, trigger_name, trigger_group),
    foreign key (sched_name, trigger_name, trigger_group)
        references quartz_triggers (sched_name, trigger_name, trigger_group)
);

create table quartz_bytea_triggers
(
    sched_name    varchar(120) not null,
    trigger_name  varchar(200) not null,
    trigger_group varchar(200) not null,
    bytea_data    bytea        null,
    primary key (sched_name, trigger_name, trigger_group),
    foreign key (sched_name, trigger_name, trigger_group)
        references quartz_triggers (sched_name, trigger_name, trigger_group)
);

create table quartz_calendars
(
    sched_name    varchar(120) not null,
    calendar_name varchar(200) not null,
    calendar      bytea        not null,
    primary key (sched_name, calendar_name)
);

create table quartz_paused_trigger_grps
(
    sched_name    varchar(120) not null,
    trigger_group varchar(200) not null,
    primary key (sched_name, trigger_group)
);

create table quartz_fired_triggers
(
    sched_name        varchar(120) not null,
    entry_id          varchar(95)  not null,
    trigger_name      varchar(200) not null,
    trigger_group     varchar(200) not null,
    instance_name     varchar(200) not null,
    fired_time        bigint not null,
    sched_time        bigint not null,
    priority          integer      not null,
    state             varchar(16)  not null,
    job_name          varchar(200) null,
    job_group         varchar(200) null,
    is_nonconcurrent  varchar(250)   null,
    requests_recovery varchar(250)   null,
    primary key (sched_name, entry_id)
);

create table quartz_scheduler_state
(
    sched_name        varchar(120) not null,
    instance_name     varchar(200) not null,
    last_checkin_time bigint not null,
    checkin_interval  bigint not null,
    primary key (sched_name, instance_name)
);

create table quartz_locks
(
    sched_name varchar(120) not null,
    lock_name  varchar(40)  not null,
    primary key (sched_name, lock_name)
);
