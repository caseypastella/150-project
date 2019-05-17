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

/**
 * Write a description of class P5Test here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class P5Test
{
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
}
