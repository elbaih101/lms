package com.elbaih;

import com.elbaih.fileinputandconvertion.Convertion;
import com.elbaih.ui.Home;

public class main {

    public static void main(String[] args) {
        Convertion.initDataBase();
        new Home().start();
        new Home().printCoursesTable();
     
     
      

    }


}
