package ru.kurtov.bowling.exceptions;

public class IllegalSpareException extends BowlingException {
    public IllegalSpareException() {
        super("Неверный спэр");
    }
}