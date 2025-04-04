package br.edu.zup.love_bank;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/accounts")
public class JointAccountController {

    private final JointAccountService service;

    public JointAccountController(JointAccountService service) {
        this.service = service;
    }

    @PostMapping("/{id}/deposit")
    public void deposit(@PathVariable Long id, @RequestParam BigDecimal amount) {
        service.deposit(id, amount);
    }

    @PostMapping("/{id}/withdraw")
    public void withdraw(@PathVariable Long id, @RequestParam BigDecimal amount) {
        service.withdraw(id, amount);
    }
}
