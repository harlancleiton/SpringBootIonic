package br.harlan.sbi.config;

import br.harlan.sbi.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class ProfileTestConfig {
    @Autowired
    DBService dbService;

    @Bean
    public boolean instantiateDatabase() throws Exception {
        dbService.instantiateTestDatabase();
        return true;
    }
}
