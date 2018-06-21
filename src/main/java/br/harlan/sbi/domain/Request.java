package br.harlan.sbi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
public class Request implements Serializable {
    private static final long serialVersionUID = 1855334184487261445L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
    private Date instant;

    @ManyToOne
    private Address address;

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "id.request")
    private List<RequestItem> requestItem;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "request")
    private Payment payment;

    public Request() {
    }

    public Request(Date instant, Client client, Address address) {
        this.instant = instant;
        this.address = address;
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getInstant() {
        return instant;
    }

    public void setInstant(Date instant) {
        this.instant = instant;
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

    public List<RequestItem> getRequestItem() {
        if (requestItem == null)
            requestItem = new ArrayList<>();
        return requestItem;
    }

    public void setRequestItem(List<RequestItem> requestItem) {
        this.requestItem = requestItem;
    }

    public Double getTotalPrice() {
        double total = 0.0;
        for (RequestItem requestItem : this.requestItem)
            total = total + requestItem.getSubTotal();
        return total;
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
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        final StringBuilder sb = new StringBuilder("Request{");
        sb.append("id=").append(id);
        sb.append(", instant=").append(simpleDateFormat.format(instant));
        sb.append(", address=").append(address);
        sb.append(", payment=").append(payment);
        sb.append(", client=").append(client.getName());
        sb.append(", requestItem=");
        for (RequestItem item : requestItem)
            sb.append(item.toString());
        sb.append(", totalPrice=").append(numberFormat.format(getTotalPrice()));
        sb.append('}');
        return sb.toString();
    }
}
