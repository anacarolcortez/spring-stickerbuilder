package com.stickers.theoffice.Models;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "theoffice")
public class Sticker {

    @Id
    private String id;
    private String character;
    private String url;

    public Sticker(){}

    public Sticker(String id, String character, String url){
        this.id = id;
        this.character = character;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sticker sticker = (Sticker) o;
        return Objects.equals(id, sticker.id) && Objects.equals(character, sticker.character) && Objects.equals(url, sticker.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, character, url);
    }
}
