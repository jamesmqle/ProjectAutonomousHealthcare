package sample;

import GUI.Exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataBase {
	private String name;
	private int age;
	List<String> softExercise;
	public static List<String> easyExercises;
    public static List<String> exerciseTypes;
    public static List<Exercise> exerciseSessions;
    public static int excludeExcerciseCount = 3;

    public static Exercise getExcerciseRecommendation() {
        List<String> tmpExcercistTypes = new ArrayList<>();
        tmpExcercistTypes.addAll(exerciseTypes);
        for (int i=exerciseSessions.size()-1-excludeExcerciseCount; i<exerciseSessions.size(); i++)
            tmpExcercistTypes.remove(exerciseSessions.get(i).type);

        Random rand = new Random();
        int idx = rand.nextInt(exerciseTypes.size());
        return new Exercise(exerciseTypes.get(idx));
    }

    public static void addSession(Exercise excercise) {
        if (excercise.hasEnded)
            exerciseSessions.add(excercise);
    }
}
