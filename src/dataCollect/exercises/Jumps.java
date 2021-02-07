package dataCollect.exercises;

import dataCollect.Exercise;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Jumps extends Exercise {

    public Jumps(boolean isOngoing, boolean hasEnded, String difficulty, Date date, long duration, List<String> soreAreasBfore, List<String> soreAreasAfter) {
        this.type = "jumps";
        this.isOngoing = isOngoing;
        this.hasEnded = hasEnded;
        this.difficulty = difficulty;
        this.soreAreasBefore = soreAreasBfore;
        this.soreAreasBefore = soreAreasAfter;
        this.date = date;
        this.duration = duration;
    }

    public Jumps() {
        this.type = "jumps";
        this.difficulty = "medium";
    }

    @Override
    public Exercise getCopy() {
        Exercise e = new Jumps();
        return super.makeCopyInto(e);
    }

    @Override
    public String getGif() {
        return "../media/stretches_easy.gif";
    }

    @Override
    public List<String> getInstructions() {
        return null;
    }
}
