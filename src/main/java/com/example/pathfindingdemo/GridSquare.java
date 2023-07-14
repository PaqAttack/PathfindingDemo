package com.example.pathfindingdemo;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GridSquare {
    Rectangle rect;
    Color color;

    public GridSquare(Color color) {
        this.color = color;
        this.rect = new Rectangle();
    }

    public Color getColor() {
        return color;
    }

    public void setRect(int x1, int y1, int size) {
        rect.setX(x1);
        rect.setY(y1);
        rect.setHeight(size);
        rect.setWidth(size);
    }

    public Rectangle getRect() {
        return rect;
    }
}
