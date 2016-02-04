package ru.kurtov.bowling;

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
    public void testShotStrike_int() {
        f.shot(10);
        
        assertEquals(10, f.getTotalPins());
        assertTrue(f.isComplite());
        assertEquals("X│ ", f.shotsToString());
    }
    
    
    @Test
    public void testShotSpare_int() {
        f.shot(4).shot(6);
        
        assertEquals(10, f.getTotalPins());
        assertTrue(f.isComplite());
        assertEquals("4│/", f.shotsToString());
    }
    
    @Test
    public void testShotOpenFrame_int() {
        f.shot(4).shot(4);
        
        assertEquals(8, f.getTotalPins());
        assertTrue(f.isComplite());
        assertEquals("4│4", f.shotsToString());
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
    
    @Test
    public void testShotStrike_String() {
        f.shot("X");
        
        assertEquals(10, f.getTotalPins());
        assertTrue(f.isComplite());
    }

    @Test
    public void testShotSpare_String() {
        f.shot("-").shot("/");
        
        assertEquals(10, f.getTotalPins());
        assertTrue(f.isComplite());
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

    @Test
    public void testIsCompliteNotComplite () {
        assertFalse(f.isComplite());
        assertEquals(" │ ", f.shotsToString());
        
        f = new Frame();
        f.shot(2);
        assertFalse(f.isComplite());
        assertEquals("2│ ", f.shotsToString());        
    }
}