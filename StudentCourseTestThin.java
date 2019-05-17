import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class StudentCourseTestThin {
    @Test
    public void testDefaultConstructor() {
        StudentCourse c = new StudentCourse();

        String expectedName = "";
        ArrayList<Double> expectedGrades = new ArrayList<>();

        assertEquals("StudentCourse() either does not set name to the empty string or getName is broken",
                expectedName, c.getName());

        ArrayList<Double> actualGrades = c.getGrades();

        assertNotNull("StudentCourse() either sets grades to null, or getGrades returns null",
                actualGrades);
        assertEquals("StudentCourse() must set the grades ArrayList to an empty ArrayList.",
                expectedGrades.size(), actualGrades.size());
    }

    @Test
    public void testConstructorString() {
        String name = "CPSC150";
        StudentCourse c = new StudentCourse(name);

        String expectedName = name;
        ArrayList<Double> expectedGrades = new ArrayList<>();

        assertEquals("StudentCourse(String) does not set name or getName is broken",
                expectedName, c.getName());

        ArrayList<Double> actualGrades = c.getGrades();

        assertNotNull("StudentCourse(String) either sets grades to null, or getGrades returns null",
                actualGrades);
        assertEquals("StudentCourse(String) must set the grades ArrayList to an empty ArrayList.",
                expectedGrades.size(), actualGrades.size());
    }

    @Test
    public void testConstructorStringList() {
        String name = "CPSC150";
        double[] inputs = {1.0, 2.0, 3.0};
        ArrayList<Double> grades = new ArrayList<>();
        for (double d : inputs)
            grades.add(d);
        StudentCourse c = new StudentCourse(name, grades);

        String expectedName = name;
        ArrayList<Double> expectedGrades = (ArrayList<Double>) grades.clone();

        assertEquals("StudentCourse(String, ArrayList) does not set name or getName is broken",
                expectedName, c.getName());

        ArrayList<Double> actualGrades = c.getGrades();

        assertNotNull("StudentCourse(String, ArrayList) either sets grades to null, or getGrades returns null",
                actualGrades);
        assertEquals("StudentCourse(String, ArrayList) must set the grades ArrayList to a deep copy of the input array list. (Size wrong)",
                expectedGrades.size(), actualGrades.size());
        assertEquals("StudentCourse(String, ArrayList) must set the grades ArrayList to a deep copy of the input array list. (Values wrong)",
                expectedGrades, actualGrades);
        assertTrue("StudentCourse(String, ArrayList) must set the grades ArrayList to a deep copy of the input array list. (Not a deep copy)",
                grades != actualGrades);
        grades.set(0, 3.0);
        assertEquals("StudentCourse(String, ArrayList) must set the grades ArrayList to a deep copy of the input array list. (Not a deep copy)",
                expectedGrades, c.getGrades());
    }

    @Test
    public void testConstructorStringListNull() {
        String name = "CPSC150";
        StudentCourse c = new StudentCourse(name, null);

        String expectedName = name;
        ArrayList<Double> expectedGrades = new ArrayList<>();

        assertEquals("StudentCourse(String, null) does not set name or getName is broken",
                expectedName, c.getName());

        ArrayList<Double> actualGrades = c.getGrades();

        assertNotNull("StudentCourse(String, null) either sets grades to null, or getGrades returns null",
                actualGrades);
        assertEquals("StudentCourse(String, null) must set the grades ArrayList to a new ArrayList",
                expectedGrades.size(), actualGrades.size());
    }

    @Test
    public void testGetSetName() {
        StudentCourse c = new StudentCourse();

        String name = "CPSC150";
        c.setName(name);
        assertEquals("setName does not set the name or getName does not get the name.", name, c.getName());
    }

    @Test
    public void testGetGrades() {
        String name = "CPSC150";
        double[] inputs = {1.0, 2.0, 3.0};
        ArrayList<Double> grades = new ArrayList<>();
        for (double d : inputs)
            grades.add(d);
        StudentCourse c = new StudentCourse(name, grades);

        ArrayList<Double> actual = c.getGrades();
        assertEquals("getGrades does not return the correct grades list.", grades, actual);
        actual.set(0, 3.0);
        assertEquals("getGrades does not return a deep copy of the grades list.", grades, c.getGrades());
    }

    @Test
    public void testAddGrades1() {
        String name = "CPSC150";
        double[] inputs = {1.0, 2.0, 3.0};
        ArrayList<Double> grades = new ArrayList<>();
        for (double d : inputs)
            grades.add(d);
        StudentCourse c = new StudentCourse(name, grades);
        c.addGrade(4.0);

        ArrayList<Double> expected = (ArrayList<Double>) grades.clone();
        expected.add(4.0);

        ArrayList<Double> actual = c.getGrades();

        assertEquals("addGrade did not append a grade to the list of grades. (grades started nonempty)",
                expected, actual);
    }

    @Test
    public void testAddGrades2() {
        String name = "CPSC150";
        StudentCourse c = new StudentCourse(name);
        c.addGrade(4.0);

        ArrayList<Double> expected = new ArrayList<>();
        expected.add(4.0);

        ArrayList<Double> actual = c.getGrades();

        assertEquals("addGrade did not append a grade to the list of grades. (grades started empty)",
                expected, actual);
    }

    @Test
    public void testCalcAverage1() {
        String name = "CPSC150";
        double[] inputs = {1.0, 2.0, 3.0, 4.0, 5.0};
        ArrayList<Double> grades = new ArrayList<>();
        for (double d : inputs)
            grades.add(d);
        StudentCourse c = new StudentCourse(name, grades);

        double expected = 3.0;
        double actual = c.calcAverage();
        double delta = 0.0001;

        assertEquals("calcAverage failed to compute the average with a nonempty list of grades",
                expected, actual, delta);
    }

    @Test
    public void testCalcAverage2() {
        String name = "CPSC150";
        StudentCourse c = new StudentCourse(name);

        double expected = 0.0;
        double actual = c.calcAverage();
        double delta = 0.0001;

        assertEquals("calcAverage failed to compute the average with an empty list of grades",
                expected, actual, delta);
    }

    @Test
    public void testCalcAverage3() {
        String name = "CPSC150";
        double[] inputs = {100.0};
        ArrayList<Double> grades = new ArrayList<>();
        for (double d : inputs)
            grades.add(d);
        StudentCourse c = new StudentCourse(name, grades);

        double expected = 100.0;
        double actual = c.calcAverage();
        double delta = 0.0001;

        assertEquals("calcAverage failed to compute the average with a nonempty list of grades",
                expected, actual, delta);
    }

    @Test
    public void testToString1() {
        String name = "CPSC150";
        double[] inputs = {1.0, 2.0, 3.0, 4.0, 5.0};
        ArrayList<Double> grades = new ArrayList<>();
        for (double d : inputs)
            grades.add(d);
        StudentCourse c = new StudentCourse(name, grades);

        String expected = "CPSC150 [1.0, 2.0, 3.0, 4.0, 5.0]";
        String actual = c.toString();

        assertEquals(
                String.format(
                        "toString returned incorrect output of \"%s\" with name \"%s\" and grades %s",
                        actual, name, grades.toString()
                ),
                expected, actual
        );
    }

    @Test
    public void testToString2() {
        String name = "CPSC150";
        double[] inputs = {};
        ArrayList<Double> grades = new ArrayList<>();
        for (double d : inputs)
            grades.add(d);
        StudentCourse c = new StudentCourse(name, grades);

        String expected = "CPSC150 []";
        String actual = c.toString();

        assertEquals(
                String.format(
                        "toString returned incorrect output of \"%s\" with name \"%s\" and grades %s",
                        actual, name, grades.toString()
                ),
                expected, actual
        );
    }

    @Test
    public void testToString3() {
        String name = "CPSC150";
        double[] inputs = {100.0};
        ArrayList<Double> grades = new ArrayList<>();
        for (double d : inputs)
            grades.add(d);
        StudentCourse c = new StudentCourse(name, grades);

        String expected = "CPSC150 [100.0]";
        String actual = c.toString();

        assertEquals(
                String.format(
                        "toString returned incorrect output of \"%s\" with name \"%s\" and grades %s",
                        actual, name, grades.toString()
                ),
                expected, actual
        );
    }
}
