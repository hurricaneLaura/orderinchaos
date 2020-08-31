package com.orderinchaos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import static com.orderinchaos.Util.*;

public class Room {
  private String name;
  private String description;
  private boolean isCleared = false;
  private Inventory inventory = new Inventory();

  // CTOR
  public Room(String name, String description) {
    setName(name);
    setDescription(description);
  }

  // BUSINESS METHODS

  // ACCESSOR METHODS
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }


  public boolean isCleared() {
    return isCleared;
  }

  // A room is considered clear when all items have been taken, no enemies exist, no puzzles are left to solve
  public void setCleared(boolean cleared) {
    if (inventory.getInventory().size() != 0) {
      this.isCleared = false;
    } else {
      this.isCleared = true;
    }
  }

  public Inventory getInventory() {
    return inventory;
  }


}
