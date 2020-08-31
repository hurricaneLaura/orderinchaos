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
    inventory.add("manuscript");
    inventory.add("scull");
    inventory.add("key");
  }

  public List<String> getInventory() {
    return inventory;
  }

  public void setInventory(List<String> inventory) {
    this.inventory = inventory;
  }

  public void addItem(String item) {
    inventory.add(item);
    System.out.println(item + " taken!");
  }

  public void removeItem(String item) {
    // DONE: CHECK IF INVENTORY CONTAINS ITEM.
    if (inventory.contains(item)) {
      inventory.remove(item);
    } else {
      System.out.println("That item is not in your inventory");
    }
  }

  // TODO: use getInventory instead of directly accessing inventory
  public void showInventory() {
    List<String> currentInv = getInventory();
    if (currentInv != null && currentInv.isEmpty()) {
      System.out.println("There are 0 items in your inventory");
      System.out.println("Explore items and enter 'take' to store in inventory");
    } else {
      for(String item : currentInv) {
        System.out.println(item);
      }
    }
  }
}
