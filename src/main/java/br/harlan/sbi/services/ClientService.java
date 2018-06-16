package br.harlan.sbi.services;

import br.harlan.sbi.domain.Client;

import java.util.Optional;

public interface ClientService {
    Optional<Client> findById(Long id);

    Optional<Client> findByName(String name);

    Optional<Client> findByEmail(String email);

    Client save(Client category);
}
