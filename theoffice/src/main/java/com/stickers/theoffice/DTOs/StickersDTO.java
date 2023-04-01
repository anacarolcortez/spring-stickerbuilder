package com.stickers.theoffice.DTOs;

import com.stickers.theoffice.Models.Sticker;
import jakarta.validation.constraints.NotBlank;

public class StickersDTO {

    @NotBlank(message = "Inform character's name")
    private String character;

    @NotBlank(message = "Inform image's url")
    private String url;

    public StickersDTO(String character, String url){
        this.character = character;
        this.url = url;
    }

    public StickersDTO(Sticker sticker){
        this.character = sticker.getCharacter();
        this.url = sticker.getUrl();
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
}
