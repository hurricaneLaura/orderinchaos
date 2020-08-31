package com.orderinchaos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import static com.orderinchaos.Util.*;

public class Game {
  private Room currentRoom;
  private List<Room> roomList = new ArrayList<>();
   private Player player = new Player("The Chosen One");

  // Load roomList


  public void runGame() {
    mainMenu();
    // TODO: start adventure phase
  }

  private void mainMenu() {
    // DONE: load start screen (new game, load game, how to play)
    int userInput = INPUT_HANDLER();
    CLEAR_SCREEN();
    switch (userInput) {
      case 1: newGame();
        break;
      case 2: loadGame();
        break;
      case 3: loadHowToPlay();
        break;
      // TODO: Asking user input again, possible infinite loop/handled at higher level
      default:
        runGame();
        break;
    }
  }

  private void newGame() {
    intro();
    loadRooms();
    roomEvents(player, roomList);
  }

  private void intro() {
    Stream<String> intro = Util.TEXT_READER("intro.txt");
    STREAM_DISPLAY(intro, 300);
    Scanner wait = new Scanner(System.in);
    System.out.print("Press ENTER to continue...");
    String userInput = wait.nextLine();
  }

  // TODO: implement load game feature
  private void loadGame() {
    System.out.println("Loading...");
  }

  // TODO: display how to play guide
  private void loadHowToPlay() {
    Stream<String> instruction = Util.TEXT_READER("instruction.txt");
    STREAM_DISPLAY(instruction, 0);
    Scanner wait = new Scanner(System.in);
    System.out.print("Press ENTER to go back...");
    String userInput = wait.nextLine();
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

