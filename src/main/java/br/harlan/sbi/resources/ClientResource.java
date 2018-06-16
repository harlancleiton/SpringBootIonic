package br.harlan.sbi.resources;

import br.harlan.sbi.domain.Client;
import br.harlan.sbi.response.Response;
import br.harlan.sbi.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/clients")
public class ClientResource {
    @Autowired
    private ClientService clientService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Response<Client>> findById(@PathVariable Long id) {
        Optional<Client> client = clientService.findById(id);
        Response<Client> response = new Response<>();
        response.setData(client.get());
        return ResponseEntity.ok(response);
    }
}
