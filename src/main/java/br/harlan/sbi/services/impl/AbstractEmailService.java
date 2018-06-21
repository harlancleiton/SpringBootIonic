package br.harlan.sbi.services.impl;

import br.harlan.sbi.domain.Request;
import br.harlan.sbi.services.EmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class AbstractEmailService implements EmailService {
    @Value("${default.sender}")
    private String emailSender;

    @Override
    public void sentOrderConfirmationEmail(Request request) {
        SimpleMailMessage simpleMailMessage = prepareSimpleMailMessageFromRequest(request);
        sendEmail(simpleMailMessage);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromRequest(Request request) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(request.getClient().getEmail());
        mailMessage.setFrom(emailSender);
        mailMessage.setSubject("Pedido " + request.getId() + " confirmado!");
        mailMessage.setSentDate(new Date(System.currentTimeMillis()));
        mailMessage.setText(request.toString());
        return mailMessage;
    }
}
