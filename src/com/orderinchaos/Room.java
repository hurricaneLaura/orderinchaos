package com.orderinchaos;

public class Room {
  private String name;
  private String description;
  private boolean isCleared = false;

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
    isCleared = cleared;
  }


}
