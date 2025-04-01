package admin;

public class Admin {
    public static void main(String[] args) {
        ProviderDetails.ProviderDetail();
        System.out.println();
        CourseSort.SortCoursesByRating();
        CourseSort.displayCourses();
        System.out.println();
        DeleteCourse.DeleteCourseLowestRating();
        System.out.println();
        BanProvider.BanProviderOnReports();
        System.out.println();
        BanProvider.BanProviderWriter();
        System.out.println();
        Analytics.ViewAnalytics();
    }
}







