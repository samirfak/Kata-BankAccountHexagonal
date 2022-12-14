package hexagonalArchitecture.application.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;
@Entity
public class BankAccount {
    //id of the bank account
    @Id
    private Long id;
    //balance of the bank account
    private BigDecimal balance;
    private ArrayList<History> histories;

    public BankAccount() {
        this.histories = new ArrayList<>();
    }
    public BankAccount(Long id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
        this.histories = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public ArrayList<History> getHistories() {
        return histories;
    }

    public void setHistories(ArrayList<History> histories) {
        this.histories = histories;
    }



    /**
     * Withdraw an amount from the bank account
     * @param amount amount to withdraw
     * @return true if successful withdrawal
     *          false otherwise
     */
    public boolean withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.valueOf(0)) <= 0) {
            throw new IllegalArgumentException();
        }
        if(balance.compareTo(amount) < 0) {
            return false;
        }

        balance = balance.subtract(amount);
        histories.add(new History(Operation.WITHDRAWAL, new Date(), amount, balance));
        return true;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    /**
     * Deposit an amount to the bank account
     * @param amount amount to deposit
     */
    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.valueOf(0)) <= 0) {
            throw new IllegalArgumentException();
        }
        balance = balance.add(amount);
        histories.add(new History(Operation.DEPOSIT, new Date(), amount, balance));
    }

    public String history() {
        return histories.stream().map(history -> history.toString()).collect(Collectors.joining("\n"));
    }
}
