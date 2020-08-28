package com.orderinchaos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player {
  // FIELDS
  String name;
  private List<String> inventory = new ArrayList<>();


  public Player(String name) {

    this.name = name;
  }

  // CONSTRUCTORS
  public Player(String name, List<String> inv){
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
  private void addInventory(String item) {
    inventory.add(item);
  }

  // BUSINESS METHODS
  public void takeItem(String item) {
      addInventory(item);
      System.out.println(item + " taken!");
  }

  public String dropItem(String item) {
    //TODO: CHECK IF PLAYER.INVENTORY CONTAINS ITEM.
    // IF TRUE, REMOVE THE ITEM FROM PLAYER INVENTORY AND RETURN ITEM.
    return item;
    // IF FALSE, RETURN ERROR MESSAGE.
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
