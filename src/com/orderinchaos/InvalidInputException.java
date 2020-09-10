package com.orderinchaos;

public class InvalidInputException extends Exception {
  private String input;
  public InvalidInputException(String input) {
    this.input = input;
  }
  @Override
  public void printStackTrace() {
    System.out.println("I'm not sure what " + input.toUpperCase() + " means...");
  }
}
