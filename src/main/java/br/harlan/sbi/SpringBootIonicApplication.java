package br.harlan.sbi;

import br.harlan.sbi.entities.Category;
import br.harlan.sbi.entities.Product;
import br.harlan.sbi.repositories.CategoryRepository;
import br.harlan.sbi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class SpringBootIonicApplication implements CommandLineRunner {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootIonicApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Category category = new Category("Informatica");
        Category category1 = new Category("Escritorio");

        Product product = new Product("Computador", 2000.00);
        Product product1 = new Product("Impressora", 900.00);
        Product product2 = new Product("Mouse", 200.00);

        category.getProducts().addAll(Arrays.asList(product, product2));
        category1.getProducts().addAll(Arrays.asList(product, product1, product2));

        product.getCategories().addAll(Arrays.asList(category, category1));
        product1.getCategories().add(category1);
        product2.getCategories().addAll(Arrays.asList(category, category1));

        categoryRepository.saveAll(Arrays.asList(category, category1));
        productRepository.saveAll(Arrays.asList(product, product1, product2));
    }
}
