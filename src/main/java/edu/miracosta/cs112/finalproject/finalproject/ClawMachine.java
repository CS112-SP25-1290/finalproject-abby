package edu.miracosta.cs112.finalproject.finalproject;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClawMachine
{
    Random random = new Random();
    MainController controller;
    final double respawnReset = 10;
    private int prizesSpawned = 0;
    public double respawnTimer = respawnReset;

    List<GameObject> updateList = new ArrayList<>();

    public ClawMachine(MainController controller, Claw base, Claw.Arm arm)
    {
        this.controller = controller;
        updateList.add(base);
        updateList.add(arm);

        AnimationTimer timer = new AnimationTimer()
        {
            @Override
            public void handle(long now)
            {
                handleObjectUpdate();
                handleCollision();
                handlePrizeSpawn();
                controller.handleUIUpdate();


                if(arm.gameOver())
                {
                    this.stop();
                    controller.handleUIResults();
                }

            }
        };
        timer.start();
    }


    public void handleObjectUpdate()
    {
        for (GameObject gameObject : updateList)
        {
            gameObject.update();
        }
    }

    public void handlePrizeSpawn()
    {
        if(prizesSpawned >= 7)
        {
            return;
        }

        respawnTimer -= 0.07;
        if(respawnTimer <= 0)
        {
            respawnTimer = respawnReset;
            int i = random.nextInt(Prize.Type.values().length);
            Prize object = new Prize(Prize.Type.values()[i]);
            updateList.add(object);

            Node node = object.getNode();
            controller.getObservableList().add(node);

            prizesSpawned++;

        }
    }

    public void handlePrizeDespawn(GameObject gameObject)
    {
        updateList.remove(gameObject);
        controller.getObservableList().remove(gameObject.getNode());
    }

    public void handleCollision()
    {
        Claw.Arm arm = null;
        for(GameObject object : updateList)
        {
            if(object instanceof Claw.Arm)
            {
                arm = (Claw.Arm) object;
                break;
            }
        }

        if(arm != null)
        {
            Prize prize = null;
            for(GameObject object : updateList)
            {
                if(arm.isColliding(object))
                {
                    if(object instanceof Prize)
                    {
                        prize = (Prize) object;
                    }
                }
            }
            if(prize != null)
            {
                arm.collectPrize(prize);
                arm.decreaseAttempts(1);
                handlePrizeDespawn(prize);
            }
        }

    }


}

