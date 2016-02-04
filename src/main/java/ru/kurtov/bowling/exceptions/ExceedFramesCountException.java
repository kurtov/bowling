package ru.kurtov.bowling.exceptions;

public class ExceedFramesCountException extends RuntimeException {
    public ExceedFramesCountException(){ 
        super("Превышено максимальное количество фреймов");
    }  
}