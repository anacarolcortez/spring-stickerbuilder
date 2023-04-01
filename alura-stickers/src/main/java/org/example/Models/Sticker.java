package org.example.Models;

public class Sticker {

    private String character;
    private String quotes;

    public Sticker(String character, String quotes){
        this.character = character;
        this.quotes = quotes;
    }

    public String getCharacter() {
        return character;
    }

    public String getQuotes() {
        return quotes;
    }
}
