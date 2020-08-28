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
   //TODO: REMOVE ITEM FROM ROOM AND PUT INTO PLAYER INVENTORY. AND VICE-VERCA
// public void transferItem(String item,Item  addItem,Item removeItem) {
  // currentRoom.getInventory().contains(item);
  // player.getInventory().contains(item);
      // CONSIDER SCOPE AND NULL CHECK

//    fromlist.remove(t);
//    tolistt.add(t);
//  }
}
