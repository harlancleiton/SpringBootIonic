package br.harlan.sbi.services.exceptions;

import java.io.Serializable;

public class DataIntegrityException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 925774188450813774L;

    public DataIntegrityException(String message) {
        super(message);
    }

    public DataIntegrityException(String message, Throwable cause) {
        super(message, cause);
    }
}
