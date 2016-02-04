package ru.kurtov.bowling.exceptions;

public class IllegalSpareException extends RuntimeException {
    public IllegalSpareException() {
        super("Неверный спэр");
    }
}