package com.elbaih.ui;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.RecursiveAction;
import java.util.function.Predicate;

import com.elbaih.data.Course;
import com.elbaih.data.Student;
import com.elbaih.dataAccessLayer.AdminDb;
import com.elbaih.dataAccessLayer.CashedDb;
import com.elbaih.dataAccessLayer.CoursesDb;
import com.elbaih.dataAccessLayer.StudentDb;
import com.elbaih.ui.Home;
import static com.elbaih.ui.Utils.padRight;
import static com.elbaih.ui.Utils.repeatString;
import static com.elbaih.ui.Utils.ifNotValidInputRetry;
import static com.elbaih.ui.Utils.sleep;

public class Actions {
    CoursesDb coursesDb = new CoursesDb();
    AdminDb adminDb = AdminDb.getInstance();
    StudentDb studentDb = new StudentDb();

    List<Course> listc = new CoursesDb().getAll();

    Scanner s = new Scanner(System.in);

    public void printHome() {
        System.out.println("Welcome to LMS" + "\n created by {moustafa hamed elbaih}");
        System.out.println(repeatString("=", 150) +
                "\nHome page\n" +
                repeatString("=", 150));
        printStudentTable(studentDb.getAll(), null);
        selectStudentAction();
        // AdminDb.insert(CashedDb.selectedStudent.id,)
    }

    public void enrollement() {
        List<String> lists = adminDb.getStudentCoursesIds(CashedDb.selectedStudent);

        List<Course> courses = coursesDb.getAvailableCoursesForStudent(CashedDb.selectedStudent);
        printCoursesTable(courses, "availablecourses");

        String validChoiseString = ifNotValidInputRetry(
                input -> courses.stream().anyMatch(i -> i.id.equals(input)) || input.equalsIgnoreCase("h"),
                () -> Menu.printEnrollementMenu(), () ->System.out.println("The input you have provided is invalid, please enter a valid input"));

        if (validChoiseString.equalsIgnoreCase("h"))
            printHome();
        if (lists.size() < 6)
            adminDb.insert(CashedDb.selectedStudent.id, validChoiseString);
        else
            System.out.println("Failed to enroll:the Student reached the limit");
        printHome();

    }

    public void deletion()

    {
        List<String> lists = adminDb.getStudentCoursesIds(CashedDb.selectedStudent);
        if (lists.isEmpty())
        {
            System.out.println("this student hasnt enrolled in any course");
            sleep(1000);
            printHome();
            return;
        }
        String validInput = ifNotValidInputRetry((input) -> lists.contains(input) || input.equalsIgnoreCase("h"),
                () -> Menu.printUnEnrollementMenu(),
                () -> System.out
                        .println("invalid course id: plz enter the course id from the list of enrolled courses"));

        if (validInput.equalsIgnoreCase("h")) {
            printHome();
            return;
        }

        adminDb.delete(CashedDb.selectedStudent.id, validInput);

    }

    public void replacemnt() {
        List<String> lists = adminDb.getStudentCoursesIds(CashedDb.selectedStudent);
        List<Course> availableCourseList = coursesDb.getAvailableCoursesForStudent(CashedDb.selectedStudent);
        if (lists.isEmpty())
        {
            System.out.println("this student hasnt enrolled in any course");
            sleep(1000);
            printHome();
            return;
        }
         
        String cucourse = ifNotValidInputRetry(s -> lists.contains(s) || s.equalsIgnoreCase("h"),
                "enter the cource id u wish to replace",
                "invalid course id:plz enter the course id from the list of enrolled courses");

        if (cucourse.equalsIgnoreCase("h")) {
            printHome();
            return;
        }

        printCoursesTable(availableCourseList, null);
        Utils.sleep(1000);

        String newcourse = ifNotValidInputRetry(
                (s) -> availableCourseList.stream().anyMatch(i -> i.id.equals(s)) || s.equalsIgnoreCase("h"),
                "plz enter the cource id u wish to rplace it by",
                "invalid course id:plz enter the course id from the list of  courses");

        if (newcourse.equalsIgnoreCase("h")) {
            printHome();
            return;
        }

        adminDb.replace(CashedDb.selectedStudent.id, cucourse, newcourse);
    }

    public void printStudentTable(List<Student> list, String title) {

        String message = title == null ? "Student list:" : title;
        message += "\n" + repeatString("=", 130);
        message += String.format("\n%s %s %s %s %s %s %s", padRight("id", 5), padRight("name", 20),
                padRight("Grade", 5),
                padRight("email", 35), padRight("address", 30), padRight("region", 20), padRight("country", 20));
        message += "\n" + repeatString("-", 130);
        for (Student student : list) {
            message += String.format("\n%s %s %s %s %s %s %s", padRight(student.id, 5), padRight(student.name, 20),
                    padRight(student.grade, 5), padRight(student.email, 35), padRight(student.address, 30),
                    padRight(student.region, 20), padRight(student.country, 20));
        }

        message += "\n" + repeatString("-", 150);
        System.out.println(message);
    }

    public void printCoursesTable(List<Course> list, String title) {

        if (list == null || list.isEmpty()) {
            System.out.println(title + "\n" + repeatString("-", 30) + " \nthis student isn't inroled in any course\n"
                    + repeatString("-", 150));
        } else {
            String message = title == null ? "Courses List:" : title;
            message += "\n" + repeatString("=", 150);
            message += String.format("\n%s %s %s %s %s %s ", padRight("id", 5), padRight("Course Name", 30),
                    padRight("Instructor", 30),
                    padRight("duration", 35), padRight("Course time", 30), padRight("location", 20));
            message += "\n" + repeatString("-", 150);

            for (Course course : list) {
                message += String.format("\n%s %s %s %s %s %s ", padRight(course.id, 5), padRight(course.name, 30),
                        padRight(course.instructor, 30), padRight(course.location, 35), padRight(course.time, 30),
                        padRight(course.duration, 20));
            }

            message += "\n" + repeatString("-", 150);
            System.out.println(message);
        }
    }

    public void selectStudentAction() {
        Scanner s = new Scanner(System.in);
        System.out.println("Please select the required student:");

        String id = s.nextLine();

        Student student = studentDb.get(id);

        if (student == null) {
            System.out.println("invalid student id");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            printHome();
        } else {
            CashedDb.selectedStudent = student;
            // System.out.println(CashedDb.selectedStudent.toString());
            printStudentTable(Arrays.asList(CashedDb.selectedStudent), "student details");
            printCoursesTable(coursesDb.getAllWithStudent(CashedDb.selectedStudent), "Enrolled courses.");
            Menu.printMainMenu();
            String choice = s.nextLine();

            switch (choice) {
                case "e":
                    enrollement();
                    break;

                case "u":
                    deletion();
                    break;

                case "r":
                    replacemnt();
                    break;

                case "h":
                    printHome();
                    break;
            }
        }
    }

}