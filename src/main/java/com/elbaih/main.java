package com.elbaih;

import java.util.Scanner;

import com.elbaih.fileinputandconvertion.Convertion;
import com.elbaih.ui.Home;
import com.elbaih.ui.Menu;

public class main {
      
    public static void main(String[] args) {
        Convertion.initDataBase();
        new Home().start();
    
        
     
      

    }


}
