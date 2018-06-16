package br.harlan.sbi.domain;

import br.harlan.sbi.domain.enuns.PaymentStatus;

import javax.persistence.Entity;

@Entity
public class PaymentCard extends Payment {
    private static final long serialVersionUID = -3118868162522316058L;

    private Integer numberParcels;

    public PaymentCard() {
    }

    public PaymentCard(PaymentStatus paymentStatus, Request request, Integer numberParcels) {
        super(paymentStatus, request);
        this.numberParcels = numberParcels;
    }

    public Integer getNumberParcels() {
        return numberParcels;
    }

    public void setNumberParcels(Integer numberParcels) {
        this.numberParcels = numberParcels;
    }

    @Override
    public String toString() {
        return "PaymentCard{" +
                "numberParcels=" + numberParcels +
                "} " + super.toString();
    }
}