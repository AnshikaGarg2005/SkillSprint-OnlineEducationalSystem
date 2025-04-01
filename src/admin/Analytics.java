package admin;
import java.io.*;
import java.util.StringTokenizer;

public class Analytics {
    private static final String PROVIDERS_FILE = "providers.txt";
    private static final String CUSTOMERS_FILE = "Coustomer.txt";
    private static final String ADMIN_FILE = "AdminFile.txt";

    public static void ViewAnalytics() {
        int providerCount = 0;
        int customerCount = 0;
        int adminCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(PROVIDERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                providerCount++;
            }
        }catch (IOException e) {
            System.out.println("Error reading providers file: " + e.getMessage());
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(CUSTOMERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                customerCount++;
            }
        }catch (IOException e) {
            System.out.println("Error reading customers file: " + e.getMessage());
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(ADMIN_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                adminCount++;
            }
        }catch (IOException e) {
            System.out.println("Error reading admin file: " + e.getMessage());
        }

        System.out.println("ANALYTICS");
        System.out.println("Total Providers: " + providerCount);
        System.out.println("Total Users: " + customerCount);
        System.out.println("Total Courses: " + providerCount);
        System.out.println("Total Admin: " + adminCount);
    }
}
