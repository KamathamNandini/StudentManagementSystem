import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

class Student {

    private int id;
    private String name;
    private int age;
    private String course;
    private double marks;

    // Constructor
    public Student(int id, String name, int age, String course, double marks) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.course = course;
        this.marks = marks;
    }

    // Getter
    public int getId() {
        return id;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    // Display Student Details
    public void displayStudent() {
        System.out.println("----------------------------------");
        System.out.println("Student ID : " + id);
        System.out.println("Name       : " + name);
        System.out.println("Age        : " + age);
        System.out.println("Course     : " + course);
        System.out.println("Marks      : " + marks);
        System.out.println("----------------------------------");
    }
}

public class Main {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {

        int choice;

        do {

            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Exit");

            choice = getValidatedInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    viewStudents();
                    break;

                case 3:
                    searchStudent();
                    break;

                case 4:
                    updateStudent();
                    break;

                case 5:
                    deleteStudent();
                    break;

                case 6:
                    System.out.println("Thank You for Using the System!");
                    break;

                default:
                    System.out.println("Invalid Choice! Please Try Again.");
            }

        } while (choice != 6);

        sc.close();
    }

    // ================= ADD STUDENT =================

    public static void addStudent() {

        int id = getValidatedInt("Enter Student ID: ");

        // Duplicate ID Validation
        for (Student s : students) {
            if (s.getId() == id) {
                System.out.println("Student ID Already Exists!");
                return;
            }
        }

        sc.nextLine(); // Clear Buffer

        String name;

        while (true) {

            System.out.print("Enter Name: ");
            name = sc.nextLine();

            if (name.matches("[a-zA-Z ]+")) {
                break;
            } else {
                System.out.println("Invalid Name! Only Alphabets Allowed.");
            }
        }

        int age;

        while (true) {

            age = getValidatedInt("Enter Age: ");

            if (age >= 5 && age <= 100) {
                break;
            } else {
                System.out.println("Age Must Be Between 5 and 100.");
            }
        }

        sc.nextLine(); // Clear Buffer

        System.out.print("Enter Course: ");
        String course = sc.nextLine();

        double marks;

        while (true) {

            marks = getValidatedDouble("Enter Marks: ");

            if (marks >= 0 && marks <= 100) {
                break;
            } else {
                System.out.println("Marks Must Be Between 0 and 100.");
            }
        }

        students.add(new Student(id, name, age, course, marks));

        System.out.println("Student Added Successfully!");
    }

    // ================= VIEW STUDENTS =================

    public static void viewStudents() {

        if (students.isEmpty()) {
            System.out.println("No Student Records Found.");
            return;
        }

        System.out.println("\n===== STUDENT RECORDS =====");

        for (Student s : students) {
            s.displayStudent();
        }
    }

    // ================= SEARCH STUDENT =================

    public static void searchStudent() {

        int searchId = getValidatedInt("Enter Student ID to Search: ");

        boolean found = false;

        for (Student s : students) {

            if (s.getId() == searchId) {
                System.out.println("\nStudent Found:");
                s.displayStudent();
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student Not Found.");
        }
    }

    // ================= UPDATE STUDENT =================

    public static void updateStudent() {

        int updateId = getValidatedInt("Enter Student ID to Update: ");

        boolean updated = false;

        for (Student s : students) {

            if (s.getId() == updateId) {

                sc.nextLine(); // Clear Buffer

                String name;

                while (true) {

                    System.out.print("Enter New Name: ");
                    name = sc.nextLine();

                    if (name.matches("[a-zA-Z ]+")) {
                        break;
                    } else {
                        System.out.println("Invalid Name! Only Alphabets Allowed.");
                    }
                }

                int age;

                while (true) {

                    age = getValidatedInt("Enter New Age: ");

                    if (age >= 5 && age <= 100) {
                        break;
                    } else {
                        System.out.println("Age Must Be Between 5 and 100.");
                    }
                }

                sc.nextLine(); // Clear Buffer

                System.out.print("Enter New Course: ");
                String course = sc.nextLine();

                double marks;

                while (true) {

                    marks = getValidatedDouble("Enter New Marks: ");

                    if (marks >= 0 && marks <= 100) {
                        break;
                    } else {
                        System.out.println("Marks Must Be Between 0 and 100.");
                    }
                }

                s.setName(name);
                s.setAge(age);
                s.setCourse(course);
                s.setMarks(marks);

                System.out.println("Student Updated Successfully!");

                updated = true;
                break;
            }
        }

        if (!updated) {
            System.out.println("Student Not Found.");
        }
    }

    // ================= DELETE STUDENT =================

    public static void deleteStudent() {

        int deleteId = getValidatedInt("Enter Student ID to Delete: ");

        boolean deleted = false;

        for (Student s : students) {

            if (s.getId() == deleteId) {

                students.remove(s);

                System.out.println("Student Deleted Successfully!");

                deleted = true;
                break;
            }
        }

        if (!deleted) {
            System.out.println("Student Not Found.");
        }
    }

    // ================= VALIDATED INTEGER INPUT =================

    public static int getValidatedInt(String message) {

        while (true) {

            try {

                System.out.print(message);

                return sc.nextInt();

            } catch (InputMismatchException e) {

                System.out.println("Invalid Input! Please Enter Numbers Only.");

                sc.nextLine();
            }
        }
    }

    // ================= VALIDATED DOUBLE INPUT =================

    public static double getValidatedDouble(String message) {

        while (true) {

            try {

                System.out.print(message);

                return sc.nextDouble();

            } catch (InputMismatchException e) {

                System.out.println("Invalid Input! Please Enter Valid Decimal Numbers.");

                sc.nextLine();
            }
        }
    }
}