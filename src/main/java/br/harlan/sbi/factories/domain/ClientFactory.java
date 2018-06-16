package br.harlan.sbi.factories.domain;

import br.harlan.sbi.domain.Client;
import br.harlan.sbi.dtos.ClientDTO;
import org.jetbrains.annotations.NotNull;

public class ClientFactory {
    @NotNull
    public static Client create(ClientDTO clientDTO) {
        Client client = new Client();
        client.setId(clientDTO.getId());
        client.setName(clientDTO.getName());
        client.setEmail(clientDTO.getEmail());
        return client;
    }
}
