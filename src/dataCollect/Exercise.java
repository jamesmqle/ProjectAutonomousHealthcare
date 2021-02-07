package dataCollect;
import java.util.*;

import dataCollect.DataBase;
import dataCollect.exercises.Jumps;
import dataCollect.exercises.NoExercise;
import dataCollect.exercises.Stretches;

public abstract class Exercise {
	public String type; //type of exercise being conducted in this session
	public boolean isOngoing = false;
	public boolean hasEnded = false;
	public String difficulty;

	public Date date;
	public long duration;
	public List<String> soreAreasBefore;
	public List<String> soreAreasAfter;

	public void startSession(List<String> soreAreas) {
		if(this.hasEnded) return;
		this.soreAreasBefore = soreAreas;
		this.date = new Date();
		boolean ongoing = true;
	}

	public void endSession(List<String> soreAreas) {
		if (!this.isOngoing) return;
		this.soreAreasAfter = soreAreas;
		this.duration = new Date().getTime()-this.date.getTime();
		this.isOngoing = false;
		DataBase.addSession(this);
		this.hasEnded = true;
	}

	public abstract Exercise getCopy();
	public abstract String getGif();
	public abstract List<String> getInstructions();

	protected Exercise makeCopyInto(Exercise e) {
		e.type = this.type;
		e.isOngoing = this.isOngoing;
		e.hasEnded = this.hasEnded;
		e.difficulty = this.difficulty;

		e.date = (Date) this.date.clone();
		e.duration = this.duration;
		e.soreAreasBefore = new ArrayList<>();
		e.soreAreasAfter = new ArrayList<>();
		e.soreAreasBefore.addAll(this.soreAreasBefore);
		e.soreAreasBefore.addAll(this.soreAreasAfter);
		return e;
	}

	public String createCSVdata(String delimiter) {
		String soreAreasBfore = "";
		String soreAreasAfter = "";

		String dem = ",";
		for (String area : this.soreAreasBefore) {
			soreAreasBfore += area+dem;
		}
		for (String area : this.soreAreasAfter) {
			soreAreasBfore += area+dem;
		}
		return this.type + delimiter +
				this.isOngoing + delimiter +
				this.hasEnded + delimiter +
				this.difficulty + delimiter +
				this.date.getTime() + delimiter +
				this.duration + delimiter +
				soreAreasBfore + delimiter +
				soreAreasAfter + delimiter;
	}
}
