package org.l3monkeys.collection.components.carousel.pagination;

public enum PaginationColor {

    DEFAULT("#18191c"), NETFLIX("#E50914"), NETFLIX_DARK("#B20710"), DISCORD("#7289da");

    private String hexValue;

    PaginationColor(String hexValue) {
        this.hexValue = hexValue;
    }

    public String getValue() {
        return this.hexValue;
    }

}