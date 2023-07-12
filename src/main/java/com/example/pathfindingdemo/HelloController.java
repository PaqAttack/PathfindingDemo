package com.example.pathfindingdemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class HelloController {
    private final int MAX_WIDTH = 45;
    private final int MAX_HEIGHT = 45;

    @FXML
    private TextField hSize;
    @FXML
    private TextField vSize;
    @FXML
    private Canvas myCanvas;

    private CanvasManager cm;

    int height;
    int width;

    public void generateGrid(ActionEvent event) {
        // Create Canvas manager if null
        if (cm == null) {
            cm = new CanvasManager(myCanvas.getGraphicsContext2D());
        }
        cm.setCanvasSize((int) myCanvas.getHeight(), (int) myCanvas.getWidth());

        // save requested height and width
        height = Math.min(Integer.parseInt(vSize.getText()), MAX_HEIGHT);
        width = Math.min(Integer.parseInt(hSize.getText()), MAX_WIDTH);

        hSize.setText(Integer.toString(height));
        vSize.setText(Integer.toString(width));

        // Generate the grid
        cm.drawGrid(height, width);
    }

}