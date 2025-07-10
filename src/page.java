package Customer;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.*;

class Utility {
    void displayOptions(int displayNum) {
        System.out.println("Select an option from the list below.");
        String[] mainOptions = {"View Courses","My courses","Exit!"};
        if(displayNum == 0){
            for(int i = 0; i < mainOptions.length; i++) {
                System.out.println("\t" + (i+1) + ". " + mainOptions[i]);
            }
        }
    }

    int userChoice(String displayLine){
        try{
            Scanner sc = new Scanner(System.in);
            System.out.print(displayLine);
            int choice = sc.nextInt();

            return choice;
        }catch(InputMismatchException e){
            System.out.println("\nPlease enter a numerical choice from 1 - 4.\n");
        }

        return 0;
    }

    String userStringInput(String displayLine) {
        try{
            Scanner sc = new Scanner(System.in);
            System.out.print(displayLine);
            String choice = sc.nextLine();

            return choice;
        }catch(InputMismatchException e){
            System.out.println("\nPlease enter a valid value.\n");
        }

        return "";
    }

    int linesInFile(String fileName) {
        try {
            int lines = 0;
            File obj = new File(fileName);
            Scanner reader = new Scanner(obj);

            while(reader.hasNextLine()) {
                reader.nextLine();
                lines += 1;
            }
            reader.close();
            return lines;
        }catch (FileNotFoundException e) {
            return 401;
        }
    }

    String[] getFileData(String fileName) {
        String[] errorString = {"File Not Found"};
        try {
            String[] storage = new String[linesInFile(fileName)];
            File obj = new File(fileName);
            Scanner reader = new Scanner(obj);

            int index = 0;
            while(reader.hasNextLine()) {
                storage[index] = reader.nextLine();
                index += 1;
            }
            reader.close();
            return storage;
        } catch (FileNotFoundException e) {
            return errorString;
        }
    }

    String[] displayCourseDetails(String[] data) {
        String[] courseCodeStorage = new String[data.length];
        for(int j=0;j<data.length;j++) {
            String a = data[j];
            String[] b = new String[a.length()];
            int index = 0;
            String temp = "";

            for(int i=0;i<a.length();i++) {
                if(a.charAt(i) != '|') {
                    temp += a.charAt(i);
                }else {
                    b[index] = temp;
                    temp = "";
                    index+=1;
                }
                courseCodeStorage[j] = b[0];
            }
            System.out.println("------------------------------------------------------------------------");
            System.out.println("S.No: "+(j+1) + "\nCourse ID: " + b[0] + "\nCourse Name: " + b[2] + "\nCourse Hours: " + b[3] + "\nPrice: " + b[4] + "\nDescription: " + b[5] + "\nRatings: " + b[6]);
            System.out.println("------------------------------------------------------------------------");
        }
        return courseCodeStorage;
    }

    boolean courseSubscriptionCheck(String fileName,String courseCode) {
        int flag = 0;
        String[] data = getFileData(fileName);
        for(int i=0;i<data.length;i++) {
            if(courseCode.equals(data[i])) {
                flag = 1;
            }
        }

        if(flag == 1) {
            return true;
        }else {
            return false;
        }
    }

    void buyCourse(String[] data,String courseCode,String mycoursesFilePath) {
        for(int j=0;j<data.length;j++) {
            String a = data[j];
            String[] b = new String[a.length()];
            int index = 0;
            String temp = "";

            for(int i=0;i<a.length();i++) {
                if(a.charAt(i) != '|') {
                    temp += a.charAt(i);
                }else {
                    b[index] = temp;
                    temp = "";
                    index+=1;
                }
            }

            if(courseCode.equals(b[0])) {
                String filePath = mycoursesFilePath;

                if(courseSubscriptionCheck(filePath,courseCode) == false) {
                    try (FileWriter fw = new FileWriter(filePath,true)) {
                        fw.write(courseCode + "\n");
                        System.out.println("\n==================================================================");
                        System.out.println("Course with Course ID "+courseCode +" added to your subscriptions.");
                        System.out.println("==================================================================\n");
                    } catch (IOException e) {
                        System.out.println("\n==================================================================");
                        System.out.println("An exception occured while adding your subscription.\nPlease try again later.");
                        System.out.println("==================================================================\n");
                    }
                }else {
                    System.out.println("\n==================================================================");
                    System.out.println("You are already subscribed to this course.");
                    System.out.println("==================================================================\n");
                }
                break;
            }
        }
    }

