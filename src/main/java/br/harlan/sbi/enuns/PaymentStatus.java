package br.harlan.sbi.enuns;

public enum PaymentStatus {
    PENDING(0, "PENDING"),
    PAID_OUT(1, "PAID_OUT"),
    CANCELED(2, "CANCELED");

    private Integer code;
    private String value;

    PaymentStatus(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public PaymentStatus getEnum(Integer code) {
        if (code == null)
            return null;
        for (PaymentStatus paymentStatus : PaymentStatus.values())
            if (code.equals(paymentStatus.code))
                return paymentStatus;
        throw new IllegalArgumentException("Enum not found! Code: " + code);
    }

    @Override
    public String toString() {
        return "PaymentStatus{" +
                "code=" + code +
                ", value='" + value + '\'' +
                "} " + super.toString();
    }
}
