package ru.kurtov.bowling.exceptions;

public class ExceedFramesCountException extends BowlingException {
    public ExceedFramesCountException(){ 
        super("Превышено максимальное количество фреймов");
    }  
}