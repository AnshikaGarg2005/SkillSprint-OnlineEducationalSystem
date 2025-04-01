package admin;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class ProviderDetails {
    private static final String FILE_NAME = "providers.txt";
    public static void ProviderDetail() {
        System.out.println("Provider Details: ");
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, "|");
                if (tokenizer.countTokens() >= 4) {
                    String name = tokenizer.nextToken().trim();
                    String mobile = tokenizer.nextToken().trim();
                    String email = tokenizer.nextToken().trim();
                    String education = tokenizer.nextToken().trim();
                    System.out.println("[Name: " + name +"| " + "Mobile Number: " + mobile +"| " +  "Email: " + email +"| " + "Education: " + education + "]");
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
