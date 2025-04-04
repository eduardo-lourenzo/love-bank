package br.edu.zup.love_bank;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class TransactionEvent extends ApplicationEvent {
    private final JointAccountEntity jointAccountEntity;
    private final String message;

    public TransactionEvent(Object source, JointAccountEntity jointAccountEntity, String message) {
        super(source);
        this.jointAccountEntity = jointAccountEntity;
        this.message = message;
    }
}