package br.harlan.sbi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class RequestItem implements Serializable {
    private static final long serialVersionUID = 2335885604844882206L;

    @JsonIgnore
    @EmbeddedId
    private RequestItemPK id;

    private Double discount;

    private Integer amount;

    private Double price;

    public RequestItem() {
        id = new RequestItemPK();
    }

    public RequestItem(Request request, Product product, Double discount, Integer amount, Double price) {
        this.id = new RequestItemPK(request, product);
        this.discount = discount;
        this.amount = amount;
        this.price = price;
    }

    @JsonIgnore
    public Request getRequest() {
        return id.getRequest();
    }

    public Product getProduct() {
        return id.getProduct();
    }

    public RequestItemPK getId() {
        return id;
    }

    public void setId(RequestItemPK id) {
        this.id = id;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSubTotal() {
        return (price - discount) * amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestItem that = (RequestItem) o;
        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "RequestItem{" +
                "id=" + id +
                ", discount=" + discount +
                ", amount=" + amount +
                ", price=" + price +
                ", subTotal=" + getSubTotal() +
                '}';
    }
}
