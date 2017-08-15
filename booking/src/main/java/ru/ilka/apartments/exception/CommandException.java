package ru.ilka.apartments.exception;

/**
 * Here could be your advertisement.
 * Ilya_Kisel +375 29 3880490
 */
public class CommandException extends Exception {
    public CommandException() {
    }

    public CommandException(String message) {
        super(message);
    }

    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommandException(Throwable cause) {
        super(cause);
    }
}
