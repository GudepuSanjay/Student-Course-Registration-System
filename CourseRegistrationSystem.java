import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Course {
    private String courseName;
    private String courseCode;

    public Course(String courseName, String courseCode) {
        this.courseName = courseName;
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    @Override
    public String toString() {
        return courseCode + ": " + courseName;
    }
}

class Student {
    private String name;
    private ArrayList<Course> registeredCourses;

    public Student(String name) {
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public void registerCourse(Course course) {
        registeredCourses.add(course);
        System.out.println("Successfully registered for " + course.getCourseName());
    }

    public void removeCourse(Course course) {
        if (registeredCourses.remove(course)) {
            System.out.println("Successfully removed " + course.getCourseName());
        } else {
            System.out.println("Course not found in your registered list.");
        }
    }

    public void showRegisteredCourses() {
        System.out.println("\nCourses Registered by " + name + ":");
        if (registeredCourses.isEmpty()) {
            System.out.println("No courses registered.");
        } else {
            for (Course course : registeredCourses) {
                System.out.println(course);
            }
        }
    }
}

public class CourseRegistrationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Creating courses
        Course course1 = new Course("Java Programming", "CS101");
        Course course2 = new Course("Data Structures", "CS102");
        Course course3 = new Course("Operating Systems", "CS103");
        Course course4 = new Course("Database Management", "CS104");

        // Adding courses to a list
        ArrayList<Course> availableCourses = new ArrayList<>();
        availableCourses.add(course1);
        availableCourses.add(course2);
        availableCourses.add(course3);
        availableCourses.add(course4);

        // Creating a student
        System.out.print("Enter student name: ");
        String studentName = scanner.nextLine();
        Student student = new Student(studentName);

        while (true) {
            System.out.println("\nAvailable Courses:");
            for (Course course : availableCourses) {
                System.out.println(course);
            }

            System.out.println("\nMenu:");
            System.out.println("1. Register for a Course");
            System.out.println("2. Remove a Course");
            System.out.println("3. View Registered Courses");
            System.out.println("4. Exit");

            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    System.out.print("Enter course code to register: ");
                    String courseCodeToRegister = scanner.nextLine();
                    boolean foundCourse = false;
                    for (Course course : availableCourses) {
                        if (course.getCourseCode().equals(courseCodeToRegister)) {
                            student.registerCourse(course);
                            foundCourse = true;
                            break;
                        }
                    }
                    if (!foundCourse) {
                        System.out.println("Invalid course code.");
                    }
                    break;

                case 2:
                    System.out.print("Enter course code to remove: ");
                    String courseCodeToRemove = scanner.nextLine();
                    foundCourse = false;
                    for (Course course : availableCourses) {
                        if (course.getCourseCode().equals(courseCodeToRemove)) {
                            student.removeCourse(course);
                            foundCourse = true;
                            break;
                        }
                    }
                    if (!foundCourse) {
                        System.out.println("Invalid course code.");
                    }
                    break;

                case 3:
                    student.showRegisteredCourses();
                    break;

                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }
}
