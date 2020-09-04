package com.orderinchaos;

import com.sun.jdi.Mirror;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Riddle {
  private String question;
  private String answer;

  // CONSTRUCTOR
  public Riddle() {
  }

  public Riddle(String question, String answer) {
    this.question = question;
    this.answer = answer;
  }

  // ON YOUR MARK
  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

  private static List<Riddle> riddles = new ArrayList<>();

  static {
    Riddle riddle = new Riddle("Its the answer to every question you've got. When you think you have it, you have it not. What is the answer?", "knowledge");
    riddles.add(riddle);
    riddle = new Riddle("What word is right when pronounced wrong, but is wrong when pronounced right?", "wrong");
    riddles.add(riddle);
    riddle = new Riddle("What has six faces, but does not wear makeup, has twenty-one eyes, but cannot see? What is it?", "dice");
    riddles.add(riddle);
    riddle = new Riddle("I am not alive, but I grow; I don't have lungs, but I need air; I don't have a mouth, but water kills me. What am I?", "fire");
    riddles.add(riddle);
    riddle = new Riddle("What runs around the whole yard without moving?", "fence");
    riddles.add(riddle);
    riddle = new Riddle("Who is that with a neck and no head, two arms and no hands?  What is it?", "shirt");
    riddles.add(riddle);
    riddle = new Riddle("David's father has three sons: Snap, Crackle, and _____?", "david");
    riddles.add(riddle);
    riddle = new Riddle("What belongs to you, but other people use it more than you?", "name");
    riddles.add(riddle);
    riddle = new Riddle("I make two people out of one. What am I?", "mirror");
    riddles.add(riddle);
    Collections.shuffle(riddles);
  }

  @Override
  public String toString() {
    return "Riddle{" +
      "question='" + question + '\'' +
      ", answer='" + answer + '\'' +
      '}';
  }

  // BUSINESS METHODS
  // DELETE DISPLAYLIST() METHOD
  public static void displayList() {
    for (Riddle riddle : riddles) {
      System.out.println(riddle);
    }
  }

  public static Riddle getRiddle() {
    Riddle current = riddles.get(0);
    riddles.remove(0);
    return current;
  }
}

