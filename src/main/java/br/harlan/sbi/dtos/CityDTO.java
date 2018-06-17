package br.harlan.sbi.dtos;

import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class CityDTO implements Serializable {
    private static final long serialVersionUID = 4140285190797850328L;

    private Long id;

    @NotEmpty(message = "City can not be empty.")
    @Length(min = 3, max = 100, message = "The size must be between 5 and 100 characters.")
    private String name;

    @Valid
    private ProvinceDTO province;

    public CityDTO() {
    }

    public CityDTO(String name) {
        this.name = name;
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

    public ProvinceDTO getProvince() {
        return province;
    }

    public void setProvince(ProvinceDTO province) {
        this.province = province;
    }

    @Override
    public String toString() {
        return "CityDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", province=" + province +
                '}';
    }
}
