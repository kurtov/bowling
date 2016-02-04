package ru.kurtov.bowling;

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
    public void testShotTripleStrike_int() {
        f.shot(10).shot(10).shot(10);
        
        assertEquals(30, f.getTotalPins());
        assertTrue(f.isComplite());
        assertEquals("X│X│X", f.shotsToString());
    }

    @Test
    public void testShotStrike_int() {
        f.shot(10).shot(4).shot(5);
        
        assertEquals(19, f.getTotalPins());
        assertTrue(f.isComplite());
        assertEquals("X│4│5", f.shotsToString());
    }
    
    @Test
    public void testShotSpareAndStrike_int() {
        f.shot(4).shot(6).shot(10);
        
        assertEquals(20, f.getTotalPins());
        assertTrue(f.isComplite());
        assertEquals("4│/│X", f.shotsToString());
    }

    @Test
    public void testShotStrikeAndSpare_String() {
        f.shot("X").shot(6).shot("/");
        
        assertEquals(20, f.getTotalPins());
        assertTrue(f.isComplite());
        assertEquals("X│6│/", f.shotsToString());
    }
    
    @Test
    public void testShotSpare_int() {
        f.shot(4).shot(6).shot(3);
        
        assertEquals(13, f.getTotalPins());
        assertTrue(f.isComplite());
        assertEquals("4│/│3", f.shotsToString());
    }
    
    @Test
    public void testShotOpenFrame_int() {
        f.shot(4).shot(4);
        
        assertEquals(8, f.getTotalPins());
        assertTrue(f.isComplite());
        assertEquals("4│4│ ", f.shotsToString());
    }

    
    @Test
    public void testShotTripleStrike_String() {
        f.shot("X").shot("X").shot("X");
        
        assertEquals(30, f.getTotalPins());
        assertTrue(f.isComplite());
    }

    @Test
    public void testShotStrike_String() {
        f.shot("X").shot("4").shot("5");
        
        assertEquals(19, f.getTotalPins());
        assertTrue(f.isComplite());
    }
    
    @Test
    public void testShotSpareAndStrike_String() {
        f.shot("4").shot("/").shot("X");
        
        assertEquals(20, f.getTotalPins());
        assertTrue(f.isComplite());
    }
    
    @Test
    public void testShotSpare_String() {
        f.shot("4").shot("/").shot("3");
        
        assertEquals(13, f.getTotalPins());
        assertTrue(f.isComplite());
    }
    
    @Test
    public void testShotOpenFrame_String() {
        f.shot("4").shot("4");
        
        assertEquals(8, f.getTotalPins());
        assertTrue(f.isComplite());
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
    
    
    @Test
    public void testIsCompliteNotComplite () {
        assertFalse(f.isComplite());
        assertEquals(" │ │ ", f.shotsToString());

        f = new TenthFrame();
        f.shot(2);
        assertFalse(f.isComplite());
        assertEquals("2│ │ ", f.shotsToString());
        
        f = new TenthFrame();
        f.shot("X");
        assertFalse(f.isComplite());
        assertEquals("X│ │ ", f.shotsToString());
        
        f = new TenthFrame();
        f.shot("X").shot("X");
        assertFalse(f.isComplite());
        assertEquals("X│X│ ", f.shotsToString());
        
        f = new TenthFrame();
        f.shot(3).shot("/");
        assertFalse(f.isComplite());
        assertEquals("3│/│ ", f.shotsToString());
    }
}