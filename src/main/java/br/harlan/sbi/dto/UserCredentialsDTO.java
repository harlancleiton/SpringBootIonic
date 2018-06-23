package br.harlan.sbi.dto;

import java.io.Serializable;

public class UserCredentialsDTO implements Serializable {
    private static final long serialVersionUID = -715283890644814228L;

    private String email;
    private String password;

    public UserCredentialsDTO() {
    }

    public UserCredentialsDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
