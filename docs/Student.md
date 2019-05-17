# Documentation

## `public Student()`

Creates a new Student object with a name of "no name" and an empty ArrayList of courses.

## `public Student(String name)`

Creates a new Student object with a given name and an empty ArrayList of courses.

 * **Parameters:** `name` — The given name.

## `public String getName()`

Gets the name of this Student.

 * **Returns:** The name of this Student.

## `public void setName(String in)`

Sets the name of this Student to the given name.

 * **Parameters:** `in` — The given name.

## `public ArrayList<StudentCourse> getCourses()`

Gets a deep copy of the list of courses.

 * **Returns:** A list of copies of the elements in the list of courses.

## `public void addCourse(String name, ArrayList<Double> grades)`

Adds a new StudentCourse with the given name and list of grades to the list of courses.

 * **Parameters:**
   * `name` — The name for the new course.
   * `grades` — The list of grades for the new course.

## `public double getCourseAverage(String name)`

Gets the average for a given course.

 * **Parameters:** `name` — The name of the course.
 * **Returns:** The average for the course with course name name, or a negative number if not found.

## `public double calcAverage()`

Calculates the average of all courses in the list of courses. This should use StudentCourse's calcAverage method.

 * **Returns:** The average of all courses in the list of courses, or 0.0 if the list is empty.

## `public String getSummary()`

Returns a summary of the Student as a String. Choosing 3 from the menu in P5 should call this method (see sample run with Grace Hopper).

 * **Returns:** name space Average: space averageOfAllCourses space Courses: space course1Name space course2Name  space ... courseMName

## `public String toString()`

Returns a String representation of this object. Choosing choice 5 from the main menu in P5 should call this method.

 * **Returns:** name colon space courses (for courses use courses.toString()).

## `public boolean equals(Object o)`

Determines whether or not this equals another Object.

 * **Parameters:** `o` — The other Object.
 * **Returns:** true if equal, false otherwise.
