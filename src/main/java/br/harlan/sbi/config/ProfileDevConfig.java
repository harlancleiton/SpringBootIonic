package br.harlan.sbi.config;

import br.harlan.sbi.services.EmailService;
import br.harlan.sbi.services.impl.EmailServiceImpl;
import br.harlan.sbi.utils.PopulateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class ProfileDevConfig {
    @Autowired
    PopulateData populateData;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategyDdlAuto;

    @Bean
    public boolean instantiateDatabase() throws Exception {
        if (strategyDdlAuto.equals("create"))
            populateData.instantiateTestDatabase();
        return true;
    }

    @Bean
    public EmailService emailService() {
        return new EmailServiceImpl();
    }
}
