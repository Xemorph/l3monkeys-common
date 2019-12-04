package org.l3monkeys.collection.components.card;

public enum GameCardColor {

    GREEN("#183123", "#406a45"), RED("#B20710", "#E50914"),
    NETFLIX("#f05640", "firebrick");

    private String startColor;
    private String stopColor;

    GameCardColor(String startColor, String stopColor) {
        this.startColor = startColor;
        this.stopColor = stopColor;
    }

    public String getStartColor() {
        return this.startColor;
    }

    public String getStopColor() {
        return this.stopColor;
    }
}