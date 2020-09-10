package com.orderinchaos;

import java.util.Date;

import static com.orderinchaos.Util.PRINT_TEXT_FILE;
import static com.orderinchaos.Util.SAVE_GAME;

public class ActionUtil {

    public static void actionDelegator(String[] userInput, Room room, Player player) {
        switch (userInput[0]) {
            case "TAKE": take(userInput[1], room, player);
                break;
            case "DROP": drop(userInput[1],player, room);
                break;
            case "LOOK": look(userInput[1], room, player);
                break;
            case "READ": read(userInput[1], room, player);
                break;
            case "CHECK":
                check(userInput[1],room, player);
                break;
            case "SAVE":
                saveGame(userInput[1], room, player);
                break;
            default:
                break;
        }
    }


    public static void check(String input, Room room, Player player) {
        // print current location and current inventory
        if ("STATUS".equals(input)) {
            Util.blue(room.getName());
            Util.red(player.getInventory().toString());
        }
    }
    public static void look(String input, Room room, Player player) {
        String result = "I don't see anything";
        if ("AROUND".equals(input)) {
            result = ("You are in the " +room.getName() + ". You can see a few items: " + room.getInventory().getItems().toString());
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

    public static void read(String input, Room room, Player player) {
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

    public static void take(String itemName, Room room, Player player) {
        if (swapItems(itemName,room.getInventory(), player.getInventory())) {
            System.out.println(itemName + " taken!");
        } else {
            System.out.println("There aren't any to take!");
        }
    }

    public static void drop(String itemName, Player player, Room room) {
        if (swapItems(itemName, player.getInventory(), room.getInventory())) {
            System.out.println(itemName + " dropped!");
        } else {
            System.out.println("You don't have any to drop.");
        }
    }
    public static boolean swapItems(String itemName, Inventory fromInv, Inventory toInv) {
        if (fromInv.getItem(itemName) != null && fromInv.getItem(itemName).canCarry() == true) {
            Item swappedItem = fromInv.getItem(itemName);
            fromInv.removeItem(swappedItem);
            toInv.addItem(swappedItem);
            return true;
        } else {
            return false;
        }
    }

    public static StringBuffer saveGame(String game, Room room, Player player) {
        StringBuffer currentGame = new StringBuffer(new Date().toString());
        if (game.toUpperCase().equals("GAME")) {
            String delimiter = "|";
            currentGame.append(delimiter.concat(player.getName()));
            if (player.getInventory().getItems().size() > 0) {
                currentGame.append(delimiter.concat(player.getInventory().getItems().toString()));
            } else {
                currentGame.append(delimiter);
            }
            currentGame.append(delimiter.concat(room.getName()));
            if (room.getInventory().getItems().size() > 0) {
                currentGame.append(delimiter.concat(room.getInventory().getItems().toString()));
            } else {
                currentGame.append(delimiter);
            }
            SAVE_GAME(currentGame);
        } else {
            System.out.println("I'm not sure how to save " + game.toUpperCase());
        }
        // return StringBuffer for test purposes
        return currentGame;
    }

    // Requisite Puzzle Methods
    public static void puzzleActionDelegator(String[] userInput, Room room, Player player, InventoryPuzzle.PuzzleThread puzzleThread) {
        switch (userInput[0]) {
            case "WARD": ward(userInput[1], room, player);
                break;
            case "NOSH": nosh(userInput[1], room, player);
                break;
            case "REMEDY": remedy(userInput[1], room, player);;
                break;
            case "TAKE": preventTake(player);
                break;
            case "DROP": drop(userInput[1],player, room);
                break;
            case "CHECK": check(userInput[1],room, player);
                break;
            default:
                break;
        }
    }

    public static void ward(String userInput, Room room, Player player) {
        if (player.getInventory().getItem("ROBES") != null) {
            System.out.println("The " + userInput + " shield you from exposure!");
        } else {
            displayImpossibleAction();
        }
    }
    public static void nosh(String userInput, Room room, Player player) {
        if (player.getInventory().getItem("ALMS") != null) {
            System.out.println("The " + userInput + " restores your energy!");
        } else {
            displayImpossibleAction();
        }
    }
    public static void remedy(String userInput, Room room, Player player) {
        if (player.getInventory().getItem("MEDICINE") != null) {
            System.out.println("The " + userInput + " cures your ailments!");
        } else {
            displayImpossibleAction();
        }
    }

    public static void displayImpossibleAction() {
        System.out.println("My mind is not powerful enough to perform that task without the proper item.");
    }
    public static void preventTake(Player player) {
        System.out.println("I am already carrying " + player.getInventory().getItems());
    }



}
