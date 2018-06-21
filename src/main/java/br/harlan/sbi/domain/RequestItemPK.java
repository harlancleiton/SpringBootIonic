package br.harlan.sbi.domain;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class RequestItemPK implements Serializable {
    private static final long serialVersionUID = 8098166299626322737L;

    @ManyToOne
    @JoinColumn(name = "request_id")
    private Request request;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public RequestItemPK() {
    }

    public RequestItemPK(Request request, Product product) {
        this.request = request;
        this.product = product;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestItemPK that = (RequestItemPK) o;
        if (!request.equals(that.request)) return false;
        return product.equals(that.product);
    }

    @Override
    public int hashCode() {
        int result = request.hashCode();
        result = 31 * result + product.hashCode();
        return result;
    }
}
