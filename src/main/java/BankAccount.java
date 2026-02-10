
// --- BankAccount.java ---
import java.math.BigDecimal;

public class BankAccount {
    private final String email;
    private BigDecimal balance;

    public BankAccount(String email) {
        this(email, 0.0);
    }

    public BankAccount(String email, double startingBalance) {
        if (!isEmailValid(email)) {
            throw new IllegalArgumentException("Invalid email");
        }
        if (!isAmountValid(startingBalance)) {
            throw new IllegalArgumentException("Invalid starting balance");
        }
        this.email = email.trim();
        this.balance = BigDecimal.valueOf(startingBalance);
    }

    public String getEmail() {
        return email;
    }

    public double getBalance() {
        return balance.doubleValue();
    }

    public void deposit(double amount) {
        validateAmountOrThrow(amount);
        balance = balance.add(BigDecimal.valueOf(amount));
    }

    public void withdraw(double amount) {
        validateAmountOrThrow(amount);
        BigDecimal withdrawal = BigDecimal.valueOf(amount);
        if (withdrawal.compareTo(balance) > 0) {
            throw new InsufficientFundsException("Insufficient funds");
        }
        balance = balance.subtract(withdrawal);
    }

    public void transfer(BankAccount toAccount, double amount) {
        if (toAccount == null) {
            throw new IllegalArgumentException("Invalid target account");
        }
        validateAmountOrThrow(amount);
        BigDecimal transferAmount = BigDecimal.valueOf(amount);
        if (transferAmount.compareTo(balance) >= 0) {
            throw new IllegalArgumentException("Insufficient funds");
        }
        balance = balance.subtract(transferAmount);
        toAccount.balance = toAccount.balance.add(transferAmount);
    }

    public static boolean isAmountValid(double amount) {
        if (!Double.isFinite(amount)) {
            return false;
        }
        if (amount < 0) {
            return false;
        }
        BigDecimal value = BigDecimal.valueOf(amount);
        return value.scale() <= 2;
    }

    public static boolean isEmailValid(String email) {
        if (email == null) {
            return false;
        }
        String trimmed = email.trim();
        if (trimmed.isEmpty()) {
            return false;
        }
        int atIndex = trimmed.indexOf('@');
        if (atIndex <= 0 || atIndex != trimmed.lastIndexOf('@')) {
            return false;
        }
        String local = trimmed.substring(0, atIndex);
        String domain = trimmed.substring(atIndex + 1);
        if (local.isEmpty() || domain.isEmpty()) {
            return false;
        }
        if (local.startsWith(".") || local.endsWith(".") || local.startsWith("-") || local.endsWith("-")) {
            return false;
        }
        if (local.contains("..")) {
            return false;
        }
        for (int i = 0; i < local.length(); i++) {
            char c = local.charAt(i);
            if (!Character.isLetterOrDigit(c) && c != '.' && c != '_' && c != '%' && c != '+' && c != '-') {
                return false;
            }
        }
        if (!domain.contains(".")) {
            return false;
        }
        String[] labels = domain.split("\\.", -1);
        if (labels.length < 2) {
            return false;
        }
        for (int i = 0; i < labels.length; i++) {
            String label = labels[i];
            if (label.isEmpty()) {
                return false;
            }
            if (label.startsWith("-") || label.endsWith("-")) {
                return false;
            }
            if (i == labels.length - 1) {
                if (label.length() < 2) {
                    return false;
                }
                for (int j = 0; j < label.length(); j++) {
                    if (!Character.isLetter(label.charAt(j))) {
                        return false;
                    }
                }
            } else {
                for (int j = 0; j < label.length(); j++) {
                    char c = label.charAt(j);
                    if (!Character.isLetterOrDigit(c) && c != '-') {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private static void validateAmountOrThrow(double amount) {
        if (!isAmountValid(amount)) {
            throw new IllegalArgumentException("Invalid amount");
        }
    }
}

class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String message) {
        super(message);
    }
}
