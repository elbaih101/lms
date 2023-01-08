package com.elbaih.dataAccessLayer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import com.elbaih.data.Student;
import com.elbaih.fileinputandconvertion.Convertion;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public abstract class  IRetrever<R>{
    public CSVReader getDb(String dbName) throws FileNotFoundException {
        CSVReader csvReader = new CSVReader(new FileReader(Convertion.outputPath + dbName+".csv"));
        try {
            csvReader.readNext();
        } catch (CsvValidationException | IOException e) {
            e.printStackTrace();
        }
        return csvReader;
    }

    abstract public ArrayList<R> getAll();
    
    

    abstract public R get(String id);

    public void printAll(){
    getAll().forEach(i->System.out.println(i.toString()+"\n"));
    
        
       // for (Student r : Student.getAll()) {
       //      System.out.println(r.id+","+r.name+","+r.grade+","+r.email+","+r.region+","+r.country);
//          }
    }
}
