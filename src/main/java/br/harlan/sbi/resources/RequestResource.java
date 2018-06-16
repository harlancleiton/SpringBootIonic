package br.harlan.sbi.resources;

import br.harlan.sbi.domain.Request;
import br.harlan.sbi.response.Response;
import br.harlan.sbi.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/requests")
public class RequestResource {
    @Autowired
    private RequestService requestService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Response<Request>> findById(@PathVariable Long id) {
        Optional<Request> request = requestService.findById(id);
        Response<Request> response = new Response<>();
        response.setData(request.get());
        return ResponseEntity.ok(response);
    }
}
