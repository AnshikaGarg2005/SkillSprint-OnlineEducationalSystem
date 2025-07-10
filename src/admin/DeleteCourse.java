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
    public static void delCrsOnReports() {
        try (BufferedReader reader = new BufferedReader(new FileReader("reports.txt"))) {
            String line;
            while ((line = reader.readLine())!= null) {
                StringTokenizer st = new StringTokenizer(line, "|");
                if (st.countTokens() >= 3) {
                    String P_id = st.nextToken().trim();
                    String report_str = st.nextToken().trim();
                    String crs_id = st.nextToken().trim();
                    int report = Integer.parseInt(report_str);
                    if (report >report_lim) {
                        deletecourse(crs_id);
                        System.out.println("Deleted course: " +crs_id);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading reports.txt");
        }
    }
}
