package com.elbaih.data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Student {
  public String id;
  public String name;
  public String grade;
  public String email;
  public String region;
  public String country;

  public Student() {
  }

  public Student(String id, String name, String grade, String email, String region, String country) {
    this.id = id;
    this.name = name;
    this.grade = grade;
    this.email = email;
    this.region = region;
    this.country = country;
  }

  @Override
  public String toString() {
    return "Student [id=" + id + ", name=" + name + ", grade=" + grade + ", email=" + email + ", region=" + region
        + ", country=" + country + "]";
  }


  

}
