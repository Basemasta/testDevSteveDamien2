/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stevedamien.model;

/**
 *
 * @author Damien
 */
public class Grid {

    private Case[][] grid;

    public int getSizeX() {
        return SIZE_X;
    }

    public int getSizeY() {
        return SIZE_Y;
    }

    private int SIZE_X;
    private int SIZE_Y;
    public static final int TOP = 0;
    public static final int RIGHT = 1;
    public static final int BOTTOM = 2;
    public static final int LEFT = 3;

    public Grid(Case[][] grid) {
        this.grid = grid;
    }

    public Grid(int SizeX, int SizeY) {
        this.SIZE_X = SizeX;
        this.SIZE_Y = SizeY;
        this.grid = new Case[SIZE_X][SIZE_Y];
        for (int i = 0; i < SIZE_X; ++i) {
            for (int j = 0; j < SIZE_Y; ++j) {
                this.grid[i][j] = new Case(i, j);
            }
        }
        for (int i = 0; i < SIZE_X; ++i) {
            for (int j = 0; j < SIZE_Y; ++j) {
                if (j > 0) {
                    this.grid[i][j].setVoisin(TOP, this.grid[i][j - 1]);
                }
                if (i > 0) {
                    this.grid[i][j].setVoisin(LEFT, this.grid[i - 1][j]);
                }
                if (i < SIZE_X - 1) {
                    this.grid[i][j].setVoisin(RIGHT, this.grid[i + 1][j]);
                }
                if (j < SIZE_Y - 1) {
                    this.grid[i][j].setVoisin(BOTTOM, this.grid[i][j + 1]);
                }

            }
        }
    }

    public Case get(int x, int y) {
        return this.grid[x][y];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < SIZE_Y; ++j) {
            if (j == 0) {
                for (int _tmp = 0; _tmp < SIZE_X; ++_tmp) {
                    sb.append(" _");
                }
                sb.append("\n");
            }

            for (int i = 0; i < SIZE_X; ++i) {
                if ((this.grid[i][j].getVoisin(LEFT) != null
                        && this.grid[i][j].getWalls()[LEFT]) || i == 0) {
                    sb.append("|");
                } else {
                    sb.append(" ");
                }

                if ((this.grid[i][j].getVoisin(BOTTOM) != null
                        && this.grid[i][j].getWalls()[BOTTOM]) || j == SIZE_Y - 1) {
                    sb.append("_");
                } else {
                    sb.append(" ");
                }
                if (i == SIZE_X - 1) {
                    sb.append("|");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static final int getOpposite(int direction) {
        return (direction+2)%4;
    }
}
