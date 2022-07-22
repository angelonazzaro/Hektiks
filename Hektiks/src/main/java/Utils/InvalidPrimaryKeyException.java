package Utils;

/**
 * L'eccezione che viene lanciata quando si cerca di inserire una chiave primaria non valida.
 */
public class InvalidPrimaryKeyException extends RuntimeException {

    public InvalidPrimaryKeyException() {

        super("Chiave primaria invalida");
    }

    public InvalidPrimaryKeyException(String message) {

        super(message);
    }
}
