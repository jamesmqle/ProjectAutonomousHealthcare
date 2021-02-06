package GUI;

import java.util.*;

import dataCollect.DataBase;

public abstract class Exercise {
    public String type; //type of exercise being conducted in this session
    public boolean isOngoing = false;
    public boolean hasEnded = false;
    public String difficulty;
    
    public Date date;
    public long duration;
    public List<String> soreAreasBefore;
    public List<String> soreAreasAfter;
    public boolean wantsToExcercise;

    public Exercise(String type) {
        this.type = type;
    }
    
    public Exercise

    public void startSession(List<String> soreAreas) {
        if(this.hasEnded) return;
        this.soreAreasBefore = soreAreas;
        this.date = new Date();
        boolean ongoing = true;
    }

    public void endSession(List<String> soreAreas) {
        if (!this.isOngoing) return;
        this.soreAreasAfter = soreAreas;
        this.duration = new Date().getTime()-this.date.getTime();
        this.isOngoing = false;
        DataBase.addSession(this);
        this.hasEnded = true;
    }
}
