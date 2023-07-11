package com.example.pathfindingdemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class HelloController {
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
        height = Integer.parseInt(vSize.getText());
        width = Integer.parseInt(hSize.getText());

        // Generate the grid
        cm.drawGrid(height, width);
    }

    public void clearGrid() {
        cm.clearGrid();
    }

}