package br.edu.zup.love_bank;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

public class JointAccountEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String husbandName;
    private String wifeName;
    private String husbandPhone;
    private String wifePhone;

    private BigDecimal balance;
    private BigDecimal limit;

    public synchronized void deposit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    public synchronized boolean withdraw(BigDecimal amount) {
        if (this.balance.add(this.limit).compareTo(amount) >= 0) {
            this.balance = this.balance.subtract(amount);
            return true;
        }
        return false;
    }

    // Getters and setters omitted for brevity
}
