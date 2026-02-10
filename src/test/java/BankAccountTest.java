
// --- BankAccountTest.java ---
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BankAccountTest {

    @Test
    void validAccountCreation() {
        BankAccount account = new BankAccount("a@b.com", 200);
        assertEquals("a@b.com", account.getEmail());
        assertEquals(200.0, account.getBalance(), 0.0001);
    }

    @Test
    void getBalanceBasic() {
        BankAccount account = new BankAccount("a@b.com", 200);
        assertEquals(200.0, account.getBalance(), 0.0001);
    }

    @Test
    void withdrawValid() {
        BankAccount account = new BankAccount("a@b.com", 200);
        account.withdraw(100);
        assertEquals(100.0, account.getBalance(), 0.0001);
    }

    @Test
    void withdrawTooMuch() {
        BankAccount account = new BankAccount("a@b.com", 200);
        assertThrows(InsufficientFundsException.class, () -> account.withdraw(300));
        assertEquals(200.0, account.getBalance(), 0.0001);
    }

    @Test
    void withdrawNegative() {
        BankAccount account = new BankAccount("a@b.com", 200);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(-100));
        assertEquals(200.0, account.getBalance(), 0.0001);
    }

    @Test
    void withdrawTooManyDecimals() {
        BankAccount account = new BankAccount("a@b.com", 200);
        assertThrows(IllegalArgumentException.class, () -> account.withdraw(100.111));
        assertEquals(200.0, account.getBalance(), 0.0001);
    }

    @Test
    void depositValid() {
        BankAccount account = new BankAccount("a@b.com", 200);
        account.deposit(50);
        assertEquals(250.0, account.getBalance(), 0.0001);
    }

    @Test
    void depositInvalid() {
        BankAccount account = new BankAccount("a@b.com", 200);
        assertThrows(IllegalArgumentException.class, () -> account.deposit(-100));
        assertEquals(200.0, account.getBalance(), 0.0001);
    }

    @Test
    void constructorInvalidEmail() {
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("", 100));
    }

    @Test
    void constructorInvalidBalance() {
        assertThrows(IllegalArgumentException.class, () -> new BankAccount("a@b.com", -100));
    }

    @Test
    void isAmountValidTrue() {
        assertTrue(BankAccount.isAmountValid(100.11));
    }

    @Test
    void isAmountValidFalse() {
        assertFalse(BankAccount.isAmountValid(100.111));
    }

    @Test
    void isEmailValidTrue() {
        assertTrue(BankAccount.isEmailValid("abc.def@mail.com"));
    }

    @Test
    void isEmailValidFalse() {
        assertFalse(BankAccount.isEmailValid("abc..def@mail.com"));
    }

    @Test
    void transferValid() {
        BankAccount acc1 = new BankAccount("a@b.com", 300);
        BankAccount acc2 = new BankAccount("a@b.com", 100);
        acc1.transfer(acc2, 50);
        assertEquals(250.0, acc1.getBalance(), 0.0001);
        assertEquals(150.0, acc2.getBalance(), 0.0001);
    }

    @Test
    void transferTooMuch() {
        BankAccount acc1 = new BankAccount("a@b.com", 300);
        BankAccount acc2 = new BankAccount("a@b.com", 100);
        assertThrows(IllegalArgumentException.class, () -> acc1.transfer(acc2, 300));
        assertEquals(300.0, acc1.getBalance(), 0.0001);
        assertEquals(100.0, acc2.getBalance(), 0.0001);
    }

    @Test
    void transferInvalidAmountAtomic() {
        BankAccount acc1 = new BankAccount("a@b.com", 300);
        BankAccount acc2 = new BankAccount("a@b.com", 100);
        assertThrows(IllegalArgumentException.class, () -> acc1.transfer(acc2, -50));
        assertEquals(300.0, acc1.getBalance(), 0.0001);
        assertEquals(100.0, acc2.getBalance(), 0.0001);
    }
}
