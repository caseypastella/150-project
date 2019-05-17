import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Write a description of class StudentCourseTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StudentCourseTest
{
   @Test
    public void testGetGrades() {
        String name = "CPSC250";
        double[] inputs = {1.5, 2.5, 3.5};
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
        String name = "CPSC250";
        double[] inputs = {1.5, 2.5, 3.5};
        ArrayList<Double> grades = new ArrayList<>();
        for (double d : inputs)
            grades.add(d);
        StudentCourse c = new StudentCourse(name, grades);
        c.addGrade(10.0);

        ArrayList<Double> expected = (ArrayList<Double>) grades.clone();
        expected.add(10.0);

        ArrayList<Double> actual = c.getGrades();

        assertEquals("addGrade did not append a grade to the list of grades. (grades started nonempty)",
                expected, actual);
    }
    
     @Test
    public void testAddGrades2() {
        String name = "CPSC150";
        StudentCourse c = new StudentCourse(name);
        c.addGrade(10.0);

        ArrayList<Double> expected = new ArrayList<>();
        expected.add(10.0);

        ArrayList<Double> actual = c.getGrades();

        assertEquals("addGrade did not append a grade to the list of grades. (grades started empty)",
                expected, actual);
    }
    
    @Test
    public void testCalcAverage1() {
        String name = "CPSC250";
        double[] inputs = {1.5, 2.5, 3.5, 4.5, 5.5};
        ArrayList<Double> grades = new ArrayList<>();
        for (double d : inputs)
            grades.add(d);
        StudentCourse c = new StudentCourse(name, grades);

        double expected = 3.5;
        double actual = c.calcAverage();
        double delta = 0.0001;

        assertEquals("calcAverage failed to compute the average with a nonempty list of grades",
                expected, actual, delta);
    }
    
    @Test
    public void testCalcAverage2() {
        String name = "CPSC250";
        StudentCourse c = new StudentCourse(name);

        double expected = 0.0;
        double actual = c.calcAverage();
        double delta = 0.0001;

        assertEquals("calcAverage failed to compute the average with an empty list of grades",
                expected, actual, delta);
    }
    
    @Test
    public void testCalcAverage3() {
        String name = "CPSC250";
        double[] inputs = {90.0};
        ArrayList<Double> grades = new ArrayList<>();
        for (double d : inputs)
            grades.add(d);
        StudentCourse c = new StudentCourse(name, grades);

        double expected = 90.0;
        double actual = c.calcAverage();
        double delta = 0.0001;

        assertEquals("calcAverage failed to compute the average with a nonempty list of grades",
                expected, actual, delta);
    }
    
    @Test
    public void testToString1() {
        String name = "CPSC250";
        double[] inputs = {1.5, 2.5, 3.5, 4.5, 5.5};
        ArrayList<Double> grades = new ArrayList<>();
        for (double d : inputs)
            grades.add(d);
        StudentCourse c = new StudentCourse(name, grades);

        String expected = "CPSC250 [1.5, 2.5, 3.5, 4.5, 5.5]";
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
        String name = "CPSC250";
        double[] inputs = {};
        ArrayList<Double> grades = new ArrayList<>();
        for (double d : inputs)
            grades.add(d);
        StudentCourse c = new StudentCourse(name, grades);

        String expected = "CPSC250 []";
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
        String name = "CPSC250";
        double[] inputs = {90.0};
        ArrayList<Double> grades = new ArrayList<>();
        for (double d : inputs)
            grades.add(d);
        StudentCourse c = new StudentCourse(name, grades);

        String expected = "CPSC250 [90.0]";
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
