package br.harlan.sbi.factory.domain;

import br.harlan.sbi.domain.Client;
import br.harlan.sbi.domain.enuns.ClientType;
import br.harlan.sbi.dto.ClientDTO;
import br.harlan.sbi.dto.ClientRegistrationDTO;
import br.harlan.sbi.utils.PasswordUtil;
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

    @NotNull
    public static Client create(ClientRegistrationDTO clientRegistrationDTO) {
        Client client = new Client();
        client.setName(clientRegistrationDTO.getName());
        client.setEmail(clientRegistrationDTO.getEmail());
        client.setPassword(PasswordUtil.encoder(clientRegistrationDTO.getPassword()));
        client.setCpfCnpj(clientRegistrationDTO.getCpfCnpj());
        client.setClientType(ClientType.PHYSICAL_PERSON);
        client.setAddress(AddressFactory.create(clientRegistrationDTO.getAddress()));
        clientRegistrationDTO.getTelephones().forEach(telephoneDTO ->
                client.getTelephones().add(TelephoneFactory.create(telephoneDTO)));
        return client;
    }
}
