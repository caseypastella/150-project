import java.util.ArrayList;
import java.util.Scanner;
/**
 * Authors: Casey Pastella and Tyler Serpa
 */

public class P5 {
    public static void main(String[] args) {
        Scanner kbd = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();
        char choice;
        System.out.println("This program manages students and their courses.");
        do {
            choice = showMenu(kbd);
            if (choice == '1') {
                Student s = readStudent(kbd);
                students.add(s);
            } else if (choice == '2') {
                double average = calcAverage(students);
                System.out.println(String.format("The average for all students is %.2f", average));
            } else if (choice == '3')
                printStudentReport(kbd, students);
            else if (choice == '4')
                printCourseReport(kbd, students);
            else if (choice == '5')
                printAllStudentReport(students);
            else if (choice != '6') // bad choice entered
                System.out.println("Choice must be 1-6.");
            System.out.println();
        } while (choice != '6');
        System.out.println("*** Program exiting ***");
    }
    public static char showMenu(Scanner kbd) {
        System.out.print("Would you like to:\n\t" +
                "1. Add a student\n\t2. Calculate an average for all students\n\t" +
                "3. Print a report for a student\n\t4. Print a report for a class"
                + "\n\t5. Print a report for all students\n\t6.Quit\nEnter choice --> ");
        char o = kbd.next().charAt(0);
        System.out.println();
        return o;
    }

    private static final String lineEnd = System.getProperty("line.separator");
    
    public static String readStudent(Scanner kbd) {
        return kbd.next();
    }
    
    public static double calcAverage(ArrayList<Student> students) {
        double sum = 0.0;
        for (Student student : students) {
            sum += student.calcAverage();
        }
        if (students.size() == 0)
            return sum;
        return sum / students.size();
        
    }
    
    public static void printStudentReport(Scanner kbd, ArrayList<Student> students) {
        System.out.print("Enter the name of the student that you would like to find a report for --> ");
        String name = kbd.next();
        int index = -1;
        for(int i = 0; i < students.size(); i++) {
            if (students.get(i).getName().equals(name)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.print("Name not found.");
        } else {
            String o = String.format("Student Report: %s Average: %.2f Courses: ", students.get(index).getName(), students.get(index).calcAverage());
            for (StudentCourse course : students.get(index).getCourses()) {
                o += course.getName();
                o += " ";
            }
            o = o.trim();
            System.out.println(o);
            }
        
    }
    
    public static void printCourseReport(Scanner kbd, ArrayList<Student> students) {
        System.out.print("Enter the course that you would like to print a report for --> ");
        String courseName = kbd.next();
        int countStudents = 0;
        double sum = 0.0;
        for (Student student : students) {
            for (int i = 0; i < student.getCourses().size(); i++) {
                if (student.getCourses().get(i).getName().equals(courseName)) {
                    countStudents++;
                    break;
                }

            }

        }
        if (countStudents == 0) {
            System.out.println("Course not found. ");
        } else {
            System.out.printf("Average for %s across all students is %.2f" + lineEnd, courseName, sum);
        }
    }

    
    
    public static void printAllStudentReport(ArrayList<Student> students) {
        String o = "";
        if (students.size() == 0) {
            System.out.println("There are no students yet.");
        } else {
            for (Student student : students) {
                o += student.toString() + lineEnd;
            }
            o = o.trim();
            System.out.println(o);
            }
        }
        
    }
    

    
    


