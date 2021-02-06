package sample;

import GUI.Excercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataBase {
    public static List<String> excerciseTypes;
    public static List<Excercise> excerciseSessions;
    public static int excludeExcerciseCount = 3;

    public static Excercise getExcerciseRecommendation() {
        List<String> tmpExcercistTypes = new ArrayList<>();
        tmpExcercistTypes.addAll(excerciseTypes);
        for (int i=excerciseSessions.size()-1-excludeExcerciseCount; i<excerciseSessions.size(); i++)
            tmpExcercistTypes.remove(excerciseSessions.get(i).type);

        Random rand = new Random();
        int idx = rand.nextInt(excerciseTypes.size());
        return new Excercise(excerciseTypes.get(idx));
    }

    public static void addSession(Excercise excercise) {
        if (excercise.hasEnded)
            excerciseSessions.add(excercise);
    }
}
