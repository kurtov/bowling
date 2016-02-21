package ru.kurtov.bowling.exceptions;

public class ExceedShotsCountException extends BowlingException {
    public ExceedShotsCountException(){ 
        super("Превышено максимальное число бросков в фрейме");
    }  
}
