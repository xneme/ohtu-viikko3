package ohtu;

public class Course {
    private String name;
    private String fullName;
    private String term;
    private int year;
    private int[] exercises;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getTerm() {
        return term;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }

    public int[] getExercises() {
        return exercises;
    }
    
    public int totalExercises() {
        int total = 0;
        for (int i : exercises) {
            total += i;
        }
        
        return total;
    }
}
