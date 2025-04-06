package admin;
import java.io.*;
import java.util.*;
public class DeleteCourse {
    private static final int report_lim = 3;
    public static void deletecourse(String crs_id) {
        String afterdlt = "";
        try {
            File myobj = new File("Course.txt");
            Scanner myread = new Scanner(myobj);
            while (myread.hasNextLine()) {
                String data = myread.nextLine();
                String[] sp = data.split("\\|");
                if (sp[0].equals(crs_id)) {
                    continue;
                } else {
                    afterdlt += data + "\n";
                }
            }
            myread.close();
        } catch (FileNotFoundException e) {
            System.out.println("Course.txt not found");
        }

        try {
            FileWriter mywrite = new FileWriter("Course.txt");
            mywrite.append(afterdlt);
            mywrite.close();
        } catch (IOException e) {
            System.out.println("Error writing Course.txt");
        }
    }
}
