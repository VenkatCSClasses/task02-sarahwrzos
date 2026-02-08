You are a coding agent.

Using SPEC.md as the behavior specification and tests.yaml as the test cases:

1. Implement the BankAccount library in Java 17.
2. Generate the following files **separately**, exactly as named:
   - BankAccount.java — contains the class implementation
   - BankAccountTest.java — contains JUnit 5 tests that fully cover all test cases from tests.yaml
   - README.md — describes usage, installation, and testing
3. Do not invent extra behavior; follow SPEC.md exactly.
4. For each file, start with a clear header, e.g.:

--- BankAccount.java ---
<file contents>

5. Ensure all tests from tests.yaml pass when compiled and run.
6. The test file must cover:
   - valid account creation
   - invalid email rejection
   - invalid starting balance rejection
   - deposits (valid and invalid)
   - withdrawals (valid, invalid, insufficient funds)
   - transfers (valid, invalid, atomic behavior)
   - validation method behavior
7. Do not combine multiple files into one output block.
