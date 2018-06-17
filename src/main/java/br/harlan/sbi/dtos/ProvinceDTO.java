package br.harlan.sbi.dtos;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class ProvinceDTO implements Serializable {
    private static final long serialVersionUID = 1531356631766285471L;

    private Long id;

    @NotEmpty(message = "Name can not be empty.")
    @Length(min = 4, max = 20, message = "The size must be between 4 and 20 characters.")
    private String name;

    @NotEmpty(message = "UF can not be empty.")
    @Length(min = 2, max = 2, message = "The UF must contain 2 characters.")
    private String uf;

    public ProvinceDTO() {
    }

    public ProvinceDTO(String name, String uf) {
        this.name = name;
        this.uf = uf;
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

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Override
    public String toString() {
        return "ProvinceDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", uf='" + uf + '\'' +
                '}';
    }
}
