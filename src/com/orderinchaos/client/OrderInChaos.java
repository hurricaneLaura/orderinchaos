package com.orderinchaos.client;

import com.orderinchaos.Game;

public class OrderInChaos {
  public static void main(String[] args) {
    System.out.println("LOADING...");
    for(int x = 0; x < 15; x++) {
      try {
        System.out.print("\r  |    |    |");
        Thread.sleep(75);
        System.out.print("\r  /    /    /");
        Thread.sleep(75);
        System.out.print("\r  -    -    -");
        Thread.sleep(75);
        System.out.print("\r  \\    \\    \\");
        Thread.sleep(75);
      }
      catch(Exception e) {
        System.err.println(e);
      }
    }
    System.out.println("\r              ");
    Game game = new Game();
    game.runGame();
  }
}