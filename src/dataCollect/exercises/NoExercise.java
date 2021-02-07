package dataCollect.exercises;

import dataCollect.Exercise;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NoExercise extends Exercise {
    public NoExercise() {
        this.type = "none";
        this.difficulty = "easy";
    }
    public NoExercise(boolean isOngoing, boolean hasEnded, String difficulty, Date date, long duration, List<String> soreAreasBfore, List<String> soreAreasAfter) {
        this.type = "none";
        this.isOngoing = isOngoing;
        this.hasEnded = hasEnded;
        this.difficulty = difficulty;
        this.soreAreasBefore = soreAreasBfore;
        this.soreAreasBefore = soreAreasAfter;
        this.date = date;
        this.duration = duration;
    }
    @Override
    public Exercise getCopy() {
        Exercise e = new NoExercise();
        return super.makeCopyInto(e);
    }

    @Override
    public String getGif() {
        return null;
    }

    @Override
    public List<String> getInstructions() {

        return null;
    }
}
