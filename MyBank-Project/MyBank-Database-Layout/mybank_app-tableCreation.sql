--Customer table
CREATE TABLE mybank_app_customer (
    customer_id      NUMBER,
    customer_name    VARCHAR(255),
    customer_address VARCHAR(255),
    customer_status  VARCHAR(255),
    cusomter_contact NUMBER,
    username         VARCHAR(255),
    password         VARCHAR(255)
);

create sequence customer_seq start with 2024001 increment by 1;

alter table mybank_app_customer add constraint customer_seq primary key(customer_id);

alter table mybank_app_customer add unique(username);


--KYC table
CREATE TABLE mybank_app_kyc (
    kyc_number  NUMBER,
    kyc_pan     NUMBER ,
    kyc_aadhaar NUMBER,
    kyc_status  VARCHAR(255),
    customer_id NUMBER,
    FOREIGN KEY ( customer_id )
        REFERENCES mybank_app_customer ( customer_id ) on delete cascade
);
create sequence kyc_seq start with 2025001 increment by 1;
alter table mybank_app_kyc add unique(kyc_pan, kyc_aadhaar);
alter table mybank_app_kyc add constraint kyc_seq primary key(kyc_number);


--Account table 
create table mybank_app_account(
    account_id     NUMBER,
    account_type   VARCHAR(255),
    account_number NUMBER,
    account_status VARCHAR(255),
    customer_id    NUMBER,
    FOREIGN KEY ( customer_id ) REFERENCES mybank_app_customer ( customer_id ),
    UNIQUE ( ACCOUNT_NUMBER ) );

create sequence account_seq start with 1001 increment by 1;

alter table mybank_app_account add constraint account_seq primary key(account_id);


--Deposits_Available table
create table mybank_app_deposits_available(deposit_id number, deposit_name varchar(255), deposit_roi number(8,2), deposit_type varchar(255), deposit_description varchar(255));

create sequence depo_seq start with 001 increment by 1;

alter table mybank_app_deposits_available add constraint depo_seq primary key(deposit_id);


--Insurance_Available table
create table mybank_app_insurance_available(insurance_id number, insurance_type varchar(255), insurance_name varchar(255), insurance_key_benefits varchar(255), insurance_lifetime varchar(255));

create sequence insurance_seq start with 100 increment by 1;

alter table mybank_app_insurance_available add constraint insurance_seq primary key(insurance_id);


--Loan_available
create table mybank_app_loan_available(loan_id number, loan_type varchar(255), loan_name varchar(255), loan_description varchar(255), loan_roi number(5,2));

create sequence loan_seq start with 2001 increment by 1;

alter table mybank_app_loan_available add constraint loan_seq primary key(loan_id);


--Transaction table
CREATE TABLE mybank_app_transaction (
    transaction_id     NUMBER,
    transaction_type   VARCHAR(255),
    transaction_from   number,
    transaction_to     number,
    transaction_date   DATE,
    transaction_amount NUMBER,
    transaction_status VARCHAR(255),
    foreign key(transaction_from) references mybank_app_account(account_number),
    foreign key(transaction_to) references mybank_app_account(account_number) on delete cascade
);

create sequence transact_seq start with 202411110001 increment by 1;

alter table mybank_app_transaction add constraint transact_seq primary key(transaction_id);


--Deposits_availed table
CREATE TABLE mybank_app_deposits_availed (
    deposit_avail_id   NUMBER,
    deposit_name       VARCHAR(255),
    deposit_roi        NUMBER(8, 2),
    deposited_amount   NUMBER(8, 2),
    deposited_duration NUMBER,
    deposit_maturity   DATE,
    customer_id        NUMBER,
    deposit_id         NUMBER,
    FOREIGN KEY ( customer_id )
        REFERENCES mybank_app_customer ( customer_id ),
    FOREIGN KEY ( deposit_id )
        REFERENCES mybank_app_deposits_available ( deposit_id )
);

create sequence deposit_avail_seq start with 2020 increment by 1;

alter table mybank_app_deposits_availed add constraint deposit_avail_seq primary key(deposit_avail_id);


--Insurance_availed table
CREATE TABLE mybank_app_insurance_availed (
    insurance_availed_id   NUMBER,
    insurance_type         VARCHAR(255),
    insurance_name         VARCHAR(255),
    insurance_key_benefits VARCHAR(255),
    insurance_coverage     VARCHAR(255),
    insurance_lifetime     NUMBER,
    insurance_premium      NUMBER,
    customer_id number,
    insurance_id number,
    foreign key(customer_id) references mybank_app_customer(customer_id),
    foreign key(insurance_id) references mybank_app_insurance_available(insurance_id)
);

create sequence insurance_availed_seq start with 2002001 increment by 1;

alter table mybank_app_insurance_availed add constraint insurance_availed_seq primary key(insurance_availed_id);


--Debitcard table
CREATE TABLE mybank_app_debitcard (
    debitcard_number              NUMBER,
    debitcard_cvv                 NUMBER,
    debitcard_pin                 NUMBER,
    debitcard_expiry              DATE,
    debitcard_status              VARCHAR(255),
    debitcard_domestic_limit      NUMBER,
    debitcard_international_limit NUMBER,
    account_number number,
    customer_id number,
    foreign key(account_number) references mybank_app_account(account_number) on delete cascade,
    foreign key(customer_id) references mybank_app_customer(customer_id) on delete cascade
);

create sequence debit_seq start with 2468135720240001 increment by 1;

alter table mybank_app_debitcard add constraint debit_seq primary key(debitcard_number); 


--Loan_availed table
CREATE TABLE mybank_app_loan_availed (
    loan_app_id NUMBER,
    loan_amount NUMBER,
    loan_emi    NUMBER,
    loan_tenure NUMBER,
    customer_id number,
    loan_id number,
    foreign key(customer_id) references mybank_app_customer(customer_id),
    foreign key(loan_id) references mybank_app_loan_available(loan_id)
);

create sequence loan_avail_sequence start with 202001 increment by 1;

alter table mybank_app_loan_availed add constraint loan_avail_seq primary key(loan_app_id);


--Payee table
create table mybank_app_payee(
    payee_id    NUMBER,
    payee_name  VARCHAR(255),
    customer_id NUMBER,
    account_number  NUMBER,
    foreign key(customer_id) references mybank_app_customer(customer_id) on delete cascade,
    foreign key(account_number) references mybank_app_account(account_number) on delete cascade
);

create sequence payee_seq start with 100001 increment by 1;

alter table mybank_app_payee add constraint payee_seq primary key(payee_id);
