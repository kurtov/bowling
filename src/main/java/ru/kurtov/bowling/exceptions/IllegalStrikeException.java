package ru.kurtov.bowling.exceptions;

public class IllegalStrikeException extends RuntimeException {
    public IllegalStrikeException() {
        super("Неверный страйк");
    }
}
