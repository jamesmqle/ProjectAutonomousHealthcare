package dataCollect;

import dataCollect.Exercise;
import dataCollect.exercises.Jumps;
import dataCollect.exercises.NoExercise;
import dataCollect.exercises.SittingDumbbellCurls;
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
	private static int randomChanceOfEasy = 10;
	private static String header = "type;isOngoing;hasEnded;difficulty;date;duration;soreAreasBefore;soreAreasAfter";
	public static List<Exercise> easyExercises = getEasyExercises();
	private static int startIncreasingDiffAfter = 3;
    public static List<Exercise> exerciseSessions;
    public static int excludeExerciseCount = 1;
    public static int difficultyReferenceCount = 10;

    public static List<Exercise> mediumExercises = getMediumExercises();
    public static List<Exercise> hardExercises = getHardExercises();

    private static List<Exercise> getEasyExercises() {
        List<Exercise> exercises = new ArrayList<>();
        exercises.add(new Stretches());
        return exercises;
    }

    private static List<Exercise> getMediumExercises() {
        List<Exercise> exercises = new ArrayList<>();
        exercises.add(new Jumps());
        return exercises;
    }

    private static List<Exercise> getHardExercises() {
        List<Exercise> exercises = new ArrayList<>();
        exercises.add(new SittingDumbbellCurls());
        return exercises;
    }

    public static void main(String[] args) {
        for (int i=0; i<20; i++) {
            Exercise e = DataBase.getExerciseRecommendation("energetic");
            e.startSession(new ArrayList<>());
            e.endSession(new ArrayList<>());
        }
        System.out.println("success!");
    }

    public static Exercise getExerciseRecommendation(String feeling) {
        //energetic - random between easy and hard
        //neutral - easy
        //exhausted - are you sore
        getDataFrom(new File(dataRelPath));
        List<Exercise> exerciseList = new ArrayList<>();
        if (feeling.toLowerCase().equals("sore"))
            return new NoExercise();
        else if (feeling.toLowerCase().equals("neutral")) {
            for (Exercise exercise : easyExercises)
                exerciseList.add(exercise.getCopy());
        } else if (feeling.toLowerCase().equals("energetic")) {
            for (Exercise exercise : easyExercises)
                exerciseList.add(exercise.getCopy());
            for (Exercise exercise : mediumExercises)
                exerciseList.add(exercise.getCopy());
            for (Exercise exercise : hardExercises)
                exerciseList.add(exercise.getCopy());
        }

        //pick a random exercise excluding the recent most exercises

        List<Exercise> newList = new ArrayList<>();
        newList.addAll(exerciseList);
        for (Exercise exercise : exerciseList) {
            boolean remove = false;
            int startIdx = exerciseSessions.size() - 1 - excludeExerciseCount;
            if (startIdx<0)
                startIdx = 0;
            for (int i = startIdx; i < exerciseSessions.size(); i++) {
                if (exercise.type.equals(exerciseSessions.get(i).type)) {
                    remove = true;
                }
            }
            if (remove && newList.size()>1)
                newList.remove(exercise);
        }

        Random rand = new Random();
        int idx = rand.nextInt(newList.size());
        return newList.get(idx);
    }

    public static Exercise getExerciseRecommendationOld(String feeling) {
        //energetic - random between easy and hard
        //neutral - easy
        //exhausted - are you sore
        getDataFrom(new File(dataRelPath));
        List<Exercise> exerciseList = new ArrayList<>();
        if (feeling.toLowerCase().equals("sore"))
            return new NoExercise();
        else if (feeling.toLowerCase().equals("neutral")) {
            for (Exercise exercise : easyExercises)
                exerciseList.add(exercise.getCopy());
        }else if (feeling.toLowerCase().equals("energetic")) {
            int easyCount = 0;
            int mediumCount = 0;
            int hardCount = 0;
            int exerciseCount = 0;
            int startIdx = exerciseSessions.size()-1-difficultyReferenceCount;
            if (startIdx<0)
                startIdx = 0;
            for (int i=startIdx; i<exerciseSessions.size(); i++) {
                if (!(exerciseSessions.get(i) instanceof NoExercise)) {
                    if (exerciseSessions.get(i).difficulty.equals("easy"))
                        easyCount++;
                    if (exerciseSessions.get(i).difficulty.equals("medium"))
                        mediumCount++;
                    if (exerciseSessions.get(i).difficulty.equals("hard"))
                        hardCount++;
                }
                exerciseCount++;
            }

            if (exerciseCount<startIncreasingDiffAfter) {
                for (Exercise exercise : easyExercises)
                    exerciseList.add(exercise.getCopy());
            }else {
                Random rand = new Random();
                if(easyCount<3) {
                    int droll = rand.nextInt(2);
                    if (droll == 2) {
                        for (Exercise exercise : easyExercises)
                            exerciseList.add(exercise.getCopy());
                    }
                }else if (hardCount < 3 && exerciseCount>=10) {
                    for (Exercise exercise : hardExercises)
                        exerciseList.add(exercise.getCopy());
                } else if (mediumCount>=4 && easyCount>2) {
                    for (Exercise exercise : mediumExercises)
                        exerciseList.add(exercise.getCopy());
                    int droll = rand.nextInt(2);
                    if (droll==1) {
                        for (Exercise exercise : hardExercises)
                            exerciseList.add(exercise.getCopy());
                    }
                }else {
                    for (Exercise exercise : easyExercises)
                        exerciseList.add(exercise.getCopy());
                    for (Exercise exercise : mediumExercises)
                        exerciseList.add(exercise.getCopy());
                }
            }
            if (exerciseList.isEmpty()) {
                for (Exercise exercise : easyExercises)
                    exerciseList.add(exercise.getCopy());
                for (Exercise exercise : mediumExercises)
                    exerciseList.add(exercise.getCopy());
            }


//            int diceRoll = rand.nextInt(randomChanceOfEasy);
//            if (exerciseCount<startIncreasingDiffAfter || diceRoll==randomChanceOfEasy) {
//                for (Exercise exercise : easyExercises)
//                    exerciseList.add(exercise.getCopy());
//            }else if (easyCount>mediumCount && easyCount>hardCount) {
//                for (Exercise exercise : mediumExercises)
//                    exerciseList.add(exercise.getCopy());
//            }else if (mediumCount>easyCount && mediumCount>hardCount) {
//                for (Exercise exercise : hardExercises)
//                    exerciseList.add(exercise.getCopy());
//                int droll = rand.nextInt(3);
//                if (droll==3) {
//                    for (Exercise exercise : mediumExercises)
//                        exerciseList.add(exercise.getCopy());
//                }
//            }else if (hardCount>mediumCount || hardCount>easyCount){
//                for (Exercise exercise : easyExercises)
//                    exerciseList.add(exercise.getCopy());
//            }else {
//                for (Exercise exercise : hardExercises)
//                    exerciseList.add(exercise.getCopy());
//                for (Exercise exercise : mediumExercises)
//                    exerciseList.add(exercise.getCopy());
//                for (Exercise exercise : easyExercises)
//                    exerciseList.add(exercise.getCopy());
//            }
        }

        //pick a random exercise excluding the recent most exercises

        List<Exercise> newList = new ArrayList<>();
        newList.addAll(exerciseList);
        for (Exercise exercise : exerciseList) {
            boolean remove = false;
            int startIdx = exerciseSessions.size() - 1 - excludeExerciseCount;
            if (startIdx<0)
                startIdx = 0;
            for (int i = startIdx; i < exerciseSessions.size(); i++) {
                if (exercise.type == exerciseSessions.get(i).type) {
                    remove = true;
                }
            }
            if (remove && newList.size()>1)
                newList.remove(exercise);
        }

        Random rand = new Random();
        int idx = rand.nextInt(newList.size());
        return newList.get(idx);
    }

    public static void addSession(Exercise exercise) {
        boolean addHeader = false;
        if (exercise.hasEnded) {
            exerciseSessions.add(exercise);
            try (Scanner sc = new Scanner(new File(dataRelPath))) {
                if (!sc.hasNextLine())
                    addHeader = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            try (FileWriter writer = new FileWriter(dataRelPath, true)) {
                if (addHeader) {
                    writer.append(header);
                    writer.append("\n");
                }
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
            if (sc.hasNextLine())
                sc.nextLine();
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
                if (lineInfo.length>=7) {
                    String[] bforList = lineInfo[6].split(",");
                    String[] aftrList = lineInfo[7].split(",");
                    for (String str : bforList) {
                        if (!str.equals(""))
                            soreAreasBefore.add(str);
                    }
                    for (String str : aftrList) {
                        if (!str.equals(""))
                            soreAreasAfter.add(str);
                    }
                }
                if (type.toLowerCase().equals("sitting_dumbbell_curls"))
                    exerciseSessions.add(new SittingDumbbellCurls(isOngoing, hasEnded, difficulty, date, duration, soreAreasBefore, soreAreasAfter));
                else if (type.toLowerCase().equals("stretches"))
                    exerciseSessions.add(new Stretches(isOngoing, hasEnded, difficulty, date, duration, soreAreasBefore, soreAreasAfter));
                else if (type.toLowerCase().equals("jumps"))
                    exerciseSessions.add(new Jumps(isOngoing, hasEnded, difficulty, date, duration, soreAreasBefore, soreAreasAfter));
                else if (type.toLowerCase().equals("none"))
                    exerciseSessions.add(new NoExercise(isOngoing, hasEnded, difficulty, date, duration, soreAreasBefore, soreAreasAfter));
            }
        } catch (FileNotFoundException e) {
            return;
        }
    }

    public static void createCSV() {
        try (FileWriter writer = new FileWriter(dataRelPath, false)) {
            writer.append(header);
            writer.append("\n");
            for (Exercise exercise : exerciseSessions) {
                writer.append(exercise.createCSVdata(delimiter));
                writer.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
