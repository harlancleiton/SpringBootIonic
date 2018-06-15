package br.harlan.sbi.response;

import java.util.ArrayList;
import java.util.List;

public class Response<T> {
    private T data;

    private List<StandardError> errors;

    public Response() {
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<StandardError> getErrors() {
        if (errors == null)
            errors = new ArrayList<>();
        return errors;
    }

    public void setErrors(List<StandardError> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "Response{" +
                "data=" + data +
                ", errors=" + errors +
                '}';
    }
}
