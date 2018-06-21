package br.harlan.sbi.services.impl;


import br.harlan.sbi.domain.PaymentTicket;
import br.harlan.sbi.services.TicketService;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class TicketServiceImpl implements TicketService {
    @Override
    public void fillInTicket(PaymentTicket paymentTicket, Date instant) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(instant);
        calendar.add(Calendar.DAY_OF_MONTH, 5);
        paymentTicket.setPaymentDate(calendar.getTime());
    }
}
