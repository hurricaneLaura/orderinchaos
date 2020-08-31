package com.orderinchaos;


import org.junit.Test;


import static org.junit.Assert.*;


public class GameTest {

    @Test
    public void test_loadRooms_shouldReturnAList_() {
        Game game = new Game();
        game.loadRooms();
        game.getRoomList().forEach(room -> System.out.println(room.getName() +"\n" + room.getDescription()));
        assertTrue(true);
    }
}
