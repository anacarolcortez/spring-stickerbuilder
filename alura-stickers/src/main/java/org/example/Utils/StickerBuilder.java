package org.example.Utils;

import org.example.Models.Sticker;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StickerBuilder {

    private String character;
    private String quotes;

    public StickerBuilder(Sticker sticker){
        this.character = sticker.getCharacter();
        this.quotes = sticker.getQuotes();
    }

    public void buildImg() throws IOException {
        LocalDateTime now = LocalDateTime.now();
        List<String> allQuotes = getBrokenQuotes();
        int extraLenght = getNewLenght(allQuotes);
        BufferedImage newImage = stretchImage(extraLenght);
        Graphics2D graphic = getGraphic(newImage);
        Graphics2D finalGraphic = insertQuotes(allQuotes, graphic);
        ImageIO.write(newImage, "png", new File("stickers/" + character + "_" + now +".png"));
    }

    // a cada 23 caracteres da String, quebrar linha e adicionar em uma lista. Se último caracter não for espaço, colocar hífen (não vai dar certo 100% das vezes, mas... só IA separaria certinho sozinha)
    // ampliar a imagem conforme a quantidade de linhas (30 px cada frase) - "for each" no size da lista de String

    public Graphics2D getGraphic(BufferedImage newImage) throws IOException {
        BufferedImage originalImage = ImageIO.read(new File("img/"+ character +".jpg"));

        Graphics2D graphic = (Graphics2D) newImage.getGraphics();
        graphic.drawImage(originalImage, 0, 0, null);

        return graphic;
    }

    private Graphics2D insertQuotes(List<String> brokenQuotes, Graphics2D graphic){
        graphic.setFont(new Font(Font.SERIF, Font.BOLD, 28));
        graphic.setColor(Color.lightGray);

        int height = 333;

        for (String quotes: brokenQuotes){
            height += 30;
            graphic.drawString(quotes, 5, height);
        }

       return graphic;
    }

    private List<String> getBrokenQuotes(){
        List<String> brokenQuotes = new ArrayList<>();
        int counter = 0;

        int loop = (int)(quotes.length()/22) + 1;

        for (int i = 0; i < loop; i++) {
            try{
                String partial = quotes.substring(counter, counter+22);
                counter += 22;
                brokenQuotes.add(partial);
            } catch (StringIndexOutOfBoundsException e){
                String partial = quotes.substring(counter, quotes.length());
                brokenQuotes.add(partial);
            }

        }

        return brokenQuotes;
    }

    private int getNewLenght (List<String> brokenQuotes){
        return brokenQuotes.size() * 30;
    }

    private BufferedImage stretchImage(int extraLength) throws IOException {
        BufferedImage originalImage = ImageIO.read(new File("img/"+ character +".jpg"));

        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int newHeight = height + extraLength;

        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.OPAQUE);

        return newImage;
    }

    public static void main(String[] args) {
        var builder = new StickerBuilder(new Sticker("Michael", "Testando a criação de uma figurinha com textão dinamicamente"));
        try {
            builder.buildImg();
        } catch (IOException e) {
            System.out.println("Erro: " + e.getMessage());;
        }

    }
}
