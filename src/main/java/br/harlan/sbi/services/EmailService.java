package br.harlan.sbi.services;

import br.harlan.sbi.domain.Request;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
    void sentOrderConfirmationEmail(Request request);

    void sendEmail(SimpleMailMessage mailMessage);
}
