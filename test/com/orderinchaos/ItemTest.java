package com.orderinchaos;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ItemTest {
    @Before


    @Test
    public void test_BuilderBuild_shouldInitializeAnItem_withDefaultValues_whenAdditionalBuilderMethods_areNotProvided() {
        Item item = new Item.Builder("name").build();
        assertEquals(item.getName(), "name");
        assertEquals(item.getDescription(), "default value");
        assertEquals(item.getDescription(), "default value");
        assertFalse(item.isKey());
        assertTrue(item.canCarry());
        assertFalse(item.canRead());
    }

}
