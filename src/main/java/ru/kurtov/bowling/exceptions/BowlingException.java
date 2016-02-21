package ru.kurtov.bowling.exceptions;

public class BowlingException extends RuntimeException {
    public BowlingException(){ 
        super("Ошибка в игре");
    }
    
    public BowlingException(String message){ 
        super(message);
    }
}
