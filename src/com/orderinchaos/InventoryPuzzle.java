package com.orderinchaos;

import java.util.*;

import static com.orderinchaos.Util.CLEAR_SCREEN;
import static com.orderinchaos.Util.INPUT_HANDLER;

public class InventoryPuzzle {
    public static void main(String[] args) {
        InventoryPuzzle puzzle = new InventoryPuzzle();
        puzzle.resetPuzzle();
        puzzle.runRoom();
    }
    private boolean isCleared = false;
    private String[] userInput = {"",""};
    private Room puzzleRoom = new Room("Chamber of Requisites", "");
    private Player puzzlePlayer = new Player("Puzzle Monk");
    private List<String[]> solutions = new ArrayList<>();
    private String[] solution = {"NOSH","ALMS"};

    private void resetPuzzle() {
        puzzleRoom.setIsPuzzle(true);
        puzzlePlayer.getInventory().clearItems();
        Inventory puzzleInventory = puzzleRoom.getInventory();
        puzzleInventory.clearItems();
        puzzleInventory.addItem(PuzzleItems.robes());
        puzzleInventory.addItem(PuzzleItems.alms());
        puzzleInventory.addItem(PuzzleItems.medicine());
        puzzleInventory.addItem(PuzzleItems.knapsack());
    }

    private void runRoom() {
        // Three rounds of puzzle, player gets less time to complete the task each round
        randomizeSolutionsList();
        for (int i=3; i>0; i--) {
            // TODO: generate a random item to initialize puzzlethread
            // Reassign solution in each loop
            solution = solutions.get(i-1);
            // Make a new thread with the new solution for prompting player with hint
            PuzzleThread puzzleThreadRunner = new PuzzleThread((i+1),solution);
            if (puzzlePlayer.getInventory().getItem("KNAPSACK") == null) {
                activateCountdown();
                setCanCarryRequisites(puzzleRoom.getInventory());
            }
            Scanner wait = new Scanner(System.in);
            System.out.println("You have chosen a monk's life, thus you must honor these Requisites.");
            System.out.print("Center yourself, then press ENTER to continue...");
            String userInput = wait.nextLine();
            CLEAR_SCREEN();
            puzzleThreadRunner.start();
            playerLoop(puzzleThreadRunner);
            resetPuzzle();
            System.out.println();
            System.out.println("The Requisites KNAPSACK has returned to it's place.");
        }
        System.out.println("You Have Mastered All The REQUISITES");
        // Player action;
    }

    public void randomizeSolutionsList() {
        // use math random 1-3 to generate a random number
        ArrayList<String[]> source = new ArrayList<>();
        source.add(new String[]{"WARD","ROBES"});
        source.add(new String[]{"NOSH","ALMS"});
        source.add(new String[]{"REMEDY","MEDICINE"});

        Random random = new Random();
        int rand;
        for (int i=3; i>0; i--) {
            rand = random.nextInt(i - 0) + 0;
            String[] temp = source.remove(rand);
            solutions.add(temp);
        }

    }


    public boolean getCleared() {
        return isCleared;
    }
    public void setCleared(boolean bool) {
        this.isCleared = bool;
    }

    private void activateCountdown() {
        while ( puzzlePlayer.getInventory().getItem("KNAPSACK") == null) {
            List<String> validInput = Arrays.asList("LOOK", "READ", "TAKE", "DROP", "CHECK");
            userInput = INPUT_HANDLER(validInput);
            ActionUtil.actionDelegator(userInput, puzzleRoom, puzzlePlayer);
        }
    }
    private void setCanCarryRequisites(Inventory inventory) {
        inventory.getItem("ROBES").setCanCarry(true);
        inventory.getItem("ALMS").setCanCarry(true);
        inventory.getItem("MEDICINE").setCanCarry(true);
        System.out.println("ROBES attainable!");
        System.out.println("ALMS attainable!");
        System.out.println("MEDICINE attainable!");
        System.out.println();
    }
    private void playerLoop(PuzzleThread puzzleThread) {
        List<String> puzzleValidInput = Arrays.asList("WARD", "NOSH", "REMEDY", "TAKE","DROP", "CHECK");
        List<String> validInput = Arrays.asList("LOOK", "READ", "TAKE", "DROP", "CHECK");
        while ( getCleared() != true) {
            // If player has an item, do item stuff, otherwise do normal stuff
            String[] userInput = {"",""};
            if (puzzlePlayer.getInventory().getItems().size() > 1) {
                userInput = INPUT_HANDLER(puzzleValidInput);
                ActionUtil.puzzleActionDelegator(userInput, puzzleRoom, puzzlePlayer, puzzleThread);
                checkSolution(userInput, puzzleThread);
            } else {
                userInput = INPUT_HANDLER(validInput);
                ActionUtil.actionDelegator(userInput, puzzleRoom, puzzlePlayer);
            }
        }
        setCleared(false);
    }

    private void checkSolution(String[] userInput, PuzzleThread puzzleThread) {
//        System.out.println(userInput.toString());
//        System.out.println(solution.toString());
        if (userInput[0].equals(solution[0]) && userInput[1].equals(solution[1])) {
//            System.out.println(userInput);
//            System.out.println(solution);
            setCleared(true);
            System.out.println("----" + solution[1] + ": you have Mastered this Requisite!----");
            puzzleThread.interrupt();

        }
    }

    class PuzzleThread extends Thread {
        private int numRounds = 5;
        private String[] solution;
        private String taskHint;
        private String countdownWarning = "Dusk is less than a Vighati away!!!";
        private String countdownMessage = " Lipta remaining. Darkness will come soon...";

        public PuzzleThread(int numRounds, String[] solutionSet) {
            this.numRounds = numRounds;
            this.taskHint = puzzleRoom.getInventory().getItem(solutionSet[1]).getReadText();
        }
        @Override
        public void run() {
            System.out.println(taskHint);
            for (int i = numRounds; i > 0; i--) {
//                System.out.println();
                try {
                    Thread.sleep(15000);
                    if (i < 3) {
                        System.out.println("\n" + countdownWarning);
                    } else {
                        System.out.println("\n"+(4 * i * 2) + countdownMessage);
                    }
                }
                catch (InterruptedException e) {
                    // Intentionally not logging error because console game
                    break;
                }
            }
            CLEAR_SCREEN();
        }
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
