package dataCollect.exercises;

import dataCollect.Exercise;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Stretches extends Exercise {
    public Stretches() {
        this.type = "stretches";
        this.difficulty = "easy";
    }
    public Stretches(boolean isOngoing, boolean hasEnded, String difficulty, Date date, long duration, List<String> soreAreasBfore, List<String> soreAreasAfter) {
        this.type = "stretches";
        this.isOngoing = isOngoing;
        this.hasEnded = hasEnded;
        this.difficulty = difficulty;
        this.soreAreasBefore = soreAreasBfore;
        this.soreAreasBefore = soreAreasAfter;
        this.date = date;
        this.duration = duration;
    }
    @Override
    public String getGif() {
        return null;
    }

    @Override
    public String getInstructions() {
        return "Ah, the good old stretch. Do not underestimate its helpfulness";
    }

    @Override
    public Exercise getCopy() {
        Exercise e = new Stretches();
        return super.makeCopyInto(e);
    }
}
