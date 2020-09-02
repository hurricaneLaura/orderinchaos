package com.orderinchaos;

import org.junit.Before;
import org.junit.Test;
import java.util.*;

import static org.junit.Assert.*;

public class ItemTest {
    Item item;
    @Before
    public void init() {
        item = new FixtureInfoItem("Informative text","An immobile yet readable Item!");
    }

    @Test
    public void addItem() {
        Class myClass = item.getClass();
        Class[] i = myClass.getInterfaces();
        System.out.println(item instanceof Readable);
        System.out.println(i[0].toString());
        System.out.println("Interfaces = " + Arrays.asList(i));
    }


}