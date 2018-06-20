package br.harlan.sbi.services.impl;

import br.harlan.sbi.domain.Request;
import br.harlan.sbi.repositories.RequestRepository;
import br.harlan.sbi.services.RequestService;
import br.harlan.sbi.services.exceptions.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RequestServiceImpl implements RequestService {
    @Autowired
    RequestRepository requestRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestServiceImpl.class);

    @Override
    public Optional<Request> findById(Long id) {
        LOGGER.info("Looking for category by id: {}", id);
        Optional<Request> request = requestRepository.findById(id);
        if (!request.isPresent())
            throw new ObjectNotFoundException("Object not found. Id: " + id + ". Class: " + request.getClass().getName());
        ;
        return request;
    }

    @Override
    public Request insert(Request request) {
        LOGGER.info("Persisting request: {}", request);
        return requestRepository.save(request);
    }
}
