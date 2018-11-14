package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // ÄLÄ laita githubiin omaa opiskelijanumeroasi
        String studentNr = "012345678";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String studentUrl = "https://studies.cs.helsinki.fi/courses/students/"+studentNr+"/submissions";
        String courseUrl = "https://studies.cs.helsinki.fi/courses/courseinfo";
        
        String studentBodyText = Request.Get(studentUrl).execute().returnContent().asString();
        String courseBodyText = Request.Get(courseUrl).execute().returnContent().asString();
        
        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(studentBodyText, Submission[].class);
        Course[] courses = mapper.fromJson(courseBodyText, Course[].class);
        
        System.out.println("Opiskelijanumero " + studentNr + "\n");
        int completedExercises = 0;
        int totalHours = 0;
        for (Submission submission : subs) {
            completedExercises += submission.getEcercises().length;
            totalHours += submission.getHours();
            
            System.out.println(submission);
        }
        
        System.out.print(String.format("\nyhteensä: %d tehtävää %d tuntia\n", completedExercises, totalHours));

    }
}
