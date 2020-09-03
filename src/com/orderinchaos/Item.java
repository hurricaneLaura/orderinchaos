package com.orderinchaos;

// Sample use Item.Builder("name").withDescription("some description").withReadText("some readable text").withIsKey(true).build()
// Returns an Item object with the specified values and defaults for the rest of the attributes.
public class Item {
    private String name;
    private String description;
    private String readText;
    private boolean isKey;
    private boolean canCarry;
    private boolean canRead;

    private Item(String name, String description, String readText, boolean isKey, boolean canCarry, boolean canRead) {
        this.name = name;
        this.description = description;
        this.readText = readText;
        this.isKey = isKey;
        this.canCarry = canCarry;
        this.canRead = canRead;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getReadText() {
        return readText;
    }

    public boolean isKey() {
        return isKey;
    }

    public boolean canCarry() {
        return canCarry;
    }

    public boolean canRead() {
        return canRead;
    }

    public static class Builder {
        // sneaky class living inside another class
        // builder design pattern :}
        private String name;
        private String description;
        private String readText;
        private Boolean isKey;
        private Boolean canCarry;
        private Boolean canRead;

        public Builder(String name) {
            this.name = name;
        }

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withReadText(String readText) {
            this.readText = readText;
            return this;
        }

        public Builder withIsKey(Boolean isKey) {
            this.isKey = isKey;
            return this;
        }

        public Builder withCanCarry(Boolean canCarry) {
            this.canCarry = canCarry;
            return this;
        }

        public Builder withCanRead(Boolean canRead) {
            this.canRead = canRead;
            return this;
        }

        public Item build() {
            Item item = new Item(
                this.name,
                this.description == null ? "default value" : this.description,
                this.readText == null ? "default text" : this.readText,
                this.isKey == null ? false: this.isKey,
                this.canCarry == null ? true : this.canCarry,
                this.canRead == null ? false : this.canRead
            );
            return item;
        }
    }

}
