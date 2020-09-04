package com.orderinchaos;

import java.util.ArrayList;
import java.util.List;

import static com.orderinchaos.Util.*;

public class Room {
  private String name;
  private List<String> description = new ArrayList<>();
  private boolean isCleared = false;
  private Inventory readItems = new Inventory();
  private Inventory inventory = new Inventory();

  // CTOR
  public Room(String name, String description) {
    setName(name);
    setDescription(description);
  }

  // ACCESSOR METHODS
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getDescription() {
    return description;
  }

  public void setDescription(String description) {
    String[] lines = description.split("[.]");
    for (String line : lines) {
      this.description.add(line.trim().concat("."));
    }
  }


  public boolean isCleared() {
    return isCleared;
  }

  // A room is considered clear when all items have been taken, no enemies exist, no puzzles are left to solve
  public void setCleared() {
    // true values for clear

    boolean sizeCleared = (inventory.getItems().size() == 0);
    boolean keysCleared = ( ! inventory.checkKeys() );

    List<Boolean> clearedConditions = new ArrayList<>(List.of(sizeCleared,keysCleared));

    if (clearedConditions.contains(false)) {
      this.isCleared = false;
    } else {
      this.isCleared = true;
    }

  }

  public Inventory getReadItems() {
    return readItems;
  }

  public void setReadItems(Inventory readItems) {
    this.readItems = readItems;
  }

  public void loadReadItems() {
  }
  public Inventory getInventory() {
    return inventory;
  }
}
