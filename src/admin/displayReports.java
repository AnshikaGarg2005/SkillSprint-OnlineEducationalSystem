package admin;
import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

class deleteHolder {
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

    void reportedCourses(String[] fileData) {
        String[] errorString = {"File Not Found"};
        String[] courseFile = getFileData("data.txt");

        String[] courseStorage = new String[fileData.length];
        int[] reportsStorage = new int[fileData.length];

        for(int i=0;i<fileData.length;i++) {
            StringTokenizer st = new StringTokenizer(fileData[i],"|");
            String courseId = st.nextToken();
            int numOfReports = Integer.parseInt(st.nextToken());

            if(numOfReports > 0) {
                courseStorage[i] = courseId;
                reportsStorage[i] = numOfReports;
            }
        }

        for(int j=0;j<courseStorage.length;j++) {
            if(courseStorage[j] != null) {
                StringTokenizer st1 = new StringTokenizer(courseFile[j],"|");
                st1.nextToken();
                st1.nextToken();

                System.out.println("------------------------------------------------------------------------");
                System.out.println("Course ID: " + courseStorage[j] + "\nCourse Name: " + st1.nextToken() + "\nNumber of Reports: " + reportsStorage[j]);
                System.out.println("------------------------------------------------------------------------");
            }
        }
    }
}

class displayReports {
    public static void display() {
        deleteHolder del = new deleteHolder();
        del.reportedCourses(del.getFileData("reports.txt"));
    }
}
