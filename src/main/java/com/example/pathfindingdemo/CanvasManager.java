package com.example.pathfindingdemo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CanvasManager {

    private GraphicsContext gc;

    private final int MAX_WIDTH = 30;
    private final int MAX_HEIGHT = 30;
    private final int GRID_LINE_SIZE = 1;

    private final int MAX_SQUARE_SIZE = 50;
    private final int MIN_SQUARE_SIZE = 10;
    private final int VERTICAL_BUFFER = 20;
    private final int HORIZONTAL_BUFFER = 20;

    private final int topX = 20;
    private final int topY = 20;

    private int canvasWidth, canvasHeight;

    public CanvasManager(GraphicsContext gc) {
        this.gc = gc;
    }

    public void setCanvasSize(int height, int width) {
        canvasHeight = height;
        canvasWidth = width;
    }

    public void drawGrid(int height, int width) {
        int squaresH = Math.min(height, MAX_HEIGHT);
        int squaresW = Math.min(width, MAX_WIDTH);

        int largestValue = Math.max(squaresW, squaresH);
        int borderTotalSize = (GRID_LINE_SIZE * largestValue) + 1;

        int squareSizeToUse = -1;
        for (int i = MAX_SQUARE_SIZE; i >= MIN_SQUARE_SIZE; i--) {
            if ((i * largestValue) + borderTotalSize < Math.min(canvasHeight, canvasWidth)) {
                squareSizeToUse = i;
                System.out.println(squareSizeToUse);
                break;
            }
        }

        if (squareSizeToUse == -1) {
            System.out.println("ERROR with size being too large");
        }

        int totalWidth = ((squareSizeToUse * squaresW) + borderTotalSize);
        int totalHeight = ((squareSizeToUse * squaresH) + borderTotalSize);

        int gridStartX = (canvasWidth / 2) - (totalWidth / 2);
        int gridStartY = (canvasHeight / 2) - (totalHeight / 2);

        clearGrid();

        for (int x = 0; x <= squaresW; x++) {
            gc.setFill(Color.BLACK);
            gc.setLineWidth(GRID_LINE_SIZE);

            gc.moveTo(gridStartX + (x * (GRID_LINE_SIZE + squareSizeToUse)), gridStartY);
            gc.lineTo(gridStartX + (x * (GRID_LINE_SIZE + squareSizeToUse)), gridStartY + totalHeight);
            gc.stroke();
        }

        for (int x = 0; x <= squaresH; x++) {
            gc.moveTo(gridStartX, gridStartY + (x * (GRID_LINE_SIZE + squareSizeToUse)));
            gc.lineTo(gridStartX + totalWidth, gridStartY + (x * (GRID_LINE_SIZE + squareSizeToUse)));
            gc.stroke();
        }


    }

    public void clearGrid() {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvasWidth, canvasHeight);
    }
}
