package ohtu;

import java.util.Arrays;
import java.util.List;

public class Submission {
    private int week;
    private int hours;
    private int[] exercises;
    private String course;
    private int totalExercises;

    public void setWeek(int week) {
        this.week = week;
    }

    public int getWeek() {
        return week;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getHours() {
        return hours;
    }

    public void setEcercises(int[] ecercises) {
        this.exercises = ecercises;
    }

    public int[] getEcercises() {
        return exercises;
    }

    public void setTotalExercises(int totalExercises) {
        this.totalExercises = totalExercises;
    }

    public int getTotalExercises() {
        return totalExercises;
    }
    
    
    public void setCourse(String course) {
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public String toString() {
        String exerciseString = Arrays.toString(exercises);
        exerciseString = exerciseString.substring(1, exerciseString.length() - 1);
        return String.format("viikko %d:\n tehtyjä tehtäviä %d/%d aikaa kului %d tehdyt tehtävät: %s", week, exercises.length, totalExercises, hours, exerciseString);
    }
    
}