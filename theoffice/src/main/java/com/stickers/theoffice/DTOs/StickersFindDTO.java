package com.stickers.theoffice.DTOs;

import com.stickers.theoffice.Models.Sticker;

public class StickersFindDTO extends StickersDTO {

    private String id;


    public StickersFindDTO(Sticker sticker) {
        super(sticker);
        this.id = sticker.getId();
    }

    public StickersFindDTO(String id, String character, String url){
        super(character, url);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    

}
