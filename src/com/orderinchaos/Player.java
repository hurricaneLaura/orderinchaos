package com.orderinchaos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player {
  // FIELDS
  String name;
  private List<Item> inventory = new ArrayList<>();

  public Player(String name) {
    this.name = name;
  }

  // CONSTRUCTORS
  public Player(String name, List<Item> inv){
    this(name);
    setInventory(inv);
  }

  // GETTERS/ SETTERS
  private String getName() {
    return name;
  }

  private void setName(String name) {
    this.name = name;
  }

  private List<Item> getInventory() {
    return inventory;
  }

  private void setInventory(List<Item> inv) {
    this.inventory = inv;
  }

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
