package com.orderinchaos;

import java.util.Objects;

public class Player {
  // FIELDS
  private String name;
  private Inventory inventory = new Inventory();

  // CONSTRUCTORS
  public Player(String name) {
    setName(name);
  }

  public Player(String name, Inventory inventory) {
    this(name);
    setInventory(inventory);
  }

  // GETTERS/SETTERS
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
  public Inventory getInventory(){
    return inventory;
  }

  private void setInventory(Inventory inventory) {
    this.inventory = inventory;
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
