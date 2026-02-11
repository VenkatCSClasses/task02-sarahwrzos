BankAccount Specification v0.1.1
Overview

BankAccount models a single bank account identified by an email address and a monetary balance.

It supports deposits, withdrawals, and transfers between accounts. All operations validate inputs to ensure balances never become negative and monetary values have at most two decimal places.

This specification is deterministic and stateful: given the same initial state and sequence of operations, the account always reaches the same final state.

This is intended for instructional and testing purposes, not production financial systems.

Project Setup (Maven + JUnit 5)

To use JUnit 5 correctly, your project must follow Maven conventions.

1. Required Folder Structure
cs345-task01/
├─ pom.xml
├─ src/
│  ├─ main/
│  │  └─ java/
│  │      └─ BankAccount.java
│  └─ test/
│     └─ java/
│        └─ BankAccountTest.java


Important:

Implementation files go in src/main/java

Test files go in src/test/java

Tests placed in the project root or src/main/java will NOT resolve JUnit imports

2. Required pom.xml Configuration

Your pom.xml must include JUnit 5 and the Maven Surefire plugin:

<dependencies>
    <!-- JUnit 5 API -->
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-api</artifactId>
        <version>5.10.0</version>
        <scope>test</scope>
    </dependency>

    <!-- JUnit 5 Engine -->
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter-engine</artifactId>
        <version>5.10.0</version>
        <scope>test</scope>
    </dependency>
</dependencies>

<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.11.0</version>
            <configuration>
                <source>17</source>
                <target>17</target>
            </configuration>
        </plugin>

        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>3.1.2</version>
        </plugin>
    </plugins>
</build>


After adding this:

mvn clean test

3. Example Test File

File location:

src/test/java/edu/ithaca/dturnbull/BankAccountTest.java

package edu.ithaca.dturnbull;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BankAccountTest {

    @Test
    void basicAssertions() {
        assertEquals(5, 2 + 3);
        assertTrue(3 < 4);
        assertFalse(5 < 4);
        assertThrows(ArithmeticException.class, () -> { int x = 1 / 0; });
    }
}

Design Principles

    Explicit state only (email + numeric balance)

    Fail fast (invalid inputs throw errors)

    No hidden behavior (no interest, no fees)

    Deterministic (no randomness, I/O, or time-based logic)

    Two-decimal monetary rule (≥ 0 and at most two decimal places)

Type Conventions
    Spec Type	Meaning	Example
    number	Finite numeric value	0, 12.50, 100
    string	UTF-8 text	"user@example.com"
    account	BankAccount instance	acctA
    error	Language-idiomatic error	Exception
    Validation Rules
    Monetary Amount

An amount is valid if:

    Value ≥ 0

    Has at most two decimal places

    Is finite

    Email Identifier

An email is valid if:

    Not null

    Not empty after trimming

    Contains exactly one @

    Local part

    Only alphanumeric characters and ._%+-

    Does not start or end with . or -

    No consecutive dots

    Domain

    Contains at least one .

    Labels non-empty

    Labels do not start or end with -

    Final label

    Length ≥ 2

    Letters only

Construction
    createAccount(email, startingBalance?) → account

Arguments:

    email (string)

    startingBalance (number, optional, default = 0)

Errors:

    Invalid email

    Invalid starting balance

Operations
    getBalance(account) → number

    Returns current balance.

    getEmail(account) → string

    Returns account email identifier.

    deposit(account, amount) → void

Errors:

    Invalid amount

    withdraw(account, amount) → void | error

Behavior:

    If amount ≤ balance → subtract

    If amount > balance → error

Errors:

    Invalid amount

    Insufficient funds

    transfer(fromAccount, amount, toAccount) → void | error

Behavior:

    If:

        Amount valid

        Amount ≤ fromAccount.balance

    Then:

        Subtract from fromAccount

        Add to toAccount

    Errors:

        Invalid amount

        Insufficient funds

        Transfers must be atomic — if the operation fails, neither account changes.

    Invariants

        After construction and every successful operation:

        Balance ≥ 0

        Email valid

        Balance has at most two decimal places

        Failed operations must not modify state.

Testing Requirements

    Tests must verify:

        Valid account creation

        Invalid email rejected

        Invalid starting balance rejected

        Deposit increases balance

        Invalid deposit rejected without state change

        Withdraw reduces balance

        Withdraw exceeding balance fails

        Transfer updates both accounts correctly

        Transfer exceeding balance fails atomically

        Validation methods behave correctly

    All tests must be placed in:

        src/test/java

Non-Goals

    This specification excludes:

        Interest

        Overdraft protection

        Currency formatting

        Persistence

        Concurrency

        Transaction history

        Real-world banking logic

Version History

    v0.1.0 — Initial specification

    v0.1.1 — Added Maven + JUnit 5 setup requirements