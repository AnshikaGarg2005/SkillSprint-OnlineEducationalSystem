package admin;
import java.io.*;
import java.util.StringTokenizer;

public class BanProvider {
    protected static final int Report_lim = 3;
    protected String a = "";
    public void Reports() {
        try (BufferedReader reader = new BufferedReader(new FileReader("reports.txt"))) {
            String line;
            while ((line=reader.readLine())!= null) {
                StringTokenizer obj = new StringTokenizer(line, "|");
                String  C_id = obj.nextToken().trim();
                int report = Integer.parseInt(obj.nextToken().trim());
                String  P_id = obj.nextToken().trim();
                if (report > Report_lim) {
                    a=a+","; //Store provider id which exceeds report_lim
                }
                a=a+P_id;
            }
        } catch (IOException e) {
            System.out.println("Error reading reports.txt file");
        }
    }
}
class Writer extends BanProvider {
    public void banProviders() {
        super.Reports();
        if (a.isEmpty()) {
            System.out.println("No providers exceed the report limit.");
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader("logins.txt"));
             BufferedWriter writer = new BufferedWriter(new FileWriter("logins_temp.txt"))) {
            String line;
            boolean updated = false;
            while ((line = reader.readLine()) != null) {
                StringTokenizer obj = new StringTokenizer(line, "|");
                if (obj.hasMoreTokens() && "PROVIDER".equals(obj.nextToken().trim())) {
                    String P_id = obj.nextToken().trim();
                    if (a.contains(P_id) && !line.contains("BANNED")) {
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
        }catch (IOException e) {
            System.out.println("Error reading logins.txt file");
        }

        File originalFile = new File("logins.txt");
        File tempFile = new File("logins_temp.txt");
        if (originalFile.delete()) {
            tempFile.renameTo(originalFile);
        } else {
            System.out.println("Error replacing the old file.");
        }
    }
}