package com.elbaih.data;

public class Student {
  public String id;
  public String name;
  public String grade;
  public String email;
  public String address;
  public String region;
  public String country;

  public Student() {
  }

  public Student(String id, String name, String grade, String email,String address, String region, String country) {
    this.id = id;
    this.name = name;
    this.grade = grade;
    this.email = email;
    this.address = address;
    this.region = region;
    this.country = country;
  }

  @Override
  public String toString() {
    return "Student [id=" + id + ", name=" + name + ", grade=" + grade + ", email=" + email + ", address=" + address
        + ", region=" + region + ", country=" + country + "]";
  }




  

}
