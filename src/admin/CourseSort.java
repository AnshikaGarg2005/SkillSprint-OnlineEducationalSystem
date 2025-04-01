package admin;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class CourseSort {
    static String[] courses = new String[100];
    static  double[] ratings = new double[100];
    static int count = 0;
    private static final String FILE_NAME = "providers.txt";

    public static void SortCoursesByRating() {
        System.out.println("Sorting Courses by Rating: ");
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, "|");
                for (int i = 0; i < 4; i++) {
                    tokenizer.nextToken();
                }
                if (tokenizer.countTokens() >= 2) {
                    courses[count] = tokenizer.nextToken();
                    ratings[count] = Double.parseDouble(tokenizer.nextToken());
                    count++;
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("File not found!");
            return;
        }
        //Bubble Sort
        for(int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - i - 1; j++) {
                if (ratings[j] < ratings[j + 1]) {
                    double temp = ratings[j];
                    ratings[j] = ratings[j + 1];
                    ratings[j + 1] = temp;
                    //Sorting Courses
                    String temp2 = courses[j];
                    courses[j] = courses[j + 1];
                    courses[j + 1] = temp2;
                }
            }
        }
    }
    public static void displayCourses () {
        for (int i = 0; i < count; i++) {
            System.out.println("Course: " + courses[i] + " Rating: " + ratings[i]);
        }
    }
}
