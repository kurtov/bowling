package ru.kurtov.bowling.exceptions;

public class ExceedPinsCountException extends RuntimeException {
    public ExceedPinsCountException(){ 
        super("Завышенное количество сбитых кегель");
    }  
}