package com.orderinchaos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.orderinchaos.Util.INPUT_HANDLER;

public class InventoryPuzzle {
    private boolean isCleared = false;
    private String[] userInput = {"",""};
    Inventory puzzleInventory;
    Inventory playerInventory;
    private void resetPuzzle() {
        playerInventory = new Inventory();
        puzzleInventory = new Inventory();
        puzzleInventory.addItem(PuzzleItems.robes());
        puzzleInventory.addItem(PuzzleItems.alms());
        puzzleInventory.addItem(PuzzleItems.medicine());
    }
    private void runRoom() {
        Task taskRunner = new Task(5);
        // taskRunner.setTask();
        taskRunner.start();
        playerLoop(taskRunner);
        // Player action;
    }

    public boolean getCleared() {
        return isCleared;
    }
    public void setCleared() {
        if (isCleared) {
            isCleared = false;
        } else {
            isCleared = true;
        }
    }

    private void playerLoop(Task task) {
        List<String> validInput = Arrays.asList("WARD", "NOSH", "REMEDY", "DROP", "CHECK");
        while ( getCleared() != true) {
            userInput = INPUT_HANDLER(validInput);
            System.out.println(userInput[0] + " " + userInput[1]);
            if ("NOSH".equals(userInput[0]) && "ALMS".equals(userInput[1])) {
                setCleared();
                System.out.println("you have cleared the puzzle !!!!");
                task.interrupt();
            }
        }
    }
    class Task extends Thread {
        private int numRounds = 5;
        public Task(int numRounds) {
            this.numRounds = numRounds;
        }
        @Override
        public void run() {
            System.out.println("Provide Task Hint!");
            for (int i = numRounds; i > 0; i--) {
//                System.out.println();
                try {
                    Thread.sleep(10000);
                    if (i < 3) {
                        System.out.println("\nDusk is less than a Vighaṭi away...");
                    } else {
                        System.out.println("\nDarkness comes. " + (4 * i * 2) + " Lipta remaining...");
                    }
                }
                catch (InterruptedException e) {
                    // Intentionally not logging error because console game
                    break;
                }
            }
            System.out.println("You have completed the puzzle");
        }
    }

    public static void main(String[] args) {
        InventoryPuzzle puzzle = new InventoryPuzzle();
        puzzle.resetPuzzle();
        puzzle.runRoom();
    }

    // The puzzle is reset with new inventories and three items

    // Player interacts with something that kicks off a puzzle

    // The puzzle then kicks off a thread
       // THREAD
         /*
            Receives a random task (random task has a test and an evaluation method
            displays the task
            starts a timer
            evaluates if the condition is met
          */
        // The thread displays a task
        // That thread will start a timer
        // At the end of the timer it will check to see if the condition was met

        // While the thread is running, the player must go complete the task that was assigned
            /*
                 Player must go pickup the item that corresponds to the hint
                 Player can only hold one item at a time
                 Player can then cast the command that corresponds to the item
             */
    // Three things can happen and the player must go get the correct item to ward off each thing
    // What will the player interact with?
    // The player can only hold one item at a time
    // The player takes by interacting with a statue
    // TWhen the player drops an item, it is returned to the puzzle inventory
    // If the player is holding an item when the timer is up, he wins






    //List<String> validInput = Arrays.asList("WARD", "NOSH", "REMEDY", "DROP", "CHECK");

    //INPUT_HANDLER(LIST

//    puzzleInventory.addItem(PuzzleItems.robes())


    // Create a puzzle that uses inventory as its main schtick

    // Start with a puzzle inventory and a player inventory
    // Use a text handler with a special list of valid commands

    // robes - WARD: I use it simply to ward off cold, to ward off heat, to ward off the touch of flies, mosquitoes, simply for the purpose of covering
    //almsfood - NOSH: I use it not playfully, but simply for the survival and continuance of this body and for ending its afflictions. Thus I will maintain myself, be blameless, and live in comfort.
    //medicinal requisites for curing the sick - REMEDY: I use them simply to remedy pains or remedy illnesses that have arisen and for the maximum freedom from disease.
}
