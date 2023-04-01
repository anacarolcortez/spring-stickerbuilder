package com.stickers.theoffice.Services;

import com.stickers.theoffice.DTOs.StickersDTO;
import com.stickers.theoffice.Models.Sticker;
import com.stickers.theoffice.Repository.StickersRepository;
import com.stickers.theoffice.Utils.exceptions.BrokenURLException;
import com.stickers.theoffice.Utils.exceptions.DatabaseException;
import com.stickers.theoffice.Utils.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class StickersService {

    @Autowired
    private StickersRepository repository;

    public InputStream getStickerRandomImage() throws IOException {
        List<Sticker> allStickers = repository.findAll();
        int randomNumber = getRandomNumber(0, allStickers.size());
        InputStream urlStream = null;

        try {
            URL url = new URL(allStickers.get(randomNumber).getUrl());
            URLConnection conn = url.openConnection();
            conn.connect();
            urlStream = conn.getInputStream();
        } catch (Exception e) {
            throw new BrokenURLException("Could not get the image. The url seems to be broken. Sorry!");
        }
        return urlStream;
    }

    @Transactional
    public StickersDTO insert(StickersDTO categoryDTO) {
        Sticker sticker = new Sticker();
        sticker.setCharacter(categoryDTO.getCharacter());
        sticker.setUrl(categoryDTO.getUrl());
        sticker = repository.save(sticker);
        return new StickersDTO(sticker);
    }

    @Transactional
    public StickersDTO update(String id, StickersDTO stickersDTO) {
        try{
            Optional<Sticker> sticker = repository.findById(id);
            if (stickersDTO.getCharacter() != null && !stickersDTO.getCharacter().isEmpty()){
                sticker.orElseThrow(() -> new ResourceNotFoundException("Id not found")).setCharacter(stickersDTO.getCharacter());
            }
            if (stickersDTO.getUrl() != null && !stickersDTO.getUrl().isEmpty()){
                sticker.orElseThrow(() -> new ResourceNotFoundException("Id not found")).setUrl(stickersDTO.getUrl());
            }
            Sticker updatedSticker = repository.save(sticker.orElseThrow(() -> new ResourceNotFoundException("Id not found")));
            return new StickersDTO(updatedSticker);
        } catch (Exception err){
            throw new ResourceNotFoundException("Id not found" + id);
        }
    }

    public void delete(String id) {
        try{
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException err){
            throw new ResourceNotFoundException("Id not found" + id);
        } catch (DataIntegrityViolationException err) {
            throw new DatabaseException("Integrity violation");
        }
    }

    private int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.ints(min, max)
                .findFirst()
                .getAsInt();
    }

}
