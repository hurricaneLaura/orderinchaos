package com.orderinchaos;

import java.util.ArrayList;
import java.util.List;

public class Game {
  private Room currentRoom = new Room("dungeon", "change later");
  private List<Room> roomList = new ArrayList<>();
  private List<Item> inventory = new ArrayList<>();
  private Player player = new Player("The Chosen One", inventory);

  private void roomEvents(Player player, List<Room> roomList) {
    for (Room room : roomList) {
      System.out.println(room.getDescription());
      changePhase(room, player);
    }
  }

  public Player changePhase(Room room, Player player) {
    while (room.isCleared() != true) {
      // Check each obstacle in the room - if all are cleared, setCleared(true)
      room.setCleared(true);
    }
    return player;
  }

  // present user with a start screen
  // they start a new game
  
  // init inventory - check, no items
  // prompt user with input for their name - check, hardcoded
  // init player with that name and the inventory
  // init a list of rooms with their own inventories
  // loop through each room
  // send a player into the room
  // print out a description of the room environment
  // encapsulate player in a loop until obstacles are cleared
    // player attempts to move and perform actions
    // if any are successful, change obstacles to cleared
    // repeat for each room
  // present user with a win/loss screen
}
