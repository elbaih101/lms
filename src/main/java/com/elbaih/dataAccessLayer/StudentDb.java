package com.elbaih.dataAccessLayer;

import java.util.ArrayList;
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
                students.add(new Student(studentLine[0], studentLine[1], studentLine[2], studentLine[3], studentLine[4],studentLine[5],studentLine[6]));
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

    
}