package com.example.pathfindingdemo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class CanvasManager {

    private GraphicsContext gc;

    private final int GRID_LINE_SIZE = 2;
    private final int MAX_SQUARE_SIZE = 50;
    private final int MIN_SQUARE_SIZE = 10;
    private final int VERTICAL_BUFFER = 20;

    private final int topX = 20;
    private final int topY = 20;

    private int canvasWidth, canvasHeight;
    private ArrayList<Line> lines = new ArrayList<Line>();

    public CanvasManager(GraphicsContext gc) {
        this.gc = gc;
    }

    public void setCanvasSize(int height, int width) {
        canvasHeight = height;
        canvasWidth = width;
    }

    public void drawGrid(int height, int width) {

        lines.clear();

        int largestValue = Math.max(width, height);
        int borderTotalSize = (GRID_LINE_SIZE * largestValue) + 1;

        int squareSizeToUse = -1;
        for (int i = MAX_SQUARE_SIZE; i >= MIN_SQUARE_SIZE; i--) {
            if ((i * largestValue) + borderTotalSize < Math.min(canvasHeight - VERTICAL_BUFFER, canvasWidth)) {
                squareSizeToUse = i;
                System.out.println(squareSizeToUse);
                break;
            }
        }

        if (squareSizeToUse == -1) {
            System.out.println("ERROR with size being too large");
        }

        int totalWidth = ((squareSizeToUse * width) + borderTotalSize);
        int totalHeight = ((squareSizeToUse * height) + borderTotalSize);

        int gridStartX = (canvasWidth / 2) - (totalWidth / 2);
        int gridStartY = ((canvasHeight - VERTICAL_BUFFER) / 2) - (totalHeight / 2) + VERTICAL_BUFFER;

        for (int x = 0; x <= width; x++) {
            lines.add(new Line(gridStartX + (x * (GRID_LINE_SIZE + squareSizeToUse)), gridStartY, gridStartX + (x * (GRID_LINE_SIZE + squareSizeToUse)), gridStartY + totalHeight));
        }

        for (int x = 0; x <= height; x++) {
            lines.add(new Line(gridStartX, gridStartY + (x * (GRID_LINE_SIZE + squareSizeToUse)), gridStartX + totalWidth, gridStartY + (x * (GRID_LINE_SIZE + squareSizeToUse))));
        }

        redraw();
    }

    public void redraw() {
        gc.clearRect(0, 0, canvasWidth, canvasHeight);
        gc.setStroke(Color.BLACK);

        gc.setFill(Color.BLACK);
        gc.setLineWidth(GRID_LINE_SIZE);

        for (Line line: lines) {
            gc.strokeLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
        }
    }
}
