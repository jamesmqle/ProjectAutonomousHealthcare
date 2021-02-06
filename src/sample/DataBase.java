package sample;

import GUI.Excercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataBase {
    public static List<String> excerciseTypes;
    public static List<Excercise> excerciseSessions;
    public static int excludeExcerciseCount = 3;

    public static String getExcerciseRecommendation() {
        Random rand = new Random();
        List<String> tmpExcercistTypes = new ArrayList<>().addAll(excerciseTypes);
        for (int i=excerciseSessions.size()-1-excludeExcerciseCount; i<excerciseSessions.size(); i++)


        rand.nextInt(excerciseTypes.size());
    }

    public static void addSession(Excercise excercise) {
        if (excercise.hasEnded)
            excerciseSessions.add(excercise);
    }
}
