package br.edu.zup.love_bank;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class TransactionEvent extends ApplicationEvent {
    private final Long accountId;
    private final String message;
    private final String husbandPhone;
    private final String wifePhone;

    public TransactionEvent(Object source, Long accountId, String message, String husbandPhone, String wifePhone) {
        super(source);
        this.accountId = accountId;
        this.message = message;
        this.husbandPhone = husbandPhone;
        this.wifePhone = wifePhone;
    }
}