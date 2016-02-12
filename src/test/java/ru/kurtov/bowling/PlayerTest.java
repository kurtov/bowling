package ru.kurtov.bowling;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ru.kurtov.bowling.exceptions.BowlingException;
import ru.kurtov.bowling.exceptions.ExceedFramesCountException;

public class PlayerTest {
    
    Player player;
    
    @Before
    public void setUp() {
        player = new Player("Иван Васильевич");
    }

    @Test
    public void testAllStrike() {
        player
                .shot("X")                         //1
                .shot("X")                         //2
                .shot("X")                         //3
                .shot("X")                         //4
                .shot("X")                         //5
                .shot("X")                         //6
                .shot("X")                         //7
                .shot("X")                         //8
                .shot("X")                         //9
                .shot("X").shot("X").shot("X");    //10
        
        assertEquals(300, player.getScore());
    }

    @Test
    public void testAllMiss() {
        player
                .shot(0).shot(0)                   //1
                .shot(0).shot(0)                   //2
                .shot(0).shot(0)                   //3
                .shot(0).shot(0)                   //4
                .shot(0).shot(0)                   //5
                .shot(0).shot(0)                   //6
                .shot(0).shot(0)                   //7
                .shot(0).shot(0)                   //8
                .shot(0).shot(0)                   //9
                .shot(0).shot(0);                  //10
        
        assertEquals(0, player.getScore());
    }

    @Test
    public void testShots() {
        player
                .shot(0).shot(0)                   //1
                .shot(8).shot(0)                   //2
                .shot(7).shot(0)                   //3
                .shot(0).shot(1)                   //4
                .shot(8).shot(1)                   //5
                .shot(9).shot("/")                 //6
                .shot("X")                         //7
                .shot(3).shot(0)                   //8
                .shot(4).shot(2)                   //9
                .shot(3).shot("/").shot(1);        //10
        
        assertEquals(78, player.getScore());
    }    
    
    
    @Test(expected = ExceedFramesCountException.class) 
    public void test11Frame() {
        player
                .shot(0).shot(0)                   //1
                .shot(0).shot(0)                   //2
                .shot(0).shot(0)                   //3
                .shot(0).shot(0)                   //4
                .shot(0).shot(0)                   //5
                .shot(0).shot(0)                   //6
                .shot(0).shot(0)                   //7
                .shot(0).shot(0)                   //8
                .shot(0).shot(0)                   //9
                .shot(0).shot(0)                   //10
                .shot(0);                          //11
    }
    
    
    
    @Test
    public void testNotComplite() {
        assertEquals(0, player.getScore());
    }
    
    @Test
    public void testNotCompliteSpare() {
        player
                .shot(1).shot(2)                   //1
                .shot(3).shot("/");                //2
        
        assertEquals(3, player.getScore());
    }
    
    @Test
    public void testNotCompliteStrike() {
        player
                .shot(1).shot(2)                   //1
                .shot("X");                        //2
        
        assertEquals(3, player.getScore());
    }
    
    @Test
    public void testNotCompliteStrike2() {
        player
                .shot(1).shot(2)                   //1
                .shot("X")                         //2
                .shot("X");                        //3
        
        assertEquals(3, player.getScore());
    }
    
    @Test
    public void testNotCompliteStrike3() {
        player
                .shot(1).shot(2)                   //1
                .shot("X")                         //2
                .shot(2);                          //3
        
        assertEquals(3, player.getScore());
    }
    
    @Test
    public void testNotCompliteStrike4() {
        player
                .shot(1).shot(2)                   //1
                .shot("X")                         //2
                .shot("X")                         //3
                .shot(7);                           //4
        
        assertEquals(30, player.getScore());
    }
    
    @Test
    public void testSetFrame() {
        Frame f = new Frame().shot(3);
        player.shot(8).shot("-").setFrame(f);
        
        assertEquals(f, player.getCurrentFrame());
    }
    
    @Test(expected = BowlingException.class)
    public void testSetFrameExcaption() {
        Frame f = new Frame().shot(3);
        
        player.shot(8).setFrame(f);
    }
}
