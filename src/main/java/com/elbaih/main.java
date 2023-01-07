package com.elbaih;

import com.elbaih.dataAccessLayer.CoursesDb;
import com.elbaih.dataAccessLayer.StudentDb;
import com.elbaih.fileinputandconvertion.Convertion;

public class main {

    public static void main(String[] args) {
        Convertion.initDataBase();
        System.out.println("Welcome to LMS"+ "\n created by {moustafa hamed elbaih}");
        System.out.println("===================================================================================="+
        "\nHome page"+
        "\n====================================================================================");


       StudentDb studentDb=new StudentDb();
       studentDb.listStudents();

    }


}
