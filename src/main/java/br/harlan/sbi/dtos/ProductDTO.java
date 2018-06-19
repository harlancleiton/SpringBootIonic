package br.harlan.sbi.dtos;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class ProductDTO implements Serializable {
    private static final long serialVersionUID = 6009679082615912844L;

    private Long id;

    @NotEmpty(message = "Name can not be empty.")
    @Length(min = 4, max = 20, message = "The size must be between 4 and 20 characters.")
    private String name;

    @NotEmpty(message = "Price can not be empty.")
    @Length(min = 1, max = 8, message = "The size must be between 1 and 8 characters.")
    private Double price;

    public ProductDTO() {
    }

    public ProductDTO(Long id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
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

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
