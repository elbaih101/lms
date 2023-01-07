package com.elbaih.data;

public class Course {

    public String id;
    public String name;
    public String instructor;
    public String location;
    public String time;
    public String duration;
    public Course(){}
    public Course(String id, String name, String instructor, String location, String time, String duration) {
        this.id = id;
        this.name = name;
        this.instructor = instructor;
        this.location = location;
        this.time = time;
        this.duration = duration;
    }
    @Override
    public String toString() {
        return "Course [id=" + id + ", name=" + name + ", instructor=" + instructor + ", location=" + location
                + ", time=" + time + ", duration=" + duration + "]";
    }

    
    

    
}
