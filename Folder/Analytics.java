package admin;
import java.io.*;
import java.util.Scanner;
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
        try{
            File myobj=new File("Course.txt");
            Scanner myread=new Scanner(myobj);
            while (myread.hasNextLine()) {
                myread.nextLine();
                totalCourses++;
            }
            myread.close();
        }catch(FileNotFoundException e){
            System.out.println("file not found!!!");
        }
        System.out.println("ANALYTICS");
        System.out.println("Total Providers: " + providerCount);
        System.out.println("Total Users: " + customerCount);
        System.out.println("Total Courses: " + totalCourses);
        System.out.println("Total Admin: " + adminCount);
        System.out.println("Total Reports!!!");
        displayReports.display();
    }
}