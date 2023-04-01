package com.stickers.theoffice.Controllers;

import com.stickers.theoffice.DTOs.StickersCreateDTO;
import com.stickers.theoffice.DTOs.StickersFindDTO;
import com.stickers.theoffice.Services.StickersService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;


@RestController
@RequestMapping("/stickers")
public class StickersController {

    @Autowired
    private StickersService service;

    @GetMapping()
    public ResponseEntity<Page<StickersFindDTO>> findAll(Pageable pageable){
        Page<StickersFindDTO> stickers = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(stickers);
    }

    @GetMapping(value = "/random", produces = MediaType.IMAGE_PNG_VALUE)
    public @ResponseBody byte[] getImageWithMediaType() throws IOException {
        InputStream in = service.getStickerRandomImage();
        return IOUtils.toByteArray(in);
    }

    @PostMapping
    public ResponseEntity<StickersCreateDTO> postSticker(@Valid @RequestBody StickersCreateDTO stickersDTO){
        StickersCreateDTO sticker = service.insert(stickersDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(sticker);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<StickersCreateDTO> update(@PathVariable String id, @Valid @RequestBody StickersCreateDTO stickersDTO){
        StickersCreateDTO sticker = service.update(id, stickersDTO);
        return ResponseEntity.ok().body(sticker);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
