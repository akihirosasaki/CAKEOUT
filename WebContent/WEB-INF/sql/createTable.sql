use cakeout;

drop table if exists order_info;
drop table if exists user;
drop table if exists cake_store;
drop table if exists cafe_store;
drop table if exists cake_menu;
drop table if exists cafe_menu;
drop table if exists cake_store_img;
drop table if exists cafe_store_img;
drop table if exists cake_stock;
drop table if exists station_location;

commit;

create table if not exists order_info (
order_id int(5) auto_increment not null,
user_id int(5) not null,
cake_store_id int(5) not null,
cafe_store_id int(5) not null,
order_num int(2) not null,
created_at timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
updated_at timestamp DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
is_checked int(1) not null,
primary key (order_id)
);

create table if not exists user(
    user_id int(5) auto_increment not null,
    user_name varchar(16) not null,
    user_mail_address varchar(32) not null,
    user_password  varchar(256) not null,
    user_status int(1) not null,
    user_role int(1) not null,
    primary key (user_id)
);

create table if not exists cake_store (
    cake_store_id int(5) auto_increment not null,
    cake_store_name varchar(32) not null,
    cake_store_open_time varchar(5),
    cake_store_close_time varchar(5),
    cake_store_phone_num varchar(13),
    cake_store_address varchar(64) not null,
    cake_store_close_days varchar(5),
    cake_store_station varchar(10),
    cake_store_lat double(9,6) not null,
    cake_store_lon double(9,6) not null,
    is_deleted int(1) not null,
    primary key (cake_store_id)
);

create table if not exists cafe_store(
    cafe_store_id int(5) auto_increment not null,
    cafe_store_name varchar(32) not null,
    cafe_store_open_time varchar(5),
    cafe_store_close_time varchar(5),
    cafe_store_phone_num varchar(13) not null,
    cafe_store_address varchar(64) not null,
    cafe_store_close_days varchar(5),
    cafe_store_seat_num int(3),
    cafe_store_smoking_seat int(1),
    cafe_store_lat double(9,6) not null,
    cafe_store_lon double(9,6) not null,
    cafe_store_station varchar(10) not null,
    is_deleted int(1) not null,
    primary key (cafe_store_id)
);

create table if not exists cake_menu (
    cake_menu_id int(3) auto_increment not null,
    cake_store_id int(5) not null,
    cake_menu_name varchar(32) not null,
    cake_menu_img_url varchar(256) not null,
    is_deleted int(1) not null,
    primary key (cake_menu_id)
);

create table if not exists cafe_menu (
     cafe_menu_id int(3) auto_increment not null,
     cafe_store_id int(5) not null,
     cafe_menu_name varchar(32) not null,
     cafe_menu_img_url varchar(256) not null,
     cafe_menu_price int(5) not null,
     is_deleted int(1) not null,
     primary key (cafe_menu_id)
);

create table if not exists cake_store_img (
    cake_store_id int(5) not null,
    cake_store_img_id int(2) not null,
    cake_store_img_url varchar(256) not null,
    cake_store_img_primary int(1) not null,
    primary key(cake_store_id, cake_store_img_id)
);

create table if not exists cafe_store_img (
    cafe_store_id int(5) not null,
    cafe_store_img_id int(2) not null,
    cafe_store_img_url varchar(256) not null,
    cafe_store_img_primary int(1) not null,
    primary key(cafe_store_id, cafe_store_img_id)
);

create table if not exists cake_stock (
    cake_menu_id int(3) not null,
    stock_num int(3) not null,
    primary key (cake_menu_id)
);

create table if not exists station_location (
	station_id int(3) not null,
	station_name varchar(10) not null,
	station_lat double(9,6) not null,
	station_lon double(9,6) not null,
	primary key(station_id)
);
