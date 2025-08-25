import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
class StudentManager {
    private final String fileName;

    public StudentManager(String fileName) {
        this.fileName = fileName;
    }

    public void addStudent(Scanner sc) throws IOException {
        System.out.print("Enter Student ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Program: ");
        String program = sc.nextLine();
        System.out.print("Enter Batch: ");
        String batch = sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.nextLine();
        System.out.print("Enter CGPA: ");
        double cgpa = Double.parseDouble(sc.nextLine());

        Student student = new Student(id, name, program, batch, password, cgpa);
        try (RandomAccessFile raf = new RandomAccessFile(fileName, "rw")) {
            raf.seek(raf.length());
            raf.writeBytes(student.toFileString() + "\n");
        }
        System.out.println("Student added successfully!");
    }

    public void viewStudents() throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(fileName, "r")) {
            String line;
            while ((line = raf.readLine()) != null) {
                Student s = Student.fromFileString(line);
                System.out.println(s.id + " | " + s.name + " | " + s.program + " | " + s.batch + " | CGPA: " + s.cgpa);
            }
        }
    }

    public void searchStudent(Scanner sc) throws IOException {
        System.out.print("Enter Student ID to search: ");
        String searchId = sc.nextLine();

        try (RandomAccessFile raf = new RandomAccessFile(fileName, "r")) {
            String line;
            boolean found = false;
            while ((line = raf.readLine()) != null) {
                Student s = Student.fromFileString(line);
                if (s.id.equals(searchId)) {
                    System.out.println(s.id + " | " + s.name + " | " + s.program + " | " + s.batch + " | CGPA: " + s.cgpa);
                    found = true;
                    break;
                }
            }
            if (!found) System.out.println("Student not found!");
        }
    }
}
