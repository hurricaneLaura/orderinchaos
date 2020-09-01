package com.orderinchaos;


import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;


public class GameTest {
    Game game;
    Room room;

    @Before
    public void init() {
       game = new Game();
       room = new Room("Test Room","TLDR","SCROLL");
    }

//    @Test
//    public void test_loadRooms_shouldReturnAList_() {
//        game.loadRooms();
//        game.getRoomList().forEach(room -> System.out.println(room.getName() +"\n" + room.getDescription()));
//        assertTrue(true);
//    }

    @Test
    public void test_actionDelegator_shouldRemoveItemFromRoom_andAddItemToPlayer_whenUserInputsTake_andValidItem(){

        Player player = game.getPlayer();
        String[] userInput = {"TAKE","SCROLL"};
        // Before inventories have been swapped
        int roomActual = room.getInventory().getItems().size();
        int roomExpected = 1;
        assertEquals(roomExpected, roomActual);

        int playerActual = player.getInventory().getItems().size();
        int playerExpected = 0;
        assertEquals(playerExpected, playerActual);


        game.actionDelegator(userInput, room, player);
        // After inventories have been swapped
        roomActual = room.getInventory().getItems().size();
        roomExpected = 0;
        assertEquals(roomExpected, roomActual);

        playerActual = player.getInventory().getItems().size();
        playerExpected = 1;
        assertEquals(playerExpected, playerActual);

    }

    @Test
    public void test_actionDelegator_shouldNotChangeInventories_whenRoomDoesNotContainItem(){

        Player player = game.getPlayer();
        String[] userInput = {"TAKE","MONKEY"};
        // Before inventories have been swapped
        int roomActual = room.getInventory().getItems().size();
        int roomExpected = 1;
        assertEquals(roomExpected, roomActual);

        int playerActual = player.getInventory().getItems().size();
        int playerExpected = 0;
        assertEquals(playerExpected, playerActual);


        game.actionDelegator(userInput, room, player);
        // After inventories have been swapped
        roomActual = room.getInventory().getItems().size();
        roomExpected = 1;
        assertEquals(roomExpected, roomActual);

        playerActual = player.getInventory().getItems().size();
        playerExpected = 0;
        assertEquals(playerExpected, playerActual);

    }

    @Test
    //TODO: write delegator souts to return strings and store in a variable before printing so we can test
//    @Test
//    public void moar_Tests_thatRelyOnGameClass_beingInstantiated(){
//
//    }
}
