package ru.kurtov.bowling.exceptions;

public class AddPlayerInStartedGameException extends RuntimeException {
    public AddPlayerInStartedGameException(){ 
        super("Добавлен игрок в начатую игру");
    }  
}
