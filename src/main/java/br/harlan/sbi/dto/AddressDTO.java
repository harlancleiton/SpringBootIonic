package br.harlan.sbi.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Optional;

public class AddressDTO implements Serializable {
    private static final long serialVersionUID = -62544519098389368L;

    private Long id;

    @NotEmpty(message = "CEP can not be empty.")
    @Length(min = 8, max = 8, message = "The CEP must contain 8 characters.")
    private String cep;

    @NotEmpty(message = "Name can not be empty.")
    @Length(min = 5, max = 30, message = "The size must be between 5 and 30 characters.")
    private String street;

    @NotEmpty(message = "Name can not be empty.")
    @Length(min = 2, max = 10, message = "The size must be between 2 and 10 characters.")
    private String district;

    @NotEmpty(message = "Name can not be empty.")
    @Length(min = 1, max = 5, message = "The size must be between 1 and 5 characters.")
    private String number;

    private Optional<String> complement;

    @Valid
    private CityDTO city;

    public AddressDTO() {
    }

    public AddressDTO(String cep, String street, String district, String number) {
        this.cep = cep;
        this.street = street;
        this.district = district;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Optional<String> getComplement() {
        return complement;
    }

    public void setComplement(Optional<String> complement) {
        this.complement = complement;
    }

    public CityDTO getCity() {
        return city;
    }

    public void setCity(CityDTO city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "AddressDTO{" +
                "id=" + id +
                ", cep='" + cep + '\'' +
                ", street='" + street + '\'' +
                ", district='" + district + '\'' +
                ", number=" + number +
                ", complement=" + complement +
                ", city=" + city +
                '}';
    }
}
