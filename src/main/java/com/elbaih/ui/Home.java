package com.elbaih.ui;

import com.elbaih.data.Course;
import com.elbaih.data.Student;
import com.elbaih.dataAccessLayer.CoursesDb;
import com.elbaih.dataAccessLayer.IRetrever;
import com.elbaih.dataAccessLayer.StudentDb;

public class Home {
  private final IRetrever<Student> students;
  private final IRetrever<Course> courses;
  public Home() {
    this.students = new StudentDb();
    this.courses= new CoursesDb();
  }

  public void start() {
    System.out.println("Welcome to LMS" + "\n created by {moustafa hamed elbaih}");
    System.out.println("====================================================================================" +
        "\nHome page" +
        "\n====================================================================================");
    printStudentTable();
    System.out.println("Please select the required student:1");
   

  }


  public String padRight(String s, int pad) {
    if(s==null) return "";

    String newS = s;
    for (int i = 0; i < pad - s.length(); i++) {
      newS += " ";
    }

    if(newS.length()>pad){
      newS = newS.substring(0,pad-3).trim()+"...";
    }

    return newS;
  }

  public void printStudentTable() {
    String message = "Student list:";
    message += String.format("\n%s %s %s %s %s %s %s", padRight("id", 5), padRight("name", 20), padRight("Grade", 5),
        padRight("email", 35), padRight("address", 30), padRight("region", 20), padRight("country", 20));

    for (Student student : students.getAll()) {
      message += String.format("\n%s %s %s %s %s %s %s", padRight(student.id, 5), padRight(student.name, 20),
          padRight(student.grade, 5), padRight(student.email, 35), padRight(student.address, 30),
          padRight(student.region, 20), padRight(student.country,20));
    }

    message += "\n------------------------------------------------------------------------------------";
    System.out.println(message);
  }
  //id,     Course Name,         Instructor,        Course duration,        Course time,        Location 
  
  public void printCoursesTable() {
    String message = "Courses List:";
    message += String.format("\n%s %s %s %s %s %s ", padRight("id", 5), padRight("Course Name", 30), padRight("Instructor",30 ),
        padRight("duration", 35), padRight("Course time", 30), padRight("location", 20));

    for (Course course : courses.getAll()) {
      message += String.format("\n%s %s %s %s %s %s ", padRight(course.id, 5), padRight(course.name, 30),
          padRight(course.instructor, 30), padRight(course.location, 35), padRight(course.time, 30),
          padRight(course.duration, 20));
    }

    message += "\n------------------------------------------------------------------------------------";
    System.out.println(message);
  }

}
