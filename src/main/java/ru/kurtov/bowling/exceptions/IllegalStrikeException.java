package ru.kurtov.bowling.exceptions;

public class IllegalStrikeException extends BowlingException {
    public IllegalStrikeException() {
        super("Неверный страйк");
    }
}
