package br.edu.zup.love_bank;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class JointAccountEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String husbandName;
    private String wifeName;
    private String husbandPhone;
    private String wifePhone;

    private BigDecimal balance;
    private BigDecimal credit_limit;

    // Construtor vazio exigido pelo JPA
    public JointAccountEntity() {
    }

    public synchronized void deposit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
    }

    public synchronized boolean withdraw(BigDecimal amount) {
        if (this.balance.add(this.credit_limit).compareTo(amount) >= 0) {
            this.balance = this.balance.subtract(amount);
            return true;
        }
        return false;
    }

}
