package br.harlan.sbi.response;

public class StandardError {
    private Integer status;
    private String message;
    private Long timeStamp;
    private String cause;

    public StandardError() {
    }

    public StandardError(Integer status, String message, Long timeStamp, String cause) {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
        this.cause = cause;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    @Override
    public String toString() {
        return "StandardError{" +
                "status=" + status +
                ", message='" + message + '\'' +
                ", timeStamp=" + timeStamp +
                ", cause='" + cause + '\'' +
                '}';
    }
}
