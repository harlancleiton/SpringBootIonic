package br.harlan.sbi;

import br.harlan.sbi.domain.*;
import br.harlan.sbi.domain.enuns.ClientType;
import br.harlan.sbi.domain.enuns.PaymentStatus;
import br.harlan.sbi.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class SpringBootIonicApplication implements CommandLineRunner {


    public static void main(String[] args) {
        SpringApplication.run(SpringBootIonicApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {

    }
}
