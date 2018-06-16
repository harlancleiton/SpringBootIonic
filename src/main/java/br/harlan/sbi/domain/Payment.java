package br.harlan.sbi.domain;

import br.harlan.sbi.domain.enuns.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Payment implements Serializable {
    private static final long serialVersionUID = 1913470778151797194L;

    @Id
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private PaymentStatus paymentStatus;

    @JsonIgnore
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

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
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
