package br.harlan.sbi.enuns;

public enum ClientType {
    PHYSICAL_PERSON(0, "PHYSICAL_PERSON"),
    LEGAL_PERSON(1, "LEGAL_PERSON");

    private Integer code;
    private String value;

    ClientType(Integer code, String value) {
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
        return "ClientType{" +
                "code=" + code +
                ", value='" + value + '\'' +
                "} " + super.toString();
    }
}