    void displayMyCourses(String mycoursesFilePath, String baseCourseFile) {
        String[] subscribedCourses = getFileData(mycoursesFilePath);
        String[] data = getFileData(baseCourseFile);

        for(int i=0;i<subscribedCourses.length;i++) {
            for(int j=0;j<data.length;j++) {
                String a = data[j];
                String[] b = new String[a.length()];
                int index = 0;
                String temp = "";

                for(int z=0;z<a.length();z++) {
                    if(a.charAt(z) != '|') {
                        temp += a.charAt(z);
                    }else {
                        b[index] = temp;
                        temp = "";
                        index+=1;
                    }
                }
                if(subscribedCourses[i].equals(b[0])) {
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("S.No: "+(j+1) + "\nCourse ID: " + b[0] + "\nCourse Name: " + b[2] + "\nCourse Hours: " + b[3] + "\nDescription: " + b[5]);
                    System.out.println("------------------------------------------------------------------------");
                    break;
                }
            }
        }
    }

    void reportCourse(String myCourses, String reportsFilePath, int val) {
        String[] ownedCourses = getFileData(myCourses);
        String[] temp = getFileData(reportsFilePath);
        String a = temp[val];
        int flag = 0;
        String new1="";

        // System.out.println(val);
        for(int z=0;z<ownedCourses.length;z++) {
            int h = Integer.parseInt(String.valueOf(ownedCourses[z].charAt(2))+String.valueOf(ownedCourses[z].charAt(3))+String.valueOf(ownedCourses[z].charAt(4)));
            if((val+1) == h) {
                flag = 1;
            }
        }

        // System.out.println(flag);
        // System.out.println(flag);
        if(flag == 0) {
            System.out.println("\n==================================================================");
            System.out.println("Invalid serial number.\nPlease enter a serial number from the courses you own.");
            System.out.println("==================================================================\n");
        }else {
            for(int i=0;i<a.length();i++) {
                if(i != 6) {
                    new1 += a.charAt(i);
                }else {
                    int reportNum = Integer.parseInt(String.valueOf(a.charAt(i))) + 1;
                    new1 += reportNum;
                }
            }
            temp[val] = new1;

            try(FileWriter fw = new FileWriter(reportsFilePath)) {
                for(int j=0;j<temp.length;j++) {
                    fw.write(temp[j] + "\n");
                }

                System.out.println("\n==================================================================");
                System.out.println("Course has been marked for review.\nThanks for your response.");
                System.out.println("==================================================================\n");
            } catch(IOException e) {
                System.out.println("\n==================================================================");
                System.out.println("Unable to report the course.\nPlease try again later.");
                System.out.println("==================================================================\n");
            }
        }
    }
}

public class page {
    public static void main(String[] args){
        String name=args[3];
        Scanner sc = new Scanner(System.in);
        Utility util = new Utility();
        String courseStorageFile = "Course.txt";
        String mycoursesFilePath = name + ".txt"; //TO BE LATER CHANGED ACCORDING TO THE USER LOGIN
        String reportsFilePath = "reports.txt";
        System.out.println("====================================== Customer Screen ======================================");
        while(true){
            util.displayOptions(0);
            int choice = util.userChoice("Enter numerical choice: ");

            if(choice == 1){
                String[] courseCodes = util.displayCourseDetails(util.getFileData(courseStorageFile));

                while (true) {
                    System.out.println("\n\tSelect an option from the options given");
                    System.out.println("\t1. Buy Course\n\t2. Go Back!");
                    int coursechoice = util.userChoice("\n\tEnter numerical choice: ");
                    if(coursechoice == 1) {
                        int n = util.userChoice("\tEnter the serial number of the course you wish to buy: ");
                        if(n > courseCodes.length) {
                            System.out.println("\tInvalid serial number!");
                        }else {
                            // System.out.println(courseCodes[n-1]+"n");
                            util.buyCourse(util.getFileData(courseStorageFile), courseCodes[n-1],mycoursesFilePath);
                            System.out.println("\tBuying Course!!");
                        }
                    }else if(coursechoice == 2) {
                        System.out.println("\n");
                        break;
                    }else {
                        System.out.println("\tInvalid option!\n\tPlease select an option from the given list.");
                    }
                }
            }else if(choice == 2){
                System.out.println("******************************* Subscribed Courses *******************************");
                util.displayMyCourses(mycoursesFilePath,"Course.txt");

                while (true) {
                    System.out.println("\n\tSelect an option from the options given");
                    System.out.println("\t1. Report a Course\n\t2. Go Back!");
                    int myCourseChoice = util.userChoice("\n\tEnter numerical choice: ");

                    if(myCourseChoice == 1) {
                        int courseToReport = (util.userChoice("\tEnter the serial number of the course you wish to report: ")) - 1;
                        // System.out.println(val);

                        util.reportCourse(mycoursesFilePath ,reportsFilePath, courseToReport);
                    }else if(myCourseChoice == 2) {
                        System.out.println("\n");
                        break;
                    }else {
                        System.out.println("\tInvalid option!\n\tPlease select an option from the given list.");
                    }
                }
            }else if(choice == 3){
                System.out.println("Exiting!!");
                break;
            }else{
                System.out.println("\nInvalid input. Please select a number from the given options.\n");
            }
        }
    }
}
