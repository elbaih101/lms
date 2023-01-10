package com.elbaih.ui;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.elbaih.data.Course;
import com.elbaih.data.Student;
import com.elbaih.dataAccessLayer.CashedDb;
import com.elbaih.dataAccessLayer.CoursesDb;
import com.elbaih.dataAccessLayer.IRetrever;
import com.elbaih.dataAccessLayer.StudentDb;
import static com.elbaih.ui.Utils.padRight;
import static com.elbaih.ui.Utils.repeatString;
public class Home {
  private final IRetrever<Student> students;
  private final CoursesDb courses;

  public Home() {
    this.students = new StudentDb();
    this.courses = new CoursesDb();
  }

  public void start() {
    printHome();
  }

  public void printHome() {
    System.out.println("Welcome to LMS" + "\n created by {moustafa hamed elbaih}");
    System.out.println(repeatString("=", 150) +
        "\nHome page\n" +
        repeatString("=", 150));
    printStudentTable(students.getAll(),null);
    selectStudentAction();
   // AdminDb.insert(CashedDb.selectedStudent.id,)

    
    
  }

  public void selectStudentAction() {
    Scanner s = new Scanner(System.in);
    System.out.println("Please select the required student:");

    String id = s.nextLine();

    Student student = students.get(id);
    if (student == null) {
      System.out.println("invalid student id");
      printHome();
    } else {
      CashedDb.selectedStudent = student;
      printStudentTable(Arrays.asList(student),"student details");
      printCoursesTable(courses.getAllWithStudentId(CashedDb.selectedStudent), "Enrolled courses.");
      Menu.printMainMenu();
      String choice= s.nextLine();

     switch(choice)
     {
        case "e":
        new Enrollement().enrollement ();
       
    
      break;
      case "u":
        Menu.printUnEnrollementMenu();
    
      break;
      case "r":
        Menu.printReplaceCourseMenu();;
    
      break;
      case "h":
        new Home().start();
    
      break;
     }
    }
    
  }

  public  void printStudentTable(List<Student> list,String title) {
    
    String message = title == null ?"Student list:":title;
    message += "\n" + repeatString("=", 130);
    message += String.format("\n%s %s %s %s %s %s %s", padRight("id", 5), padRight("name", 20), padRight("Grade", 5),
        padRight("email", 35), padRight("address", 30), padRight("region", 20), padRight("country", 20));
    message += "\n" + repeatString("-", 130);
    for (Student student : list) {
      message += String.format("\n%s %s %s %s %s %s %s", padRight(student.id, 5), padRight(student.name, 20),
          padRight(student.grade, 5), padRight(student.email, 35), padRight(student.address, 30),
          padRight(student.region, 20), padRight(student.country, 20));
    }

    message += "\n"+repeatString("-", 150);
    System.out.println(message);
  }

  public  void printCoursesTable(List<Course> list, String title) {
    
    if (list == null || list.isEmpty()){System.out.println(title+"\n"+repeatString("-",30)+" \nthis student isn't inroled in any course\n"+repeatString("-", 150));}
   else{
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

    message += "\n"+repeatString("-", 150);
    System.out.println(message);
  }
  }
 
  
}
