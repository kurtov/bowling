package ru.kurtov.bowling.exceptions;

public class ExceedShotsCountException extends RuntimeException {
    public ExceedShotsCountException(){ 
        super("Превышено максимальное число бросков в фрейме");
    }  
}
