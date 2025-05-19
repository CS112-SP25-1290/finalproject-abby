package edu.miracosta.cs112.finalproject.finalproject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;

import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class MainController {
    @FXML
    Label attemptsLabel;
    @FXML
    ImageView firstPrizeImageView;
    @FXML
    ImageView secondPrizeImageView;
    @FXML
    ImageView thirdPrizeImageView;
    @FXML
    ImageView insideMachine;
    @FXML
    ImageView insideMachine2;
    @FXML
    ImageView baseClawImageView;
    @FXML
    ImageView armClawImageView;
    @FXML
    Pane gameResults;
    @FXML
    Pane gamePane;


    Claw base;
    Claw.Arm arm;
    ClawMachine clawMachine;
    public ObservableList<Node> getObservableList()
    {
        return gamePane.getChildren();
    }

    public void initialize()
    {
        baseClawImageView.setFocusTraversable(true);
        baseClawImageView.requestFocus();

        armClawImageView.setFocusTraversable(true);
        armClawImageView.requestFocus();

        gamePane.setFocusTraversable(true);
        gamePane.requestFocus();
        gamePane.setOnKeyPressed(this::handleKeyPressed);
        gamePane.setOnKeyReleased(this::handleKeyReleased);

        base = new Claw(baseClawImageView,insideMachine);
        arm = base.new Arm(armClawImageView,insideMachine2);
        clawMachine = new ClawMachine(this,base,arm);
    }

    public void handleExitButton()
    {
        System.exit(0);
    }

    public void handleUIUpdate()
    {
        attemptsLabel.setText("Attempts: " + arm.getAttempts());
    }

    public void handleUIResults()
    {
        gameResults.setVisible(true);
        ArrayList<String> prizeResults = arm.getPrizes();
        String imagePath1 = prizeResults.get(0);
        Image image1 = new Image(imagePath1);
        firstPrizeImageView.setImage(image1);
        String imagePath2 = prizeResults.get(1);
        Image image2 = new Image(imagePath2);
        secondPrizeImageView.setImage(image2);
        String imagePath3 = prizeResults.get(2);
        Image image3 = new Image(imagePath3);
        thirdPrizeImageView.setImage(image3);

    }

    public void handleKeyPressed(KeyEvent event)
    {
        switch(event.getCode())
        {
            case UP:
                arm.setDeltaY(-1.25);
                break;
            case DOWN:
                arm.setDeltaY(1.25);
                break;
            case LEFT:
                arm.setDeltaX(-1.25);
                base.setDeltaX(-1.25);
                break;
            case RIGHT:
                arm.setDeltaX(1.25);
                base.setDeltaX(1.25);
                break;
            default:
                break;
        }
    }

    public void handleKeyReleased(KeyEvent event) {
        switch (event.getCode())
        {
            case UP, DOWN:
                arm.setDeltaY(0);
                break;
            case LEFT, RIGHT:
                arm.setDeltaX(0);
                base.setDeltaX(0);
                break;
            default:
                break;
        }
    }


}