package com.orderinchaos;

public  class Item {
    private String description;
    private boolean isKey = false;
    private boolean canCarry = false;

    public Item(String description) {
        setDescription(description);
    }

    public Item(String description, boolean isKey, boolean canCarry) {
        this(description);
        setKey(isKey);
        setCanCarry(canCarry);
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isKey() {
        return isKey;
    }

    public void setKey(boolean key) {
        isKey = key;
    }

    public boolean canCarry() {
        return canCarry;
    }

    public void setCanCarry(boolean canCarry) {
        this.canCarry = canCarry;
    }
}
