import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StudentTestThin {

    public static void addCourseToStudent(Student s, String cName, double[] in) {
        ArrayList<Double> grades = new ArrayList<>();

        for (double d : in)
            grades.add(d);
        s.addCourse(cName, grades);
    }

    @Test
    public void testDefaultConstructor() {
        Student s = new Student();

        String expectedName = "no name";
        ArrayList<StudentCourse> expectedCourses = new ArrayList<>();

        assertTrue("Student() does not set the name properly",
                expectedName.equals(s.getName()));
        assertEquals("Student() does not set the list of courses to a new list.",
                expectedCourses, s.getCourses());
    }

    @Test
    public void testConstructorString() {
        String name = "Matt";
        Student s = new Student(name);

        String expectedName = name;
        ArrayList<StudentCourse> expectedCourses = new ArrayList<>();

        assertTrue("Student(String) does not set the name properly",
                expectedName.equals(s.getName()));
        assertEquals("Student(String) does not set the list of courses to a new list.",
                expectedCourses, s.getCourses());
    }

    @Test
    public void testSetGetName() {
        Student s = new Student();
        String name = "Matt";
        s.setName(name);

        assertEquals("Either setName does not set the name, or getName does not get the name",
                name, s.getName());
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
        String cName = "CPSC150";
        expected.add(new StudentCourse(cName, gradesList));
        s.addCourse(cName, gradesList);
        actual = s.getCourses();

        assertEquals("Either addCourses does not add a course when the list is empty, or getCourses does not get the list",
                expected, actual);

        actual.add(new StudentCourse(cName, gradesList));
        actual = s.getCourses();
        assertEquals("getCourses does not return a deep copy of the list of courses (I can modify the list).",
                expected, actual);

        actual.get(0).setName("PHYS201");
        actual = s.getCourses();
        assertEquals("getCourses does not return a deep copy of the list of courses (I can modify elements of the list).",
                expected, actual);

        grades = new Double[]{80.1, 79.6, 94.2, 21.0};
        gradesList = new ArrayList<>();
        for (double grade : grades)
            gradesList.add(grade);
        cName = "CPSC150L";
        expected.add(new StudentCourse(cName, gradesList));
        s.addCourse(cName, gradesList);
        actual = s.getCourses();

        assertEquals("Either addCourses does not add a course when the list is not empty, or getCourses does not get the list",
                expected, actual);

        actual.add(new StudentCourse(cName, gradesList));
        actual = s.getCourses();
        assertEquals("getCourses does not return a deep copy of the list of courses (I can modify the list).",
                expected, actual);

        actual.get(0).setName("PHYS201");
        actual = s.getCourses();
        assertEquals("getCourses does not return a deep copy of the list of courses (I can modify elements of the list).",
                expected, actual);
    }

    @Test
    public void testGetCourseAverageNoCourses() {
        String name = "Matt";
        Student s = new Student(name);
        String findMe = "CPSC150";

        double actual = s.getCourseAverage(findMe);

        assertTrue("getCourseAverage(String) must return a negative number if it can't find the course.",
                actual < 0);
    }

    @Test
    public void testGetCourseAverage1() {
        String name = "Matt";
        Student s = new Student(name);
        double[] grades = {1, 2, 3, 4};
        String cName = "CPSC150";
        addCourseToStudent(s, cName, grades);
        String findMe = "CPSC150";

        double delta = 0.0001;
        double expected = 2.5;
        double actual = s.getCourseAverage(findMe);

        assertEquals("getCourseAverage(String) failed to return the average for a course in a list of length 1",
                expected, actual, delta);
    }

    @Test
    public void testGetCourseAverage2() {
        String name = "Matt";
        Student s = new Student(name);
        double[] grades = {1, 2, 3, 4};
        String cName = "CPSC150";
        addCourseToStudent(s, cName, grades);
        String findMe = "PHYS201";

        double actual = s.getCourseAverage(findMe);

        assertTrue("getCourseAverage(String) must return a negative number if it can't find the course.",
                actual < 0);
    }

    @Test
    public void testGetCourseAverage3() {
        String name = "Matt";
        Student s = new Student(name);
        double[] grades = {1, 2, 3, 4};
        String cName = "CPSC150";
        addCourseToStudent(s, cName, grades);
        cName = "ENGR213";
        grades = new double[]{30, 40, 80, 20, 10, 35};
        addCourseToStudent(s, cName, grades);
        cName = "CPEN214";
        grades = new double[]{80, 75, 90, 85};
        addCourseToStudent(s, cName, grades);
        String findMe = "ENGR213";

        double delta = 0.0001;
        double expected = 35.83333;
        double actual = s.getCourseAverage(findMe);

        assertEquals("getCourseAverage(String) failed to return the average for a course in a list of length 3",
                expected, actual, delta);
    }

    @Test
    public void testGetCourseAverage4() {
        String name = "Matt";
        Student s = new Student(name);
        double[] grades = {1, 2, 3, 4};
        String cName = "CPSC150";
        addCourseToStudent(s, cName, grades);
        cName = "ENGR213";
        grades = new double[]{30, 40, 80, 20, 10, 35};
        addCourseToStudent(s, cName, grades);
        cName = "CPEN214";
        grades = new double[]{80, 75, 90, 85};
        addCourseToStudent(s, cName, grades);
        String findMe = "CPEN214";

        double delta = 0.0001;
        double expected = 82.5;
        double actual = s.getCourseAverage(findMe);

        assertEquals("getCourseAverage(String) failed to return the average for a course in a list of length 3",
                expected, actual, delta);
    }

    @Test
    public void testGetCourseAverage5() {
        String name = "Matt";
        Student s = new Student(name);
        double[] grades = {1, 2, 3, 4};
        String cName = "CPSC150";
        addCourseToStudent(s, cName, grades);
        cName = "ENGR213";
        grades = new double[]{30, 40, 80, 20, 10, 35};
        addCourseToStudent(s, cName, grades);
        cName = "CPEN214";
        grades = new double[]{80, 75, 90, 85};
        addCourseToStudent(s, cName, grades);
        String findMe = "PHYS201";

        double actual = s.getCourseAverage(findMe);

        assertTrue("getCourseAverage(String) must return a negative number if it can't find the course.",
                actual < 0);
    }

    @Test
    public void testCalcAverageNoCourses() {
        String name = "Matt";
        Student s = new Student(name);

        double delta = 0.0001;
        double actual = s.calcAverage();
        double expected = 0.0;

        assertEquals("calcAverage() does not compute the average correctly when there are no courses.",
                expected, actual, delta);
    }

    @Test
    public void testCalcAverage1() {
        String name = "Matt";
        Student s = new Student(name);
        double[] grades = {1, 2, 3, 4};
        String cName = "CPSC150";
        addCourseToStudent(s, cName, grades);

        double delta = 0.0001;
        double expected = 2.5;
        double actual = s.calcAverage();

        assertEquals("calcAverage() failed to return the average for a list of length 1",
                expected, actual, delta);
    }

    @Test
    public void testCalcAverage2() {
        String name = "Matt";
        Student s = new Student(name);
        double[] grades = {1, 2, 3, 4};
        String cName = "CPSC150";
        addCourseToStudent(s, cName, grades);
        cName = "ENGR213";
        grades = new double[]{30, 40, 80, 20, 10, 35};
        addCourseToStudent(s, cName, grades);
        cName = "CPEN214";
        grades = new double[]{80, 75, 90, 85};
        addCourseToStudent(s, cName, grades);

        double delta = 0.0001;
        double expected = 40.2777;
        double actual = s.calcAverage();

        assertEquals("calcAverage() failed to return the average for a list of length greater than 1",
                expected, actual, delta);
    }

    @Test
    public void testGetSummaryNoCourses() {
        String name = "Matt";
        Student s = new Student(name);

        String expected = "Matt Average: 0.00 Courses:";
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
        String name = "Matt";
        Student s = new Student(name);
        double[] grades = {1, 2, 3, 4};
        String cName = "CPSC150";
        addCourseToStudent(s, cName, grades);

        String expected = "Matt Average: 2.50 Courses: CPSC150";
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
        String name = "Matt";
        Student s = new Student(name);
        double[] grades = {1, 2, 3, 4};
        String[] cNames = {"CPSC150", "ENGR213", "CPEN214"};
        addCourseToStudent(s, cNames[0], grades);
        grades = new double[]{30, 40, 80, 20, 10, 35};
        addCourseToStudent(s, cNames[1], grades);
        grades = new double[]{80, 75, 90, 85};
        addCourseToStudent(s, cNames[2], grades);

        String expected = "Matt Average: 40.28 Courses: CPSC150 ENGR213 CPEN214";
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
        String name = "Matt";
        Student s = new Student(name);

        String expected = "Matt: []";
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
        String name = "Matt";
        Student s = new Student(name);
        double[] grades = {1, 2, 3, 4};
        String cName = "CPSC150";
        addCourseToStudent(s, cName, grades);

        String expected = "Matt: [CPSC150 [1.0, 2.0, 3.0, 4.0]]";
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
        String name = "Matt";
        Student s = new Student(name);
        double[] grades = {1, 2, 3, 4};
        String[] cNames = {"CPSC150", "ENGR213", "CPEN214"};
        addCourseToStudent(s, cNames[0], grades);
        grades = new double[]{30, 40, 80, 20, 10, 35};
        addCourseToStudent(s, cNames[1], grades);
        grades = new double[]{80, 75, 90, 85};
        addCourseToStudent(s, cNames[2], grades);

        String expected = "Matt: [CPSC150 [1.0, 2.0, 3.0, 4.0], ENGR213 [30.0, 40.0, 80.0, 20.0, 10.0, 35.0], CPEN214 [80.0, 75.0, 90.0, 85.0]]";
        String actual = s.toString();

        assertEquals(
                String.format("toString() returned incorrect value of \"%s\" with name \"%s\" and courses %s.",
                        actual, name, Arrays.asList(cNames).toString()
                ),
                expected, actual
        );
    }
}