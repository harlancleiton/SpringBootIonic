package br.harlan.sbi.services.impl;

import br.harlan.sbi.domain.*;
import br.harlan.sbi.repositories.*;
import br.harlan.sbi.services.ClientService;
import br.harlan.sbi.services.exceptions.DataIntegrityException;
import br.harlan.sbi.services.exceptions.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintDeclarationException;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private TelephoneRepository telephoneRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceImpl.class);

    @Override
    public Optional<Client> findById(Long id) {
        LOGGER.info("Looking for client by Id: {}", id);
        Optional<Client> client = clientRepository.findById(id);
        if (!client.isPresent())
            throw new ObjectNotFoundException("Object not found. Id: " + id + ". Class: " + client.getClass().getName());
        ;
        return client;
    }

    @Override
    public Optional<Client> findByEmail(String email) {
        LOGGER.info("Looking for client by Email: {}", email);
        Optional<Client> client = clientRepository.findByEmail(email);
        if (!client.isPresent())
            throw new ObjectNotFoundException("Object not found. Email: " + email + ". Class: " + client.getClass().getName());
        ;
        return client;
    }

    @Override
    public List<Client> findAll() {
        LOGGER.info("Find all clients.");
        return clientRepository.findAll();
    }

    @Override
    public Page<Client> findPage(Integer page, Integer linesPerPage, String direction, String orderBy) {
        LOGGER.info("Looking for client by Page: {}", page);
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return clientRepository.findAll(pageRequest);
    }

    @Override
    public Optional<Client> findByName(String name) {
        LOGGER.info("Looking for client by Name: {}", name);
        return clientRepository.findByName(name);
    }

    @Override
    @Transactional
    public Client insert(Client client) {
        LOGGER.info("Persisting client: {}", client);
        client.setId(null);
        Address address = client.getAddress();
        address.setClient(client);
        City city = address.getCity();
        Province province = city.getProvince();
        List<Telephone> telephones = client.getTelephones();
        clientRepository.save(client);
        telephoneRepository.saveAll(telephones);
        addressRepository.save(address);
        cityRepository.save(city);
        provinceRepository.save(province);
        return client;
    }

    @Override
    public Client update(Client client) {
        LOGGER.info("Updating client: {}", client);
        Optional<Client> updateClient = findById(client.getId());
        client = updateData(updateClient, client);
        return clientRepository.save(client);
    }

    @Override
    public void delete(Long id) {
        LOGGER.info("Deleting for client by Id: {}", id);
        findById(id);
        try {
            clientRepository.deleteById(id);
        } catch (DataIntegrityViolationException | ConstraintDeclarationException e) {
            throw new DataIntegrityException(
                    "It is not possible to exclude a Client that contains Requests.", e.getCause());
        }
    }

    private Client updateData(Optional<Client> optionalClient, Client client) {
        Client updateClient = optionalClient.get();
        updateClient.setName(client.getName());
        updateClient.setEmail(client.getEmail());
        return updateClient;
    }
}
