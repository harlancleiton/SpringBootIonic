package br.harlan.sbi.services.impl;

import br.harlan.sbi.domain.PaymentTicket;
import br.harlan.sbi.domain.Request;
import br.harlan.sbi.domain.RequestItem;
import br.harlan.sbi.domain.enuns.PaymentStatus;
import br.harlan.sbi.repositories.PaymentRepository;
import br.harlan.sbi.repositories.RequestItemRepository;
import br.harlan.sbi.repositories.RequestRepository;
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
            requestItem.setPrice(
                    productService.findById(requestItem.getProduct().getId()).get().getPrice());
            requestItem.setRequest(request);
        }
        requestItemRepository.saveAll(request.getRequestItem());
        return requestRepository.save(request);
    }
}
