public class Pupil {
    private Integer rollNo;
    private String name;
    private String email;
    private String course;
    private Double marks;

    public Pupil(Integer rollNo, String name, String email, String course, Double marks) {
        this.rollNo = rollNo;
        this.name = name == null ? "" : name.trim();
        this.email = email == null ? "" : email.trim();
        this.course = course == null ? "" : course.trim();
        this.marks = marks;
    }

    public Integer getRollNo() { return rollNo; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getCourse() { return course; }
    public Double getMarks() { return marks; }

    public void validate() {
        if (rollNo == null) throw new IllegalArgumentException("Roll number is required.");
        if (name.isEmpty()) throw new IllegalArgumentException("Name is required.");
        if (email.isEmpty()) throw new IllegalArgumentException("Email is required.");
        if (course.isEmpty()) throw new IllegalArgumentException("Course is required.");
        if (marks == null) throw new IllegalArgumentException("Marks is required.");
        if (marks < 0.0 || marks > 100.0) throw new IllegalArgumentException("Marks must be between 0 and 100.");
    }

    @Override
    public String toString() {
        return "Roll No: " + rollNo +
                "\nName: " + name +
                "\nEmail: " + email +
                "\nCourse: " + course +
                "\nMarks: " + marks;
    }

    public String toCSV() {
        return rollNo + "," + escape(name) + "," + escape(email) + "," + escape(course) + "," + marks;
    }

    private String escape(String s) {
        return s.replace(",", " ");
    }
}
