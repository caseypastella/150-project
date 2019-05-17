import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class P5TestThin {
    private static PrintStream console;
    private static ByteArrayOutputStream out;
    private static InputStream in;

    private static final String lineEnd = System.getProperty("line.separator");

    public static void addCourseToStudent(Student s, String cName, double[] in) {
        ArrayList<Double> grades = new ArrayList<>();

        for (double d : in)
            grades.add(d);
        s.addCourse(cName, grades);
    }

    public static Student makeStudent(String name, String[] names, double[][] grades) {
        Student o = new Student(name);
        for (int i = 0; i < names.length; i++)
            addCourseToStudent(o, names[i], grades[i]);
        return o;
    }

    @BeforeClass
    public static void beforeTestsBegin() {
        console = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        in = System.in;
    }

    @AfterClass
    public static void afterTestsEnd() {
        System.setOut(console);
        System.setIn(in);
    }

    @After
    public void afterEachTest() {
        out.reset();
    }

    @Test
    public void testReadStudent1() {
        String input = "Matt CPSC150 100 100 90 85 -1 CPSC150L 100 100 100 -1 done";
        Student expected = new Student("Matt");

        addCourseToStudent(expected, "CPSC150", new double[]{100., 100., 90., 85.});
        addCourseToStudent(expected, "CPSC150L", new double[]{100., 100., 100.});

        Student actual = P5.readStudent(new Scanner(input));

        assertEquals(
                String.format("readStudent produced %s with input \"%s\"", actual.toString(), input),
                actual, expected
        );
    }

    @Test
    public void testReadStudent2() {
        String input = "Matt CPSC150 100 100 90 85 -1 done";
        Student expected = new Student("Matt");

        addCourseToStudent(expected, "CPSC150", new double[]{100., 100., 90., 85.});

        Student actual = P5.readStudent(new Scanner(input));

        assertEquals(
                String.format("readStudent produced %s with input \"%s\"", actual.toString(), input),
                actual, expected
        );
    }

    @Test
    public void testReadStudent3() {
        String input = "Matt CPSC150 100 100 90 85 -1 CPSC150L 100 100 100 -1 ENGR213 65 75 70 90 -1 done";
        Student expected = new Student("Matt");

        addCourseToStudent(expected, "CPSC150", new double[]{100., 100., 90., 85.});
        addCourseToStudent(expected, "CPSC150L", new double[]{100., 100., 100.});
        addCourseToStudent(expected, "ENGR213", new double[]{65, 75, 70, 90});

        Student actual = P5.readStudent(new Scanner(input));

        assertEquals(
                String.format("readStudent produced %s with input \"%s\"", actual.toString(), input),
                actual, expected
        );
    }

    @Test
    public void testCalcAverage1() {
        ArrayList<Student> input = new ArrayList<>();

        double delta = 0.0001;
        double expected = 0.0;
        double actual = P5.calcAverage(input);

        assertEquals("calcAverage returns a nonzero average with an empty list",
                expected, actual, delta);
    }

    @Test
    public void testCalcAverage2() {
        ArrayList<Student> input = new ArrayList<>();
        input.add(makeStudent("Matt",
                new String[]{"CPSC150", "CPSC150L"},
                new double[][]{{100, 90, 80, 70}, {100, 95, 90}}
                )
        );

        double delta = 0.0001;
        double expected = 90.0;
        double actual = P5.calcAverage(input);

        assertEquals("calcAverage returns an incorrect average for a list of length 1",
                expected, actual, delta);
    }

    @Test
    public void testCalcAverage3() {
        ArrayList<Student> input = new ArrayList<>();
        input.add(makeStudent("Matt",
                new String[]{"CPSC150", "CPSC150L"},
                new double[][]{{100, 90, 80, 70}, {100, 95, 90}}
                )
        );
        input.add(makeStudent("BillGates",
                new String[]{"CPSC150", "CPSC150L", "CPSC125"},
                new double[][]{{60, 50, 60, 70}, {40, 55, 30}, {0, 5, 10}}
                )
        );
        input.add(makeStudent("SteveJobs",
                new String[]{"CPSC150", "CPSC150L", "CPSC125"},
                new double[][]{{0, 0, 0, 0}, {0, 0, 0}, {0, 0, 0}}
                )
        );

        double delta = 0.001;
        double expected = 41.8518;
        double actual = P5.calcAverage(input);

        assertEquals("calcAverage returns an incorrect average for a list of length 1",
                expected, actual, delta);
    }

    @Test
    public void testPrintStudentReport1() {
        ArrayList<Student> input = new ArrayList<>();

        String scrInput = "Matt";

        P5.printStudentReport(new Scanner(scrInput), input);

        String expected = "Enter the name of the student that you would like to find a report for --> Name not found." + lineEnd;

        String err = String.format(
                "printStudentReport did not print the correct output for the input Scanner: \"%s\", ArrayList: %s.",
                scrInput, input.toString()
        );

        assertEquals(err, expected, out.toString());
    }

    @Test
    public void testPrintStudentReport2() {
        ArrayList<Student> input = new ArrayList<>();
        input.add(makeStudent("Matt",
                new String[]{"CPSC150", "CPSC150L"},
                new double[][]{{100, 90, 80, 70}, {100, 95, 90}}
                )
        );

        String scrInput = "Matt";

        P5.printStudentReport(new Scanner(scrInput), input);

        String expected = "Enter the name of the student that you would like to find a report for --> " +
                "Student Report: Matt Average: 90.00 Courses: CPSC150 CPSC150L" + lineEnd;

        String err = String.format(
                "printStudentReport did not print the correct output for the input Scanner: \"%s\", ArrayList: %s.",
                scrInput, input.toString()
        );

        assertEquals(err, expected, out.toString());
    }

    @Test
    public void testPrintStudentReport3() {
        ArrayList<Student> input = new ArrayList<>();
        input.add(makeStudent("Matt",
                new String[]{"CPSC150", "CPSC150L"},
                new double[][]{{100, 90, 80, 70}, {100, 95, 90}}
                )
        );

        String scrInput = "BillGates";

        P5.printStudentReport(new Scanner(scrInput), input);

        String expected = "Enter the name of the student that you would like to find a report for --> " +
                "Name not found." + lineEnd;

        String err = String.format(
                "printStudentReport did not print the correct output for the input Scanner: \"%s\", ArrayList: %s.",
                scrInput, input.toString()
        );

        assertEquals(err, expected, out.toString());
    }

    @Test
    public void testPrintStudentReport4() {
        ArrayList<Student> input = new ArrayList<>();
        input.add(makeStudent("Matt",
                new String[]{"CPSC150", "CPSC150L"},
                new double[][]{{100, 90, 80, 70}, {100, 95, 90}}
                )
        );
        input.add(makeStudent("BillGates",
                new String[]{"CPSC150", "CPSC150L", "CPSC125"},
                new double[][]{{60, 50, 60, 70}, {40, 55, 30}, {0, 5, 10}}
                )
        );
        input.add(makeStudent("SteveJobs",
                new String[]{"CPSC150", "CPSC150L", "CPSC125"},
                new double[][]{{0, 0, 0, 0}, {0, 0, 0}, {0, 0, 0}}
                )
        );

        String scrInput = "BillGates";

        P5.printStudentReport(new Scanner(scrInput), input);

        String expected = "Enter the name of the student that you would like to find a report for --> " +
                "Student Report: BillGates Average: 35.56 Courses: CPSC150 CPSC150L CPSC125" + lineEnd;

        String err = String.format(
                "printStudentReport did not print the correct output for the input Scanner: \"%s\", ArrayList: %s.",
                scrInput, input.toString()
        );

        assertEquals(err, expected, out.toString());
    }

    @Test
    public void testPrintStudentReport5() {
        ArrayList<Student> input = new ArrayList<>();
        input.add(makeStudent("Matt",
                new String[]{"CPSC150", "CPSC150L"},
                new double[][]{{100, 90, 80, 70}, {100, 95, 90}}
                )
        );
        input.add(makeStudent("BillGates",
                new String[]{"CPSC150", "CPSC150L", "CPSC125"},
                new double[][]{{60, 50, 60, 70}, {40, 55, 30}, {0, 5, 10}}
                )
        );
        input.add(makeStudent("SteveJobs",
                new String[]{"CPSC150", "CPSC150L", "CPSC125"},
                new double[][]{{0, 0, 0, 0}, {0, 0, 0}, {0, 0, 0}}
                )
        );

        String scrInput = "SteveJobs";

        P5.printStudentReport(new Scanner(scrInput), input);

        String expected = "Enter the name of the student that you would like to find a report for --> " +
                "Student Report: SteveJobs Average: 0.00 Courses: CPSC150 CPSC150L CPSC125" + lineEnd;

        String err = String.format(
                "printStudentReport did not print the correct output for the input Scanner: \"%s\", ArrayList: %s.",
                scrInput, input.toString()
        );

        assertEquals(err, expected, out.toString());
    }

    @Test
    public void testPrintCourseReport1() {
        ArrayList<Student> input = new ArrayList<>();

        String scrInput = "CPSC150";

        P5.printCourseReport(new Scanner(scrInput), input);

        String expected = "Enter the course that you would like to print a report for --> " +
                "Course not found." + lineEnd;

        String err = String.format(
                "printCourseReport did not print the correct output for the input Scanner: \"%s\", ArrayList: %s.",
                scrInput, input.toString()
        );

        assertEquals(err, expected, out.toString());
    }

    @Test
    public void testPrintCourseReport2() {
        ArrayList<Student> input = new ArrayList<>();
        input.add(makeStudent("Matt",
                new String[]{"CPSC150", "CPSC150L"},
                new double[][]{{100, 90, 80, 70}, {100, 95, 90}}
                )
        );

        String scrInput = "CPSC150";

        P5.printCourseReport(new Scanner(scrInput), input);

        String expected = "Enter the course that you would like to print a report for --> " +
                "Average for CPSC150 across all students is 85.00" + lineEnd;

        String err = String.format(
                "printCourseReport did not print the correct output for the input Scanner: \"%s\", ArrayList: %s.",
                scrInput, input.toString()
        );

        assertEquals(err, expected, out.toString());
    }

    @Test
    public void testPrintCourseReport3() {
        ArrayList<Student> input = new ArrayList<>();
        input.add(makeStudent("Matt",
                new String[]{"CPSC150", "CPSC150L"},
                new double[][]{{100, 90, 80, 70}, {100, 95, 90}}
                )
        );

        String scrInput = "ENGR213";

        P5.printCourseReport(new Scanner(scrInput), input);

        String expected = "Enter the course that you would like to print a report for --> " +
                "Course not found." + lineEnd;

        String err = String.format(
                "printCourseReport did not print the correct output for the input Scanner: \"%s\", ArrayList: %s.",
                scrInput, input.toString()
        );

        assertEquals(err, expected, out.toString());
    }

    @Test
    public void testPrintCourseReport4() {
        ArrayList<Student> input = new ArrayList<>();
        input.add(makeStudent("Matt",
                new String[]{"CPSC150", "CPSC150L"},
                new double[][]{{100, 90, 80, 70}, {100, 95, 90}}
                )
        );
        input.add(makeStudent("BillGates",
                new String[]{"CPSC150", "CPSC150L", "CPSC125"},
                new double[][]{{60, 50, 60, 70}, {40, 55, 30}, {0, 5, 10}}
                )
        );
        input.add(makeStudent("SteveJobs",
                new String[]{"CPSC150", "CPSC150L", "CPSC125"},
                new double[][]{{0, 0, 0, 0}, {0, 0, 0}, {0, 0, 0}}
                )
        );

        String scrInput = "CPSC150";

        P5.printCourseReport(new Scanner(scrInput), input);

        String expected = "Enter the course that you would like to print a report for --> " +
                "Average for CPSC150 across all students is 48.33" + lineEnd;

        String err = String.format(
                "printCourseReport did not print the correct output for the input Scanner: \"%s\", ArrayList: %s.",
                scrInput, input.toString()
        );

        assertEquals(err, expected, out.toString());
    }

    @Test
    public void testPrintCourseReport5() {
        ArrayList<Student> input = new ArrayList<>();
        input.add(makeStudent("Matt",
                new String[]{"CPSC150", "CPSC150L"},
                new double[][]{{100, 90, 80, 70}, {100, 95, 90}}
                )
        );
        input.add(makeStudent("BillGates",
                new String[]{"CPSC150", "CPSC150L", "CPSC125"},
                new double[][]{{60, 50, 60, 70}, {40, 55, 30}, {0, 5, 10}}
                )
        );
        input.add(makeStudent("SteveJobs",
                new String[]{"CPSC150", "CPSC150L", "CPSC125"},
                new double[][]{{0, 0, 0, 0}, {0, 0, 0}, {0, 0, 0}}
                )
        );

        String scrInput = "ENGR213";

        P5.printCourseReport(new Scanner(scrInput), input);

        String expected = "Enter the course that you would like to print a report for --> " +
                "Course not found." + lineEnd;

        String err = String.format(
                "printCourseReport did not print the correct output for the input Scanner: \"%s\", ArrayList: %s.",
                scrInput, input.toString()
        );

        assertEquals(err, expected, out.toString());
    }

    @Test
    public void testPrintCourseReport6() {
        ArrayList<Student> input = new ArrayList<>();
        input.add(makeStudent("Matt",
                new String[]{"CPSC150", "CPSC150L", "ENGR213"},
                new double[][]{{100, 90, 80, 70}, {100, 95, 90}, {100,100,100,100,100}}
                )
        );
        input.add(makeStudent("BillGates",
                new String[]{"CPSC150", "CPSC150L", "CPSC125"},
                new double[][]{{60, 50, 60, 70}, {40, 55, 30}, {0, 5, 10}}
                )
        );
        input.add(makeStudent("SteveJobs",
                new String[]{"CPSC150", "CPSC150L", "CPSC125"},
                new double[][]{{0, 0, 0, 0}, {0, 0, 0}, {0, 0, 0}}
                )
        );

        String scrInput = "ENGR213";

        P5.printCourseReport(new Scanner(scrInput), input);

        String expected = "Enter the course that you would like to print a report for --> " +
                "Average for ENGR213 across all students is 100.00" + lineEnd;

        String err = String.format(
                "printCourseReport did not print the correct output for the input Scanner: \"%s\", ArrayList: %s.",
                scrInput, input.toString()
        );

        assertEquals(err, expected, out.toString());
    }

    @Test
    public void testPrintAllStudentReport1() {
        ArrayList<Student> input = new ArrayList<>();

        P5.printAllStudentReport(input);

        String expected = "There are no students yet." + lineEnd;

        String err = String.format(
                "printAllStudentReport did not print the correct output for the input ArrayList: %s.",
                input.toString()
        );

        assertEquals(err, expected, out.toString());
    }

    @Test
    public void testPrintAllStudentReport2() {
        ArrayList<Student> input = new ArrayList<>();
        input.add(makeStudent("Matt",
                new String[]{"CPSC150", "CPSC150L"},
                new double[][]{{100, 90, 80, 70}, {100, 95, 90}}
                )
        );

        P5.printAllStudentReport(input);

        String expected = "Matt: [CPSC150 [100.0, 90.0, 80.0, 70.0], CPSC150L [100.0, 95.0, 90.0]]" + lineEnd;

        String err = String.format(
                "printAllStudentReport did not print the correct output for the input ArrayList: %s.",
                input.toString()
        );

        assertEquals(err, expected, out.toString());
    }

    @Test
    public void testPrintAllStudentReport3() {
        ArrayList<Student> input = new ArrayList<>();
        input.add(makeStudent("Matt",
                new String[]{"CPSC150", "CPSC150L"},
                new double[][]{{100, 90, 80, 70}, {100, 95, 90}}
                )
        );
        input.add(makeStudent("BillGates",
                new String[]{"CPSC150", "CPSC150L", "CPSC125"},
                new double[][]{{60, 50, 60, 70}, {40, 55, 30}, {0, 5, 10}}
                )
        );
        input.add(makeStudent("SteveJobs",
                new String[]{"CPSC150", "CPSC150L", "CPSC125"},
                new double[][]{{0, 0, 0, 0}, {0, 0, 0}, {0, 0, 0}}
                )
        );

        P5.printAllStudentReport(input);

        String expected = "Matt: [CPSC150 [100.0, 90.0, 80.0, 70.0], CPSC150L [100.0, 95.0, 90.0]]" + lineEnd +
                "BillGates: [CPSC150 [60.0, 50.0, 60.0, 70.0], CPSC150L [40.0, 55.0, 30.0], CPSC125 [0.0, 5.0, 10.0]]" + lineEnd +
                "SteveJobs: [CPSC150 [0.0, 0.0, 0.0, 0.0], CPSC150L [0.0, 0.0, 0.0], CPSC125 [0.0, 0.0, 0.0]]" + lineEnd;

        String err = String.format(
                "printAllStudentReport did not print the correct output for the input ArrayList: %s.",
                input.toString()
        );

        assertEquals(err, expected, out.toString());
    }
}