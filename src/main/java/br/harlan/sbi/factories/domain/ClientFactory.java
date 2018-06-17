package br.harlan.sbi.factories.domain;

import br.harlan.sbi.domain.Client;
import br.harlan.sbi.domain.enuns.ClientType;
import br.harlan.sbi.dtos.ClientDTO;
import br.harlan.sbi.dtos.ClientRegistrationDTO;
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

    public static Client create(ClientRegistrationDTO clientRegistrationDTO) {
        Client client = new Client();
        client.setEmail(clientRegistrationDTO.getEmail());
        client.setName(clientRegistrationDTO.getName());
        client.setCpfCnpj(clientRegistrationDTO.getCpf());
        client.setClientType(ClientType.PHYSICAL_PERSON);
        client.setAddress(AddressFactory.create(clientRegistrationDTO.getAddress()));
        clientRegistrationDTO.getTelephones().forEach(telephoneDTO ->
                client.getTelephones().add(TelephoneFactory.create(telephoneDTO)));
        return client;
    }
}
