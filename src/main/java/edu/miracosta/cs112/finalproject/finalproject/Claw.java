package edu.miracosta.cs112.finalproject.finalproject;

import javafx.scene.image.ImageView;
import java.util.ArrayList;
import java.util.List;

public class Claw extends GameObject
{
    public static final int MAX_ATTEMPTS = 3;
    private ImageView boundaryImageView;
    public double deltaX;

    public class Arm extends GameObject
    {
        private ImageView boundaryImageView;
        private double deltaY;
        public int attempts;
        List<Prize> drama = new ArrayList<>();
        List<Prize> horror = new ArrayList<>();
        List<Prize> fantasy = new ArrayList<>();
        List<Prize> romance = new ArrayList<>();
        List<Prize> comedy = new ArrayList<>();

        public Arm(ImageView armImageView, ImageView boundaryImageView)
        {
            super();
            this.imageView = armImageView;
            this.boundaryImageView = boundaryImageView;
            this.attempts = MAX_ATTEMPTS;
            setPosition(0,0);//re-adjust position
        }

        public void setDeltaY(double deltaY)
        {
            this.deltaY = deltaY;
        }

        public void setDeltaX(double deltaX)
        {
            Claw.this.setDeltaX(deltaX);
        }

        public boolean gameOver()
        {
            return this.attempts <= 0;
        }

        @Override
        public void update()
        {
            double x = imageView.getX() + deltaX;
            double y = imageView.getY() + deltaY;

            double armWidth = imageView.getFitWidth();
            double armHeight = imageView.getFitHeight();

            double boundaryWidth = boundaryImageView.getFitWidth();
            double boundaryHeight = boundaryImageView.getFitHeight();

            if(x < 0)
            {
                x = 0;
            }
            else if((x + armWidth) > boundaryWidth)
            {
                x = boundaryWidth - armWidth;
            }

            if(y < 0)
            {
                y = 0;
            }
            else if((y + armHeight) > boundaryHeight)
            {
                y = boundaryHeight - armHeight;
            }

            setPosition(x,y);
        }

        public void decreaseAttempts(int attempt)
        {
            this.attempts -= attempt;
        }

        public int getAttempts()
        {
            return this.attempts;
        }

        public void collectPrize(Prize prizeItem)
        {
            switch(prizeItem.getType())
            {
                case Prize.Type.COMEDY:
                    comedy.add(prizeItem);
                    break;
                case Prize.Type.DRAMA:
                    drama.add(prizeItem);
                    break;
                case Prize.Type.FANTASY:
                    fantasy.add(prizeItem);
                    break;
                case Prize.Type.HORROR:
                    horror.add(prizeItem);
                    break;
                case Prize.Type.ROMANCE:
                    romance.add(prizeItem);
                    break;
            }
        }

        public int getComedyCount()
        {
            return comedy.size();
        }

        public int getDramaCount()
        {
            return drama.size();
        }

        public int getFantasyCount()
        {
            return fantasy.size();
        }

        public int getHorrorCount()
        {
            return horror.size();
        }

        public int getRomanceCount()
        {
            return romance.size();
        }

        public ArrayList<String> getPrizes()
        {
            ArrayList<String> icons = new ArrayList<>();
            if(!comedy.isEmpty())
            {
                int amount = getComedyCount();
                while(amount > 0)
                {
                    icons.add("file:./src/main/resources/Images/comedyPrize.png");
                    amount--;
                }
            }
            if(!drama.isEmpty())
            {
                int amount = getDramaCount();
                while(amount > 0)
                {
                    icons.add("file:./src/main/resources/Images/dramaPrize.png");
                    amount--;
                }
            }
            if(!fantasy.isEmpty())
            {
                int amount = getFantasyCount();
                while(amount > 0)
                {
                    icons.add("file:./src/main/resources/Images/fantasyPrize.png");
                    amount--;
                }
            }
            if(!horror.isEmpty())
            {
                int amount = getHorrorCount();
                while(amount > 0)
                {
                    icons.add("file:./src/main/resources/Images/horrorPrize.png");
                    amount--;
                }
            }
            if(!romance.isEmpty())
            {
                int amount = getRomanceCount();
                while(amount > 0)
                {
                    icons.add("file:./src/main/resources/Images/romancePrize.png");
                    amount--;
                }
            }

            return icons;
        }
    }


    public Claw(ImageView clawImageView, ImageView boundaryImageView)
    {
        super();
        this.imageView = clawImageView;
        this.boundaryImageView = boundaryImageView;
        setPosition(0,0);
    }

    public void setDeltaX(double deltaX)
    {
        this.deltaX = deltaX;
    }

    @Override
    public void update()
    {
        double x = imageView.getX() + deltaX;

        double baseWidth = imageView.getFitWidth();
        double boundaryWidth = boundaryImageView.getFitWidth();

        if(x < 0)
        {
            x = 0;
        }
        else if((x + baseWidth) > boundaryWidth)
        {
            x = boundaryWidth - baseWidth;
        }

        setPosition(x,imageView.getY());
    }

}
