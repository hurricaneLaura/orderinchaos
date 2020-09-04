package com.orderinchaos;

import java.util.*;
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
    printBanner();
    int userInput = INPUT_HANDLER();
    CLEAR_SCREEN();
    switch (userInput) {
      case 1:
        newGame();
        break;
      case 2:
        loadGame();
        break;
      case 3:
        loadHowToPlay();
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
      STREAM_DISPLAY(room.getDescription().stream(), 0);
      changePhase(room, player);
    }
    end();
  }

  public Player changePhase(Room room, Player player) {
    while (room.isCleared() != true) {
      // TODO: look into putting these commands into a text file, their own class, or a higher scope
      List<String> validInput = Arrays.asList("LOOK", "READ", "TAKE", "DROP");
      String[] userInput = INPUT_HANDLER(validInput);
      actionDelegator(userInput, room, player);
      room.setCleared();
    }
    return player;
  }

  public void loadRooms() {
    Stream<String> descriptions = Util.TEXT_READER("room_descriptions.txt");
    descriptions.forEach(line -> {
      String[] tempArray = line.split("[|]");
      roomList.add(new Room(tempArray[0], tempArray[1], tempArray[2]));
    });
  }

  public List<Room> getRoomList(){
    return Collections.unmodifiableList(roomList);
  }

  public void printBanner() {
    Util.STREAM_DISPLAY(Util.TEXT_READER("banner.txt"), 300);
  }

  public void actionDelegator(String[] userInput, Room room, Player player) {
    switch (userInput[0]) {
      case "TAKE":
        // Delete from room inv and add to player inv
        if (swapItems(userInput[1],room.getInventory(), player.getInventory())) {
          System.out.println(userInput[1] + " taken!");
        } else {
          System.out.println("There aren't any to take!");
        }
        break;
      case "DROP":
        // Delete from player inv and add to room inv
        if (swapItems(userInput[1],player.getInventory(), room.getInventory())) {
          System.out.println(userInput[1] + " dropped!");
        } else {
          System.out.println("You don't have any to drop.");
        }
        break;
      case "LOOK":
        // TODO: implement look and read methods
        System.out.println("Looks good");
        break;
      case "READ":
        System.out.println("Maybe I'll read later...");
        break;
      default:
        break;
    }
  }

    public boolean swapItems(String item, Inventory fromInv, Inventory toInv) {
      if (fromInv.getItems().contains(item)) {
        fromInv.removeItem(item);
        toInv.addItem(item);
        return true;
      } else {
        return false;
      }
    }

  // TODO: validate user input
  public void end() {
    Scanner endSc = new Scanner(System.in);
    System.out.println("Success! you have rescued the monkey king. With the scrolls you both can go forth and conquer the demons - bringing peace to the world once again." + "\n" + "**Based on a true story**");
    System.out.println("\n" + "What would you like to do?" + "\n" + ">play again" + "\n" + ">quit");
    String Question = endSc.nextLine();
    if (Question.toLowerCase().contains("quit")) {
      System.exit(0);
    } else {
      if (Question.toLowerCase().contains("play again")) {
        newGame();
      } else {
        System.out.println("You have failed us all, try again later");
      }
    }
  }
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


