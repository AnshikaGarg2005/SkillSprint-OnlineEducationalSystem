import java.io.*;
import java.util.*;
import Creator.Provider;
import Customer.page;
import admin.Admin;

public class OnlineEducationSystem {
    private static final String FILE_NAME = "logins.txt";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Signup\n2. Login\n3. Exit");
            System.out.print("Enter your choice: ");
            try {
                int choice = sc.nextInt();
                sc.nextLine();
                switch (choice) {
                    case 1:
                        signup(sc);
                        break;
                    case 2:
                        if (login(sc)) {
                            return;
                        }
                        break;
                    case 3:
                        System.out.println("SEE YOU AGAIN...");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number (1-3).");
                sc.nextLine();
            }
        }
    }

    private static void signup(Scanner sc) {
        System.out.println("\nSignup as:\n1. Provider\n2. Customer");
        System.out.print("Enter your choice: ");

        int type = 0;
        try {
            type = sc.nextInt();
            if (type != 1 && type != 2) {
                System.out.println("Invalid choice. Please select 1 or 2.");
                return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid number (1 or 2).");
            sc.nextLine();
            return;
        }
        sc.nextLine();

        String role;
        if (type == 1) {
            role = "PROVIDER";
        } else {
            role = "CUSTOMER";
        }

        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        if (!email.contains("@") || !email.contains(".")) {
            System.out.println("Invalid email format. Please try again.");
            return;
        }

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Phone no: ");
        String phoneNo = sc.nextLine();

        String qualification = "";
        if (type == 1) {
            System.out.print("Enter Qualification: ");
            qualification = sc.nextLine();
        }

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        if (loginExists(email)) {
            System.out.println("A login with this email already exists. Please login instead.");
            return;
        }

        String loginId = generateLoginId();
        saveLoginDetails(role, loginId, email, name, phoneNo, qualification, password);
        System.out.println("\nSignup successful! Your Login ID: " + loginId);
    }

    private static boolean loginExists(String email) {
        try (Scanner sc = new Scanner(new File(FILE_NAME))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split("\\|");
                if (parts.length >= 3 && parts[2].equalsIgnoreCase(email)) {
                    return true;
                }
            }
        } catch (IOException e) {
            // File doesn't exist yet, so no logins
        }
        return false;
    }

    private static boolean login(Scanner sc) {
        System.out.print("\nEnter Email: ");
        String email = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        try (Scanner fileScanner = new Scanner(new File(FILE_NAME))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split("\\|");

                if (parts.length >= 7 && parts[2].equalsIgnoreCase(email)) {
                    if (parts[6].equals(password)) {
                        System.out.println("\nLogin successful! Welcome, " + parts[3] + " to " + parts[0].toLowerCase() + " panel.");
                        if(parts[0].equals("PROVIDER")){
                            if(parts.length>=8 && parts[7].equals("BANNED")){
                                System.out.println("------------------------------------------------------------------------");
                                System.out.println("Sorry you are banned from using our system!!!");
                                System.out.println("------------------------------------------------------------------------");
                            }
                            else{
                                Provider.main(parts);
                            }
                        }
                        else if(parts[0].equals("CUSTOMER")){
                            page.main(parts);
                        }
                        else{
                            fileScanner.close();
                            Admin.main(parts);
                        }
                        return true;
                    } else {
                        System.out.println("Incorrect password!");
                        return false;
                    }
                }
            }
            System.out.println("Login not found. Please sign up first.");
        } catch (IOException e) {
            System.out.println("No logins registered yet. Please sign up first.");
        }
        return false;
    }

    private static String generateLoginId() {
        int count = 0;
        try (Scanner sc = new Scanner(new File(FILE_NAME))) {
            while (sc.hasNextLine()) {
                sc.nextLine();
                count++;
            }
        } catch (IOException e) {

        }
        return String.valueOf(count + 1);
    }

    private static void saveLoginDetails(String role, String id, String email, String name, String phoneNo, String qualification, String password) {
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
            writer.write(String.join("|", role, id, email, name, phoneNo, qualification, password));
            writer.write(System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Error saving login details. Please try again.");
        }
    }
}
