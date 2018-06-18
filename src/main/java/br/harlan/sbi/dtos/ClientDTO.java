package br.harlan.sbi.dtos;

import br.harlan.sbi.services.validators.UpdateClient;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@UpdateClient
public class ClientDTO implements Serializable {
    private static final long serialVersionUID = 3454406018777249023L;

    private Long id;

    @NotEmpty(message = "Name can not be empty.")
    @Length(min = 5, max = 20, message = "The size must be between 5 and 100 characters.")
    private String name;

    @NotEmpty(message = "Email can not be empty.")
    @Email(message = "Invalid email.")
    private String email;

    public ClientDTO() {
    }

    public ClientDTO(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ClientDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
