package br.harlan.sbi.services;

import br.harlan.sbi.domain.Client;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ClientService {
    Optional<Client> findById(Long id);

    Optional<Client> findByName(String name);

    Optional<Client> findByEmail(String email);

    Optional<Client> findByCpfCnpj(String cpf);

    Client insert(Client client);

    Client update(Client client);

    void delete(Long id);

    List<Client> findAll();

    Page<Client> findPage(Integer page, Integer linesPerPage, String direction, String orderBy);
}
