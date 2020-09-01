package com.orderinchaos;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
  private List<String> inventory = new ArrayList<>();

  public Inventory () {

  }

  // TODO: set inventory create accessor methods
  public Inventory(List<String> inventory) {
    setInventory(inventory);
  }

  public List<String> getItems() {
    return inventory;
  }

  public void setInventory(List<String> inventory) {
    this.inventory = inventory;
  }

  public void addItem(String item) {
    inventory.add(item);
  }

  public void removeItem(String item) {
    // DONE: CHECK IF INVENTORY CONTAINS ITEM.
      inventory.remove(item);
  }

  // TODO: use getInventory instead of directly accessing inventory
  public void showInventory() {
    List<String> currentInv = getItems();
    if (currentInv != null && currentInv.isEmpty()) {
      System.out.println("There are 0 items in your inventory");
      System.out.println("Explore items and enter 'take' to store in inventory");
    } else {
      for(String item : currentInv) {
        System.out.println(item);
      }
    }
  }

  @Override
  public String toString() {
    return "Inventory{" +
            "inventory=" + inventory +
            '}';
  }
}
