package com.stickers.theoffice.DTOs;

import com.stickers.theoffice.Models.Sticker;

public class StickersCreateDTO extends StickersDTO {

    public StickersCreateDTO(String character, String url){
        super(character, url);
    }

    public StickersCreateDTO(Sticker sticker){
        super(sticker);
    }

}
