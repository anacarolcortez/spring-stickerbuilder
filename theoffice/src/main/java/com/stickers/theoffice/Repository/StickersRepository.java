package com.stickers.theoffice.Repository;

import com.stickers.theoffice.Models.Sticker;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StickersRepository extends MongoRepository<Sticker, String> {

    Sticker findByCharacter(String character);

}
