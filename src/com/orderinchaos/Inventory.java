package com.orderinchaos;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
  private List<Item> items = new ArrayList<>();

  // CONSTRUCTORS
  public Inventory () {

  }

  // TODO: set inventory create accessor methods
  public Inventory(List<Item> items) {
    setItems(items);
  }

  public Item getItem(String name) {
    Item result = null;
    for (Item item : items) {
      if (item.getName().equals(name)) {
        result = item;
      }
    }
    return result;
  }

  // Used for room clear condition. Just checking if one or more items in inventory are keys.
  public boolean checkKeys() {
    boolean result = false;
    if ( ! items.isEmpty() ) {
      for (Item item : items) {
        if (item.isKey()) {
          result = true;
        }
      }
    }
    return result;
  }

  public List<Item> getItems() {
    return items;
  }

  public void setItems(List<Item> items) {
    this.items = items;
  }
  public void clearItems() {
    this.items.clear();
  }

  public void addItem(Item item) {
    items.add(item);
  }

  public void removeItem(Item item) {
    // DONE: CHECK IF INVENTORY CONTAINS ITEM.
      items.remove(item);
  }

  // TODO: use getInventory instead of directly accessing inventory
  public void showInventory() {
    List<Item> currentInv = getItems();
    if (currentInv != null && currentInv.isEmpty()) {
      System.out.println("There are 0 items in your inventory");
      System.out.println("Explore items and enter 'take' to store in inventory");
    } else {
      for(Item item : currentInv) {
        System.out.println(item);
      }
    }
  }

  @Override
  public String toString() {
    return "Player Inventory: " + getItems();
  }
}
