package com.example.pathfindingdemo.Maps;

import com.example.pathfindingdemo.GridSquare;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class RandomMap implements iCollisionMap{

    @Override
    public ArrayList<GridSquare> generateMap(int rows, int cols) {
        ArrayList<GridSquare> tiles = new ArrayList<>(rows * cols);

        int[][] valueMap = new int[rows][cols];
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                valueMap[x][y] = Math.random() < 0.5 ? 1: 0;
            }
        }

//       DEBUG Code
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                System.out.print(valueMap[x][y] + ", ");
            }
            System.out.print("\n");
        }

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                Color color = (valueMap[x][y] == 1) ? Color.BLUE : Color.RED;
                tiles.add(new GridSquare(color));
            }
        }

        return tiles;
    }
}
