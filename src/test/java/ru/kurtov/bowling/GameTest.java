package ru.kurtov.bowling;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import ru.kurtov.bowling.exceptions.AddPlayerInStartedGameException;
import ru.kurtov.bowling.exceptions.GameWithoutPlayersException;

public class GameTest {
    
    Game g;
    
    @Before
    public void setUp() {
        g = new Game();
    }


    @Test
    public void testChangePlayers() {
        g.addPlayer("Ваня").addPlayer("Петя");
                
        assertEquals("Ваня", g.getPlayer().getName());
        
        g.shot("X");
        assertEquals("Петя", g.getPlayer().getName());
        
        g.shot("-");
        assertEquals("Петя", g.getPlayer().getName());
        
        g.shot(1);
        assertEquals("Ваня", g.getPlayer().getName());
    }   
    
    @Test(expected = GameWithoutPlayersException.class)
    public void testGameWithoutPlayersException() {
        g.shot(1);
    }
    
    @Test(expected = GameWithoutPlayersException.class)
    public void testGameWithoutPlayersException2() {
        g.toString();
    }
    
    @Test(expected = AddPlayerInStartedGameException.class)
    public void testAddPlayerInStartedGameException() {
        g.addPlayer("Ваня").addPlayer("Петя");
  
        g.shot("X");
        g.addPlayer("Сережа");
    }
    
    @Test
    public void testGame() {
        Game.main(new String[]{"Ваня", "Перя", "2", "4", "X", "4", "/", "5", "3"});
    }
    
    @Test
    public void testEmptyGame() {
        Game.main(new String[]{"Ваня", "Перя"});
    }
}
