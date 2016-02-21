package ru.kurtov.bowling.calculation;

import ru.kurtov.bowling.Frame;
import ru.kurtov.bowling.Player;

public abstract class Calculation {
    
    abstract public Integer getScore(Player p, int index);
}
