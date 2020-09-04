package com.orderinchaos;

import java.util.Scanner;

public class Obstacle {
  // FIELDS/ ATTRIBUTES
  private boolean pass;
  private int attempts = 4;

  // ON YOUR MARK
  public boolean isPass() {
    return pass;
  }

  public void setPass(boolean pass) {
    this.pass = pass;
  }

  public int getAttempts() {
    return attempts;
  }

  public void setAttempts(int attempts) {
    this.attempts = attempts;
  }

  // BUSINESS METHODS
  // TODO: create a riddle list
  public void displayRiddle() {
    Scanner sc = new Scanner(System.in);
    Riddle currentRiddle = Riddle.getRiddle();
    System.out.println(currentRiddle.getQuestion());
    String userAttempt = sc.nextLine();

    while (attempts > 0) {
      boolean isCorrect = userAttempt.toLowerCase().contains(currentRiddle.getAnswer());

      if (isCorrect) {
        System.out.println("Success");
        success();
        break;
      } else {
        attempts = attempts - 1;
        System.out.println("That is not the correct answer. You have " + attempts + " attempts remaining");
        if (attempts == 0) {
          System.out.println("You have failed us all, demons continue their evil rule unimpeded.");
          System.exit(0);
        } else {
          userAttempt = sc.nextLine();
        }
      }
    }
  }


  // TODO: give user a reward
  public void success() {
    System.out.println("very cool treasure added to inventory");
  }
}
