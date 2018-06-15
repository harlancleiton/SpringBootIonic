package br.harlan.sbi.entities;

import br.harlan.sbi.enuns.PaymentStatus;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Payment implements Serializable {
    private static final long serialVersionUID = 1913470778151797194L;

    @Id
    private Long id;

    private PaymentStatus paymentStatus;

    @OneToOne
    @JoinColumn(name = "request_id")
    @MapsId
    private Request request;

    public Payment() {
    }

    public Payment(PaymentStatus paymentStatus, Request request) {
        this.paymentStatus = paymentStatus;
        this.request = request;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        return id != null ? id.equals(payment.id) : payment.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", paymentStatus=" + paymentStatus +
                '}';
    }
}
