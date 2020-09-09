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
    Util.PRINT_TEXT_FILE("banner.txt", 300);
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
    PRINT_TEXT_FILE("intro.txt", 300);
    Scanner wait = new Scanner(System.in);
    Util.blue("Press ENTER to continue...");
    String userInput = wait.nextLine();
  }

  // TODO: implement load game feature
  private void loadGame() {
    System.out.println("Loading...");
  }

  // TODO: display how to play guide
  private void loadHowToPlay() {
    PRINT_TEXT_FILE("instruction.txt", 0);
    Scanner wait = new Scanner(System.in);
    System.out.print("Press ENTER to go back...");
    String userInput = wait.nextLine();
  }

  public void roomEvents(Player player, List<Room> roomList) {
    for (Room room : roomList) {
      STREAM_DISPLAY(room.getDescription().stream(), 0);
      room.presentRiddle();
      changePhase(room, player);
    }
    end();
  }

  public Player changePhase(Room room, Player player) {
    while (room.isCleared() != true) {
      // TODO: look into putting these commands into a text file, their own class, or a higher scope
      List<String> validInput = Arrays.asList("LOOK", "READ", "TAKE", "DROP", "CHECK");
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
      // TODO: check if remove() returns value
      Riddle riddle = riddles.remove(0);
      roomList.add(new Room(tempArray[0], tempArray[1], riddle));
    });
    for (Room room : roomList) {
      Inventory roomInv = room.getInventory();
      roomInv.addItem(new Item.Builder("SCROLL")
        .withDescription("This look very important")
        .withReadText("After his invitation to Heaven, the Monkey King believed that he was receiving an honorable place amongst the gods.\nHowever, upon his arrival, he was told that he would become ‘Protector of the Horses’: a lowly stable boy.")
        .withIsKey(true)
        .withCanRead(true)
        .build()
      );
    }
  }

  public List<Room> getRoomList() {
    return Collections.unmodifiableList(roomList);
  }

  public void actionDelegator(String[] userInput, Room room, Player player) {
    switch (userInput[0]) {
      case "TAKE":
        take(userInput[1], room, player);
        break;
      case "DROP":
        drop(userInput[1], player, room);
        break;
      case "LOOK":
        look(userInput[1], room, player);
        break;
      case "READ":
        read(userInput[1], room, player);
        break;
      case "CHECK":
        check(userInput[1], room, player);
        break;
      default:
        break;
    }
  }

  public void check(String input, Room room, Player player) {
    // print current location and current inventory
    if ("STATUS".equals(input)) {
      System.out.println(room.getName());
      System.out.println(player.getInventory().toString());
    }
  }

  public void look(String input, Room room, Player player) {
    String result = "I don't see anything";
    if ("AROUND".equals(input)) {
      result = ("You are in the " + room.getName() + ". You can see a few items: " + room.getInventory().getItems().toString());
    } else {
      Item playerItem = player.getInventory().getItem(input);
      Item roomItem = room.getInventory().getItem(input);
      if (playerItem != null) {
        result = playerItem.getDescription();
      } else if (roomItem != null) {
        result = roomItem.getDescription();
      }
    }
    System.out.println(result);

  }

  public void read(String input, Room room, Player player) {
    String result = "I have heard the sound of one hand clapping, yet this I cannot comprehend.";
    Item playerItem = player.getInventory().getItem(input);
    Item roomItem = room.getInventory().getItem(input);
    if (playerItem != null && playerItem.canRead()) {
      PRINT_TEXT_FILE("scroll.txt", 200);
      result = playerItem.getReadText();
    } else if (roomItem != null && roomItem.canRead()) {
      if (roomItem.canCarry()) {
        result = "I can't reach that!";
      } else {
        result = roomItem.getReadText();
      }
    }
    System.out.println(result);

  }

  public void take(String itemName, Room room, Player player) {
    if (swapItems(itemName, room.getInventory(), player.getInventory())) {
      System.out.println(itemName + " taken!");
    } else {
      System.out.println("There aren't any to take!");
    }
  }

  public void drop(String itemName, Player player, Room room) {
    if (swapItems(itemName, player.getInventory(), room.getInventory())) {
      System.out.println(itemName + " dropped!");
    } else {
      System.out.println("You don't have any to drop.");
    }
  }
  // drop(a,b,c);

  public boolean swapItems(String itemName, Inventory fromInv, Inventory toInv) {
    // Check that item exists in fromInv
    // Check that item can be carried
    //
    if (fromInv.getItem(itemName) != null) {
      Item swappedItem = fromInv.getItem(itemName);
      fromInv.removeItem(swappedItem);
      toInv.addItem(swappedItem);
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

  public Player getPlayer() {
    return player;
  }

  private static List<Riddle> riddles = new ArrayList<>();

  static {
    Riddle riddle = new Riddle("Its the answer to every question you've got. When you think you have it, you have it not. What is the answer?", "knowledge");
    riddles.add(riddle);
    riddle = new Riddle("What word is right when pronounced wrong, but is wrong when pronounced right?", "wrong");
    riddles.add(riddle);
    riddle = new Riddle("What has six faces, but does not wear makeup, has twenty-one eyes, but cannot see? What is it?", "dice");
    riddles.add(riddle);
    riddle = new Riddle("I am not alive, but I grow; I don't have lungs, but I need air; I don't have a mouth, but water kills me. What am I?", "fire");
    riddles.add(riddle);
    riddle = new Riddle("What runs around the whole yard without moving?", "fence");
    riddles.add(riddle);
    riddle = new Riddle("Who is that with a neck and no head, two arms and no hands?  What is it?", "shirt");
    riddles.add(riddle);
    riddle = new Riddle("David's father has three sons: Snap, Crackle, and _____?", "david");
    riddles.add(riddle);
    riddle = new Riddle("What belongs to you, but other people use it more than you?", "name");
    riddles.add(riddle);
    riddle = new Riddle("I make two people out of one. What am I?", "mirror");
    riddles.add(riddle);
    Collections.shuffle(riddles);
  }

//  public void verifyRiddle(Room room) {
//    Util.INPUT_HANDLER("yes");
//    System.out.println("do you want to attempt this Riddle? \nyes \nno");
//    if (Util.INPUT_HANDLER("yes")) {
//      room.presentRiddle();
//    } else {
//      System.out.println("some 'user' thought");
//    }
//  }
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


