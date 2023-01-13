package com.elbaih.ui;

import java.util.List;
import java.util.Scanner;

import com.elbaih.data.Course;
import com.elbaih.dataAccessLayer.AdminDb;
import com.elbaih.dataAccessLayer.CashedDb;
import com.elbaih.dataAccessLayer.CoursesDb;

public class Actions {
    public void enrollement()

    {
        new Home().printCoursesTable(new CoursesDb().getAll(), null);
        Menu.printEnrollementMenu();
        Scanner s = new Scanner(System.in);
        String choice = s.nextLine();
        List<Course> list = new CoursesDb().getAll();

        for (Course course : list) {
            if (course.id.equals(choice)) {
                AdminDb.insert(CashedDb.selectedStudent.id, choice);
                break;
            }

        }
    }

    public void deletion()

    {
        Menu.printUnEnrollementMenu();
        Scanner s = new Scanner(System.in);
        String choice = s.nextLine();
        List<String> list = AdminDb.studentCourseRelatioMap.get(CashedDb.selectedStudent.id);

        for (String courseId : list) {
            if (courseId.equals(choice)) {
                AdminDb.delete(CashedDb.selectedStudent.id, choice);
                break;
            }

        }

    }

    public void replacemnt()

    {
        Scanner s = new Scanner(System.in);
        System.out.println("enter the cource id u wish to replace");
        String cucourse = s.nextLine();
        List<String> lists = AdminDb.studentCourseRelatioMap.get(CashedDb.selectedStudent.id);
        for (String courseId : lists) {
            if (courseId.equals(cucourse)) {
                new Home().printCoursesTable(new CoursesDb().getAll(), null);
            System.out.println("plz enter the cource id u wish to rplace it by");
                String newcourse = s.nextLine();
                List<Course> listc = new CoursesDb().getAll();

                for (Course course : listc) {
                    if (course.id.equals(newcourse)) {
                        AdminDb.replace(CashedDb.selectedStudent.id, cucourse, newcourse);
                        break;
                    }

                }

            }

        }
    }
}