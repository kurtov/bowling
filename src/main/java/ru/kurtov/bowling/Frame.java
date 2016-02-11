package ru.kurtov.bowling;

import java.util.Arrays;
import org.apache.commons.lang.StringUtils;
import ru.kurtov.bowling.calculation.Calculation;
import ru.kurtov.bowling.calculation.Open;
import ru.kurtov.bowling.calculation.Spare;
import ru.kurtov.bowling.calculation.Strike;
import ru.kurtov.bowling.exceptions.ExceedPinsCountException;
import ru.kurtov.bowling.exceptions.ExceedShotsCountException;
import ru.kurtov.bowling.exceptions.IllegalSpareException;
import ru.kurtov.bowling.exceptions.IllegalStrikeException;

public class Frame {
    static public final int PINS_COUNT = 10;
    
    protected Integer[] pins;
    protected int shotInFrame = 0; //Указатель на номер броска в фрейме
    private int totalPins = 0;     //Количество сбитых кегель в фрейме
    private Integer score;
    
    private Calculation calculation = new Open();

    public Frame() {
        this.pins = new Integer[]{0, 0};
    }
    
    public int getTotalPins() {
        return totalPins;
    }
    
    public Integer getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    public int getShotInFrame() {
        return this.shotInFrame;
    }
    
    public int getPins(int shotNumber) {
        return pins[shotNumber];
    }
    
    public Calculation getCalculation() {
        return this.calculation;
    }
    
    protected void checkIllegalStrikeException(String pinsCount) {
        if(pinsCount.equals("X") && (shotInFrame == 1 && pins[0] < PINS_COUNT)) {
            throw new IllegalStrikeException();
        }
    }
    
    protected void checkIllegalSpareException(String pinsCount) {
        //Спэр не может быть при первом броске
        //Спэр не может быть при втором броске при условии, что в первом страйк
        if(pinsCount.equals("/") && ((shotInFrame == 0) || (shotInFrame == 1 && pins[0] == PINS_COUNT))) {
            throw new IllegalSpareException();
        }
    }

    protected void checkIntArgument(int pinsCount) {
        if(pinsCount < 0 || pinsCount > 10) {
            throw new IllegalArgumentException();
        }
    }
    
    protected void checkPinsCountArgument(int pinsCount) {
        if(shotInFrame == 1 && pins[0] < PINS_COUNT && (pins[0] + pinsCount > PINS_COUNT)) {
            throw new ExceedPinsCountException();
        }
    }
    
    
    protected void checkStringArgument(String pinsCount) {
        if(!(availableValue(pinsCount))) {
            throw new IllegalArgumentException();
        }
    }

    public Frame shot(int pinsCount) {
        checkIntArgument(pinsCount);
        
        if(isComplite()) {
            throw new ExceedShotsCountException();
        }
        
        checkPinsCountArgument(pinsCount);
        
        this.pins[shotInFrame++] = pinsCount;
        this.totalPins += pinsCount;
        this.defineCalculation();
        
        return this;
    }
    
    
    public Frame shot(String pinsCount) {
        checkIllegalSpareException(pinsCount);
        checkIllegalStrikeException(pinsCount);
        checkStringArgument(pinsCount);
 
        return shot(decodePins(pinsCount));
    }

    private int decodePins(String pinsCount) {
        if(pinsCount.equals("X")) {return PINS_COUNT;}
        if(pinsCount.equals("/")) {return PINS_COUNT - pins[shotInFrame-1];}
        if(pinsCount.equals("-")) {return 0;}
        
        return Integer.parseInt(pinsCount);
    }
    
    private String encodePins(int shotNumber) {
        int p = this.pins[shotNumber];
        
        if(p==0) {return "-";}
        if(shotNumber > 0 && pins[shotNumber-1] + pins[shotNumber] == PINS_COUNT) {return "/";}
        if(p==PINS_COUNT) {return "X";}
        
        return String.valueOf(p);
    }
    
    
    public boolean isComplite() {
        return (pins[0] == PINS_COUNT) || (shotInFrame == 2);
    }
    
    public boolean isEmpty() {
        return shotInFrame == 0;
    }
    
    
    public void defineCalculation() {
        if(pins[0] == PINS_COUNT) {
            this.calculation = new Strike();
        } else if(pins[0] + pins[1] == PINS_COUNT) {
            this.calculation = new Spare();
        }
    }
    
    public String shotsToString() {
        String[] ret = new String[pins.length];
        Arrays.fill(ret, " ");
        
        for(int i = 0; i<shotInFrame; i++) {
            ret[i] = encodePins(i);
        }
        
        return StringUtils.join(ret, "│");
    }; 
    
    public static boolean availableValue(String value) {
        return StringUtils.isNumeric(value) || 
                value.equals("/") ||
                value.equals("X") ||
                value.equals("-");
    }
}
