create table persona(
    id          bigint auto_increment,
    source_account      varchar(100),
    target_account      varchar(100),
    transfer_type       varchar(100),
    value               varchar(100),
    status              varchar(10)
);