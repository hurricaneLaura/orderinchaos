package com.orderinchaos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

public class Util {
  public static final int INPUT_HANDLER() {
    int userInput = 1;
    boolean validInput = false;
    String prompt = "1: New Game\n2: Load Game\n3: How to Play";
    while (!validInput) {
      try{
        System.out.println(prompt);
        Scanner wait = new Scanner(System.in);
        userInput = wait.nextInt();
        if (userInput >= 1 && userInput <=3) {
          validInput = true;
        } else {
          System.out.println("Please select an item from the menu.");
        }
      } catch (InputMismatchException e) {
        System.out.println("Invalid input. Please select an item from the menu.");
      }
    }

    return userInput;
  }

  public static final String[] INPUT_HANDLER(List<String> validCommands) {
    // TODO: change String[] to a KVP collection (hashset maybe)
    String[] userInput = new String[2];
    boolean isValidInput = false;
    while (!isValidInput) {
      try{
        Scanner wait = new Scanner(System.in);
        String input = wait.nextLine();
        String[] inputArr = input.trim().split(" ",2);
        String verb = inputArr[0].trim().toUpperCase();
        String noun = inputArr[1].trim().toUpperCase();

        // DONE: check verb inputArr[0] & noun inputArr[1]
        if (validCommands.contains(verb)) {
          isValidInput = true;
          userInput[0] = verb;
          userInput[1] = noun;
        } else {
          System.out.println("I'm not sure what " + input + " means...");
        }
      } catch (IndexOutOfBoundsException e) {
        // TODO: display suggestion
        System.out.println("Invalid input.");
      }
    }

    return userInput;
  }

  public static final Stream<String> TEXT_READER(String fileName) {
    Stream<String> result = null;
    try {
      result = Files.lines(Paths.get("docs",fileName));
    } catch (IOException e) {
      e.printStackTrace();
    }

    return result;
  }

  public static final void STREAM_DISPLAY(Stream<String> stream, int duration) {
    stream.forEach(line -> {
      System.out.println(line);
      try {
        Thread.sleep(duration);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    System.out.print("\n");
  }

  public static final void CLEAR_SCREEN() {
    try {
      final String os = System.getProperty("os.name");
      if (os.contains("Windows")) {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      } else {
        new ProcessBuilder("clear").inheritIO().start().waitFor();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
