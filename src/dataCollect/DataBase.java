package dataCollect;

import dataCollect.Exercise;
import dataCollect.exercises.Jumps;
import dataCollect.exercises.NoExercise;
import dataCollect.exercises.Stretches;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

//Stanley, everything good? yosh. it keeps disconnecting every now and then tho. Yeah idk why that happens. Maybe
//its because my wifi is sloppy rn. ah, ok. damn eclass, always messign with my wifi. lool. ok lets get back to work. got it. we got
//a lot of stuff to do...

public class DataBase {
    private boolean dataLoaded = false;
    private static String dataRelPath = "src/dataCollect/info.csv";
    private static String delimiter = ";";
	private String name;
	private int age;
	public static List<Exercise> easyExercises;
    public static List<Exercise> mediumExercises;
    public static List<Exercise> hardExercises;
    public static List<Exercise> exerciseSessions;
    public static int excludeExerciseCount = 1;
    public static int difficultyReferenceCount = 10;

    public static void main(String[] args) {
        Exercise e = DataBase.getExerciseRecommendation("energetic");
        e.startSession(new ArrayList<>());
        e.endSession(new ArrayList<>());
        System.out.println("success!");
    }


    public static Exercise getExerciseRecommendation(String feeling) {
        //energetic - random between easy and hard
        //neutral - easy
        //exhausted - are you sore
        getDataFrom(new File(dataRelPath));
        List<Exercise> exerciseList = new ArrayList<>();
        if (feeling.toLowerCase()=="neutral") {
            for (Exercise exercise : easyExercises)
                exerciseList.add(exercise.getCopy());
        }else if (feeling.toLowerCase()=="energetic") {
            int easyCount = 0;
            int mediumCount = 0;
            int hardCount = 0;
            int exerciseCount = 0;
            int startIdx = exerciseSessions.size()-1-difficultyReferenceCount;
            for (int i=startIdx; i<exerciseSessions.size(); i++) {
                if (!(exerciseSessions.get(i) instanceof NoExercise)) {
                    if (exerciseSessions.get(i).difficulty == "easy")
                        easyCount++;
                    if (exerciseSessions.get(i).difficulty == "medium")
                        mediumCount++;
                    if (exerciseSessions.get(i).difficulty == "hard")
                        hardCount++;
                }
                exerciseCount++;
            }

            if (exerciseCount<difficultyReferenceCount) {
                for (Exercise exercise : easyExercises)
                    exerciseList.add(exercise.getCopy());
            }else if (easyCount>mediumCount && easyCount>hardCount) {
                for (Exercise exercise : easyExercises)
                    exerciseList.add(exercise.getCopy());
            }else if (mediumCount>easyCount && mediumCount>hardCount) {
                for (Exercise exercise : mediumExercises)
                    exerciseList.add(exercise.getCopy());
            }else if (hardCount>mediumCount){
                for (Exercise exercise : easyExercises)
                    exerciseList.add(exercise.getCopy());
            }else {
                for (Exercise exercise : hardExercises)
                    exerciseList.add(exercise.getCopy());
            }
        }

        //pick a random exercise excluding the recent most exercises

        List<Exercise> newList = new ArrayList<>();
        newList.addAll(exerciseList);
        for (Exercise exercise : exerciseList) {
            boolean add = true;
            int startIdx = exerciseSessions.size() - 1 - excludeExerciseCount;
            for (int i = startIdx; i < exerciseSessions.size(); i++) {
                if (exercise.type == exerciseSessions.get(i).type) {
                    add = false;
                }
            }
            if (add)
                newList.add(exercise);
        }


        Random rand = new Random();
        int idx = rand.nextInt(newList.size());
        return newList.get(idx);
    }

    public static void addSession(Exercise exercise) {
        if (exercise.hasEnded) {
            exerciseSessions.add(exercise);
            try (FileWriter writer = new FileWriter(dataRelPath, true)) {
                writer.append(exercise.createCSVdata(delimiter));
                writer.append("\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void getDataFrom(File file) {
        exerciseSessions = new ArrayList<>();
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String temp = sc.nextLine();
                String[] lineInfo = temp.split(delimiter);
                String type = lineInfo[0];
                boolean isOngoing = Boolean.valueOf(lineInfo[1]);
                boolean hasEnded = Boolean.valueOf(lineInfo[2]);
                String difficulty = lineInfo[3];
                Date date = new Date(Long.valueOf(lineInfo[4]));
                long duration = Long.valueOf(lineInfo[5]);
                List<String> soreAreasBefore = new ArrayList<>();
                List<String> soreAreasAfter = new ArrayList<>();
                String[] bforList = lineInfo[6].split(",");
                String[] aftrList = lineInfo[6].split(",");
                for (String str : bforList) {
                    if (!str.equals(""))
                        soreAreasBefore.add(str);
                }
                for (String str : aftrList) {
                    if (!str.equals(""))
                        soreAreasAfter.add(str);
                }
                if (type.toLowerCase()=="stretches")
                    exerciseSessions.add(new Stretches(isOngoing, hasEnded, difficulty, date, duration, soreAreasBefore, soreAreasAfter));
                else if (type.toLowerCase()=="jumps")
                    exerciseSessions.add(new Jumps(isOngoing, hasEnded, difficulty, date, duration, soreAreasBefore, soreAreasAfter));
                else if (type.toLowerCase()=="none")
                    exerciseSessions.add(new NoExercise(isOngoing, hasEnded, difficulty, date, duration, soreAreasBefore, soreAreasAfter));
            }
        } catch (FileNotFoundException e) {
            return;
        }
    }

    public static void createCSV() {
        try (FileWriter writer = new FileWriter(dataRelPath, false)) {
            for (Exercise exercise : exerciseSessions) {
                writer.append(exercise.createCSVdata(delimiter));
                writer.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
