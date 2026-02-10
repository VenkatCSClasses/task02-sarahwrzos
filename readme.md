--- README.md ---
# BankAccount (Java 17)

A small instructional BankAccount library that supports balance management, deposits, withdrawals, and transfers with strict validation rules.

## Setup

This project uses Maven and JUnit 5. Ensure Java 17 is installed and available on your PATH.

## Installation

No external installation steps are required beyond Maven. The implementation is in [src/main/java/BankAccount.java](src/main/java/BankAccount.java).

## Compilation

```bash
mvn compile
```

## Testing

```bash
mvn test
```

## Usage

```java
BankAccount account = new BankAccount("user@example.com", 100.00);
account.deposit(25.50);
account.withdraw(10.00);
System.out.println(account.getBalance());
```
