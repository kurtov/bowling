package ru.kurtov.bowling;

import java.util.ArrayList;

public class FrameHelper {
    
    private ArrayList shots;
    private int totalPins;
    private boolean isComplite;
    private String str;
    
    public FrameHelper(int[] shots, int totalPins, boolean isComplite, String str) {
        this.shots = new <Integer>ArrayList();
        for(int i=0, len = shots.length; i<len; i++) {
            this.shots.add(shots[i]);
        }
        
        sharedInit(totalPins, isComplite, str);
    }
    
    public FrameHelper(String[] shots, int totalPins, boolean isComplite, String str) {
        this.shots = new <String>ArrayList();
        for(int i=0, len = shots.length; i<len; i++) {
            this.shots.add(shots[i]);
        }
        
        sharedInit(totalPins, isComplite, str);
    }    
    
    private void sharedInit(int totalPins, boolean isComplite, String str) {
        this.totalPins = totalPins;
        this.isComplite = isComplite;
        this.str = str;
    }
    
    public ArrayList getShots() {
        return shots;
    }

    public int getTotalPins() {
        return totalPins;
    }

    public boolean isComplite() {
        return isComplite;
    }

    public String getStr() {
        return str;
    }
    
    public Frame populateFrame(Frame f) {
        for (Object shots : getShots()) {
            if( shots instanceof Integer) {
                f.shot((int)shots);    
            } else {
                f.shot((String)shots);    
            }
        }
        return f;
    }
}