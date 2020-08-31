package com.orderinchaos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player {
  // FIELDS AKA ATTRIBUTES
  String name;
  private Inventory inventory = new Inventory();

//  public void test() {
//    inventory.dropItem("elixer");
//  }
//  Player player1 = new Player();
//  player1.test();
//  player1.inventory.dropItem("sword");
//  player1.inventory.dropItem("food");

  // CONSTRUCTORS
  public Player(String name) {
    setName(name);
  }

  // GETTERS/ SETTERS
  private String getName() {
    return name;
  }

  private void setName(String name) {
    this.name = name;
  }

  // BUSINESS METHODS




  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Player player = (Player) o;
    return name.equals(player.name) &&
      inventory.equals(player.inventory);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, inventory);
  }

  @Override
  public String toString() {
    return "Player{" +
      "name='" + name + '\'' +
      ", inventory=" + inventory +
      '}';
  }
}
