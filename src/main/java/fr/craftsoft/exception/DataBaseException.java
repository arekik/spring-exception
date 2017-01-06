package fr.craftsoft.exception;

/**
 * Created by ayr on 05/01/17.
 */
public class DataBaseException extends GenericException {



    private static final String DEFAULT_MESSAGE  = "Data base exception";

    public DataBaseException() {
        super(DEFAULT_MESSAGE);
    }


    public DataBaseException(String message) {
        super(message);
    }
}
