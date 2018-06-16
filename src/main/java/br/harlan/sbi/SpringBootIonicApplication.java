package br.harlan.sbi;

import br.harlan.sbi.domain.*;
import br.harlan.sbi.domain.enuns.ClientType;
import br.harlan.sbi.domain.enuns.PaymentStatus;
import br.harlan.sbi.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
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
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private TelephoneRepository telephoneRepository;
    @Autowired
    private RequestRepository requestRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private RequestItemRepository requestItemRepository;

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

        Telephone telephone = new Telephone();
        telephone.setCodeProvince("71");
        telephone.setTelephone("991487946");
        Telephone telephone1 = new Telephone();
        telephone1.setCodeProvince("71");
        telephone1.setTelephone("30478489");

        Client client = new Client();
        Client client1 = new Client();

        client.setName("Maria Silva");
        client.setClientType(ClientType.PHYSICAL_PERSON);
        client.setCpfCnpj("87782461480");
        client.getTelephones().add(telephone);

        client1.setName("João Silva");
        client1.setClientType(ClientType.LEGAL_PERSON);
        client1.setCpfCnpj("51418224278");
        client1.getTelephones().add(telephone1);

        Address address = new Address();
        address.setStreet("Rua dos Bobos");
        address.setNumber("0");
        address.setDistrict("Pituba");
        address.setCep("40000000");
        address.setClient(client);
        address.setCity(city);
        client.setAddress(address);

        Address address1 = new Address();
        address1.setStreet("Rua Paulista");
        address1.setNumber("666");
        address1.setDistrict("Avenida Zorro");
        address1.setCep("74847153");
        address1.setClient(client1);
        address1.setCity(city2);
        client1.setAddress(address1);

        categoryRepository.saveAll(Arrays.asList(category, category1));
        productRepository.saveAll(Arrays.asList(product, product1, product2));
        provinceRepository.saveAll(Arrays.asList(province, province1));
        cityRepository.saveAll(Arrays.asList(city, city1, city2));
        telephoneRepository.saveAll(Arrays.asList(telephone, telephone1));
        addressRepository.saveAll(Arrays.asList(address, address1));
        clientRepository.saveAll(Arrays.asList(client, client1));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Request request = new Request(simpleDateFormat.parse("30/09/2017 17:41"), client, address);
        Request request1 = new Request(simpleDateFormat.parse("30/09/2017 09:28"), client1, address1);
        Payment payment = new PaymentCard(PaymentStatus.PAID_OUT, request, 5);
        request.setPayment(payment);
        Payment payment1 = new PaymentTicket(PaymentStatus.PENDING, request1,
                simpleDateFormat.parse("30/11/2017 03:25"));
        request1.setPayment(payment1);

        client.getRequests().addAll(Arrays.asList(request, request1));

        requestRepository.saveAll(Arrays.asList(request, request1));
        paymentRepository.saveAll(Arrays.asList(payment, payment1));
        clientRepository.save(client);

        RequestItem requestItem = new RequestItem(request, product, 0.00, 1, 2000.00);
        RequestItem requestItem1 = new RequestItem(request, product2, 0.00, 2, 200.00);
        RequestItem requestItem2 = new RequestItem(request1, product1, 100.00, 1, 800.00);

        request.getRequestItems().addAll(Arrays.asList(requestItem, requestItem1));
        request1.getRequestItems().add(requestItem2);

        product.getRequestItems().add(requestItem);
        product1.getRequestItems().add(requestItem2);
        product2.getRequestItems().add(requestItem1);

        requestItemRepository.saveAll(Arrays.asList(requestItem, requestItem1, requestItem2));
    }
}
