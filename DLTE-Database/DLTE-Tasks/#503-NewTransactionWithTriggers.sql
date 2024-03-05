SQL> create or replace trigger check_some
  2  before insert on transaction
  3  for each row
  4  begin
  5  if :new.remarks is null then :new.remarks := 'Some transaction';
  6  end if;
  7  end;
  8  /

Trigger created.

SQL> insert into transaction(transaction_id, transaction_date, transaction_amount, transacti
on_to) values(2024010,'28-feb-2024', 30000, 'Rose');

1 row created.

SQL> commit;

Commit complete.


--SQL Table data
2024010	28-FEB-24	30000	Rose	Some transaction
