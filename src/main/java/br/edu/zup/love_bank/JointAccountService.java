package br.edu.zup.love_bank;

import jakarta.transaction.Transactional;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class JointAccountService {
    private final JointAccountRepository repository;
    private final ApplicationEventPublisher eventPublisher;

    public JointAccountService(JointAccountRepository repository, ApplicationEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    @Transactional
    public void deposit(Long accountId, BigDecimal amount) {
        JointAccountEntity account = repository.findById(accountId).orElseThrow();
        account.deposit(amount);
        repository.save(account);

        if (amount.compareTo(BigDecimal.valueOf(100)) > 0) {
            String message = "Depósito acima de 100 reais realizado por um dos cônjuges.";
            eventPublisher.publishEvent(new TransactionEvent(this, account, message));
        }
    }

    @Transactional
    public void withdraw(Long accountId, BigDecimal amount) {
        JointAccountEntity account = repository.findById(accountId).orElseThrow();
        boolean success = account.withdraw(amount);
        repository.save(account);

        if (success) {
            if (amount.compareTo(BigDecimal.valueOf(50)) > 0) {
                String message = "Saque acima de 50 reais realizado.";
                eventPublisher.publishEvent(new TransactionEvent(this, account, message));
            }
            if (account.getBalance().compareTo(BigDecimal.ZERO) < 0) {
                String message = "Conta entrou no limite.";
                eventPublisher.publishEvent(new TransactionEvent(this, account, message));
            }
        } else {
            throw new IllegalArgumentException("Saldo insuficiente.");
        }
    }
}