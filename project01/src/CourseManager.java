import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
class CourseManager {
    private final String fileName;

    public CourseManager(String fileName) {
        this.fileName = fileName;
    }

    public void assignCourse(Scanner sc) throws IOException {
        System.out.print("Enter Student ID: ");
        String studentId = sc.nextLine();
        System.out.print("Enter Course Name: ");
        String courseName = sc.nextLine();

        Course course = new Course(studentId, courseName);
        try (RandomAccessFile raf = new RandomAccessFile(fileName, "rw")) {
            raf.seek(raf.length());
            raf.writeBytes(course.toFileString() + "\n");
        }
        System.out.println("Course assigned successfully!");
    }

    public void viewCoursesByStudent(Scanner sc) throws IOException {
        System.out.print("Enter Student ID: ");
        String studentId = sc.nextLine();

        try (RandomAccessFile raf = new RandomAccessFile(fileName, "r")) {
            String line;
            boolean found = false;
            while ((line = raf.readLine()) != null) {
                Course c = Course.fromFileString(line);
                if (c.studentId.equals(studentId)) {
                    System.out.println("Course: " + c.courseName);
                    found = true;
                }
            }
            if (!found) System.out.println("No courses found for this student.");
        }
    }
}