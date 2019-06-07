insert into cake_store (cake_store_id, cake_store_name, cake_store_open_time, cake_store_close_time, cake_store_phone_num, cake_store_address, cake_store_close_days, cake_store_station, cake_store_lat, cake_store_lon, is_deleted) values (1, "チリーロ", "12:00", "19:00", "090-3443-9202", "東京都渋谷区猿楽町1-3", "日曜・祝日", "代官山", 35.65035198, 139.7083288, 0);
insert into cake_store (cake_store_id, cake_store_name, cake_store_open_time, cake_store_close_time, cake_store_phone_num, cake_store_address, cake_store_close_days, cake_store_station, cake_store_lat, cake_store_lon, is_deleted) values (2, "パティスリー レザネフォール 恵比寿本店", "10:00", "22:00", "03-6455-0141", "東京都渋谷区恵比寿西1-21-3 グレイス代官山 1F", "不定", "代官山", 35.64321333, 139.7094828, 0);
insert into cake_store (cake_store_id, cake_store_name, cake_store_open_time, cake_store_close_time, cake_store_phone_num, cake_store_address, cake_store_close_days, cake_store_station, cake_store_lat, cake_store_lon, is_deleted) values (3, "リベルターブル 渋谷ヒカリエShinQs", "10:00", "21:00", "03-6434-1848", "東京都渋谷区渋谷2-21-1 渋谷ヒカリエ ShinQs B2F", "なし", "渋谷", 35.65578417, 139.7067922, 0);

insert into cafe_store (cafe_store_id, cafe_store_name, cafe_store_open_time, cafe_store_close_time, cafe_store_phone_num, cafe_store_address, cafe_store_close_days, cafe_store_seat_num, cafe_store_smoking_seat, cafe_store_lat, cafe_store_lon, cafe_store_station, is_deleted) values (1, "珈琲店トップ 東急本店", "11:00", "21:00", "03-3477-3361", "東京都渋谷区道玄坂2-24-1 東急百貨店 渋谷本店 8F", "無休", 000, 0, 35.6574925, 139.6993414, "神泉", 0);
insert into cafe_store (cafe_store_id, cafe_store_name, cafe_store_open_time, cafe_store_close_time, cafe_store_phone_num, cafe_store_address, cafe_store_close_days, cafe_store_seat_num, cafe_store_smoking_seat, cafe_store_lat, cafe_store_lon, cafe_store_station, is_deleted) values (2, "珈琲店トップ 渋谷道玄坂店", "06:30", "21:30", "03-3461-1624", "東京都渋谷区道玄坂2-29-7 道玄坂センタービル B1F", "日曜", 069, 0, 35.65606, 139.7010761, "渋谷", 0);
insert into cafe_store (cafe_store_id, cafe_store_name, cafe_store_open_time, cafe_store_close_time, cafe_store_phone_num, cafe_store_address, cafe_store_close_days, cafe_store_seat_num, cafe_store_smoking_seat, cafe_store_lat, cafe_store_lon, cafe_store_station, is_deleted) values (3, "珈琲店トップ 渋谷駅前店", "08:00", "00:30", "03-3461-5686", "東京都渋谷区道玄坂2-3-1 渋谷駅前ビル B1F", "日曜・祝日", 050, 1, 35.65606333, 139.7031122, "渋谷", 0);

