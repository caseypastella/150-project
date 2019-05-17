# `cpsc150_p5`

**CPSC150**
**Fall 2017**
**P5: ArrayLists**

## Preliminaries

Please refer to [PCSE's git tutorial](https://gitlab.pcs.cnu.edu/cpsc-labs/bash-and-git-wiki/wikis/home) if you need further reference.

You only need to do step 1 a single time for my class.
You need to do step 2 once for each project I give.

1. Create a group on Gitlab called `firstname.lastname.yy-cpsc150` and add me to that group as a **reporter** (follow instructions under **Creating a Gitlab Group** [here](https://gitlab.pcs.cnu.edu/cpsc-labs/bash-and-git-wiki/wikis/tutorials/git-setup)).
Your group name should look like your CNU email address with a `-cpsc150` on the end instead of a `@cnu.edu`.

2. Click the "Fork" button on this page (see image below) and select your newly created group as the place to which you will fork this repository.
![Click the fork button under the repository name](https://gitlab.pcs.cnu.edu/cpsc-labs/bash-and-git-wiki/wikis/tutorials/images/gitlab-fork.png)

## Workflow Proper

This section describes the general workflow for a Git enabled project with a single developer.
For the rest of the tutorial, ensure that Git Bash (Windows) or a terminal (Mac or Linux) is open.

### First time working on current machine

You only need to follow these steps if this is the first time working on a project on the current computer (if you're on the lab machines, you have to do this each time you log on).

1. Inside the directory in which you store your repositories run `pwd`.
This command will tell you where you currently are (in Windows this is much less helpful, however if it outputs `/home/username` or `~` then this corresponds to `C:\users\<username here>`).
Take note of its output as this is where Git will place your assignment.

2. Near the fork button, there is a text box with a URL in it.
Ensure that this box has `HTTPS` next to it and then copy the URL.
**This URL should begin with `https`!!!!**

3. In your terminal/Git Bash, type `git clone` and then paste the URL.
If the command looks as follows, go ahead and press enter.
```
git clone https://gitlab.pcs.cnu.edu/<container name>/<repo name>
```

Proceed to the next section.

### After cloning

Once you've cloned the repository to your current machine, you can just follow these steps.

1. If you just cloned the repository, you can ignore this step.
If you've worked on the project on another machine and wish to update the copy on your current computer, run `git pull`.

2. Do changes. (Differs for each project)

3. Track your changes using `git add .` .
This tells Git to figure out what changed between the repository's previous state and its current state.

4. Double check your changes using `git status`.
Running `git status` will tell you what files you've added, what files you've removed, and what files you've modified so inspect its output carefully.
If you're not happy about its output, go back to step 2 and fix what you don't like.

5. Commit your changes using `git commit -m "Commit message here"`.
This will **permenantly log** the changes you've made in your repository.
Once you've committed your changes, you can view the commit's *hash* and your commit message for it using `git log`.

6. Push your changes back to Gitlab using `git push`.
This uploads your changes to Gitlab so that you have a backup that you can share with your professor and teammates.

7. Check the CI pipeline on Gitlab to see the automated feedback that Gitlab provided (if enabled for your project).

## Goal

The goal of this assignment is that you get practice with ArrayLists and objects.

## Description

This program creates an `ArrayList` of `Student`s, and reads in, and prints out information on them. 
Note: **Every collection in this assignment should be an `ArrayList`, not an array**. 
When comparing `String`s, please use `equalsIgnoreCase` throughout the program so that `"CPSC150"` will be equal to `"cpsc150"` and `"Cpsc150"`.

## Sample Run

[Here](docs/samplerun.md) is a sample run.
Ensure that your output matches the format of the sample run.
Note: **ALWAYS use `next()` as opposed to `nextLine()`.**

## Classes to Write

Remember, **ALL** code should be properly indented and Javadoc'd.

### `StudentCourse`

Create a class, `StudentCourse`. 
[Here](docs/StudentCourse.md) is the API for the class `StudentCourse`. 
`StudentCourse` should have two instance variables, a `courseName` (of type `String`) an `ArrayList` of `Doubles` called `courseGrades` that contains the grade for that student in that course.
I provide the following `equals` method, include it in your code.
```java
public class StudentCourse {
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
```

### `Student`

Create a new class, `Student`. 
[Here](docs/Student.md) is the API for the class `Student`. 
`Student` should have two instance variables, a `studentName` (of type `String`) an `ArrayList` of `StudentCourses` called `courses` that contains the courses for that student.
I provide the following `equals` method, include it in your code.
```java
public class Student {
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
```

### `P5`

`P5` will ask the user to enter information about students and report on information about students according to a menu in `main`. 
I include `main` below. 
Your job is to write all of the methods that `main` calls (using the [samplerun](docs/samplerun.md) as a format guide).
The output in the samplerun is formatted as follows:
```
Would you like to:
     1. to Add a student
     2. to Calculate an average for all students
     3. to Print a report for a student
     4. to Print a report for a class
     5. to Print a report for all students
     6. to Quit
Enter choice --> n

<begin your method output>
...
<end your method output

Would you like to:
     1. to Add a student
     2. to Calculate an average for all students
     3. to Print a report for a student
     4. to Print a report for a class
     5. to Print a report for all students
     6. to Quit
Enter choice -->
```
That is `main` will print a menu, the user will enter an option, and another blank line will be printed.
Your code should assume that it starts on a new line.
Moreover, the last print statement in your method should be a `println` with some text in it.
After this, `main` will print another blank line and show the menu again.
Your methods should **NEVER** print a blank line.
Messages must be **EXACTLY** what the samplerun dictates (up to names and numbers).
These will all be static methods (no methods in `Student` or `StudentCourse` should be static).
You should use this as your base for the `P5` class.
```java
public class P5 {
    public static void main(String[] args) {
        Scanner kbd = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();
        char choice;
        System.out.println("This program manages students and their courses.");
        do {
            choice = showMenu(kbd);
            if (choice == '1') {
                Student s = readStudent(kbd);
                students.add(s);
            } else if (choice == '2') {
                double average = calcAverage(students);
                System.out.println(String.format("The average for all students is %.2f", average));
            } else if (choice == '3')
                printStudentReport(kbd, students);
            else if (choice == '4')
                printCourseReport(kbd, students);
            else if (choice == '5')
                printAllStudentReport(students);
            else if (choice != '6') // bad choice entered
                System.out.println("Choice must be 1-6.");
            System.out.println();
        } while (choice != '6');
        System.out.println("*** Program exiting ***");
    }
    public static char showMenu(Scanner kbd) {
        System.out.print("Would you like to:\n\t" +
                "1. Add a student\n\t2. Calculate an average for all students\n\t" +
                "3. Print a report for a student\n\t4. Print a report for a class"
                + "\n\t5. Print a report for all students\n\t6.Quit\nEnter choice --> ");
        char o = kbd.next().charAt(0);
        System.out.println();
        return o;
    }
}
```

Here is a table of options and the method to which they correspond.

| Option | Method |
| ------ | ------ |
| 1 | `readStudent` |
| 2 | `calcAverage` |
| 3 | `printStudentReport` |
| 4 | `printCourseReport` |
| 5 | `printAllStudentReport` |

## Testing

As you write the program, compile and test frequently. 
Write the methods first, and test them by running them. 
Once you have written every method, include a screenshot of the entire program running.
Create JUnit test classes for each class (that is `StudentCourseTest`, `StudentTest`, and `P5Test`).
Write JUnit tests for all methods that return a value.
Tests for each class **MUST** be in its respective JUnit test class.

## Documentation

Write javadoc for each method in each class and one javadoc comment at the top of each class.
The program as a whole should include a comment with your name and your partner's name.
Indicate in the comment how long it took you to work on the program.
If you do not have a partner, include an explanation of why you do not.
If anybody helped you on the program, write a note about how they helped.
If you helped anybody or received help, include a comment about that.
You should also include a one line description of what the program does.

## Schedule

You should write the program in parts, and push to Gitlab. 
Here is a schedule for when each part should be completed: 

| Class | Tested and Pushed to Gitlab by |
| ----- | ------------------------------ |
| `StudentCourse` | Thursday, 30 Nov. |
| `Student` | Monday, 4 Dec. |
| `P5` | Thursday, 7 Dec. |
| All | Friday, 8 Dec. |

## Submissions

Submit your code and tests to WebCAT.
To do so, fill out the `login.txt` in the root of the repository by running:

Windows:
```
notepad login.txt
```

Mac:
```
open -e login.txt
```

Once this is filled out, Gitlab will upload your code (and tests) to WebCAT for grading.

## Pipelines

If a pipeline fails, check out its output.
You only need to be concerned if one of the upload tasks fails for some files you wrote.
An upload task associated with a particular class will fail if
1. The `login.txt` is not filled out correctly.
2. The program class does not exist.
3. The test class does not exist.

For example, the `StudentCourse-upload` task will fail: 
if the `login.txt` is not filled out correctly; 
`StudentCourse.java` does not exist; 
or `StudentCourseTest.java` does not exist.

If a build or test task fails, then your program does not pass all of the JUnit tests for that class.