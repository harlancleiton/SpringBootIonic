package br.harlan.sbi.entities;

import br.harlan.sbi.enuns.PaymentStatus;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class PaymentTicket extends Payment {
    private static final long serialVersionUID = 5915360530999426148L;

    private Date paymentDate;
    private Date saleDate;

    public PaymentTicket() {
    }

    public PaymentTicket(PaymentStatus paymentStatus, Request request, Date paymentDate) {
        super(paymentStatus, request);
        this.paymentDate = paymentDate;
        this.saleDate = saleDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    @Override
    public String toString() {
        return "PaymentTicket{" +
                "paymentDate=" + paymentDate +
                ", saleDate=" + saleDate +
                "} " + super.toString();
    }
}
