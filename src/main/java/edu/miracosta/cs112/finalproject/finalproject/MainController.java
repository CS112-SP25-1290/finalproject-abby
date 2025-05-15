package edu.miracosta.cs112.finalproject.finalproject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import java.io.IOException;
import javafx.scene.layout.Pane;

public class MainController {
    @FXML
    private Label welcomeText;
    @FXML
    private ProgressBar timerProgressBar;
    @FXML
    private Pane gameResults;
    @FXML
    private Pane gamePane;

    public ObservableList<Node> getObservableList()
    {
        return gamePane.getChildren();
    }

    public void initialize()
    {
        //Claw image traversable
        //Claw request focus
        //initialize claw
        //initialize claw machine
    }

    public void handleExitButton()
    {
        System.exit(0);
    }

    public void handleRightButton()
    {

    }

    public void handleLeftButton()
    {

    }

    public void handleGrabButton()
    {

    }


}