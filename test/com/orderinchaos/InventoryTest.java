package com.orderinchaos;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class InventoryTest {
  Inventory inventory;
  @Before
  public void init() {
    Item item1 = new Item.Builder("SCROLL").build();
    Item item2 = new Item.Builder("Monkey").build();
    List<Item>  itemsList = new ArrayList<>(List.of(item1,item2));
    inventory = new Inventory(itemsList);
  }

  @Test
  public void addItem() {
  }

  @Test
  public void test_getItem() {
    Item targetItem = inventory.getItem("SCROLL");
    assertNotNull(targetItem);
    assertEquals("SCROLL", targetItem.getName());
  }

  @Test
  public void removeItem_shouldRemoveItemFromInventory() {
    Item targetItem = inventory.getItem("SCROLL");
    inventory.removeItem(targetItem);
    assertNull(inventory.getItem(targetItem.getName()));
  }

  @Test
  public void showInventory() {
    //assertEquals();
    }
}