package br.edu.zup.love_bank;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ConcurrentTransactionTest implements CommandLineRunner {

    private final JointAccountService service;
    private final JointAccountRepository repository;

    public ConcurrentTransactionTest(JointAccountService service, JointAccountRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Criando três contas conjuntas
        JointAccountEntity account1 = new JointAccountEntity();
        account1.setHusbandName("John");
        account1.setWifeName("Jane");
        account1.setHusbandPhone("1111-3333");
        account1.setWifePhone("3333-1111");
        account1.setBalance(BigDecimal.valueOf(500));
        account1.setCredit_limit(BigDecimal.valueOf(200));
        repository.save(account1);

        JointAccountEntity account2 = new JointAccountEntity();
        account2.setHusbandName("Mike");
        account2.setWifeName("Anna");
        account2.setHusbandPhone("2222-4444");
        account2.setWifePhone("4444-2222");
        account2.setBalance(BigDecimal.valueOf(1000));
        account2.setCredit_limit(BigDecimal.valueOf(300));
        repository.save(account2);

        JointAccountEntity account3 = new JointAccountEntity();
        account3.setHusbandName("Paul");
        account3.setWifeName("Emma");
        account3.setHusbandPhone("5555-7777");
        account3.setWifePhone("7777-5555");
        account3.setBalance(BigDecimal.valueOf(200));
        account3.setCredit_limit(BigDecimal.valueOf(100));
        repository.save(account3);

        // Criando um pool de threads com 5 threads
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Simulando transações para as três contas
        for (int i = 0; i < 5; i++) {
            executor.submit(() -> service.withdraw(account1.getId(), BigDecimal.valueOf(50))); // Saque na conta 1
            executor.submit(() -> service.deposit(account1.getId(), BigDecimal.valueOf(100))); // Depósito na conta 1

            executor.submit(() -> service.withdraw(account2.getId(), BigDecimal.valueOf(200))); // Saque na conta 2
            executor.submit(() -> service.deposit(account2.getId(), BigDecimal.valueOf(300))); // Depósito na conta 2

            executor.submit(() -> service.withdraw(account3.getId(), BigDecimal.valueOf(30))); // Saque na conta 3
            executor.submit(() -> service.deposit(account3.getId(), BigDecimal.valueOf(50))); // Depósito na conta 3
        }

        // Encerra o executor após concluir as tarefas
        executor.shutdown();
    }
}