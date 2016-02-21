package ru.kurtov.bowling.exceptions;

public class AddPlayerInStartedGameException extends BowlingException {
    public AddPlayerInStartedGameException(){ 
        super("Добавлен игрок в начатую игру");
    }  
}
