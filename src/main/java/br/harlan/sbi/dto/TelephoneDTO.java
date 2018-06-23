package br.harlan.sbi.dto;

public class TelephoneDTO {
    private Long id;

    private String codeProvince;

    private String telephone;

    public TelephoneDTO() {
    }

    public TelephoneDTO(String codeProvince, String telephone) {
        this.codeProvince = codeProvince;
        this.telephone = telephone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeProvince() {
        return codeProvince;
    }

    public void setCodeProvince(String codeProvince) {
        this.codeProvince = codeProvince;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "TelephoneDTO{" +
                "id=" + id +
                ", codeProvince='" + codeProvince + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
