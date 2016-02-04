package ru.kurtov.bowling.exceptions;

public class GameWithoutPlayersException extends RuntimeException {
    public GameWithoutPlayersException() {
        super("Игра без играков");
    }
}
