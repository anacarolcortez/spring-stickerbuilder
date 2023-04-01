package com.stickers.theoffice.Controllers;

import com.stickers.theoffice.DTOs.StickersDTO;
import com.stickers.theoffice.Services.StickersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.io.IOUtils;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


@RestController
@RequestMapping("/stickers")
public class StickersController {

    @Autowired
    private StickersService service;

    @GetMapping(produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getImageWithMediaType() throws IOException {
        InputStream in = service.getStickerRandomImage();
        return IOUtils.toByteArray(in);
    }

    @PostMapping()
    public ResponseEntity<StickersDTO> postSticker(@Valid @RequestBody StickersDTO stickersDTO){
        StickersDTO sticker = service.insert(stickersDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(sticker);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<StickersDTO> update(@PathVariable String id, @Valid @RequestBody StickersDTO stickersDTO){
        StickersDTO sticker = service.update(id, stickersDTO);
        return ResponseEntity.ok().body(sticker);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
