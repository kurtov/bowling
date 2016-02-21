package ru.kurtov.bowling;

import java.util.ArrayList;
import org.apache.commons.lang.StringUtils;
import ru.kurtov.bowling.exceptions.AddPlayerInStartedGameException;
import ru.kurtov.bowling.exceptions.GameWithoutPlayersException;

public class Game {
    private final ArrayList<Player> players = new ArrayList();
    private int playerNumner = 0;
    private int playerCount  = 0;
    private boolean started = false; //Признак начатой игры
    
    public static void main(String[] args) {
        Game g = new Game();
        int i=0;
        
        for(; i<args.length; i++) {
            if(!Frame.availableValue(args[i])) {
                g.addPlayer(args[i]);
            } else {
                break;
            }
        }
        for(; i<args.length; i++) {
            g.shot(args[i]);
        }
        
        System.out.println(g.toString());
    }
    
    public Game addPlayer(String name) {
        if(started) {
            throw new AddPlayerInStartedGameException();
        }
        
        players.add(new Player(name));
        playerCount++;
        
        return this;
    }
    
    public Player getPlayer() {
        if(playerCount == 0) {
            throw new GameWithoutPlayersException();
        }
        
        Player p = players.get(playerNumner);
        if(p.getCurrentFrame().isComplite()) {
            playerNumner = (playerNumner + 1) % playerCount;
            p = players.get(playerNumner);
        }
        
        return p;        
    }
    
    public Game shot(String pins) {
        started = true;
        getPlayer().shot(pins);
        
        return this;
    }
    
    public Game shot(int pins) {
        started = true;
        getPlayer().shot(pins);
        
        return this;
    }
    
    @Override
    public String toString() {
        if(playerCount == 0) {
            throw new GameWithoutPlayersException();
        }
        String[] res = new String[playerCount];
        
        for(int i = 0; i<playerCount; i++) {
            res[i] = players.get(i).toString();
        }
        
        return StringUtils.join(res, "\n");
    }
}
