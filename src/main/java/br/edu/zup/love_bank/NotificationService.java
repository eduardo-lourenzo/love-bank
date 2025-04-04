package br.edu.zup.love_bank;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationService {

    @EventListener
    public void handleTransactionEvent(TransactionEvent event) {
        JointAccountEntity account = event.getJointAccountEntity();

        sendMessage(account.getHusbandName(), account.getHusbandPhone(), event.getMessage());

        sendMessage(account.getWifeName(), account.getWifePhone(), event.getMessage());
    }

    private void sendMessage(String name, String phoneNumber, String message) {
        // Simulação de envio de mensagem
        System.out.println("Mensagem enviada para " + name + " (" + phoneNumber + "): " + message);
    }
}