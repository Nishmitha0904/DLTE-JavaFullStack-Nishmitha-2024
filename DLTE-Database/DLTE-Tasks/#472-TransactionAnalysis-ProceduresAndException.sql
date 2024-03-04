--insert new transaction

SQL> create or replace procedure insert_transaction(
  2  transact_id number, transact_date date, transact_amount number, transact_to varchar2, transact_remarks varchar2, transact_info out varchar2)
  3  as
  4  begin
  5  insert into transaction(transaction_id,transaction_date,transaction_amount,transaction_to,remarks) values(transact_id,transact_date,transact_amount,tra
nsact_to,transact_remarks);
  6  transact_info:='Transaction inserted';
  7  exception
  8  when others then
  9  transact_info:='Transaction failed: '||SQLERRM;
 10  end;
 11  /

Procedure created.

SQL> variable err_or_info varchar2(255);
SQL> execute insert_transaction(2024007,'29-feb-2024',50000,'Tim','Education',:err_or_info);

PL/SQL procedure successfully completed.

SQL> print err_or_info;

ERR_OR_INFO
--------------------------------------------------------------------------------
Transaction inserted

SQL> commit;

Commit complete.



--delete transaction of given TO

SQL> create or replace procedure delete_transaction(
  2  transact_to varchar2,
  3  transact_info out varchar2)
  4  as
  5  begin
  6  delete from transaction where transaction_to=transact_to;
  7  transact_info:='Transaction deleted';
  8  exception
  9  when others then
 10  transact_info:='Deletion failed: '||SQLERRM;
 11  end;
 12  /

Procedure created.

SQL> variable err_or_info varchar2(255);
SQL> execute delete_transaction('Peter',:err_or_info);

PL/SQL procedure successfully completed.

SQL> commit;

Commit complete.

SQL> print err_or_info;

ERR_OR_INFO
--------------------------------------------------------------------------------
Transaction deleted






--filter transaction those done for Education

SQL> create or replace procedure filter_transaction(
  2  transact_remarks varchar2,
  3  transact_date out date,
  4  transact_amount out number,
  5  transact_to out varchar2,
  6  transact_info out varchar2)
  7  as
  8  begin
  9  select transaction_date,transaction_amount,transaction_to into transact_date,transact_amount,transact_to from transaction where remarks=transact_remarks;
 10  transact_info:='Transaction fetched successfully';
 11  exception
 12  when no_data_found then
 13  transact_info:='No transaction matched';
 14  when others then
 15  transact_info:='Error due to '||SQLERRM;
 16  end;
 17  /

Procedure created.

SQL> variable found_date varchar2(255);
SQL> variable found_amount number;
SQL> variable found_to varchar2(255);
SQL> variable err_or_info varchar2(255);
SQL> execute filter_transaction('Education',:found_date,:found_amount,:found_to,:err_or_info);

PL/SQL procedure successfully completed.

SQL> print found_date

FOUND_DATE
--------------------------------------------------------------------------------
29-FEB-24

SQL> print found_amount

FOUND_AMOUNT
------------
       50000

SQL> print found_to

FOUND_TO
--------------------------------------------------------------------------------
Tim

SQL> print err_or_info

ERR_OR_INFO
--------------------------------------------------------------------------------
Transaction fetched successfully

