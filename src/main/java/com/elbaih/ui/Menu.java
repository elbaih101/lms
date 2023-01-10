package com.elbaih.ui;

public class Menu 

{


public static void printMainMenu(){
    String[] options = {"e - Enroll in a course",
    "u - Unenrollfrom an existing course",
    "r - Replacing an existing course",
    "h - Back to the home page",
};
   System.out.println("Please choose from the following:");
    for ( String item : options){
        System.out.println(item);
    }
    System.out.print("please select the required action: ");}




public static void printEnrollementMenu(){
    String[] options = {"Enter the course id that you want to enroll the student to",
    "Enter h to go back to the home page",};
System.out.println("Please choose from the following:");
    for ( String item : options){
        System.out.println(item);
    }
    System.out.print("please select the required action: ");}


    public static void printUnEnrollementMenu(){
        String[] options = {"Enter the course id that you want to unenroll the student from",
        "Enter h to go back to the home page",};
    System.out.println("Please choose from the following:");
        for ( String item : options){
            System.out.println(item);
        }
        System.out.print("please select the required action: ");}

        public static void printReplaceCourseMenu(){
            String[] options = {"Please enter the course id to be replaced:",
            "Enter h to go back to the home page",};
        System.out.println("Please choose from the following:");
            for ( String item : options){
                System.out.println(item);
            }
            System.out.print("please select the required action: ");}
    
}

    

