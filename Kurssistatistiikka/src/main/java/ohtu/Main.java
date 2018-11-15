package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {
        // ÄLÄ laita githubiin omaa opiskelijanumeroasi
        String studentNr = "012345678";
        if (args.length > 0) {
            studentNr = args[0];
        }

        String studentUrl = "https://studies.cs.helsinki.fi/courses/students/" + studentNr + "/submissions";
        String courseUrl = "https://studies.cs.helsinki.fi/courses/courseinfo";

        String studentBodyText = Request.Get(studentUrl).execute().returnContent().asString();
        String courseBodyText = Request.Get(courseUrl).execute().returnContent().asString();

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(studentBodyText, Submission[].class);
        Course[] courses = mapper.fromJson(courseBodyText, Course[].class);

        System.out.print("Opiskelijanumero " + studentNr + "\n\n");

        for (Course c : courses) {
            if (containsCourse(subs, c)) {
                int completedExercises = 0;
                int totalHours = 0;

                System.out.printf("%s %s %d\n\n", c.getFullName(), c.getTerm(), c.getYear());

                for (Submission s : subs) {

                    if (s.getCourse().equals(c.getName())) {
                        
                        completedExercises += s.getEcercises().length;
                        totalHours += s.getHours();

                        s.setTotalExercises(c.getExercises()[s.getWeek()]);
                        System.out.println(s);
                    }
                }
                System.out.print(String.format("\nyhteensä: %d/%d tehtävää %d tuntia\n\n", completedExercises, c.totalExercises(), totalHours));
                
                String courseStatsText = Request.Get("https://studies.cs.helsinki.fi/courses/" + c.getName() + "/stats").execute().returnContent().asString();
                JsonParser parser = new JsonParser();
                JsonObject parsittuData = parser.parse(courseStatsText).getAsJsonObject();
                
                System.out.printf("kurssilla yhteensä %d palautusta, palautettuja tehtäviä %d kpl, aikaa käytetty yhteensä %d tuntia\n\n",
                        parsittuData.entrySet().stream().mapToInt(e -> e.getValue().getAsJsonObject().get("students").getAsInt()).sum(),
                        parsittuData.entrySet().stream().mapToInt(e -> e.getValue().getAsJsonObject().get("exercise_total").getAsInt()).sum(),
                parsittuData.entrySet().stream().mapToInt(e -> e.getValue().getAsJsonObject().get("hour_total").getAsInt()).sum());
                
            }
        }

        

    }

    public static boolean containsCourse(Submission[] subs, Course course) {
        for (Submission s : subs) {
            if (s.getCourse().equals(course.getName())) {
                return true;
            }
        }

        return false;
    }
}
