package com.orderinchaos;

import com.sun.jdi.Mirror;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Riddle {
  private String question;
  private String answer;

  // CONSTRUCTOR
  public Riddle () {

  }
  public Riddle(String question, String answer) {
    setQuestion(question);
    setAnswer(answer);
  }

  // ON YOUR MARK
  public String getQuestion() {
    return question;
  }

  private void setQuestion(String question) {
    this.question = question;
  }

  public String getAnswer() {
    return answer;
  }

  private void setAnswer(String answer) {
    this.answer = answer;
  }

  @Override
  public String toString() {
    return "Riddle{" +
      "question='" + question + '\'' +
      ", answer='" + answer + '\'' +
      '}';
  }

  // BUSINESS METHODS
//  public static Riddle getRiddle() {
//    Riddle current = riddles.get(0);
//    riddles.remove(0);
//    return current;
//  }
}

