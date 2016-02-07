package ru.kurtov.bowling;

import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class MockTest {
    Player player;
    
    @Before
    public void setUp() {
        player = new Player("Петя");
    }
    
    @Test
    public void testMock() {
        //Предположим, что еще нет реализации класса TenthFrame
        //В 10-м фрейме игрок выбил страйк, а следом 4 и 0
        Frame f = mock(TenthFrame.class);
        when(f.getPins(0)).thenReturn(10);
        when(f.getPins(1)).thenReturn(4);
        when(f.getPins(2)).thenReturn(0);
        
        when(f.getType()).thenReturn(Frame.ORDINAR);
        when(f.isComplite()).thenReturn(true);
        when(f.getTotalPins()).thenReturn(14);
        when(f.getShotInFrame()).thenReturn(3);
        
        when(f.getScore()).thenReturn(278);
        when(f.shotsToString()).thenReturn("X│4│0");
        
        
        //System.out.println("10-й фрейм, 1-й бросок: " + f.getPins(0));
        //System.out.println("10-й фрейм, 2-й бросок: " + f.getPins(1));
        //System.out.println("10-й фрейм, 3-й бросок: " + f.getPins(2));
        //System.out.println("Тип 10-го фрейма: " + f.getType());
        //System.out.println("10-й фрейм завершен?: " + f.isComplite());
        //System.out.println("За фрейм сбито кегель: " + f.getTotalPins());
        //System.out.println("За фрейм проведено бросков " + f.getShotInFrame());
        
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
                .setFrame(f);                      //10

        assertEquals(278, player.getScore());
        System.out.println(player.toString());

        verify(f, atLeastOnce()).getPins(0);  //минимум 1 раз вызывался
        verify(f, atLeastOnce()).getPins(1);  //минимум 1 раз вызывался
        verify(f, never()).getPins(2);        //Ни разу не вызывался
        
    }
}