package com.springstudy.jpa.item;

public class Item {

    public static String id = "oldId";

    private String name = "book";

    public Item() {
    }

    private Item(String name) {
        this.name = name;
    }

    private int sum(int a, int b) {
        return a + b;
    }

    @Override
    public String toString() {
        return name;
    }
}