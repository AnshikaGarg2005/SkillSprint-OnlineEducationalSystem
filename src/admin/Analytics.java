package admin;
import java.io.*;
import java.util.StringTokenizer;

public class Analytics {
    public static void ViewAnalytics() {
        int providerCount = 0;
        int customerCount = 0;
        int adminCount = 0;
        int totalCourses = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader("logins.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                StringTokenizer obj = new StringTokenizer(line, "|");
                if (obj.hasMoreTokens()) {
                    String role = obj.nextToken().trim();
                    if ("PROVIDER".equalsIgnoreCase(role)) {
                        providerCount++;
                    }else if ("CUSTOMER".equalsIgnoreCase(role)) {
                        customerCount++;
                    } else if ("ADMIN".equalsIgnoreCase(role)) {
                        adminCount++;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        try (BufferedReader reader = new BufferedReader(new FileReader("Course.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                totalCourses++;
            }
        }catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        System.out.println("ANALYTICS");
        System.out.println("Total Providers: " + providerCount);
        System.out.println("Total Users: " + customerCount);
        System.out.println("Total Courses: " + totalCourses);
        System.out.println("Total Admin: " + adminCount);
    }
}