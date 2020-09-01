package com.orderinchaos;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.List;

public class Room {
  private String name;
  private String description;
  private boolean isCleared = false;
  private Inventory readItems = new Inventory();


  // CONSTRUCTOR
  public Room(String name, String description) {
    setName(name);
    setDescription(description);
  }

  public Room(String name, String description, Inventory readItems) {
    this.name = name;
    this.description = description;
    this.readItems = readItems;
  }

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

  public void setCleared(boolean cleared) {
    this.isCleared = cleared;
  }

  public Inventory getReadItems() {
    return readItems;
  }

  public void setReadItems(Inventory readItems) {
    this.readItems = readItems;
  }

  public void loadReadItems() {
  }
}
