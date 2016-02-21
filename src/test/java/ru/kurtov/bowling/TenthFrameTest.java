package ru.kurtov.bowling;

import java.util.ArrayList;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ru.kurtov.bowling.exceptions.ExceedPinsCountException;
import ru.kurtov.bowling.exceptions.ExceedShotsCountException;
import ru.kurtov.bowling.exceptions.IllegalSpareException;
import ru.kurtov.bowling.exceptions.IllegalStrikeException;

public class TenthFrameTest {

    TenthFrame f;
    
    @Before
    public void setUp() {
        f = new TenthFrame();
    }
    
    @Test
    public void testTenthFrame() {
        ArrayList data = new <FrameHelper>ArrayList();
        
        data.add(new FrameHelper(new int[]{10, 10, 10},       30, true, "X│X│X"));
        data.add(new FrameHelper(new int[]{10, 4,  5},        19, true, "X│4│5"));
        data.add(new FrameHelper(new int[]{4,  6,  10},       20, true, "4│/│X"));
        data.add(new FrameHelper(new int[]{4,  6,  3},        13, true, "4│/│3"));
        data.add(new FrameHelper(new int[]{4,  4},             8, true, "4│4│ "));
        
        data.add(new FrameHelper(new String[]{"X", "6", "/"}, 20, true, "X│6│/"));
        data.add(new FrameHelper(new String[]{"X", "X", "X"}, 30, true, "X│X│X"));
        data.add(new FrameHelper(new String[]{"X", "4", "5"}, 19, true, "X│4│5"));
        data.add(new FrameHelper(new String[]{"4", "/", "X"}, 20, true, "4│/│X"));
        data.add(new FrameHelper(new String[]{"4", "/", "3"}, 13, true, "4│/│3"));
        data.add(new FrameHelper(new String[]{"4", "4"},       8, true, "4│4│ "));
        
        data.add(new FrameHelper(new int[]{2},                 2, false, "2│ │ "));
        data.add(new FrameHelper(new String[]{},               0, false, " │ │ "));
        data.add(new FrameHelper(new String[]{"X"},           10, false, "X│ │ "));
        data.add(new FrameHelper(new String[]{"X", "X"},      20, false, "X│X│ "));
        data.add(new FrameHelper(new String[]{"3", "/"},      10, false, "3│/│ "));
        
        Iterator<FrameHelper>iterator = data.iterator();
        while(iterator.hasNext()) {
            FrameHelper fh = iterator.next();
            TenthFrame frame = (TenthFrame)fh.populateFrame(new TenthFrame());           
            
            assertEquals(fh.getTotalPins(), frame.getTotalPins());
            assertEquals(fh.isComplite(), frame.isComplite());
            assertEquals(fh.getStr(), frame.shotsToString());            
        }
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testShotIllegalArgumentException_int() {
        f.shot(4).shot("/").shot(12);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testShotIllegalArgumentException_String() {
        f.shot("strike");
    }
    
    @Test(expected = ExceedShotsCountException.class)  
    public void testShotExceedMaximumShotsPerFrameException1_int() {
        f.shot(4).shot(1).shot(2);
    }
    
    @Test(expected = ExceedShotsCountException.class)  
    public void testShotExceedMaximumShotsPerFrameException2_int() {
        f.shot(4).shot(1).shot("X");
    }
    
    @Test(expected = ExceedShotsCountException.class)  
    public void testShotExceedMaximumShotsPerFrameException3_int() {
        f.shot("X").shot("X").shot("X").shot(5);
    }
    
    @Test(expected = ExceedShotsCountException.class)  
    public void testShotExceedMaximumShotsPerFrameException4_int() {
        f.shot(5).shot("/").shot("X").shot(5);
    }
    
    @Test(expected = ExceedShotsCountException.class)  
    public void testShotExceedMaximumShotsPerFrameException5_int() {
        f.shot(5).shot("/").shot(5).shot(5);
    }
    
    @Test(expected = IllegalStrikeException.class)  
    public void testShotIlligalStrike_String() {
        f.shot("4").shot("X");
    }

    @Test(expected = IllegalSpareException.class)  
    public void testShotIlligalSpare1_String() {
        f.shot("/");
    }

    @Test(expected = IllegalSpareException.class)  
    public void testShotIlligalSpare2_String() {
        f.shot("X").shot("/");
    }

    @Test(expected = IllegalSpareException.class)  
    public void testShotIlligalSpare3_String() {
        f.shot("2").shot("/").shot("/");
    }

    @Test(expected = IllegalSpareException.class)  
    public void testShotIllegalSpareException4_String() {
        f.shot("2").shot("3").shot("/");
    }
    
    @Test(expected = ExceedPinsCountException.class)  
    public void testShotExceedPinsCount1() {
        f.shot(6).shot(6);
    }
    
    @Test(expected = ExceedPinsCountException.class)  
    public void testShotExceedPinsCount2() {
        f.shot("X").shot(6).shot(6);
    }
}