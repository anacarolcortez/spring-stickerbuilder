package com.stickers.theoffice.Repository;

import com.stickers.theoffice.Models.Sticker;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface StickersRepository extends MongoRepository<Sticker, String> {

    Optional<Sticker> findByCharacter(String character);

}
