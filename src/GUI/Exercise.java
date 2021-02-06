package GUI;

import java.util.Date;

public class Excercise {
    public String type; //type of excercise being conducted in this session
    public boolean isOngoing;
    public boolean hasEnded;
    public Date date;
    public long duration;

    public Excercise(String type) {
        this.type = type;
    }

    public void startSession() {
        this.date = new Date();
        boolean ongoing = true;
    }

    public void endSession() {
        if (!this.isOngoing) return;
        this.duration = new Date().getTime()-this.date.getTime();
        this.isOngoing = false;
        this.hasEnded = true;
    }
}
