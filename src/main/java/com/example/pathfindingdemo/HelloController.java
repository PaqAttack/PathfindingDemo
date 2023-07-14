package com.example.pathfindingdemo;

import com.example.pathfindingdemo.Maps.RandomMap;
import com.example.pathfindingdemo.Maps.iCollisionMap;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

public class HelloController {
    private final int MAX_WIDTH = 45;
    private final int MAX_HEIGHT = 45;

    @FXML
    private TextField hSize;
    @FXML
    private TextField vSize;
    @FXML
    private Canvas myCanvas;
    @FXML
    private Button genMapBtn;
    @FXML
    private ComboBox<String> mapOptsDropDown;

    private CanvasManager cm;

    int height;
    int width;

    public void generateGrid(ActionEvent event) {
        // Create Canvas manager if null
        if (cm == null) {
            cm = new CanvasManager(myCanvas.getGraphicsContext2D());
        }

        // account for resizing
        cm.setCanvasSize((int) myCanvas.getHeight(), (int) myCanvas.getWidth());

        //populate drop down if necessary.
        if (mapOptsDropDown.getItems().isEmpty()) {
            mapOptsDropDown.getItems().addAll("Random", "Option 2");
        }

        // clamp values within acceptable range
        height = Math.min(Integer.parseInt(vSize.getText()), MAX_HEIGHT);
        width = Math.min(Integer.parseInt(hSize.getText()), MAX_WIDTH);

        // Display value to be used
        vSize.setText(Integer.toString(height));
        hSize.setText(Integer.toString(width));

        // Generate the grid
        cm.drawGrid(height, width);
    }

    public void generateMap() {
        // get map
        iCollisionMap rndMap = new RandomMap();
        ArrayList<GridSquare> myMap = rndMap.generateMap(height, width);

        // draw map
        cm.processTiles(myMap);
    }



}