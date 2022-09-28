CREATE SCHEMA if not exists dts;

create table if not exists dts.future_device
(
    id                bigserial not null constraint future_device_pk primary key,
    serial_number     varchar(50) not null,
    product_id        varchar(50) not null,
    customer_id        bigint not null,
    created_by         text,
    created_at         timestamp with time zone,
    updated_by         text,
    updated_at         timestamp with time zone,
    CONSTRAINT sn_an_customer_unique UNIQUE (serial_number, product_id, customer_id)
);