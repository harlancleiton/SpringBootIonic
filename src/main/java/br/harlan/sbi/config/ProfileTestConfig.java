package br.harlan.sbi.config;

import br.harlan.sbi.services.EmailService;
import br.harlan.sbi.services.impl.MockEmailServiceImpl;
import br.harlan.sbi.utils.PopulateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class ProfileTestConfig {
    @Autowired
    PopulateData populateData;

    @Bean
    public boolean instantiateDatabase() throws Exception {
        populateData.instantiateTestDatabase();
        return true;
    }

    @Bean
    public EmailService getEmailService() {
        return new MockEmailServiceImpl();
    }
}
