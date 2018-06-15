package br.harlan.sbi.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Request implements Serializable {
    private static final long serialVersionUID = 1855334184487261445L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date instante;

    @ManyToOne
    private Address address;

    @ManyToOne
    private Client client;

    private Set<RequestItem> requestItems;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "request")
    private Payment payment;

    public Request() {
    }

    public Request(Date instante, Client client, Address address) {
        this.instante = instante;
        this.address = address;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getInstante() {
        return instante;
    }

    public void setInstante(Date instante) {
        this.instante = instante;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Set<RequestItem> getRequestItems() {
        if (requestItems == null)
            requestItems = new HashSet<>();
        return requestItems;
    }

    public void setRequestItems(Set<RequestItem> requestItems) {
        this.requestItems = requestItems;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Request request = (Request) o;

        return id != null ? id.equals(request.id) : request.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", instante=" + instante +
                ", payment=" + payment +
                '}';
    }
}
