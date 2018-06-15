package br.harlan.sbi;

import br.harlan.sbi.entities.Category;
import br.harlan.sbi.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class SpringBootIonicApplication implements CommandLineRunner {
    @Autowired
    private CategoryRepository categoryRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootIonicApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Category category = new Category("Informatica");
        Category category1 = new Category("Escritorio");
        categoryRepository.saveAll(Arrays.asList(category, category1));
    }
}
