package com.elbaih.ui;

public class Utils {
    
  public static String padRight(String s, int pad) {
    if (s == null)
      return "";

    String newS = s;
    for (int i = 0; i < pad - s.length(); i++) {
      newS += " ";
    }

    if (newS.length() > pad) {
      newS = newS.substring(0, pad - 3).trim() + "...";
    }

    return newS;
  }
  public static String repeatString(String s, int numOfRepeats) {
    String newS = s;
    for (int i = 0; i < numOfRepeats; i++) {
      newS+=s;
    }

    return newS;
  }

  
}
