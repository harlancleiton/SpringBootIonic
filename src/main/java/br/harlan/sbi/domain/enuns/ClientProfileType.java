package br.harlan.sbi.domain.enuns;

public enum ClientProfileType {
    ROLE_CLIENT(0, "ROLE_CLIENT"),
    ROLE_ADMIN(1, "ROLE_ADMIN");

    private Integer code;
    private String value;

    ClientProfileType(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "ClientProfileType{" +
                "code=" + code +
                ", value='" + value + '\'' +
                '}';
    }
}
