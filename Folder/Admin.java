package admin;
import java.util.Scanner;

public class Admin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int flag=0;
        while (flag==0) {
            System.out.println("Admin Panel - Choose an Option:");
            System.out.println("1. View Provider Details");
            System.out.println("2. Delete banned Courses");
            System.out.println("3. Ban Provider");
            System.out.println("4. View Analytics");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    ProviderDetails.ProviderDetail();
                    break;
                case 2:
                    sc.nextLine();
                    System.out.print("enter course id of the course which you want to: ");
                    String id = sc.nextLine();
                    DeleteCourse.deletecourse(id);
                    System.out.println("\n==================================================================");
                    System.out.println("Course successfully Deleted!!!");
                    System.out.println("\n==================================================================");
                    break;
                case 3:
                    Writer writer = new Writer();
                    writer.banProviders();
                    break;
                case 4:
                    Analytics.ViewAnalytics();
                    break;
                case 5:
                    System.out.println("Exiting Admin Panel...");
                    flag=1;
                    break;
                default:
                    System.out.println("Invalid choice! Please enter choice between 1 and 6");
            }
            System.out.println();
        }
    }
}
