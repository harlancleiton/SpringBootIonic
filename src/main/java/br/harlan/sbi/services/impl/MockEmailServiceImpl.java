package br.harlan.sbi.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailServiceImpl extends AbstractEmailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MockEmailServiceImpl.class);

    @Override
    public void sendEmail(SimpleMailMessage mailMessage) {
        LOGGER.info("Email simulation: {}", mailMessage.toString());
    }
}
