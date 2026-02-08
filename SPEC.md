# BankAccount Specification v0.1.0

## Overview

BankAccount models a single bank account identified by an email address and a monetary balance.

It supports deposits, withdrawals, and transfers between accounts. All operations validate inputs to ensure balances never become negative and monetary values have at most two decimal places.

This specification is deterministic and stateful: given the same initial state and sequence of operations, the account always reaches the same final state.

This is intended for instructional and testing purposes, not production financial systems.

---

## Design Principles

### 1. Explicit state only
Each account stores:
- An email identifier
- A numeric balance

### 2. Fail fast
Invalid inputs immediately produce errors.

### 3. No hidden behavior
No interest, no fees, no rounding beyond validation rules.

### 4. Deterministic
No randomness, I/O, or time-based behavior.

### 5. Two-decimal monetary rule
All monetary amounts must:
- Be non-negative
- Have at most two decimal places

---

## Type Conventions

| Spec Type | Meaning | Example |
|---------|--------|---------|
| `number` | Finite numeric value | `0`, `12.50`, `100` |
| `string` | UTF-8 text | `"user@example.com"` |
| `account` | BankAccount instance | `acctA` |
| `error` | Language-idiomatic error | Exception, Result error |

---

## Validation Rules

### Monetary Amount

An amount is **valid** if:
- The value is ≥ 0
- The value has at most two decimal places
- The value is finite

Implementations must provide:

isEmailValid(email) → boolean

### Email Identifier

An email is **valid** if:
- It is not null
- It is not empty after trimming
- It contains exactly one `@`

#### Local part
- Contains only alphanumeric characters and `._%+-`
- Does not start or end with `.` or `-`
- Does not contain consecutive dots

#### Domain
- Contains at least one `.`
- Labels are non-empty
- Labels do not start or end with `-`

#### Final label
- Has length ≥ 2
- Contains only letters

Implementations must provide:

isEmailValid(email) → boolean

## Construction

### createAccount(email, startingBalance?) → account

Creates a new account.

#### Arguments
- `email` (string)
- `startingBalance` (number, optional, default = 0)

#### Errors
- Invalid email → error
- Invalid starting balance → error

---

## Operations

### getBalance(account) → number
Returns the current balance.

---

### getEmail(account) → string
Returns the account email identifier.

---

### deposit(account, amount) → void
Adds money to the account.

**Errors**
- Invalid amount → error

---

### withdraw(account, amount) → void | error
Removes money from the account.

**Behavior**
- If `amount ≤ balance` → subtract
- If `amount > balance` → error

**Errors**
- Invalid amount → error
- Insufficient funds → error

---

### transfer(fromAccount, amount, toAccount) → void | error
Moves money between accounts.

**Behavior**
If:
- `amount` is valid
- `amount ≤ fromAccount.balance`

Then:
- Subtract from `fromAccount`
- Add to `toAccount`

**Errors**
- Invalid amount → error
- Insufficient funds → error

Transfers must be **atomic** — if the operation fails, neither account changes.

---

## Invariants

After construction and after every successful operation:
- Balance ≥ 0
- Email is valid
- Balance has at most two decimal places

Failed operations must not modify state.

---

## Testing Requirements

Implementations must include tests verifying:
- Valid account creation
- Invalid email rejected
- Invalid starting balance rejected
- Deposit increases balance
- Invalid deposit rejected without state change
- Withdraw reduces balance
- Withdraw exceeding balance fails
- Transfer updates both accounts correctly
- Transfer exceeding balance fails atomically
- Validation methods behave correctly

---

## Non-Goals

This specification intentionally excludes:
- Interest
- Overdraft protection
- Currency formatting
- Persistence
- Concurrency
- Transaction history
- Real-world banking logic

---

## Version History

- **v0.1.0** — Initial specification