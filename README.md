# Kata-BankAccountHexagonal

This application is a simulation of a BankAccount. It implements the hexagonal architecture. 
The adapter is of Spring / Rest API and the persisstence is with an H2 Database. 

As a bank client, we can:
- make a deposit to an account : /account/{id}/deposit/{amount}
- make a withdrawal from an account: /account/{id}/withdraw/{amount}
- see the history of an account : /account/{id}/history

The default server port is 8180.
