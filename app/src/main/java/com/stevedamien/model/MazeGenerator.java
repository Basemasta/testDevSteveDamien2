/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevedamien.model;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import com.stevedamien.model.Case;
import com.stevedamien.model.Grid;

/**
 *
 * @author Damien
 */
public class MazeGenerator {

    private Grid grid;

    private List<Case> partsOfTheMaze;
    private Map<Case, List<Integer>> wallList;
    private int SIZE_X;
    private int SIZE_Y;

    public MazeGenerator(Grid grid) {
        this.grid = grid;
        this.partsOfTheMaze = new ArrayList<>();
        this.wallList = new HashMap<>();
        this.SIZE_X = grid.getSizeX();
        this.SIZE_Y = grid.getSizeY();
    }

    private void getFirstCase() {
        Random random = new Random();
        int _r = random.nextInt(100);
        int startx, starty;
        if (_r % 2 == 0) {
            startx = 0;
            starty = random.nextInt(SIZE_Y - 1);
        } else {
            startx = random.nextInt(SIZE_X - 1);
            starty = 0;
        }
        this.partsOfTheMaze.add(this.grid.get(startx, starty));
        for (int direction = 0; direction < 4; ++direction) {
            if (this.grid.get(startx, starty).getVoisin(direction) != null) {
                if (this.grid.get(startx, starty).getWalls()[direction]) {
                    if (this.wallList.get(this.grid.get(startx, starty)) == null) {
                        this.wallList.put(this.grid.get(startx, starty), new ArrayList<Integer>());
                    }
                    this.wallList.get(this.grid.get(startx, starty)).add(direction);
                }
            }
        }
    }

    private Entry<Case, Integer> getRandom() {
        int size = 0;
        Entry<Case, Integer> returnvalue;
        for (Entry<Case, List<Integer>> aEntry : this.wallList.entrySet()) {
            size += aEntry.getValue().size();
        }
        int _rand = new Random().nextInt(size);
        for (Entry<Case, List<Integer>> aEntry : this.wallList.entrySet()) {
            if (_rand <= aEntry.getValue().size()) {
                returnvalue = new AbstractMap.SimpleEntry<>(aEntry.getKey(), aEntry.getValue().get(Math.max(_rand - 1, 0)));
                return returnvalue;
            } else {
                _rand -= aEntry.getValue().size();
            }
        }
        return null;
    }

    private void addWallsToWallList(Case c) {
        for (int i = 0; i < 4; ++i) {
            if (c.getVoisin(i) != null) {
                if (!this.wallList.containsKey(c)) {
                    this.wallList.put(c, new ArrayList<Integer>());
                }
                this.wallList.get(c).add(i);
            }
        }
    }

    public void runGenerator() {
        for (int i = 0; i < SIZE_X; ++i) {
            for (int j = 0; j < SIZE_Y; ++j) {
                Boolean[] wallstmp = new Boolean[4];
                for (int _i = 0; _i < 4; ++_i) {
                    wallstmp[_i] = true;
                }
                this.grid.get(i, j).setWalls(wallstmp);
            }//FOR SIZE_Y
        }//FOR SIZE_X

        this.getFirstCase();

        while (!this.wallList.isEmpty()) {
            Entry<Case, Integer> aWall = this.getRandom();
            if (!this.partsOfTheMaze.contains(aWall.getKey().getVoisin(aWall.getValue()))) {
                if (aWall.getKey().getVoisin(aWall.getValue()) != null) {
                    aWall.getKey().getWalls()[aWall.getValue()] = false;
                    aWall.getKey().getVoisin(aWall.getValue()).getWalls()[Grid.getOpposite(aWall.getValue())] = false;
                    this.partsOfTheMaze.add(aWall.getKey().getVoisin(aWall.getValue()));
                    addWallsToWallList(aWall.getKey().getVoisin(aWall.getValue()));
                }
            } else {
                this.wallList.get(aWall.getKey()).remove(aWall.getValue());
                if (this.wallList.get(aWall.getKey()).isEmpty()) {
                    this.wallList.remove(aWall.getKey());
                }
            }

        }
    }//FUNCTION
}
