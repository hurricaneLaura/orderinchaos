package com.orderinchaos;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.orderinchaos.Util.INPUT_HANDLER;
import static org.junit.Assert.*;

public class UtilTest {
  private static List<String> validCommands;

  @Before
  public void init() {
    validCommands = new ArrayList<>(List.of("LOOK", "READ", "TAKE", "DROP"));
  }

  @Test
  public void INPUT_HANDLER_ShouldReturnValidCommandIfSynonymsAreProvided() {
    // Provided "glance room"
    String[] res = INPUT_HANDLER(validCommands, "test.txt");
    String expectedOutput = "LOOK";
    assertEquals(expectedOutput, res[0]);
  }

  @Test (expected = IndexOutOfBoundsException.class)
  public void INPUT_HANDLER_ShouldThrowIOBExceptionIfSynonymsAreNOTProvided() {
    // Provided "kick item"
    String[] res = INPUT_HANDLER(validCommands, "failTest.txt");
//    assertNull(res);
  }
}