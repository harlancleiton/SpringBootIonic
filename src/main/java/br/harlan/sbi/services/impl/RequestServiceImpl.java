package br.harlan.sbi.services.impl;

import br.harlan.sbi.domain.*;
import br.harlan.sbi.domain.enuns.PaymentStatus;
import br.harlan.sbi.repositories.AddressRepository;
import br.harlan.sbi.repositories.PaymentRepository;
import br.harlan.sbi.repositories.RequestItemRepository;
import br.harlan.sbi.repositories.RequestRepository;
import br.harlan.sbi.services.ClientService;
import br.harlan.sbi.services.ProductService;
import br.harlan.sbi.services.RequestService;
import br.harlan.sbi.services.TicketService;
import br.harlan.sbi.services.exceptions.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Service
public class RequestServiceImpl implements RequestService {
    @Autowired
    RequestRepository requestRepository;
    @Autowired
    TicketService ticketService;
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    ProductService productService;
    @Autowired
    RequestItemRepository requestItemRepository;
    @Autowired
    ClientService clientService;
    @Autowired
    AddressRepository addressRepository;

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
    @Transactional
    public Request insert(Request request) {
        LOGGER.info("Persisting request: {}", request);
        request.setId(null);
        request.setInstant(new Date());
        Client client = clientService.findById(request.getClient().getId()).get();
        request.setClient(client);
        Address address = addressRepository.findById(request.getAddress().getId()).get();
        request.setAddress(address);
        request.getPayment().setPaymentStatus(PaymentStatus.PENDING);
        request.getPayment().setRequest(request);
        if (request.getPayment() instanceof PaymentTicket) {
            PaymentTicket paymentTicket = (PaymentTicket) request.getPayment();
            ticketService.fillInTicket(paymentTicket, request.getInstant());
        }
        request = requestRepository.save(request);
        paymentRepository.save(request.getPayment());
        for (RequestItem requestItem : request.getRequestItem()) {
            requestItem.setDiscount(0.0);
            requestItem.setProduct(productService.findById(requestItem.getProduct().getId()).get());
            requestItem.setPrice(requestItem.getProduct().getPrice());
            requestItem.setRequest(request);
        }
        requestItemRepository.saveAll(request.getRequestItem());
        LOGGER.info("Request: {}", request);
        return requestRepository.save(request);
    }
}
