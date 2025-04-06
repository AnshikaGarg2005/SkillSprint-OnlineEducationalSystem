package admin;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class ProviderDetails {
    public static void ProviderDetail() {
        System.out.println("Provider Details: ");
        try (BufferedReader reader = new BufferedReader(new FileReader("logins.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                StringTokenizer obj = new StringTokenizer(line, "|");
                if ("PROVIDER".equals(obj.nextToken().trim()) && obj.hasMoreTokens()) {
                    int id = Integer.parseInt(obj.nextToken().trim());
                    String email = obj.nextToken().trim();
                    String name = obj.nextToken().trim();
                    String mobile = obj.nextToken().trim();
                    String education = obj.nextToken().trim();
                    System.out.println("[Id: "+id +" Name: " + name + " | " + "Mobile Number: " + mobile + " | " + "Email: " + email + " | " + "Education: " + education + "]");
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading login.txt file");
        }
    }
}