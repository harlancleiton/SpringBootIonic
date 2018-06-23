package br.harlan.sbi.resources;

import br.harlan.sbi.domain.Client;
import br.harlan.sbi.dto.ClientDTO;
import br.harlan.sbi.dto.ClientRegistrationDTO;
import br.harlan.sbi.factory.domain.ClientFactory;
import br.harlan.sbi.factory.dtos.ClientDTOFactory;
import br.harlan.sbi.response.Response;
import br.harlan.sbi.services.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/clients")
public class ClientResource {
    @Autowired
    private ClientService clientService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientResource.class);

    @GetMapping(value = "/{id}")
    public ResponseEntity<Response<Client>> find(@PathVariable Long id) {
        LOGGER.info("Finding Client by Id: {}", id);
        Optional<Client> client = clientService.findById(id);
        Response<Client> response = new Response<>();
        response.setData(client.get());
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<Response<List<ClientDTO>>> findAll() {
        LOGGER.info("Finding all Categories.");
        List<Client> clients = clientService.findAll();
        List<ClientDTO> clientDTOs = new ArrayList<>();
        clients.forEach(client -> clientDTOs.add(ClientDTOFactory.create(client)));
        Response<List<ClientDTO>> response = new Response<>();
        response.setData(clientDTOs);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = "/page")
    public ResponseEntity<Response<Page<ClientDTO>>> findPage(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(name = "direction", defaultValue = "ASC") String direction,
            @RequestParam(name = "orderBy", defaultValue = "name") String orderBy
    ) {
        LOGGER.info("Finding Categories by Page: {}, LinesPerPage: {}, OrderBy: {}, Direction: {}", page, linesPerPage, orderBy, direction);
        Response<Page<ClientDTO>> response = new Response<Page<ClientDTO>>();
        Page<Client> clientPage = clientService.findPage(page, linesPerPage, direction, orderBy);
        Page<ClientDTO> clientDTOPage = clientPage.map(ClientDTOFactory::create);
        response.setData(clientDTOPage);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping
    public ResponseEntity<Response<ClientDTO>> insert(@Valid @RequestBody ClientRegistrationDTO clientRegistrationDTO) {
        LOGGER.info("Inserting Client: {}", clientRegistrationDTO);
        Response<ClientDTO> response = new Response<>();
        Client client = ClientFactory.create(clientRegistrationDTO);
        client = clientService.insert(client);
        response.setData(ClientDTOFactory.create(client));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(client.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody ClientDTO clientDTO, @PathVariable Long id) {
        LOGGER.info("Updating Client: {}", clientDTO);
        clientDTO.setId(id);
        Client client = ClientFactory.create(clientDTO);
        clientService.update(client);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        LOGGER.info("Deleting Client by Id: {}", id);
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
