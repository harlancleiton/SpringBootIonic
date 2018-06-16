package br.harlan.sbi.services.impl;

import br.harlan.sbi.domain.Client;
import br.harlan.sbi.repositories.ClientRepository;
import br.harlan.sbi.services.ClientService;
import br.harlan.sbi.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Optional<Client> findById(Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if (!client.isPresent())
            throw new ObjectNotFoundException("Object not found. Id: " + id + ". Class: " + Client.class.getName());
        return client;
    }

    @Override
    public Optional<Client> findByName(String name) {
        return clientRepository.findByName(name);
    }

    @Override
    public Optional<Client> findByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }
}
