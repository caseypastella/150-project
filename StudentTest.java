import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Write a description of class StudentTest here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class StudentTest
{
    
    public static void addCourseToStudent(Student s, String cName, double[] in) {
        ArrayList<Double> grades = new ArrayList<>();

        for (double d : in)
            grades.add(d);
        s.addCourse(cName, grades);
    }
    
    
    @Test
    public void testAddGetCourses() {
        String name = "Matt";
        Student s = new Student(name);

        ArrayList<StudentCourse> expected = new ArrayList<>();
        ArrayList<StudentCourse> actual = s.getCourses();

        assertEquals("getCourses does not get the list of courses when it is empty",
                expected, actual);

        Double[] grades = {100.0, 98.0, 88.2};
        ArrayList<Double> gradesList = new ArrayList<>();
        for (double grade : grades)
            gradesList.add(grade);
        String cName = "ENGR213";
        expected.add(new StudentCourse(cName, gradesList));
        s.addCourse(cName, gradesList);
        actual = s.getCourses();

        assertEquals("Either addCourses does not add a course when the list is empty, or getCourses does not get the list",
                expected, actual);

        actual.add(new StudentCourse(cName, gradesList));
        actual = s.getCourses();
        assertEquals("getCourses does not return a deep copy of the list of courses (I can modify the list).",
                expected, actual);

        actual.get(0).setName("CPEN150");
        actual = s.getCourses();
        assertEquals("getCourses does not return a deep copy of the list of courses (I can modify elements of the list).",
                expected, actual);

        grades = new Double[]{99.0, 100.0, 93.0, 75.0};
        gradesList = new ArrayList<>();
        for (double grade : grades)
            gradesList.add(grade);
        cName = "CPSC250L";
        expected.add(new StudentCourse(cName, gradesList));
        s.addCourse(cName, gradesList);
        actual = s.getCourses();

        assertEquals("Either addCourses does not add a course when the list is not empty, or getCourses does not get the list",
                expected, actual);

        actual.add(new StudentCourse(cName, gradesList));
        actual = s.getCourses();
        assertEquals("getCourses does not return a deep copy of the list of courses (I can modify the list).",
                expected, actual);

        actual.get(0).setName("ENGL150");
        actual = s.getCourses();
        assertEquals("getCourses does not return a deep copy of the list of courses (I can modify elements of the list).",
                expected, actual);
    }
    
    @Test
    public void testAddGetCourses2() {
        String name = "Casey";
        Student s = new Student(name);

        ArrayList<StudentCourse> expected = new ArrayList<>();
        ArrayList<StudentCourse> actual = s.getCourses();

        assertEquals("getCourses does not get the list of courses when it is empty",
                expected, actual);

        Double[] grades = {90.0, 95.0, 70.2};
        ArrayList<Double> gradesList = new ArrayList<>();
        for (double grade : grades)
            gradesList.add(grade);
        String cName = "CPSC250";
        expected.add(new StudentCourse(cName, gradesList));
        s.addCourse(cName, gradesList);
        actual = s.getCourses();

        assertEquals("Either addCourses does not add a course when the list is empty, or getCourses does not get the list",
                expected, actual);

        actual.add(new StudentCourse(cName, gradesList));
        actual = s.getCourses();
        assertEquals("getCourses does not return a deep copy of the list of courses (I can modify the list).",
                expected, actual);

        actual.get(0).setName("CPSC215");
        actual = s.getCourses();
        assertEquals("getCourses does not return a deep copy of the list of courses (I can modify elements of the list).",
                expected, actual);

        grades = new Double[]{90.0, 85.0, 92.0, 50.0};
        gradesList = new ArrayList<>();
        for (double grade : grades)
            gradesList.add(grade);
        cName = "CPSC270";
        expected.add(new StudentCourse(cName, gradesList));
        s.addCourse(cName, gradesList);
        actual = s.getCourses();

        assertEquals("Either addCourses does not add a course when the list is not empty, or getCourses does not get the list",
                expected, actual);

        actual.add(new StudentCourse(cName, gradesList));
        actual = s.getCourses();
        assertEquals("getCourses does not return a deep copy of the list of courses (I can modify the list).",
                expected, actual);

        actual.get(0).setName("CPSC270");
        actual = s.getCourses();
        assertEquals("getCourses does not return a deep copy of the list of courses (I can modify elements of the list).",
                expected, actual);
    }
    
    @Test
    public void testGetCourseAverageNoCourses() {
        String name = "Casey";
        Student s = new Student(name);
        String findMe = "CPSC250";

        double actual = s.getCourseAverage(findMe);

        assertTrue("getCourseAverage(String) must return a negative number if it can't find the course.",
                actual < 0);
    }
    
    @Test
    public void testGetCourseAverage1() {
        String name = "Casey";
        Student s = new Student(name);
        double[] grades = {5, 4, 3, 2};
        String cName = "CPSC250";
        addCourseToStudent(s, cName, grades);
        String findMe = "CPSC250";

        double delta = 0.0001;
        double expected = 3.5;
        double actual = s.getCourseAverage(findMe);

        assertEquals("getCourseAverage(String) failed to return the average for a course in a list of length 1",
                expected, actual, delta);
    }
    
    @Test
    public void testGetCourseAverage2() {
        String name = "Matt";
        Student s = new Student(name);
        double[] grades = {5, 4, 3, 2};
        String cName = "CPSC250";
        addCourseToStudent(s, cName, grades);
        String findMe = "CPEN213";

        double actual = s.getCourseAverage(findMe);

        assertTrue("getCourseAverage(String) must return a negative number if it can't find the course.",
                actual < 0);
    }

    @Test
    public void testGetCourseAverage3() {
        String name = "Casey";
        Student s = new Student(name);
        double[] grades = {5, 4, 3, 2};
        String cName = "CPEN213";
        addCourseToStudent(s, cName, grades);
        cName = "ENGL250";
        grades = new double[]{50, 40, 60, 25, 10, 22};
        addCourseToStudent(s, cName, grades);
        cName = "CPEN150";
        grades = new double[]{99, 75, 100, 80};
        addCourseToStudent(s, cName, grades);
        String findMe = "ENGL250";

        double delta = 0.0001;
        double expected = 34.5;
        double actual = s.getCourseAverage(findMe);

        assertEquals("getCourseAverage(String) failed to return the average for a course in a list of length 3",
                expected, actual, delta);
    }
    
    @Test
    public void testCalcAverageNoCourses() {
        String name = "Casey";
        Student s = new Student(name);

        double delta = 0.0001;
        double actual = s.calcAverage();
        double expected = 0.0;

        assertEquals("calcAverage() does not compute the average correctly when there are no courses.",
                expected, actual, delta);
    }
    
    @Test
    public void testCalcAverage1() {
        String name = "Casey";
        Student s = new Student(name);
        double[] grades = {5, 4, 3, 2};
        String cName = "CPSC250";
        addCourseToStudent(s, cName, grades);

        double delta = 0.0001;
        double expected = 3.5;
        double actual = s.calcAverage();

        assertEquals("calcAverage() failed to return the average for a list of length 1",
                expected, actual, delta);
    }
    
     @Test
    public void testCalcAverage2() {
        String name = "Casey";
        Student s = new Student(name);
        double[] grades = {5, 4, 3, 2};
        String cName = "CPSC250";
        addCourseToStudent(s, cName, grades);
        cName = "ENGL250";
        grades = new double[]{30, 40, 80, 20, 10, 35};
        addCourseToStudent(s, cName, grades);
        cName = "ENGR150";
        grades = new double[]{80, 75, 90, 85};
        addCourseToStudent(s, cName, grades);

        double delta = 0.0001;
        double expected = 40.61111;
        double actual = s.calcAverage();

        assertEquals("calcAverage() failed to return the average for a list of length greater than 1",
                expected, actual, delta);
    }
    
    @Test
    public void testGetSummaryNoCourses() {
        String name = "Casey";
        Student s = new Student(name);

        String expected = "Casey Average: 0.00 Courses:";
        String actual = s.getSummary();

        assertEquals(
                String.format("getSummary() returned incorrect value of \"%s\" with name \"%s\" and no courses.",
                        actual, name
                ),
                expected, actual
        );
    }
    
     @Test
    public void testGetSummary1() {
        String name = "Casey";
        Student s = new Student(name);
        double[] grades = {5, 4, 3, 2};
        String cName = "CPSC250";
        addCourseToStudent(s, cName, grades);

        String expected = "Casey Average: 3.50 Courses: CPSC250";
        String actual = s.getSummary();

        assertEquals(
                String.format("getSummary() returned incorrect value of \"%s\" with name \"%s\" and a single course \"%s\".",
                        actual, name, cName
                ),
                expected, actual
        );
    }

    @Test
    public void testGetSummary2() {
        String name = "Casey";
        Student s = new Student(name);
        double[] grades = {1, 2, 3, 4};
        String[] cNames = {"CPSC250", "ENGL250", "CPEN999"};
        addCourseToStudent(s, cNames[0], grades);
        grades = new double[]{30, 40, 80, 20, 10, 35};
        addCourseToStudent(s, cNames[1], grades);
        grades = new double[]{80, 75, 90, 85};
        addCourseToStudent(s, cNames[2], grades);

        String expected = "Casey Average: 40.28 Courses: CPSC250 ENGL250 CPEN999";
        String actual = s.getSummary();

        assertEquals(
                String.format("getSummary() returned incorrect value of \"%s\" with name \"%s\" and courses %s.",
                        actual, name, Arrays.asList(cNames).toString()
                ),
                expected, actual
        );
    }

    @Test
    public void testToStringNoCourses() {
        String name = "Casey";
        Student s = new Student(name);

        String expected = "Casey: []";
        String actual = s.toString();

        assertEquals(
                String.format("toString() returned incorrect value of \"%s\" with name \"%s\" and no courses.",
                        actual, name
                ),
                expected, actual
        );
    }

    @Test
    public void testToString1() {
        String name = "Casey";
        Student s = new Student(name);
        double[] grades = {5, 4, 3, 2};
        String cName = "CPSC250";
        addCourseToStudent(s, cName, grades);

        String expected = "Casey: [CPSC250 [5.0, 4.0, 3.0, 2.0]]";
        String actual = s.toString();

        assertEquals(
                String.format("toString() returned incorrect value of \"%s\" with name \"%s\" and course \"%s\".",
                        actual, name, cName
                ),
                expected, actual
        );
    }

    @Test
    public void testToString2() {
        String name = "Casey";
        Student s = new Student(name);
        double[] grades = {1, 2, 3, 4};
        String[] cNames = {"CPSC250", "ENGL250", "CPEN999"};
        addCourseToStudent(s, cNames[0], grades);
        grades = new double[]{30, 40, 80, 20, 10, 35};
        addCourseToStudent(s, cNames[1], grades);
        grades = new double[]{80, 75, 90, 85};
        addCourseToStudent(s, cNames[2], grades);

        String expected = "Casey: [CPSC250 [1.0, 2.0, 3.0, 4.0], ENGL250 [30.0, 40.0, 80.0, 20.0, 10.0, 35.0], CPEN999 [80.0, 75.0, 90.0, 85.0]]";
        String actual = s.toString();

        assertEquals(
                String.format("toString() returned incorrect value of \"%s\" with name \"%s\" and courses %s.",
                        actual, name, Arrays.asList(cNames).toString()
                ),
                expected, actual
        );
    }
}
