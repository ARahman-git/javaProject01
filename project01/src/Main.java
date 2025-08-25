import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        createFileIfNotExists("officers.txt", "admin,1234\n");
        createFileIfNotExists("students.txt", "");
        createFileIfNotExists("courses.txt", "");

        OfficerAuthentication auth = new OfficerAuthentication("officers.txt");
        StudentManager studentManager = new StudentManager("students.txt");
        CourseManager courseManager = new CourseManager("courses.txt");

        System.out.println("==== Student Management System ====");
        boolean loggedIn = false;

        while (!loggedIn) {
            System.out.print("Enter username: ");
            String username = sc.nextLine();
            System.out.print("Enter password: ");
            String password = sc.nextLine();

            if (auth.authenticate(username, password)) {
                loggedIn = true;
                System.out.println("\nLogin successful!\n");
            } else {
                System.out.println("Invalid username or password. Try again.\n");
            }
        }

        while (true) {
            System.out.println("\n1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Assign Course to Student");
            System.out.println("5. View Courses by Student ID");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1 -> studentManager.addStudent(sc);
                case 2 -> studentManager.viewStudents();
                case 3 -> studentManager.searchStudent(sc);
                case 4 -> courseManager.assignCourse(sc);
                case 5 -> courseManager.viewCoursesByStudent(sc);
                case 6 -> {
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }


    private static void createFileIfNotExists(String filename, String defaultContent) throws IOException {
        File f = new File(filename);
        if (!f.exists()) {
            try (FileWriter fw = new FileWriter(f)) {
                fw.write(defaultContent);
            }
            System.out.println("Created file: " + filename);
        }
    }
}

