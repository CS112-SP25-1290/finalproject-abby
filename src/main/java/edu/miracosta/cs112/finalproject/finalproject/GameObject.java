package edu.miracosta.cs112.finalproject.finalproject;
import javafx.scene.Node;
import javafx.scene.image.ImageView;

public abstract class GameObject
{
    protected ImageView imageView;
    public Node getNode()
    {
        return imageView;
    }

    protected GameObject()
    {

    }

    public void setPosition(double x, double y)
    {
        imageView.setX(x);
        imageView.setY(y);
    }

    public boolean isColliding(GameObject other)
    {
        if (this == other)
        {
            return false;
        }

        return this.imageView.getBoundsInParent().intersects(other.imageView.getBoundsInParent());
    }

    public abstract void update();
}
