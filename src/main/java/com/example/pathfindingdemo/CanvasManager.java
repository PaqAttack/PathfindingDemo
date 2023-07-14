package com.example.pathfindingdemo;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

import java.util.ArrayList;

public class CanvasManager {

    private GraphicsContext gc;

    public final int GRID_LINE_SIZE = 3;
    private final int MAX_SQUARE_SIZE = 50;
    private final int MIN_SQUARE_SIZE = 10;
    private final int VERTICAL_BUFFER = 20;

    public int gridStartX;
    public int gridStartY;
    public int squareSizeToUse = -1;
    private ArrayList<GridSquare> tiles = new ArrayList<>();
    private int canvasWidth, canvasHeight;
    private int gridWidth, gridHeight;
    private ArrayList<Line> lines = new ArrayList<Line>();
    public CanvasManager(GraphicsContext gc) {
        this.gc = gc;
    }

    public void setCanvasSize(int height, int width) {
        canvasHeight = height;
        canvasWidth = width;
    }

    public void drawGrid(int height, int width) {
        gridHeight = height;
        gridWidth = width;

        lines.clear();

        // dertermine longest side and ensure that fits
        int largestValue = Math.max(gridWidth, gridHeight);

        // determine the total longest side size from borders
        int longestBorderTotalSize = (GRID_LINE_SIZE * largestValue) + GRID_LINE_SIZE;

        // Reduce square size from max to min to see how big the squares can be and still be visible.
        squareSizeToUse = -1;
        for (int i = MAX_SQUARE_SIZE; i >= MIN_SQUARE_SIZE; i--) {
            if ((i * largestValue) + longestBorderTotalSize < Math.min(canvasHeight - VERTICAL_BUFFER, canvasWidth)) {
                squareSizeToUse = i;
                break;
            }
        }

        // error catch. This would indicate that the smallest size square is too big at the requested quantity.
        // If this triggers the cap needs to be reduced in HelloController or the minimum square size needs to be reduced.
        if (squareSizeToUse == -1) {
            System.out.println("ERROR with size being too large");
        }

        // calculate total width of squares and lines
        int totalWidth = (squareSizeToUse * gridWidth) + ((GRID_LINE_SIZE * gridWidth) + 1);
        int totalHeight = (squareSizeToUse * gridHeight) + ((GRID_LINE_SIZE * gridHeight) + 1);

        // determine top left corner
        gridStartX = (canvasWidth / 2) - (totalWidth / 2);
        gridStartY = ((canvasHeight - VERTICAL_BUFFER) / 2) - (totalHeight / 2) + VERTICAL_BUFFER;

        // draw vertival lines
        for (int x = 0; x <= gridWidth; x++) {
            int lineXcoord = gridStartX + (x * (GRID_LINE_SIZE + squareSizeToUse)) + GRID_LINE_SIZE;
            lines.add(new Line(lineXcoord, gridStartY, lineXcoord, gridStartY + totalHeight - GRID_LINE_SIZE + 1));
        }

        // draw horizontal lines
        for (int x = 0; x <= gridHeight; x++) {
            int lineYcoord = gridStartY + (x * (GRID_LINE_SIZE + squareSizeToUse));
            lines.add(new Line(gridStartX + GRID_LINE_SIZE, lineYcoord, gridStartX + totalWidth, lineYcoord));
        }

        // draw the grid
        redraw();
    }

    public void redraw() {
        // clear canvas
        gc.clearRect(0, 0, canvasWidth, canvasHeight);
        gc.setStroke(Color.BLACK);

        // set properties
        gc.setFill(Color.BLACK);
        gc.setLineWidth(GRID_LINE_SIZE);

        // draw grid lines
        for (Line line: lines) {
            gc.strokeLine(line.getStartX(), line.getStartY(), line.getEndX(), line.getEndY());
        }
    }

    public void processTiles(ArrayList<GridSquare> map) {
        int tileIndex = 0;

        int xpos = gridStartX + (GRID_LINE_SIZE * 2);
        int ypos = gridStartY + GRID_LINE_SIZE;
        int size = squareSizeToUse - GRID_LINE_SIZE;

        // sets rectangle position for each element based on current grid
        for (int row = 0; row < gridHeight; row++) {
            for (int col = 0; col < gridWidth; col++) {
                map.get(tileIndex).setRect(xpos, ypos, size);
                tileIndex++;
                xpos += size + (GRID_LINE_SIZE * 2);
            }
            xpos = gridStartX + (GRID_LINE_SIZE * 2);
            ypos += size + (GRID_LINE_SIZE * 2);
        }

        drawTiles(map);
    }

    public void drawTiles(ArrayList<GridSquare> map) {
        for (GridSquare tile : map) {
            gc.setFill(tile.getColor());
            gc.fillRect(tile.getRect().getX(), tile.getRect().getY(), tile.getRect().getHeight(), tile.getRect().getWidth());
        }

    }

}
