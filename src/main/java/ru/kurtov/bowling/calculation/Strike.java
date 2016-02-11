package ru.kurtov.bowling.calculation;

import ru.kurtov.bowling.Frame;
import ru.kurtov.bowling.Player;

public class Strike extends Calculation {
    
    @Override
    public Integer getScore(Player player, int index) {
        Frame frame = player.getFrame(index);
        Integer score = null;
        Frame nextFrame = player.getFrame(index+1);

        //Если следующий фрейм - страйк и в через один фрейме сделан первый бросок
        //Если следующий фрейм - не страйк и в нем сделано два броска
        if(nextFrame.getCalculation() instanceof Strike) {
            Frame nextNextFrame = player.getFrame(index+2);

            if(nextNextFrame.getShotInFrame() > 0) {
                score = frame.getTotalPins()
                    + nextFrame.getTotalPins() 
                    + nextNextFrame.getPins(0);
            }
        } else {
            if(nextFrame.getShotInFrame() > 1) {
                score = frame.getTotalPins()
                    + nextFrame.getPins(0)
                    + nextFrame.getPins(1);
            }                
        }

        return score;
    }
    
}
