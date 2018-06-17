package br.harlan.sbi.dtos;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

public class ClientRegistrationDTO implements Serializable {
    private static final long serialVersionUID = 3454406018777249023L;

    @NotEmpty(message = "Name can not be empty.")
    @Length(min = 5, max = 100, message = "The size must be between 5 and 100 characters.")
    private String name;

    @NotEmpty(message = "Email can not be empty.")
    @Email(message = "Invalid email.")
    private String email;

    @NotEmpty(message = "CPF can not be empty.")
    @CPF(message = "Invalid CPF.")
    private String cpf;

    @Valid
    private AddressDTO address;

    private List<TelephoneDTO> telephones;

    public ClientRegistrationDTO() {
    }

    public ClientRegistrationDTO(Long id, String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public List<TelephoneDTO> getTelephones() {
        return telephones;
    }

    public void setTelephones(List<TelephoneDTO> telephones) {
        this.telephones = telephones;
    }

    @Override
    public String toString() {
        return "ClientRegistrationDTO{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", address=" + address +
                ", telephones=" + telephones +
                '}';
    }
}
