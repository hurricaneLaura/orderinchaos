package com.orderinchaos;

public class PuzzleItems {
    //TODO: use readText to store hidden hints
    public static Item robes() {
        return new Item.Builder("ROBES")
                .withDescription("WARD ROBES:\nI use it simply to ward off cold, to ward off heat, to ward off the touch of flies, mosquitoes, simply for the purpose of covering.")
                .withReadText("The pilgrimage will exhaust you in your vulnerable human form\nYet not beginning the journey will end you sooner!\nChoose the middle way, young Bhikku\n\nMASTER THIS REQUISITE\n")
                .withCanCarry(false)
                .build();
    }
    public static Item alms() {
        return new Item.Builder("ALMS")
                .withDescription("NOSH ALMS:\nI use this food not playfully nor for beautification, but simply for the survival and continuance of this body and for ending its afflictions. Thus I will maintain myself, be blameless, and live in comfort.")
                .withReadText("The stream flows from SPIRIT to MIND to BODY.\nWhile beauty trickles away with the stream of time,\nSurvival of this form must be honored.\n\nMASTER THIS REQUISITE:\n")
                .withCanCarry(false)
                .build();
    }
    public static Item medicine() {
        return new Item.Builder("MEDICINE")
                .withDescription("REMEDY MEDICINE:\nI use them simply to remedy pains or remedy illnesses that have arisen and for the maximum freedom from disease.s")
                .withReadText("If this fleshy furnace called the stomach\nIs but one door to health and illness.\nThen how does one treat the skin, the lungs, the aura?\n\nMASTER THIS REQUISITE:\n")
                .withCanCarry(false)
                .build();
    }
    public static Item knapsack() {
        return new Item.Builder("KNAPSACK")
                .withDescription("A bag of Requisites")
                .build();
    }
}
