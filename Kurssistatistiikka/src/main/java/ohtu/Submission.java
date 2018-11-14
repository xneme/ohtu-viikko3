package ohtu;

import java.util.Arrays;
import java.util.List;

public class Submission {
    private int week;
    private int hours;
    private int[] exercises;
    private String course;

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
        return String.format("%s, viikko %d tehtyjä tehtäviä yhteensä %d aikaa kului %d tehdyt tehtävät: %s", course, week, exercises.length, hours, exerciseString);
    }
    
}