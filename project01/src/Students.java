class Student {
    String id;
    String name;
    String program;
    String batch;
    String password;
    double cgpa;

    public Student(String id, String name, String program, String batch, String password, double cgpa) {
        this.id = id;
        this.name = name;
        this.program = program;
        this.batch = batch;
        this.password = password;
        this.cgpa = cgpa;
    }

    public String toFileString() {
        return id + "," + name + "," + program + "," + batch + "," + password + "," + cgpa;
    }

    public static Student fromFileString(String line) {
        String[] p = line.split(",");
        return new Student(p[0], p[1], p[2], p[3], p[4], Double.parseDouble(p[5]));
    }
}