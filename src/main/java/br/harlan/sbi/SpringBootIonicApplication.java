package br.harlan.sbi;

import br.harlan.sbi.entities.Category;
import br.harlan.sbi.entities.City;
import br.harlan.sbi.entities.Product;
import br.harlan.sbi.entities.Province;
import br.harlan.sbi.repositories.CategoryRepository;
import br.harlan.sbi.repositories.CityRepository;
import br.harlan.sbi.repositories.ProductRepository;
import br.harlan.sbi.repositories.ProvinceRepository;
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
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private CityRepository cityRepository;

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

        Province province = new Province();
        Province province1 = new Province();

        province.setName("Bahia");
        province.setUf("BA");
        province1.setName("São Paulo");
        province1.setUf("SP");

        City city = new City();
        City city1 = new City();
        City city2 = new City();

        city.setName("Salvador");
        city1.setName("Simões Filho");
        city2.setName("São Paulo");

        city.setProvince(province);
        city1.setProvince(province);
        city2.setProvince(province1);

        province.getCities().addAll(Arrays.asList(city, city1));
        province1.getCities().add(city2);

        categoryRepository.saveAll(Arrays.asList(category, category1));
        productRepository.saveAll(Arrays.asList(product, product1, product2));
        provinceRepository.saveAll(Arrays.asList(province, province1));
        cityRepository.saveAll(Arrays.asList(city, city1, city2));
    }
}
