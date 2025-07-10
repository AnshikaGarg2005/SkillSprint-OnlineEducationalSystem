package Creator;

import java.util.*;
import java.io.*;
import java.io.FileNotFoundException;

class Course {
    Scanner sc = new Scanner(System.in);
    String course_name;
    String description;
    double price;
    int course_hour;
    static String provider_id = "1";
    StringBuffer course_ID;

    Course() { // constructor to get last value of course_ID
        String lastline = "CS001|default|default|0";
        try {
            File myobj = new File("Course.txt");
            Scanner myread = new Scanner(myobj);
            while (myread.hasNextLine()) {
                String data = myread.nextLine().trim();
                if (!data.isEmpty()) {
                    lastline = data;
                }
            }
            myread.close();
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
        String[] sp = lastline.split("\\|");
        course_ID = new StringBuffer(sp[0]);
    }

    Course(String course_name, int course_hour, double price, String description) {
        this();
        int j = Integer.parseInt(course_ID.substring(2, 5));
        j++;
        String s = "000" + j;
        s = s.substring(s.length() - 3); // get last 3 characters only
        course_ID.replace(2, course_ID.length(), s);
        this.course_name = course_name;
        this.course_hour = course_hour;
        this.description = description;
        this.price = price;
    }

    void course_add() { // abhi file me add kerne ka code kerna hai next
        String a = course_ID + "|" + provider_id + "|" + course_name + "|" + course_hour + "|" + price + "|"
                + description + "|\n";
        try {
            FileWriter mywrite = new FileWriter("Course.txt", true);
            FileWriter report=new FileWriter("reports.txt",true);
            mywrite.append(a);
            report.append(course_ID+"|"+'0'+"|"+provider_id);
            report.close();
            mywrite.close();
        } catch (IOException e) {
            System.out.println("An error occurred");
        }
        System.out.println("\n==================================================================");
        System.out.println("\nCourse registered successfully!!!\n");
        System.out.println("\n==================================================================");
    }

    String updname_check(String upd_id) {
        String upd = "";
        String data;
        String[] sp;
        try {
            File myobj = new File("Course.txt");
            Scanner myread = new Scanner(myobj);
            while (myread.hasNextLine()) {
                data = myread.nextLine().trim();
                sp = data.split("\\|");
                if (sp[0].equals(upd_id)) {
                    upd = data;
                    break;
                }
            }
            myread.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return upd;
    }

    void update_request(String upd, int update) { // upd me hai data hmara and update me hai kya chiz update kerni hai.
        String[] sp = upd.split("\\|");
        switch (update) {
            case 1:
                System.out.print("enter your new course name: ");
                String name = sc.nextLine();
                sp[2] = name;
                break;
            case 2:
                try{
                    System.out.print("enter your new course hours: ");
                    int hours = sc.nextInt();
                    sc.nextLine();
                    String a = String.valueOf(hours);
                    sp[3] = a;
                }catch(InputMismatchException e){
                    System.out.println("Wrong input! Please try again!!");
                }
                break;
            case 3:
                try{
                    System.out.print("enter your new course price: ");
                    double price = sc.nextDouble();
                    sc.nextLine();
                    String b = String.valueOf(price);
                    sp[4] = b;
                }catch(InputMismatchException e){
                    System.out.println("Wrong input! Please try again!!");
                }
                break;
            case 4:
                System.out.print("enter your new course description: ");
                String description = sc.nextLine();
                sp[5] = description;
                break;
            default:
                System.out.println("no such update is available");
        }
        String newupd = sp[0] + "|" + sp[1] + "|" + sp[2] + "|" + sp[3] + "|" + sp[4] + "|" + sp[5] + "|\n";
        // ok so mene change variable tak le aya ab bs file me change kerna bacha hai wo
        // baad me.
        String[] pf;
        String maindata = "";
        try {
            File myobj = new File("Course.txt");
            Scanner myread = new Scanner(myobj);
            while (myread.hasNextLine()) {
                String data = myread.nextLine();
                pf = data.split("\\|");
                if (pf[0].equals(sp[0])) {
                    maindata = maindata + newupd;
                } else {
                    maindata += data + "\n";
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
        try {
            FileWriter mywrite = new FileWriter("Course.txt");
            mywrite.append(maindata);
            mywrite.close();
        } catch (IOException e) {
            System.out.println("error!");
        }
    }

    void viewcourse() {
        String data = "";
        String[] sp;
        try {
            File myobj = new File("Course.txt");
            Scanner myread = new Scanner(myobj);
            System.out.println("******************************* Your Courses *******************************");
            while (myread.hasNextLine()) {
                data = myread.nextLine();
                sp = data.split("\\|");
                if (sp[1].equals(provider_id)) {
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Course ID: " + sp[0] + "\nCourse name: " + sp[2] + "\ncourse hours: " + sp[3]+ "\nprice: " + sp[4] + "\ndescription: " + sp[5]);
                    System.out.println("------------------------------------------------------------------------");
                }
            }
            System.out.println("******************************* Done! *******************************");
            myread.close();
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
    }

    boolean Check(String id){
        String[] sp;
        try{
            File myobj=new File("Course.txt");
            Scanner myread=new Scanner(myobj);
            while(myread.hasNextLine()){
                String data=myread.nextLine();
                sp=data.split("\\|");
                if(sp[0].equals(id)){
                    if(sp[1].equals(provider_id)){
                        return true;
                    }
                }
            }
            myread.close();
        }catch(FileNotFoundException e){
            System.out.println("File not found");
        }
        return false;
    }

    void deletecourse(String crs_id) {
        String[] sp;
        String afterdlt = "";
        try {
            File myobj = new File("Course.txt");
            Scanner myread = new Scanner(myobj);
            while (myread.hasNextLine()) {
                String data = myread.nextLine();
                sp = data.split("\\|");
                if (sp[0].equals(crs_id)) {
                    continue;
                } else {
                    afterdlt += data + "\n";
                }
            }
            myread.close();
        } catch (FileNotFoundException e) {
            System.out.println("file not found");
        }
        try {
            FileWriter mywrite = new FileWriter("Course.txt");
            mywrite.append(afterdlt);
            mywrite.close();
        } catch (IOException e) {
            System.out.println("error");
        }

    }

}

public class Provider { // main class only for display
    public static void main(String args[]) {
        Course.provider_id = args[1];
        int a = 1;
        Scanner sc = new Scanner(System.in);
        System.out.println("====================================== Provider Screen ======================================");
        while (a != 0) {
            try {
                System.out.println("Please choose what you want to do!!!");
                System.out.println("1.Add Course\n2.Update Course\n3.View Course\n4.Delete Course\n5.Exit");
                System.out.print("Please select: ");
                int i = sc.nextInt();
                Course obj = new Course();
                switch (i) { // i will add all methods to this
                    case 1: // to add course to text file.
                        sc.nextLine();
                        System.out.print("Enter Course name: ");
                        String course_name = sc.nextLine();
                        System.out.print("enter course hours: ");
                        int course_hour = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Price of your course: ");
                        double price = sc.nextDouble();
                        sc.nextLine();
                        System.out.println("Add some description about course: ");
                        String description = sc.nextLine();
                        Course obj1 = new Course(course_name, course_hour, price, description);
                        obj1.course_add();
                        break;
                    case 2:
                        sc.nextLine();
                        while(true){
                            System.out.print("Which course you want to update(tell your course ID): ");
                            String upd_id = sc.nextLine();
                            boolean x = obj.Check(upd_id);
                            if (x == true) {
                                String upd = obj.updname_check(upd_id);
                                System.out.println("what you want to change!!! \n1.Name of your course\n2.number of hours\n3.price\n4.description");
                                System.out.print("select: ");
                                int update = sc.nextInt();
                                sc.nextLine();
                                obj.update_request(upd, update);
                                break;
                            }
                            else {
                                System.out.println("This is not your course!");
                            }
                        }
                        break;
                    case 3:
                        obj.viewcourse();
                        break;
                    case 4:
                        sc.nextLine();
                        while(true){
                            System.out.print("enter course id of your course: ");
                            String id = sc.nextLine();
                            boolean x = obj.Check(id);
                            if (x == true) {
                                obj.deletecourse(id);
                                break;
                            }
                            else {
                                System.out.println("This is not your course!");
                            }
                        }
                        break;
                    case 5:
                        a = 0;
                        break;
                    default:
                        System.out.println("Wrong Choice! Please try again");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Wrong Input!!! Please enter a valid number");
                sc.nextLine();
            }
        }
    }
}