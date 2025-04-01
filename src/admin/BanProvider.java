package admin;
import java.io.*;
import java.util.StringTokenizer;

public class BanProvider {
    private static final String FILE_NAME = "providers.txt";
    private static final int REPORT_LIMIT = 3;

    public static void BanProviderOnReports() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            boolean found = false;
            while ((line = reader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, "|");
                for (int i = 0; i < 6; i++) {
                    tokenizer.nextToken();
                }
                if (tokenizer.hasMoreTokens()) {
                    int reports = Integer.parseInt(tokenizer.nextToken().trim());
                    if (reports > REPORT_LIMIT) {
                        System.out.println("Provider Banned: " + line);
                        found = true;
                    }
                }
            }
            if (!found) {
                System.out.println("No providers exceeded the report limit.");
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public static void BanProviderWriter() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
             BufferedWriter writer = new BufferedWriter(new FileWriter("providers_temp.txt"))) {
            String line;
            boolean updated = false;
            while ((line = reader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, "|");
                for (int i = 0; i < 6 ; i++) {
                    tokenizer.nextToken();
                }
                if (tokenizer.hasMoreTokens()) {
                    int reports = Integer.parseInt(tokenizer.nextToken().trim());
                    if (reports > REPORT_LIMIT && !line.contains("BANNED")) {
                        line = line + " | BANNED";
                        updated = true;
                    }
                }
                writer.write(line);
                writer.newLine();
            }
            if (updated) {
                System.out.println("Banned providers marked.");
            } else {
                System.out.println("No new providers required banning.");
            }
        } catch (IOException e) {
            System.out.println("Error processing file: " + e.getMessage());
        }
        File originalFile = new File(FILE_NAME);
        File tempFile = new File("providers_temp.txt");
        if (originalFile.delete()) {
            tempFile.renameTo(originalFile);
        } else {
            System.out.println("Error replacing the old file.");
        }
    }
}
