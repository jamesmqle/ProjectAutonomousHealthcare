package dataCollect;

import GUI.Exercise;

import java.util.*;

//Stanley, everything good? yosh. it keeps disconnecting every now and then tho. Yeah idk why that happens. Maybe
//its because my wifi is sloppy rn. ah, ok. damn eclass, always messign with my wifi. lool. ok lets get back to work. got it. we got
//a lot of stuff to do...

public class DataBase {
	private String name;
	private int age;
	List<String> softExercise;
	public static List<Excercise> easyExercises;
    public static List<Excercise> mediumExercises;
    public static List<Excercise> hardExercises;
    public static List<Exercise> exerciseSessions;
    public static int excludeExerciseCount = 1;
    public static int difficultyReferenceCount = 10;

    public static Exercise getExerciseRecommendation() {
        List<String> tmpExerciseTypeList = new ArrayList<>();

        int easyCount = 0;
        int mediumCount = 0;
        int hardCount = 0;
        for (int i=exerciseSessions.size()-1-difficultyReferenceCount; i<exerciseSessions.size(); i++) {
            if (exerciseSessions.get(i).difficulty=="easy")
                easyCount++;
            if (exerciseSessions.get(i).difficulty=="easy")
                mediumCount++;
            if (exerciseSessions.get(i).difficulty=="easy")
                hardCount++;
        }

        
        tmpExerciseTypes.addAll(exerciseType);
        for (int i=exerciseSessions.size()-1-excludeExerciseCount; i<exerciseSessions.size(); i++)
            tmpExerciseTypes.remove(exerciseSessions.get(i).type);

        Random rand = new Random();
        int idx = rand.nextInt(exerciseTypes.size());
        return new Exercise(exerciseTypes.get(idx));
    }

    public static void addSession(Exercise excercise) {
        if (excercise.hasEnded)
            exerciseSessions.add(excercise);
    }
}
