package com.orderinchaos;

import java.util.ArrayList;
import java.util.List;

public class Game {
  private Room currentRoom = new Room("dungeon", "change later");
  private List<Room> roomList = new ArrayList<>();
  // [room1, room2, room3, ...]

  public void roomEvents() {
    for (Room room : roomList) {
      System.out.println(room.getDescription());
    }
  }
}
