package com.orderinchaos;

import java.util.*;
import java.util.stream.Stream;

import static com.orderinchaos.Util.*;

public class Game {
  private List<Room> roomList = new ArrayList<>();
  private Player player = new Player("The Chosen One");

  public void runGame() {
    LOAD_SCREEN();
    mainMenu();
    // TODO: start adventure phase
  }

  private void mainMenu() {
    // DONE: load start screen (new game, load game, how to play)
    CLEAR_SCREEN();
    Util.PRINT_TEXT_FILE("banner.txt", 300);
    String[] prompt = {"New Game", "Load Game", "How to Play"};
    int userInput = INPUT_HANDLER(prompt);
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
        mainMenu();
        break;
      default:
        runGame();
        break;
    }
  }

  private void newGame() {
    CLEAR_SCREEN();
    intro();
    loadRooms();
    roomEvents(player, roomList);
  }

  private void intro() {
    PRINT_TEXT_FILE("intro.txt", 300);
    Scanner wait = new Scanner(System.in);
    Util.green("Press ENTER to continue...");
    String userInput = wait.nextLine();
    CLEAR_SCREEN();
  }

  // TODO: implement load game feature


  private void loadGame() {
    LOAD_SCREEN();
    // TODO: need to refactor this part to display saved list
    Stream<String> games = TEXT_READER("/save/game.txt");
    List<String> savedGames = new ArrayList<>();
    games.forEach(game -> savedGames.add(game));
    String gameSelected = savedGames.get(0);

    // loadGame
    String[] gameInfo = gameSelected.split("[|]");
    System.out.println("Game saved on " + gameInfo[0] + " successfully loaded" + "\n");
    System.out.println("Welcome back, " + gameInfo[1]);
    Player player = new Player(gameInfo[1]);
    loadRooms();
    roomEvents(player, roomList);
  }

  // TODO: display how to play guide
  private void loadHowToPlay() {
    PRINT_TEXT_FILE("instruction.txt", 0);
    Scanner wait = new Scanner(System.in);
    Util.green("Press ENTER to go back...");
    String userInput = wait.nextLine();
    CLEAR_SCREEN();
  }

  public void roomEvents(Player player, List<Room> roomList) {
    for (Room room : roomList) {
      CLEAR_SCREEN();
      STREAM_DISPLAY(room.getDescription().stream(), 0);
      randomize(room);
      if ("Western Mountains".equals(room.getName())) {
        InventoryPuzzle puzzle = new InventoryPuzzle();
        puzzle.resetPuzzle();
        puzzle.runRoom();
      }
      changePhase(room, player);
      if (roomList.indexOf(room) == (roomList.size() - 1)){
        end(true);
      }
    }
  }

  public Player changePhase(Room room, Player player) {
    while (room.isCleared() != true) {
      // TODO: look into putting these commands into a text file, their own class, or a higher scope
      List<String> validInput = Arrays.asList("LOOK", "READ", "TAKE", "DROP", "CHECK", "SAVE");
      String[] userInput = INPUT_HANDLER(validInput);
      // TODO: print user input iot confirm : Do we want to display native vs synonym cmd
      String input = userInput[2];
      System.out.println((input + " " + userInput[1]).toUpperCase());
      ActionUtil.actionDelegator(userInput, room, player);
      room.setCleared();
    }
    return player;
  }
  // TODO: modify to take one arg, fileName
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

  // TODO: validate user input
  public void end(boolean didWin) {
    String[] playAgain = {"play again", "quit"};
    if (didWin) {
      System.out.println("Success! you have rescued the monkey king. With the scrolls you both can go forth and conquer the demons - bringing peace to the world once again." + "\n" + "**Based on a true story**");
    } else {
      System.out.println("You have failed us all...");
    }
    System.out.println("\n" + "What would you like to do?");
    int userInput = INPUT_HANDLER(playAgain);
    switch (userInput) {
      case 1:
        newGame();
        break;
      case 2:
        System.exit(0);
        break;
      default:
        System.out.println("It's neither play again nor quit...");
        break;
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

  private void randomize(Room room) {
    double rand = Math.random();
    if (rand < 0.5) {
      verifyRiddle(room);
    }
  }

  private void verifyRiddle(Room room) {
    String[] verifyRiddle = {"yes", "no"};
    System.out.println("do you want to attempt this Riddle?");
    int input = Util.INPUT_HANDLER(verifyRiddle);
    switch (input) {
      case 1:
        room.presentRiddle();
        break;
      case 2:
        System.out.println("Come back when you are ready");
        break;
      default:
        break;
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


