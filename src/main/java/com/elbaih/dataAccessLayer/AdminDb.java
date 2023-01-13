package com.elbaih.dataAccessLayer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections4.functors.CatchAndRethrowClosure;

import com.elbaih.data.Student;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class AdminDb {
 

    public static final String outputPath = "src/main/resources/";
    public static HashMap<String, List<String>> studentCourseRelatioMap = new HashMap<>();
    
    AdminDb() {
      
        initDataFromJsonFile();
    }

    public static void insert(String studentId, String courseId) {
        
        List<String> coursesList = studentCourseRelatioMap.get(studentId);
        if (coursesList == null) {
            coursesList = Arrays.asList(courseId);
        } else {
            if (!coursesList.contains(courseId)) {
                coursesList.add(courseId);
            }
        }
        studentCourseRelatioMap.put(studentId,coursesList);
        
        saveToFileJson();
    }

    public static void delete(String studentId,String courseId){
        List<String> coursesList = studentCourseRelatioMap.get(studentId);
        if (coursesList != null){
            coursesList.remove(courseId);
        }
        studentCourseRelatioMap.put(studentId, coursesList);
        saveToFileJson();
    }

    public static void replace(String studentId, String currentCourseId,String newCourseId){
        delete(studentId, currentCourseId);
        insert(studentId, newCourseId);
    }

    public List<String> getStudentCoursesIds(Student selectedStudent) {
        return studentCourseRelatioMap.get(selectedStudent.id);
    }

    public static void saveToFileJson() {
     
        String data =new Gson().toJson(studentCourseRelatioMap);
        File jsonFile = new File(outputPath + "Student-course-details" + ".json");
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(jsonFile));
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void initDataFromJsonFile() {
        File file = new File(outputPath,  "Student-course-details" + ".json");

    try {
        if (!file.isFile() && !file.createNewFile())
        {
            file.getAbsolutePath();
            
        }
    } catch (IOException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
    }

        try {

            String fileString = Files.readString(Paths.get(outputPath + "Student-course-details" + ".json"));
            Type dbType = new TypeToken<HashMap<String, List<String>>>() {}.getType();
            studentCourseRelatioMap = new Gson().fromJson(fileString, dbType);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
