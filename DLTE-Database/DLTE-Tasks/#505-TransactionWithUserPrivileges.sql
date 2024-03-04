SQL> connect sys as sysdba
Enter password:
Connected.


Grant select privilege to user1
--------------------------------------------------------
SQL> create user user1 identified by user123;
User created.
SQL> grant connect to user1
  2  ;
Grant succeeded.
SQL> grant select on transaction to user1;
Grant succeeded.
---------------------------------------------------------


Grant delete privilege to user2
---------------------------------------------------------
SQL> create user user2 identified by user234;
User created.
SQL> grant connect to user2;
Grant succeeded.
SQL> grant delete on transaction to user2;
Grant succeeded.
---------------------------------------------------------


Grant select privilege to user3
---------------------------------------------------------
SQL> create user user3 identified by user345;
User created.
SQL> grant connect to user3;
Grant succeeded.
SQL> grant select on transaction to user3;
Grant succeeded.
---------------------------------------------------------


Grant insert privilege to user4
---------------------------------------------------------
SQL> create user user4 identified by user456;
User created.
SQL> grant connect to user4;
Grant succeeded.
SQL> grant insert on transaction to user4;
Grant succeeded.
---------------------------------------------------------


Grant update privilege to user5
---------------------------------------------------------
SQL> create user user4 identified by user456;
User created.
SQL> grant connect to user4;
Grant succeeded.
SQL> grant insert on transaction to user4;
Grant succeeded.
