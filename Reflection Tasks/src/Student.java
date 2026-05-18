public class Student {
    private String name;
    private int age;
    private double gpa;

    public Student() {
        this.name = "Unknown";
        this.age  = 0;
        this.gpa  = 0.0;
    }

    public Student(String name) {
        this.name = name;
        this.age  = 0;
        this.gpa  = 0.0;
    }

    public Student(String name, int age) {
        this.name = name;
        this.age  = age;
        this.gpa  = 0.0;
    }

    public String getName() { return name; }
    public int getAge()     { return age; }
    public double getGpa()  { return gpa; }

    public void sayHello() {
        System.out.println("Hello, I am " + name + " and I am " + age + " years old.");
    }

    private String secretInfo() {
        return "GPA: " + gpa;
    }

    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + ", gpa=" + gpa + "}";
    }
}