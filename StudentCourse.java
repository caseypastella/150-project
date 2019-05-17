import java.util.ArrayList;

/**
 * Authors: Casey Pastella and Tyler Serpa
 */

public class StudentCourse {

    private String courseName;
    private ArrayList<Double> courseGrades;
        
    /**
     * Creates an empty StudentCourse with an empty name and grades ArrayList.
     */
    public StudentCourse() {

        courseName = "";
        ArrayList<Double> courseGrades = new ArrayList<>();
    }
    
    /**
     * Creates a StudentCourse with a given name and empty grades.
     * @param Name The name for StudentCourses
     */
    public StudentCourse(String name) {

        courseName = name;
        courseGrades = null;

    }
    
    /** 
     * Creates a StudentCourse with a given name and given list of grades. 
     * If the given list of grades is not null, it is deep copied from the input. 
     * If the given list of grades is null, then the resulting StudentCourse should 
     * have an empty ArrayList for its list of grades.
     * @param name - The name for StudentCourses, grades = The list of Grades for StudentCourse
     */
    public StudentCourse(String name, ArrayList<Double> grades) {

        courseName = name;
        if (grades != null) {
             courseGrades = (ArrayList<Double>) grades.clone();
            }
        else {
            courseGrades = new ArrayList<>();
        }
    }
    
    /**
     * Gets a deep copy of the list of grades
     * @return a deep copy of grades
     */
    public ArrayList<Double> getGrades() {
        ArrayList <Double> copy = new ArrayList<>();
        


        if (courseGrades != null) {
            for (double a : courseGrades)
                copy.add(a);
        }

        return copy;

    }
    
    /** 
     * Gets the name of this StudentCourse
     */
    public String getName() {
        return this.courseName;
    }

    /**
     * Sets the name for this StudentCourse
     * @param in The name to set
     */
    public void setName(String in) {
        this.courseName = in;
    }
    
    /**
     * Adds a grade to the list of grades
     * @param grade the grade to add
     */
    public void addGrade(double grade) {
        ArrayList<Double> copy = new ArrayList<>();
        
        copy = getGrades();
        
        copy.add(grade);
        
        courseGrades = (ArrayList<Double>) copy.clone();
    }
    
    /**
     * Computes the average of the grades in the list of grades
     */
    public double calcAverage() {
        double average = 0.0;
        double sum = 0.0;
        if (courseGrades == null) {
            average = 0.0;
            return average;
        } else {
            for (double a : courseGrades) {
                sum += a;
            }

            average = sum / courseGrades.size();
        }

        return average;
    }
    
    /**
     * Returns a String representation of this StudentCourse
     */
    public String toString() {
        return courseName + " " + courseGrades.toString();
        
        
    }




    /**
     * Determines if this StudentCourse is equal to another Object.
     *
     * @param o The other object.
     * @return true if equal, false otherwise.
     */
    public boolean equals(Object o) {
        if (o.getClass().equals(this.getClass())) {
            StudentCourse other = (StudentCourse) o;
            if (!courseName.equalsIgnoreCase(other.courseName))
                return false;
            else {
                if (courseGrades.size() != other.courseGrades.size())
                    return false;
                else {
                    for (int i = 0; i < courseGrades.size(); i++)
                        if (!courseGrades.get(i).equals(other.courseGrades.get(i)))
                            return false;

                    return true;
                }
            }
        } else
            return false;
    }
}


