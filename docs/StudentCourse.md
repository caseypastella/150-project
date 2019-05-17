# Documentation

## `public StudentCourse()`

Creates an empty StudentCourse with an empty name and grades ArrayList.

## `public StudentCourse(String name)`

Creates a StudentCourse with a given name and empty grades.

 * **Parameters:** `name` — The name for the StudentCourse.

## `public StudentCourse(String name, ArrayList<Double> grades)`

Creates a StudentCourse with a given name and given list of grades. If the given list of grades is not null, it is deep copied from the input. If the given list of grades is null, then the resulting StudentCourse should have an empty ArrayList for its list of grades.

 * **Parameters:**
   * `name` — The name for the StudentCourse.
   * `grades` — The list of grades for the StudentCourse

## `public String getName()`

Gets the name of this StudentCourse.

 * **Returns:** The name of this StudentCourse.

## `public void setName(String in)`

Sets the name of this StudentCourse to the given name.

 * **Parameters:** `in` — The name to set.

## `public ArrayList<Double> getGrades()`

Gets a deep copy of the list of grades.

 * **Returns:** A deep copy of the list of grades.

## `public void addGrade(double grade)`

Adds a grade to the list of grades for this course.

 * **Parameters:** `grade` — The grade to add.

## `public double calcAverage()`

Computes the average grade from the list of grades. If the list of grades is empty, returns 0.0.

 * **Returns:** The average of the list of grades.

## `public String toString()`

Returns a String representation of this StudentCourse.

 * **Returns:** CourseName space Grades (use courseGrades.toString() for the grades bit).

## `public boolean equals(Object o)`

Determines if this StudentCourse is equal to another Object.

 * **Parameters:** `o` — The other object.
 * **Returns:** true if equal, false otherwise.
