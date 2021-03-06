package br.harlan.sbi.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.util.*;

@Entity
public class Product implements Serializable {
    private static final long serialVersionUID = -544067476073048548L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    @JsonIgnore
    @OneToMany(mappedBy = "id.product")
    private Set<RequestItem> requestItem = new HashSet<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "PRODUCT_CATEGORY", joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories = new ArrayList<>();

    public Product() {
    }

    public Product(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    @JsonIgnore
    public List<Request> getRequests() {
        List<Request> requests = new ArrayList<>();
        for (RequestItem requestItem : requestItem)
            requests.add(requestItem.getRequest());
        return requests;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Category> getCategories() {
        if (categories == null)
            categories = new ArrayList<>();
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Set<RequestItem> getRequestItem() {
        if (requestItem == null)
            requestItem = new HashSet<>();
        return requestItem;
    }

    public void setRequestItem(Set<RequestItem> requestItem) {
        this.requestItem = requestItem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id != null ? id.equals(product.id) : product.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + numberFormat.format(price) +
                '}';
    }
}