insert into cake_menu (cake_menu_id, cake_store_id, cake_menu_name, cake_menu_img_url, is_deleted) values (1, 1, "ショートケーキ", "shortcake.jpeg", 0);
insert into cake_menu (cake_menu_id, cake_store_id, cake_menu_name, cake_menu_img_url, is_deleted) values (2, 1, "チョコレートケーキ", "chocolatecake.jpeg", 0);
insert into cake_menu (cake_menu_id, cake_store_id, cake_menu_name, cake_menu_img_url, is_deleted) values (3, 1, "チーズケーキ", "cheezecake.jpeg", 0);
insert into cake_menu (cake_menu_id, cake_store_id, cake_menu_name, cake_menu_img_url, is_deleted) values (4, 2, "ショートケーキ", "shortcake.jpeg", 0);
insert into cake_menu (cake_menu_id, cake_store_id, cake_menu_name, cake_menu_img_url, is_deleted) values (5, 2, "チョコレートケーキ", "chocolatecake.jpeg", 0);
insert into cake_menu (cake_menu_id, cake_store_id, cake_menu_name, cake_menu_img_url, is_deleted) values (6, 2, "チーズケーキ", "cheezecake.jpeg", 0);
insert into cake_menu (cake_menu_id, cake_store_id, cake_menu_name, cake_menu_img_url, is_deleted) values (7, 3, "ショートケーキ", "shortcake.jpeg", 0);
insert into cake_menu (cake_menu_id, cake_store_id, cake_menu_name, cake_menu_img_url, is_deleted) values (8, 3, "チョコレートケーキ", "chocolatecake.jpeg", 0);
insert into cake_menu (cake_menu_id, cake_store_id, cake_menu_name, cake_menu_img_url, is_deleted) values (9, 3, "チーズケーキ", "cheezecake.jpeg", 0);


insert into cafe_menu (cafe_menu_id, cafe_store_id, cafe_menu_name, cafe_menu_img_url, cafe_menu_price, is_deleted) values (1, 1, "コーヒー", "coffee.jpeg", 350, 0);
insert into cafe_menu (cafe_menu_id, cafe_store_id, cafe_menu_name, cafe_menu_img_url, cafe_menu_price, is_deleted) values (2, 1, "紅茶", "tea.jpeg", 400, 0);
insert into cafe_menu (cafe_menu_id, cafe_store_id, cafe_menu_name, cafe_menu_img_url, cafe_menu_price, is_deleted) values (3, 1, "オレンジジュース", "orangejuice.jpeg", 300, 0);
insert into cafe_menu (cafe_menu_id, cafe_store_id, cafe_menu_name, cafe_menu_img_url, cafe_menu_price, is_deleted) values (4, 2, "コーヒー", "coffee.jpeg", 350, 0);
insert into cafe_menu (cafe_menu_id, cafe_store_id, cafe_menu_name, cafe_menu_img_url, cafe_menu_price, is_deleted) values (5, 2, "紅茶", "tea.jpeg", 400, 0);
insert into cafe_menu (cafe_menu_id, cafe_store_id, cafe_menu_name, cafe_menu_img_url, cafe_menu_price, is_deleted) values (6, 2, "オレンジジュース", "orangejuice.jpeg", 300, 0);
insert into cafe_menu (cafe_menu_id, cafe_store_id, cafe_menu_name, cafe_menu_img_url, cafe_menu_price, is_deleted) values (7, 3, "コーヒー", "coffee.jpeg", 350, 0);
insert into cafe_menu (cafe_menu_id, cafe_store_id, cafe_menu_name, cafe_menu_img_url, cafe_menu_price, is_deleted) values (8, 3, "紅茶", "tea.jpeg", 400, 0);
insert into cafe_menu (cafe_menu_id, cafe_store_id, cafe_menu_name, cafe_menu_img_url, cafe_menu_price, is_deleted) values (9, 3, "オレンジジュース", "orangejuice.jpeg", 300, 0);


insert into cake_store_img (cake_store_id, cake_store_img_id, cake_store_img_url, cake_store_img_primary) values (1, 1, "cakes1.jpeg", 1);
insert into cake_store_img (cake_store_id, cake_store_img_id, cake_store_img_url, cake_store_img_primary) values (1, 2, "cakes2.jpeg", 0);
insert into cake_store_img (cake_store_id, cake_store_img_id, cake_store_img_url, cake_store_img_primary) values (2, 1, "cakes1.jpeg", 0);
insert into cake_store_img (cake_store_id, cake_store_img_id, cake_store_img_url, cake_store_img_primary) values (2, 2, "cakes2.jpeg", 1);
insert into cake_store_img (cake_store_id, cake_store_img_id, cake_store_img_url, cake_store_img_primary) values (3, 1, "cakes1.jpeg", 1);
insert into cake_store_img (cake_store_id, cake_store_img_id, cake_store_img_url, cake_store_img_primary) values (3, 2, "cakes2.jpeg", 0);

