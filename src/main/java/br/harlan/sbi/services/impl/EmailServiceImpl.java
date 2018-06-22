package br.harlan.sbi.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class EmailServiceImpl extends AbstractEmailService {
    @Autowired
    private MailSender mailSender;

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Override
    public void sendEmail(SimpleMailMessage mailMessage) {
        LOGGER.info("MailMessage: {}", mailMessage);
        mailSender.send(mailMessage);
    }
}
