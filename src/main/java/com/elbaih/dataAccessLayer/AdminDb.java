package com.elbaih.dataAccessLayer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.elbaih.data.Course;
import com.elbaih.data.Student;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class AdminDb {
    public static final String outputPath = "src/main/resources/";
    HashMap<String, List<String>> studentCourseRelatioMap = new HashMap<>();

    AdminDb() {
        initDataFromJsonFile();
    }

    public void insert(String studentId, String courseId) {
        List<String> coursesList = studentCourseRelatioMap.get(studentId);
        if (coursesList == null) {
            coursesList = Arrays.asList(courseId);
        } else {
            if (!coursesList.contains(courseId)) {
                coursesList.add(courseId);
            }
        }
        studentCourseRelatioMap.put(courseId, coursesList);
        saveToFileJson();
    }

    public void delete(String studentId,String courseId){
        List<String> coursesList = studentCourseRelatioMap.get(studentId);
        if (coursesList != null){
            coursesList.remove(courseId);
        }
        studentCourseRelatioMap.put(courseId, coursesList);
        saveToFileJson();
    }

    public void replace(String studentId, String currentCourseId,String newCourseId){
        delete(studentId, currentCourseId);
        insert(studentId, newCourseId);
    }

    public List<String> getStudentCoursesIds(Student selectedStudent) {
        return studentCourseRelatioMap.get(selectedStudent);
    }

    public void saveToFileJson() {
        String data = new Gson().toJson(studentCourseRelatioMap);
        File csvFile = new File(outputPath + "Student course details" + ".json");
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void initDataFromJsonFile() {
        try {
            String fileString = Files.readString(Paths.get(outputPath + "Student course details" + ".json"));
            Type dbType = new TypeToken<HashMap<String, List<String>>>() {}.getType();
            studentCourseRelatioMap = new Gson().fromJson(fileString, dbType);
        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }
}
