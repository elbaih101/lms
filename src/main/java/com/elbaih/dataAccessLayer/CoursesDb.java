package com.elbaih.dataAccessLayer;

import java.util.ArrayList;
import java.util.List;

import com.elbaih.data.Course;
import com.elbaih.data.Student;
import com.elbaih.fileinputandconvertion.Convertion;
import com.opencsv.CSVReader;

public class CoursesDb  extends IRetrever<Course>{
    private AdminDb adminDb;

    public CoursesDb(){
        this.adminDb = new AdminDb();
    }

    @Override
    public ArrayList<Course> getAll() {
        ArrayList<Course> courses = new ArrayList<>();
        try {
            CSVReader csvReader = getDb(Convertion.courseDataDb);
            String[] coursesLine;
            while ((coursesLine = csvReader.readNext()) != null) {
                courses.add(new Course(coursesLine[0], coursesLine[1], coursesLine[2], coursesLine[3], coursesLine[4],coursesLine[5]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courses;
    }

    @Override
    public Course get(String id) {
        for (Course course : getAll()) {
            if (course.id.equalsIgnoreCase(id))
                return course;
        }
        return null;
    }

    
    public ArrayList<Course> getAllWithStudentId(Student selectedStudent) {
        List<String> coursesIds = adminDb.getStudentCoursesIds(selectedStudent);
        ArrayList<Course> courses = new ArrayList<>();

        if(coursesIds!=null) {
            for (String courseId : coursesIds) {
               Course course = get(courseId);
               if(course!=null)
                courses.add(course);
            }
        }
      
        return courses;
    }

    
        
    }

   


