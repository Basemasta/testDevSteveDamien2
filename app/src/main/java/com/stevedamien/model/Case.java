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
public class Case {

    private int coord_x;
    private int coord_y;

    private Case[] voisins;
    private Boolean[] walls;

    public int getCoord_x() {
        return coord_x;
    }

    public void setCoord_x(int coord_x) {
        this.coord_x = coord_x;
    }

    public int getCoord_y() {
        return coord_y;
    }

    public void setCoord_y(int coord_y) {
        this.coord_y = coord_y;
    }

    public Case[] getVoisins() {
        return voisins;
    }

    public void setVoisins(Case[] voisins) {
        this.voisins = voisins;
    }

    public Case(int coord_x, int coord_y, Case[] voisins, Boolean[] walls) {
        this.coord_x = coord_x;
        this.coord_y = coord_y;
        this.voisins = voisins;
        this.walls = walls;
    }

    public Case(int coord_x, int coord_y) {
        this.coord_x = coord_x;
        this.coord_y = coord_y;
        this.walls = new Boolean[4];
        this.voisins = new Case[4];
    }

    public Case getVoisin(int i) {
        return this.voisins[i];
    }

    public boolean isThereAWallBetween(int i) {
        return this.walls[i];
    }

    public Boolean[] getWalls() {
        return walls;
    }

    public void setWalls(Boolean[] walls) {
        this.walls = walls;
    }

    public void setWall(int i, boolean wall) {
        this.walls[i] = wall;
    }

    void setVoisin(int direction, Case aCase) {
        this.voisins[direction] = aCase;
    }
    
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(this.coord_x);
        sb.append(" / ");
        sb.append(this.coord_y);
        return sb.toString();
    }

}
