package com.orderinchaos;

import java.util.ArrayList;
import java.util.List;

public class Game {
  private Room currentRoom;
  private List<Room> roomList = new ArrayList<>();
  private List<String> inventory = new ArrayList<>();
  private Player player = new Player("The Chosen One", inventory);

  // Load roomList


  public void runGame() {
    // TODO: load start screen (new game, load game, how to play)
    // TODO: INPUT_HANDLER()
    // TODO: start adventure phase
    loadRooms();
    roomEvents(player, roomList);


  }

  public void roomEvents(Player player, List<Room> roomList) {
    for (Room room : roomList) {
      System.out.println(room.getDescription());
      changePhase(room, player);

    }
  }

  public Player changePhase(Room room, Player player) {
    while (room.isCleared() != true) {
      // TODO: Check each obstacle in the room - if all are cleared, setCleared(true)
      room.setCleared(true);
    }
    return player;
  }

  public void loadRooms() {
    roomList.add(new Room("Western Mountains", "You’re standing and the edge of a mountain, looking out to the jagged rocks below. you know your mission, you turn around and head to the a small building in the distance."));
    roomList.add(new Room("The Edge of Nowhere", "You stand at the edge of a shallow are in a cluster of roofless,homes, apparently part of small . Abandoned items litter the ground suggesting the residents left in a hurry. An ominous feeling suddenly suddenly hits you."));

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
   //TODO: REMOVE ITEM FROM ROOM AND PUT INTO PLAYER INVENTORY. AND VICE-VERCA
// public void transferItem(String item,Item  addItem,Item removeItem) {
  // currentRoom.getInventory().contains(item);
  // player.getInventory().contains(item);
      // CONSIDER SCOPE AND NULL CHECK

//    fromlist.remove(t);
//    tolistt.add(t);
//  }
}
