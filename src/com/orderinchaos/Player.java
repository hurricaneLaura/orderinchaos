package com.orderinchaos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player {
  // FIELDS AKA ATTRIBUTES
  String name;
  private List<String> inventory = new ArrayList<>();

  // CONSTRUCTORS
  public Player(String name) {
    this.name = name;
  }

  public Player(String name, List<String> inv) {
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

  private List<String> getInventory() {
    return inventory;
  }

  private void setInventory(List<String> inv) {
    this.inventory = inv;
  }

  // BUSINESS METHODS
  public void takeItem(Room room, String item) {
      inventory.add(item);
      System.out.println(item + " taken!");
    }

  public void dropItem(String item) {
    // DONE: CHECK IF PLAYER.INVENTORY CONTAINS ITEM.
    if (inventory.contains(item)) {
      inventory.remove(item);
    } else {
      System.out.println("That item is not in your inventory");
    }
  }

  public String showInventory(ArrayList<String> inventory) {
    if (inventory != null && inventory.isEmpty()) {
      System.out.println("There are 0 items in your inventory" + "\n");
      System.out.println("Explore items and enter 'take' to store in inventory");
    } else {
      System.out.println(inventory);
      }
    return null;
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
