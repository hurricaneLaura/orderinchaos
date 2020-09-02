package com.orderinchaos;

public class InfoItem extends Item implements Readable {
    private String text;

    public InfoItem(String text, String description) {
        super(description);
        setText(text);
    }

    public InfoItem(String text, String description, boolean isKey, boolean canCarry) {
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
