package org.example;

import org.example.Models.Sticker;
import org.example.Services.QuotesService;
import org.example.Utils.StickerBuilder;

public class Main {
    public static void main(String[] args) throws Exception {

        for (int i = 0; i < 100; i++) {
            var obj = QuotesService.getRandomQuotes().getData();
            StickerBuilder sticker = new StickerBuilder(new Sticker(obj.getCharacter().getFirstname(), obj.getContent()));

            sticker.buildImg();
        }
    }
}

/*
 * {
  "data": {
    "_id": "5e96685a6a66e65486e244a7",
    "content": "I wish there was a way to know you’re in the good old days, before you’ve actually left them.",
    "character": {
      "_id": "5e93b5453af44260882e33b9",
      "firstname": "Andy",
      "lastname": "Bernard",
      "__v": 0
    },
    "__v": 0
  }
}
 */