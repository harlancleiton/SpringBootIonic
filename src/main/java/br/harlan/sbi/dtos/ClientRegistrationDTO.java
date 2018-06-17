package br.harlan.sbi.dtos;

import br.harlan.sbi.services.validators.InsertClient;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@InsertClient
public class ClientRegistrationDTO implements Serializable {
    private static final long serialVersionUID = 3454406018777249023L;

    @NotEmpty(message = "Name can not be empty.")
    @Length(min = 5, max = 100, message = "The size must be between 5 and 100 characters.")
    private String name;

    @NotEmpty(message = "Email can not be empty.")
    @Email(message = "Invalid email.")
    private String email;

    //@NotEmpty(message = "CPF can not be empty.")
    //@CPF(message = "Invalid CPF.")
    private String cpfCnpj;

    private String clientType;

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

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
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

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    @Override
    public String toString() {
        return "ClientRegistrationDTO{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", cpfCnpj='" + cpfCnpj + '\'' +
                ", clientType='" + clientType + '\'' +
                ", address=" + address +
                ", telephones=" + telephones +
                '}';
    }
}
