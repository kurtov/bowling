package ru.kurtov.bowling.calculation;

import ru.kurtov.bowling.Frame;
import ru.kurtov.bowling.Player;

public class Spare extends Calculation {
    
    @Override
    public Integer getScore(Player player, int index) {
        Frame frame = player.getFrame(index);
        Integer score = null;
        Frame nextFrame = player.getFrame(index+1);
        
        if(nextFrame.getShotInFrame() > 0) {
            score = frame.getTotalPins()
                + nextFrame.getPins(0);
        }
     
        return score;
    }    
}
