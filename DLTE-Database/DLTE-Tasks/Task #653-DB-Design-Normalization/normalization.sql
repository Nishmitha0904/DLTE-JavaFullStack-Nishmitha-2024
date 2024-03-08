--Before normalization

SQL> create table mybank_recharge_before_norm(username varchar(255), upi varchar(20), mobile_number number(13), email varchar(255), wallet_type varchar(10), recharged_date date, recharged_provider varchar(255), recharged_to varchar(255), recharged_amount number);

Table created.


--First normal form

SQL> create table mybank_recharge_first_norm(username varchar(255) primary key, upi varchar(20), mobile_number number(13), email varchar(255), wallet_type varchar(10), recharged_date date, recharged_provider varchar(255), recharged_to varchar(255), recharged_amount number);

Table created.


--Second normal form

SQL> create table mybank_recharge_second_form(username varchar(255) primary key, upi varchar
(20), mobile_number number(13), email varchar(255));

Table created.

SQL> create table recharge_second_form(wallet_type varchar(10), recharged_date date, recharged_provider varchar(255), recharged_to varchar(255), recharged_amount number);

Table created.


--Third normal form

SQL> create table mybank_recharge_third_form(username varchar(255) primary key, upi varchar(
20), mobile_number number(13), email varchar(255), wallet_type varchar(10));

Table created.

SQL> alter table recharge_second_form add primary key (wallet_type);

Table altered.

SQL> alter table mybank_recharge_third_form add FOREIGN key(wallet_type) references recharge
_second_form(wallet_type);

Table altered.