package br.harlan.sbi.factory.dtos;

import br.harlan.sbi.domain.Client;
import br.harlan.sbi.dto.ClientDTO;
import org.jetbrains.annotations.NotNull;

public class ClientDTOFactory {
    @NotNull
    public static ClientDTO create(Client client) {
        return new ClientDTO(client.getId(), client.getName(), client.getEmail());
    }
}
