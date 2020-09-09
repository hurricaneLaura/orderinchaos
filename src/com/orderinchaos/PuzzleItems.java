package com.orderinchaos;

public class PuzzleItems {
    public static Item robes() {
        return new Item.Builder("Robes").withDescription("WARD: I use it simply to ward off cold, to ward off heat, to ward off the touch of flies, mosquitoes, simply for the purpose of covering.").build();
    }
    public static Item alms() {
        return new Item.Builder("Alms").withDescription("NOSH: I use this food not playfully or for beautification, but simply for the survival and continuance of this body and for ending its afflictions. Thus I will maintain myself, be blameless, and live in comfort.").build();
    }
    public static Item medicine() {
        return new Item.Builder("Medicine").withDescription("REMEDY: I use them simply to remedy pains or remedy illnesses that have arisen and for the maximum freedom from disease.s").build();
    }
    public static Item knapsack() {
        return new Item.Builder("Knapsack").withDescription("A bag of Requisites").build();
    }
}
