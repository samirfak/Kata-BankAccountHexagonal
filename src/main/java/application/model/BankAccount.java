package application.model;

import java.math.BigDecimal;

public class BankAccount {
    //id of the bank account
    private Long id;
    //balance of the bank account
    private BigDecimal balance;

    public BankAccount(Long id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }

    /**
     * Withdraw an amount from the bank account
     * @param amount amount to withdraw
     * @return true if successful withdrawal
     *          false otherwise
     */
    public boolean withdraw(BigDecimal amount) {
        if(balance.compareTo(amount) < 0) {
            return false;
        }

        balance = balance.subtract(amount);
        return true;
    }

    /**
     * Deposit an amount to the bank account
     * @param amount amount to deposit
     */
    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
    }
}
