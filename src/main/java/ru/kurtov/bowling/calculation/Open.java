package ru.kurtov.bowling.calculation;

import ru.kurtov.bowling.Frame;
import ru.kurtov.bowling.Player;

public class Open extends Calculation {
    
    @Override
    public Integer getScore(Player player, int index) {
        Frame frame = player.getFrame(index);
        Integer score = null;
        
        if(frame.isComplite()) {
            score = frame.getTotalPins();
        }
        
        return score;
    }
    
}
