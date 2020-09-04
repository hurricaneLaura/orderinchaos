package com.orderinchaos;

import junit.framework.TestCase;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;

import static com.orderinchaos.Util.INPUT_HANDLER;

public class UtilTest extends TestCase {

  @Before
  public void init() {

  }

  public void testINPUT_HANDLER() {
    List<String> validCmds = new ArrayList<>(List.of("LOOK", "READ", "TAKE", "DROP"));
    String[] res = INPUT_HANDLER(validCmds, "test.txt");
  }
}