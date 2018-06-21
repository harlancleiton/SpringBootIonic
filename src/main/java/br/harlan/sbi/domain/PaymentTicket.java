package br.harlan.sbi.domain;

import br.harlan.sbi.domain.enuns.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@JsonTypeName("paymentTicket")
public class PaymentTicket extends Payment {
    private static final long serialVersionUID = 5915360530999426148L;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date paymentDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
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
