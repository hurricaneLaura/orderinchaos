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
    private boolean roundCleared = false;
    private String[] userInput = {"",""};
    private Room puzzleRoom = new Room("Chamber of Requisites", "Requisites are sacred, young monk -- Take this time to learn how to use these items properly: [ROBES,ALMS,MEDICINE]");
    private Player puzzlePlayer = new Player("Puzzle Monk");
    private List<String[]> solutions = new ArrayList<>();
    private String[] solution = {"NOSH","ALMS"};

    public void resetPuzzle() {
        puzzleRoom.setIsPuzzle(true);
        puzzlePlayer.getInventory().clearItems();
        Inventory puzzleInventory = puzzleRoom.getInventory();
        puzzleInventory.clearItems();
        puzzleInventory.addItem(PuzzleItems.robes());
        puzzleInventory.addItem(PuzzleItems.alms());
        puzzleInventory.addItem(PuzzleItems.medicine());
        puzzleInventory.addItem(PuzzleItems.knapsack());
    }

    public void runRoom() {
        // Three rounds of puzzle, player gets less time to complete the task each round
        randomizeSolutionsList();
        System.out.println("You have entered the " + puzzleRoom.getName());
        System.out.println(puzzleRoom.getDescription());
        for (int i=3; i>0; i--) {
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
            wait.nextLine();
            CLEAR_SCREEN();
            puzzleThreadRunner.start();
            actionPhase(puzzleThreadRunner);
            if (getRoundCleared() == false) {
                System.out.println("You have FAILED to master the Requisites...");
                // TODO: pass the fail up to game class
                setCleared(false);
                break;
            } else if (getRoundCleared() == true && i == 1) {
                System.out.println("You Have Mastered All The REQUISITES");
                setCleared(true);
            } else {
                resetPuzzle();
                System.out.println();
                System.out.println("The Requisites KNAPSACK has returned to it's place.");
            }
        }
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

    public boolean getRoundCleared() {
        return roundCleared;
    }
    public void setRoundCleared(boolean bool) {
        this.roundCleared = bool;
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
    private void actionPhase(PuzzleThread puzzleThread) {
        setRoundCleared(false);
        List<String> puzzleValidInput = Arrays.asList("WARD", "NOSH", "REMEDY", "TAKE","DROP", "CHECK");
        List<String> validInput = Arrays.asList("LOOK", "READ", "TAKE", "DROP", "CHECK");
        while ( puzzleThread.isAlive() && getRoundCleared() != true ) {
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

    }

    private void checkSolution(String[] userInput, PuzzleThread puzzleThread) {
        if (userInput[0].equals(solution[0]) && userInput[1].equals(solution[1])) {
            setRoundCleared(true);
            System.out.println("----" + solution[1] + ": you have Mastered this Requisite!----");
            puzzleThread.interrupt();
        } else {
            System.out.println("...you still have not Mastered this Requisite.");
        }
    }

    class PuzzleThread extends Thread {
        private int numRounds = 5;
        private String taskHint;
        private String countdownWarning = "Dusk is less than 10 seconds away!!!";
        private String countdownMessage = " seconds remain. Darkness will come soon...";

        public PuzzleThread(int numRounds, String[] solutionSet) {
            this.numRounds = numRounds;
            this.taskHint = puzzleRoom.getInventory().getItem(solutionSet[1]).getReadText();
        }
        @Override
        public void run() {
            System.out.println(taskHint);
            for (int i = numRounds + 1; i >= 0; i--) {
                try {
                    if (i > 0) {
                        System.out.println("\n" + (i * 15) + countdownMessage);
                    } else  {
                        System.out.println("\n" + countdownWarning);
                    }
                    Thread.sleep(15000);
                }
                catch (InterruptedException e) {
                    // Intentionally not logging error because console game
                    break;
                }
            }
            CLEAR_SCREEN();
        }
    }
}
