package br.harlan.sbi.services;

import br.harlan.sbi.entities.Request;

import java.util.Optional;

public interface RequestService {
    Optional<Request> findById(Long id);
}
