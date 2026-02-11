You are a coding agent.

Using SPEC.md as the complete behavioral specification and tests.yaml as the required test definitions, perform the following tasks:

Implement the BankAccount library in Java 17, strictly following SPEC.md.

Generate the following files separately, exactly as named:

BankAccount.java — the full class implementation

BankAccountTest.java — JUnit 5 tests that fully cover all cases from tests.yaml

README.md — includes setup, installation, compilation, and testing instructions

Do not introduce any behavior that is not explicitly defined in SPEC.md.

For each file, begin with a clear header in this exact format:

--- BankAccount.java ---
<file contents>


Ensure:

All tests in tests.yaml are implemented.

All tests pass when compiled and run with Java 17.

The project follows the Maven + JUnit 5 setup described in SPEC.md.

Test files are structured under src/test/java.

Implementation files are structured under src/main/java.

The test suite must explicitly cover:

    Valid account creation

    Invalid email rejection

    Invalid starting balance rejection

    Deposits (valid and invalid)

    Withdrawals (valid, invalid, insufficient funds)

    Transfers (valid, invalid, atomic behavior)

    Validation method behavior

Do not combine multiple files into a single output block.

Do not skip or ignore the project setup requirements described in SPEC.md.

Only produce the three required files, properly separated by headers. No additional commentary.