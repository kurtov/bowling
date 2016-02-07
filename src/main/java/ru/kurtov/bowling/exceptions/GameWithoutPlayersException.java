package ru.kurtov.bowling.exceptions;

public class GameWithoutPlayersException extends BowlingException {
    public GameWithoutPlayersException() {
        super("Игра без играков");
    }
}
