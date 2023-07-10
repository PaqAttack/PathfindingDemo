package com.example.pathfindingdemo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private TextField hSize;
    @FXML
    private TextField vSize;
    @FXML
    private Canvas myCanvas;

    int height;
    int width;

    public void generateGrid(ActionEvent event) {
        height = Integer.parseInt(vSize.getText());
        width = Integer.parseInt(hSize.getText());

        System.out.println(height + width);

    }



}