package ru.kurtov.bowling;

import static ru.kurtov.bowling.Frame.PINS_COUNT;
import ru.kurtov.bowling.exceptions.ExceedPinsCountException;
import ru.kurtov.bowling.exceptions.IllegalSpareException;

public class TenthFrame extends Frame {
    
    public TenthFrame() {
        super();
        this.pins = new Integer[]{0, 0, 0};
    }
    
    @Override
    public boolean isComplite() {
        if((pins[0] == PINS_COUNT) || pins[0] + pins[1] == PINS_COUNT) {
            return shotInFrame == 3;
        } else {
            return shotInFrame == 2;
        }
    }
    
    @Override
    public int defineType() {   
        return OPEN;
    }
    
    @Override
    protected void checkPinsCountArgument(int pinsCount) {
        super.checkPinsCountArgument(pinsCount);
        if(shotInFrame == 2 && pins[0] == PINS_COUNT && pins[shotInFrame-1] < PINS_COUNT && (pins[shotInFrame-1] + pinsCount > PINS_COUNT)) {
            throw new ExceedPinsCountException();
        }
    }
    
    @Override
    protected void checkIllegalSpareException(String pinsCount) {
        super.checkIllegalSpareException(pinsCount);
        
        //Спэр не может быть при третьем броске при условии, что в первом страйк
        if(pinsCount.equals("/") && (shotInFrame == 2) && (pins[0] < PINS_COUNT)) {
            throw new IllegalSpareException();
        }
    }
}
