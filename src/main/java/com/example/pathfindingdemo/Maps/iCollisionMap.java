package com.example.pathfindingdemo.Maps;

import com.example.pathfindingdemo.GridSquare;

import java.util.ArrayList;

public interface iCollisionMap {
    ArrayList<GridSquare> generateMap(int cols, int rows);

}
