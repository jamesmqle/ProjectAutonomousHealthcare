package dataCollect;

import java.util.*;
import java.io.*;

public class Exercise {
	private Date date;
	private double duration;
	private List<String> soreAreas;
	private boolean wantToExercise;
	private String exercise;
	private int reps;


	public Exercise(Date date) {
	    
	}
	
	public Exercise() {
		
	}
	
	public void end() {
		
	}


	public Date getDate() {
		return date;
	}


	public List<String> getSoreAreas() {
		return soreAreas;
	}


	public void setSoreAreas(List<String> soreAreas) {
		this.soreAreas = soreAreas;
	}


	public boolean isWantToExercise() {
		return wantToExercise;
	}


	public void setWantToExercise(boolean wantToExercise) {
		this.wantToExercise = wantToExercise;
	}


	public String getExercise() {
		return exercise;
	}


	public void setExercise(String exercise) {
		this.exercise = exercise;
	}


	public int getReps() {
		return reps;
	}


	public void setReps(int reps) {
		this.reps = reps;
	}
	
}
