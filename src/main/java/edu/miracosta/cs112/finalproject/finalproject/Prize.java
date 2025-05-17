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

    final String[] icons = {
            "file:./src/main/resources/Images/dramaPrize.png",
            "file:./src/main/resources/Images/comedyPrize.png",
            "file:./src/main/resources/Images/fantasyPrize.png",
            "file:./src/main/resources/Images/horrorPrize.png",
            "file:./src/main/resources/Images/romancePrize.png",
    };
    Type type;

    public Prize(Type type)
    {
        super();
        this.type = type;
        Image image = new Image(icons[this.type.ordinal()]);
        imageView = new ImageView(image);
        imageView.setFitWidth(50);
        imageView.setFitHeight(50);
        setPosition((random.nextDouble(425 - 135 + 1) + 135), 537);
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
        double x = imageView.getX();
        double y = imageView.getY();
        setPosition(x,y);
    }
}
