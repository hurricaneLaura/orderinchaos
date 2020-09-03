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
        assertEquals(item.getReadText(), "default text");
        assertFalse(item.isKey());
        assertTrue(item.canCarry());
        assertFalse(item.canRead());

        System.out.println(item);

    }

    @Test
    public void test_BuilderBuild_shouldInitializeAnItem_withSpecifiedValues_whenAdditionalBuilderMethods_areProvided() {
        Item item = new Item.Builder("name").withDescription("expected description").withReadText("expected read text").build();

        assertEquals("name", "name");
        assertEquals("expected description", item.getDescription());
        assertEquals("expected read text", item.getReadText());
        assertFalse(item.isKey());
        assertTrue(item.canCarry());
        assertFalse(item.canRead());

        System.out.println(item);

    }

}
