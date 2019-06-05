create database CAKEOUT default character set utf8mb4;
create user sasaki@localhost identified by 'password';
grant all on CAKEOUT.* to sasaki@localhost;
flush privileges;
