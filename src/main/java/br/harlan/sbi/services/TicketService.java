package br.harlan.sbi.services;

import br.harlan.sbi.domain.PaymentTicket;

import java.util.Date;

public interface TicketService {
    void fillInTicket(PaymentTicket paymentTicket, Date instant);
}
