package edu.miracosta.cs112.finalproject.finalproject;

import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import java.util.Random;

public class Prize extends GameObject{

    static Random random = new Random();
    public enum Type {DRAMA, COMEDY, FANTASY, HORROR, ROMANCE}

    final String[] types = {
            "Drama",
            "Comedy",
            "Fantasy",
            "Horror",
            "Romance"
    };

    final String[] icons = {};
    Type type;

    public Prize(Type type)
    {
        super();
        this.type = type;
        Image image = new Image(icons[this.type.ordinal()]);
        imageView = new ImageView(image);
        imageView.setFitWidth(25);
        imageView.setFitHeight(25);
        setPosition((random.nextDouble(169 - 2 + 1) + 2), 182);
    }
    public Type getType()
    {
        return this.type;
    }

    public String getName()
    {
        return types[this.type.ordinal()];
    }

    @Override
    public void update()
    {

    }
}
