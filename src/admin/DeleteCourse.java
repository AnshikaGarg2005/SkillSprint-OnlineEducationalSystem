package admin;

public class DeleteCourse {
    public static void DeleteCourseLowestRating() {
        if (CourseSort.count == 0) {
            System.out.println("No courses available to delete.");
            return;
        }
        int index= CourseSort.count-1;
        System.out.println("Deleted Course: " + CourseSort.courses[index] + ", Rating: " + CourseSort.ratings[index]);
        CourseSort.count--;
        System.out.println("Updated Course List:");
        CourseSort.displayCourses();
    }
}
