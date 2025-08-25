class Course {
    String studentId;
    String courseName;

    public Course(String studentId, String courseName) {
        this.studentId = studentId;
        this.courseName = courseName;
    }

    public String toFileString() {
        return studentId + "," + courseName;
    }

    public static Course fromFileString(String line) {
        String[] p = line.split(",");
        return new Course(p[0], p[1]);
    }
}