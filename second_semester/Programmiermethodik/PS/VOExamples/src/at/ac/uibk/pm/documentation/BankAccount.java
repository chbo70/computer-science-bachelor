package at.ac.uibk.pm.documentation;

import java.math.BigDecimal;

/**
 * A bank account that allows depositing and withdrawing money without overdrawing.
 *
 * @author Franz Hacker
 * @version 1.1.1
 * @since 1.0.0
 */
public class BankAccount {

    private final String accountHolder;

    /*
     * In the context of doing computations on variables holding money,
     * facing the problem of rounding errors when doing computations with floatingpoint values,
     * we decided to use BigDecimal values
     * to circumvent the problem of rounding errors in our context,
     * accepting the somewhat cumbersome arithmetic operations and slower performance for
     * BigDecimal.
     */
    private BigDecimal balance;

    /**
     * Creates a bank account with an initial balance for the account holder.
     *
     * @param accountHolder Account holder's name.
     * @param balance       Initial balance.
     *                      Must not be negative, must not be null.
     * @throws IllegalArgumentException if balance is not greater than zero.
     * @throws NullPointerException     if balance is null.
     * @see BankAccount#BankAccount(String) to instantiate with no initial balance.
     */
    public BankAccount(final String accountHolder, final BigDecimal balance) {
        if (balance == null) {
            throw new NullPointerException("Expected positive balance but got null");
        }
        if (balance.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    String.format("Expected positive balance but got %s", balance));
        }
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    /**
     * Creates an empty bank account for the account holder.
     *
     * @param accountHolder Account holder's name.
     * @see BankAccount#BankAccount(String, BigDecimal) to instantiate with an initial balance.
     */
    public BankAccount(final String accountHolder) {
        this(accountHolder, BigDecimal.ZERO);
    }

    /**
     * Increases the balance of the account.
     *
     * @param amount Amount to be deposited.
     *               Must not be negative, must not be null.
     * @throws IllegalArgumentException if amount is not greater than zero.
     * @throws NullPointerException     if amount is null.
     */
    public void deposit(final BigDecimal amount) {
        if (balance == null) {
            throw new NullPointerException("Expected positive amount but got null");
        }
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    String.format("Expected positive amount but got %s", amount));
        }
        this.balance = this.balance.add(amount);
    }

    /**
     * Withdraws the amount from the account.
     *
     * <p>
     * Withdraws money up to the total balance. Overdrawing is impossible. In case overdraw would
     * happen only the available money would be withdrawn.
     *
     * @param amount Amount to be withdrawn.
     *               Must not be negative, must not be null.
     * @return Amount that was actually withdrawn.
     * If the balance was not enough to cover the amount requested by the parameter only the
     * available balance
     * is returned.
     * @throws IllegalArgumentException if amount is not greater than zero.
     * @throws NullPointerException     if amount is null.
     */
    public BigDecimal withdraw(final BigDecimal amount) {
        if (balance == null) {
            throw new NullPointerException("Expected positive amount but got null");
        }
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    String.format("Expected positive amount but got %s", amount));
        }
        BigDecimal withdrawnAmount = balance.compareTo(amount) < 0 ? balance : amount;
        balance = balance.subtract(withdrawnAmount);
        return withdrawnAmount;
    }

    /**
     * Returns the name of the account holder.
     *
     * @return Name of the account holder.
     */
    public String getAccountHolder() {
        return accountHolder;
    }

    /**
     * Returns the current balance.
     *
     * @return Current balance
     */
    public BigDecimal getBalance() {
        return balance;
    }
}
