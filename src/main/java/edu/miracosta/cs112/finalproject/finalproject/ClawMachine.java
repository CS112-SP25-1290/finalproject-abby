package edu.miracosta.cs112.finalproject.finalproject;

import javafx.animation.AnimationTimer;
import javafx.scene.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The ClawMachine class represents the game loop for the claw machine.
 *
 * @author Abby Mene
 * @version 1.0
 * @since 2025-05-18
 */
public class ClawMachine
{
    Random random = new Random();//random generator
    MainController controller;//controller tied the UI
    final double respawnReset = 10;//value for respawn reset Prizes
    private int prizesSpawned = 0;//counts the amount of prizes spawned in the machine
    public double respawnTimer = respawnReset;//sets the respawn timer for Prizes

    List<GameObject> updateList = new ArrayList<>();//a list to hold all the game objects that need updating

    /**
     * ClawMachine constructor that builds and handles the functions of my program
     * @param controller main controller
     * @param base Claw object that represents the base of the claw that only moves left and right in machine
     * @param arm Arm object that represents the arm of the claw that moves left-right, and up-down in machine
     */
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
                handlePrizeSpawn();
                controller.handleUIUpdate();
                try
                {
                    handleCollision();
                }
                catch(MaxAttemptsExceededException e)
                {
                    System.out.println("Game over: " + e.getMessage());
                }


                if(arm.gameOver())
                {
                    this.stop();
                    controller.handleUIResults();
                }

            }
        };
        timer.start();
    }

    /**
     * Updates all game objects in the update list
     * It refreshes object states, apply logic, or perform per-frame updates in a game loop
     */
    public void handleObjectUpdate()
    {
        for (GameObject gameObject : updateList)
        {
            gameObject.update();
        }
    }

    /**
     * Randomly spawns 7 prizes in the machine in 0.04 units per update cycle
     */
    public void handlePrizeSpawn()
    {
        if(prizesSpawned >= 7)
        {
            return;
        }

        respawnTimer -= 0.04;
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

    /**
     * Removes GameObject from updateList, so it will be removed from GUI
     * @param gameObject gameObject that will be removed from updateList
     */
    public void handlePrizeDespawn(GameObject gameObject)
    {
        updateList.remove(gameObject);
        controller.getObservableList().remove(gameObject.getNode());
    }

    /**
     * Searches for a Claw.Arm in the updateList, and checks for collisions with a Prize, and handles prize collection
     * @throws MaxAttemptsExceededException if the exceeds the maximum allowed number of prize collection attempts
     */
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

                if(arm.getAttempts() <= 0)
                {
                    throw new MaxAttemptsExceededException("Maximum prize collection attempts reached.");
                }
            }
        }

    }


}

