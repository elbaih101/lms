package com.elbaih.ui;

import java.util.Scanner;
import java.util.function.Predicate;

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
      newS += s;
    }

    return newS;
  }

  public static void sleep(int millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static String ifNotValidInputRetry(
      Predicate<String> isValid,
      String firstTimeMessage,
      String invalidMessage) {

    System.out.println(firstTimeMessage);
    Scanner s = new Scanner(System.in);
    String choice = s.nextLine();

    if (isValid.test(choice))
      return choice;
    else
      return ifNotValidInputRetry(isValid, invalidMessage, invalidMessage);
  }

  public static String ifNotValidInputRetry(
      Predicate<String> isValid,
      Runnable firstTimeMessage,
      Runnable invalidMessage) {

    firstTimeMessage.run();
    Scanner s = new Scanner(System.in);
    String choice = s.nextLine();

    if (isValid.test(choice))
      return choice;
    else
      return ifNotValidInputRetry(isValid, invalidMessage, invalidMessage);
  }

  public static String ifNotValidInputRetryOrGoHome(
      Predicate<String> isValid,
      Predicate<String> isGoHome,
      Runnable firstTimeMessage,
      Runnable invalidMessage,
      Runnable goHome) {

    firstTimeMessage.run();
    Scanner s = new Scanner(System.in);
    String choice = s.nextLine();

    if (isGoHome.test(choice)) {
      goHome.run();
      return null;
    }

    if (isValid.test(choice))
      return choice;
    else
      return ifNotValidInputRetryOrGoHome(isValid,isGoHome, invalidMessage, invalidMessage,goHome);
  }

}
