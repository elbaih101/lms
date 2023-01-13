package com.elbaih.ui;

import com.elbaih.data.Student;
import com.elbaih.dataAccessLayer.CoursesDb;
import com.elbaih.dataAccessLayer.IRetrever;
import com.elbaih.dataAccessLayer.StudentDb;

public class Home {
  private final IRetrever<Student> students;
  private final CoursesDb courses;
  private Actions actions;

  public Home() {
    this.students = new StudentDb();
    this.courses = new CoursesDb();
    this.actions = new Actions();

  }

  public void start() {
    while(true){
      actions.printHome();
    }
  }
}