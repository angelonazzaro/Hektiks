package Utils;

public class InvalidPrimaryKeyException extends RuntimeException {

    public InvalidPrimaryKeyException() {

        super("Chiave primaria invalida");
    }

    public InvalidPrimaryKeyException(String message) {

        super(message);
    }
}
