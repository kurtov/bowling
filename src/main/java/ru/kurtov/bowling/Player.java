package ru.kurtov.bowling;

import org.apache.commons.lang.StringUtils;
import ru.kurtov.bowling.calculation.Calculation;
import ru.kurtov.bowling.exceptions.BowlingException;
import ru.kurtov.bowling.exceptions.ExceedFramesCountException;

public class Player {
    static public final int FRAMES_COUNT = 10;
    private final String name;
    private final Frame[] frames = new Frame[FRAMES_COUNT];

    private int currentFrameIndex = 0;
    
    public Player(String name) {
        this.name = name;
        int lastIndes = FRAMES_COUNT-1;
        
        for(int i = 0; i<lastIndes; i++) {
            frames[i] = new Frame();
        }
        frames[lastIndes] = new TenthFrame();
    }
    
    public String getName() {
        return name;
    }
    
    public Frame getFrame(int index) {
        return frames[index];
    }
    
    public Player shot(String pins) {
        getNotCompliteFrame().shot(pins);
        
        return this;
    }
    
    public Player shot(int pins) {
        getNotCompliteFrame().shot(pins);
        
        return this;
    }
    
    //Метод используется для демонстрации возможностей Mock-объектов
    public Player setFrame(Frame f) {
        //Сместить currentFrameIndex на первый незавершенный фрейм
        //А потом проверить, пустой ли это фрейм
        //Если да, установить этому фрейму значение f
        //Иначе - нельзя заменить начатый фрейм.
        if(getNotCompliteFrame().isEmpty()) {
            frames[currentFrameIndex] = f;
        } else {
            throw new BowlingException();
        }
        
        return this;
    }
    
    public int getScore() {
        Frame currentFrame;
        Frame nextFrame;
        Frame nextNextFrame;
        
        int currentScore = 0;
        
        
        for(int i = 0; i<= currentFrameIndex; i++) {
            currentFrame = frames[i];
                        
            //Очки считаем только в завершенных фреймах
            if(currentFrame.isComplite()) {
                Calculation calculation = currentFrame.getCalculation();
                Integer score = calculation.getScore(this, i);
                
                if(score != null) {
                    currentScore += score;
                    currentFrame.setScore(currentScore);
                }
            }
        }
        return currentScore;
    }
   
    public Frame getCurrentFrame() {
        return frames[currentFrameIndex];
    }

    private Frame getNotCompliteFrame() {
        Frame currentFrame = frames[currentFrameIndex];

        if(currentFrame.isComplite()) {
            if(currentFrameIndex < FRAMES_COUNT - 1) {
                currentFrame = frames[++currentFrameIndex];
            } else {
                throw new ExceedFramesCountException();
            }
        }
        
        return currentFrame;
    }
    
    @Override
    public String toString() {
        String[] shots = new String[FRAMES_COUNT];
        String[] scores = new String[FRAMES_COUNT];
        StringBuilder res = new StringBuilder("");
        
        this.getScore(); //Рассчитать очки

        for(int i=0; i<FRAMES_COUNT; i++) {
            Frame f = frames[i];
            String scoreFormat = i==FRAMES_COUNT-1 ? "%5d" : "%3d";
            String space = i==FRAMES_COUNT-1 ? "     " : "   ";
            
            shots[i] = f.shotsToString();
            
            if(f.getScore() == null)
                scores[i] = space;
            else {
                scores[i] = String.format(scoreFormat, f.getScore());
            }
        }
        
        res
                .append("┌─────────┬───┬───┬───┬───┬───┬───┬───┬───┬───┬─────┐\n")
                .append("│   Имя   │ 1 │ 2 │ 3 │ 4 │ 5 │ 6 │ 7 │ 8 │ 9 │  10 │\n")
                .append("├─────────┼─┬─┼─┬─┼─┬─┼─┬─┼─┬─┼─┬─┼─┬─┼─┬─┼─┬─┼─┬─┬─┤\n")
                .append("│")
                .append(String.format("%-9s", StringUtils.substring(name, 0, 9)))
                .append("│")
                .append(StringUtils.join(shots, "│"))
                .append("│\n")
                .append("│         ├─┴─┼─┴─┼─┴─┼─┴─┼─┴─┼─┴─┼─┴─┼─┴─┼─┴─┼─┴─┴─┤\n")
                .append("│         │")
                .append(StringUtils.join(scores, "│"))
                .append("│\n")
                .append("└─────────┴───┴───┴───┴───┴───┴───┴───┴───┴───┴─────┘");
        return res.toString();
    }
}
