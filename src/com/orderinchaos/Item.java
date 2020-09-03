package com.orderinchaos;

// Minimum requirement to construct a new item - Item.Builder(String name).build()
// Specify additional properties by chaining their methods in Builder: .withDescription(String description) .withIsKey(Boolean isKey) etc.
// .build() must be the last method in a Builder chain, as it is responsible for returning the instance of Item.
public class Item {
    private String name;
    private String description;
    private String readText;
    private boolean isKey;
    private boolean canCarry;
    private boolean canRead;

    // Only need one private constructor as inner Builder class methods will take care of which properties to initialize
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

    // sneaky class living inside another class
    // builder design pattern :}
    public static class Builder {

        private String name;
        private String description;
        private String readText;
        private Boolean isKey;
        private Boolean canCarry;
        private Boolean canRead;

        // all instances of Builder, and therefore Item, must have a name specified (no defaults!)
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

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", readText='" + readText + '\'' +
                ", isKey=" + isKey +
                ", canCarry=" + canCarry +
                ", canRead=" + canRead +
                '}';
    }
}
