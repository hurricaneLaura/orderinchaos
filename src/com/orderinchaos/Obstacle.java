package com.orderinchaos;

import java.util.Scanner;

import static com.orderinchaos.Util.CLEAR_SCREEN;
import static com.orderinchaos.Util.LOAD_SCREEN;

public class Obstacle {
  // FIELDS/ ATTRIBUTES
  private boolean pass;
  private Riddle riddle;
  private static final int ATTEMPTS = 4;


  public Obstacle(Riddle riddle) {
    setRiddle(riddle);
  }

  // ON YOUR MARK
  public boolean isPass() {
    return pass;
  }

  public void setPass(boolean pass) {
    this.pass = pass;
  }

  public int getAttempts() {
    return ATTEMPTS;
  }

  private void setRiddle (Riddle riddle) {
    this.riddle = riddle;
  }

  // BUSINESS METHODS
  public void displayRiddle() {
    int attemptsLeft = getAttempts();
    Scanner sc = new Scanner(System.in);
    System.out.println(riddle.getQuestion());
    String userAttempt = sc.nextLine();

    while (attemptsLeft > 0) {
      boolean isCorrect = userAttempt.toLowerCase().contains(riddle.getAnswer());

      if (isCorrect) {
        System.out.println("Success");
        success();
        break;
      } else {
        attemptsLeft = attemptsLeft - 1;
        System.out.println("That is not the correct answer. You have " + attemptsLeft + " attempts remaining");
        if (attemptsLeft == 0) {
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
    LOAD_SCREEN();
    CLEAR_SCREEN();
  }
}
