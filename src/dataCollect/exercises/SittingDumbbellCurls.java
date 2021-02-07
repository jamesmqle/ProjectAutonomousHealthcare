package dataCollect.exercises;

import dataCollect.Exercise;

import java.util.Date;
import java.util.List;

public class SittingDumbbellCurls extends Exercise {
    public SittingDumbbellCurls(boolean isOngoing, boolean hasEnded, String difficulty, Date date, long duration, List<String> soreAreasBfore, List<String> soreAreasAfter) {
        this.type = "sitting_dumbbell_curls";
        this.isOngoing = isOngoing;
        this.hasEnded = hasEnded;
        this.difficulty = "hard";
        this.soreAreasBefore = soreAreasBfore;
        this.soreAreasBefore = soreAreasAfter;
        this.date = date;
        this.duration = duration;
    }

    public SittingDumbbellCurls() {
        this.type = "sitting_dumbbell_curls";
        this.difficulty = "hard";
    }

    @Override
    public Exercise getCopy() {
        Exercise e = new Jumps();
        return super.makeCopyInto(e);
    }

    @Override
    public String getGif() {
        return "C:\\Users\\stani\\IdeaProjects\\ProjectAutonomousHealthcare\\src\\dataCollect\\media\\SittingDumbbellCurls_hard.gif";
    }

    @Override
    public String getInstructions() {
        return "Repeat this 7 times. It is a bit challenging, but your back will thank you later";
    }
}
