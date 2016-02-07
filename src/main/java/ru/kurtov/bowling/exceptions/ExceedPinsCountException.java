package ru.kurtov.bowling.exceptions;

public class ExceedPinsCountException extends BowlingException {
    public ExceedPinsCountException(){ 
        super("Завышенное количество сбитых кегель");
    }  
}