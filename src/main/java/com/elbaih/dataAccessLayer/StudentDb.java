package com.elbaih.dataAccessLayer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.elbaih.data.Student;
import com.elbaih.fileinputandconvertion.Convertion;
import com.opencsv.CSVReader;

public class StudentDb extends IRetrever<Student> {
    // 0, 1, 2, 3, 4, 5, 6
    // id,name,Grade,email,address,region,country

    @Override
    public ArrayList<Student> getAll() {
        ArrayList<Student> students = new ArrayList<>();
        try {
            CSVReader csvReader = getDb(Convertion.studentDataDb);
            String[] studentLine;
            while ((studentLine = csvReader.readNext()) != null) {
                students.add(new Student(studentLine[0], studentLine[1], studentLine[2], studentLine[3], studentLine[4],
                        studentLine[5]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public Student get(String id) {
        for (Student student : getAll()) {
            if (student.id.equalsIgnoreCase(id))
                return student;
        }

        return null;
    }


   public void listStudents()
   {    System.out.println("student list:");
   System.out.println("id,      name,  Grade,   email,    address,   region,  country");
    for (Student r : getAll()) {
        System.out.println(r.id+","+r.name+","+r.grade+","+r.email+","+r.region+","+r.country);
   }
   }


}