insert into cafe_store_img (cafe_store_id, cafe_store_img_id, cafe_store_img_url, cafe_store_img_primary) values (1, 1, "cafe1.jpeg", 1);
insert into cafe_store_img (cafe_store_id, cafe_store_img_id, cafe_store_img_url, cafe_store_img_primary) values (1, 2, "cafe2.jpeg", 0);
insert into cafe_store_img (cafe_store_id, cafe_store_img_id, cafe_store_img_url, cafe_store_img_primary) values (2, 1, "cafe1.jpeg", 0);
insert into cafe_store_img (cafe_store_id, cafe_store_img_id, cafe_store_img_url, cafe_store_img_primary) values (2, 2, "cafe2.jpeg", 1);
insert into cafe_store_img (cafe_store_id, cafe_store_img_id, cafe_store_img_url, cafe_store_img_primary) values (3, 1, "cafe1.jpeg", 1);
insert into cafe_store_img (cafe_store_id, cafe_store_img_id, cafe_store_img_url, cafe_store_img_primary) values (3, 2, "cafe2.jpeg", 0);


insert into cake_stock (cake_menu_id, stock_num) values (1, 050);
insert into cake_stock (cake_menu_id, stock_num) values (2, 010);
insert into cake_stock (cake_menu_id, stock_num) values (3, 000);
insert into cake_stock (cake_menu_id, stock_num) values (4, 050);
insert into cake_stock (cake_menu_id, stock_num) values (5, 010);
insert into cake_stock (cake_menu_id, stock_num) values (6, 000);
insert into cake_stock (cake_menu_id, stock_num) values (7, 050);
insert into cake_stock (cake_menu_id, stock_num) values (8, 010);
insert into cake_stock (cake_menu_id, stock_num) values (9, 000);


insert into user (user_id, user_name, user_mail_address, user_password, user_status, user_role) values (1, "admin", "admin@r.recruit.co.jp", "b109f3bbbc244eb82441917ed06d618b9008dd09b3befd1b5e07394c706a8bb980b1d7785e5976ec049b46df5f1326af5a2ea6d103fd07c95385ffab0cacbc86", 0, 1);
insert into user (user_id, user_name, user_mail_address, user_password, user_status, user_role) values (2, "user1", "user1@r.recruit.co.jp", "b109f3bbbc244eb82441917ed06d618b9008dd09b3befd1b5e07394c706a8bb980b1d7785e5976ec049b46df5f1326af5a2ea6d103fd07c95385ffab0cacbc86", 0, 2);
insert into user (user_id, user_name, user_mail_address, user_password, user_status, user_role) values (3, "user2", "user2@r.recruit.co.jp", "b109f3bbbc244eb82441917ed06d618b9008dd09b3befd1b5e07394c706a8bb980b1d7785e5976ec049b46df5f1326af5a2ea6d103fd07c95385ffab0cacbc86", 0, 2);
insert into user (user_id, user_name, user_mail_address, user_password, user_status, user_role) values (4, "boot", "boot@ml.cocorou.jp", "d5fb5a0d4d4245f8c2e322c6abbe18d471a0cd6294e21bfc837e99e3d0d31c200b496169a6f30d7e40129373da1e495725915a8ebb3a3fb0facc99a11b5997ef", 0, 2);

insert into order_info (order_id, user_id, cake_store_id, cafe_store_id, order_num, is_checked) values (1, 1, 1, 1, 1, 0);
insert into order_info (order_id, user_id, cake_store_id, cafe_store_id, order_num, is_checked) values (2, 1, 1, 1, 1, 0);
insert into order_info (order_id, user_id, cake_store_id, cafe_store_id, order_num, is_checked) values (3, 1, 1, 1, 1, 0);
insert into order_info (order_id, user_id, cake_store_id, cafe_store_id, order_num, is_checked) values (4, 1, 1, 1, 1, 0);
insert into order_info (order_id, user_id, cake_store_id, cafe_store_id, order_num, is_checked) values (5, 1, 1, 1, 1, 0);

insert into station_location (station_id, station_name, station_lat, station_lon) values (1, "渋谷", 35.658034, 139.701636);
insert into station_location (station_id, station_name, station_lat, station_lon) values (2, "代官山", 35.650547, 139.704221);
insert into station_location (station_id, station_name, station_lat, station_lon) values (3, "原宿", 35.669968, 139.709008);