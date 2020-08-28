package com.orderinchaos;

import java.util.*;

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

  public static final String INPUT_HANDLER(List<String> items) {
    String confirmation = "";
    List<String> validInput = Arrays.asList("look", "read", "take", "drop");

    boolean isValidInput = false;
    while (!isValidInput) {
      try{
        Scanner wait = new Scanner(System.in);
        String userInput = wait.nextLine();
        String[] inputArr = userInput.trim().split(" ",2);
        String verb = inputArr[0].trim().toLowerCase();
        String noun = inputArr[1].trim().toLowerCase();

        // DONE: check verb inputArr[0] & noun inputArr[1]
        if (validInput.contains(verb)) {
          if (verb.equals("take") || verb.equals("drop")) {
            if (verb.equals("take") && items.contains(noun)) {
              confirmation = noun.toUpperCase() + " is added to player's inventory";
              isValidInput = true;
            } else if (verb.equals("drop") && items.contains(noun)) {
              confirmation = noun.toUpperCase() + " is removed from player's inventory";
              isValidInput = true;
            } else {
              System.out.println(noun.toUpperCase() + " is not available...");
            }
          } else if (verb.equals("look")) {
            // TODO: display currentRoom.description
            confirmation = "display currentRoom.description";
            isValidInput = true;
          } else if (verb.equals("read")) {
            // TODO: create a list of items categorized as "document"
            confirmation = "display content if item is categorized as \"document\"";
            isValidInput = true;
          }
        } else {
          System.out.println("I'm not sure what " + userInput + " means...");
        }
      } catch (IndexOutOfBoundsException e) {
        // TODO: display suggestion
        System.out.println("Invalid input.");
      }
    }

    return confirmation;
  }
}
