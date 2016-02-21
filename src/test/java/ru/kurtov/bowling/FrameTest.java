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


public class FrameTest {
    Frame f;

    @Before
    public void setUp() {
        f = new Frame();
    }
    
    
    @Test
    public void testTenthFrame() {
        ArrayList data = new <FrameHelper>ArrayList();
        
        data.add(new FrameHelper(new int[]{10},          10, true,  "X│ "));
        data.add(new FrameHelper(new int[]{4, 6},        10, true,  "4│/"));
        data.add(new FrameHelper(new int[]{4, 4},         8, true,  "4│4"));
        data.add(new FrameHelper(new String[]{"X"},      10, true,  "X│ "));
        data.add(new FrameHelper(new String[]{"-", "/"}, 10, true,  "-│/"));
        data.add(new FrameHelper(new String[]{},          0, false, " │ "));
        data.add(new FrameHelper(new String[]{"2"},       2, false, "2│ "));
        
        Iterator<FrameHelper>iterator = data.iterator();
        while(iterator.hasNext()) {
            FrameHelper fh = iterator.next();
            Frame frame = fh.populateFrame(new Frame());           
            
            assertEquals(fh.getTotalPins(), frame.getTotalPins());
            assertEquals(fh.isComplite(), frame.isComplite());
            assertEquals(fh.getStr(), frame.shotsToString());            
        }
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testShotIllegalArgumentException() {
        f.shot(4).shot(14);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testShotIllegalArgumentException2() {
        f.shot(-2);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testShotIllegalArgumentException3() {
        f.shot(14);
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
        f.shot(10).shot(2);
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

    @Test(expected = ExceedPinsCountException.class)  
    public void testShotExceedPinsCount_int() {
        f.shot(6).shot(6);
    }
}