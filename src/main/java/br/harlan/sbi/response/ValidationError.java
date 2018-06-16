package br.harlan.sbi.response;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
    private List<FieldMessage> fieldMessages;

    public ValidationError() {
    }

    public ValidationError(List<FieldMessage> fieldMessages) {
        this.fieldMessages = fieldMessages;
    }

    public ValidationError(Integer status, String message, Long timeStamp, String cause) {
        super(status, message, timeStamp, cause);
    }

    public List<FieldMessage> getFieldMessages() {
        if (fieldMessages == null)
            fieldMessages = new ArrayList<>();
        return fieldMessages;
    }

    public void setFieldMessages(List<FieldMessage> fieldMessages) {
        this.fieldMessages = fieldMessages;
    }

    @Override
    public String toString() {
        return "ValidationError{" +
                "fieldMessages=" + fieldMessages +
                "} " + super.toString();
    }
}
