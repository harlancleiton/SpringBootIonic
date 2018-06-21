package br.harlan.sbi.resources;

import br.harlan.sbi.domain.Request;
import br.harlan.sbi.response.Response;
import br.harlan.sbi.services.RequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/requests")
public class RequestResource {
    @Autowired
    private RequestService requestService;

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestResource.class);

    @GetMapping(value = "/{id}")
    public ResponseEntity<Response<Request>> findById(@PathVariable Long id) {
        Optional<Request> request = requestService.findById(id);
        Response<Request> response = new Response<>();
        response.setData(request.get());
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Response<Request>> insert(@RequestBody Request request) {
        LOGGER.info("Inserting Request: {}", request);
        Response<Request> response = new Response<>();
        request = requestService.insert(request);
        response.setData(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(request.getId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }
}
