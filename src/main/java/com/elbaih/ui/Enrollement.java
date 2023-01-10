package com.elbaih.ui;
import java.util.FormatFlagsConversionMismatchException;
import java.util.List;
import java.util.Scanner;

import com.elbaih.data.Course;
import com.elbaih.data.Student;
import com.elbaih.dataAccessLayer.AdminDb;
import com.elbaih.dataAccessLayer.CashedDb;
import com.elbaih.dataAccessLayer.CoursesDb;
import com.elbaih.dataAccessLayer.IRetrever;
import com.elbaih.ui.Home;
public class Enrollement
 { 
    public void enrollement ()
    
    {
    new Home().printCoursesTable( new CoursesDb().getAll(), null);
    Menu.printEnrollementMenu();
    Scanner s =new Scanner(System.in);
    String choice = s.nextLine();
    List<Course> list =new CoursesDb().getAll();

    for (Course course : list) {
        if (choice ==course.id)
        {
            AdminDb.insert(CashedDb.selectedStudent.id,choice);
        }
    
    }

    
   }
 }