import java.util.ArrayList;
/**
 * Authors: Casey Pastella and Tyler Serpa
 * 
 */

public class Student {

    private String studentName;
    private ArrayList<StudentCourse> courses;
    /**
     * Creates a new Student object with a name of "no name" and an empty ArrayList of courses.
     */
    public Student() {

        studentName = "no name";
        courses = new ArrayList<>();

    }
    
     /**
     * Creates a new Student object with a given name and an empty ArrayList of courses.
     * @param 
     */
    public Student(String name) {

        studentName = name;
        courses = new ArrayList<>();
    }
    
    
     /**
     * Gets the name of this Student
     * @return The name of this student
     */

    public String getName() {
        return studentName;

    }

    /**
     * Sets the name of this Student to the given name.
     * @param in the given name
     */
    public void setName(String in) {
        this.studentName = in;
    }
    
    /**
     * Gets a deep copy of the list of courses.
     * @return A list of copies of the elements in the list of courses.
     */
    public ArrayList<StudentCourse> getCourses() {
        ArrayList<StudentCourse> courses2 = new ArrayList<StudentCourse>();
        
        if (courses != null) {
            for(StudentCourse c : courses) 
            courses2.add(new StudentCourse(c.getName(), c.getGrades()));
        }
        return courses2;
    }
    
    /**
     * Adds a new StudentCourse with the given name and list of grades to the list of courses.
     * @param name the name for the new course
     * @param grades the list of grades for the new course
     */

    public void addCourse(String name, ArrayList<Double> grades) {
        courses.add(new StudentCourse(name,grades));
        
         
         



    }
    
    /**
     * Gets the average for a given course.
     * @param name the name of the course
     * @return the average for the course with course name name, or a negative number if not found
     */
    public double getCourseAverage(String name) {
        for (StudentCourse course : courses) {

            if(course.getName().equals(name))
                return course.calcAverage();

        }
        
        return -1;
        
    }
    
    /**
     * Calculates the average of all courses in the list of courses.
     * This should use StudentCourse's calcAverage method.
     * @return the average of all courses, or 0.0 if the list is empty
     */

    public double calcAverage() {
        double sum = 0.0;
        for (StudentCourse course : courses) {
            sum += course.calcAverage();
        }
        if (courses.size() == 0)
            return sum;
        return sum / courses.size();

    }

    
    /**
     * Returns a summary of the Student as a String. 
     * Choosing 3 from the menu in P5 should call this method (see sample run with Grace Hopper).
     * @return name space Average: space averageOfAllCourses space Courses: space course1Name space course2Name  space ... courseMName
     */

    public String getSummary() {
        String o = getName() + " Average: " + String.format("%.2f",calcAverage()) + " Courses:";
        for(StudentCourse c: courses) {
            o += " "+ c.getName();
        }
        return o;
    }
    
    /**
     * Returns a String representation of this object. 
     * Choosing choice 5 from the main menu in P5 should call this method.
     * @return name colon space courses (for courses use courses.toString()).
     */

    public String toString() {
        return studentName + ":" + " " + courses.toString(); 
    }

    /**
     * Determines whether or not this equals another Object.
     *
     * @param o The other Object.
     * @return true if equal, false otherwise.
     */
    public boolean equals(Object o) {
        if (o.getClass().equals(this.getClass())) {
            Student other = (Student) o;
            if (!this.studentName.equalsIgnoreCase(other.studentName))
                return false;
            else {
                if (this.courses.size() != other.courses.size())
                    return false;
                else {
                    for (int i = 0; i < this.courses.size(); i++)
                        if (!this.courses.get(i).equals(other.courses.get(i)))
                            return false;
                    return true;
                }
            }
        } else
            return false;
    }
}




