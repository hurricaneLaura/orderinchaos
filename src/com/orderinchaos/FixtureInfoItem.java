package com.orderinchaos;

public class FixtureInfoItem extends Item implements Readable{
    private String text;
    private final boolean canCarry = false;

    public FixtureInfoItem(String text, String description) {
        super(description);
        setText(text);
    }

    public FixtureInfoItem(String text, String description, boolean isKey, boolean canCarry) {
        super(description, isKey, canCarry);
        setText(text);
    }

    private void setText(String text) {
        this.text = text;
    }

    @Override
    public String readText() {
        return text;
    }

}
