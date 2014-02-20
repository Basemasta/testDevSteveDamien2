package com.stevedamien.gamecontroller;

import com.stevedamien.activities.DisplayGridActivity;
import com.stevedamien.model.Grid;
import com.stevedamien.model.Case;
import com.stevedamien.model.MazeGenerator;

/**
 * Created by Damien on 17/02/14.
 */
public class SoloGameController {

    private Case currentCase;
    int sizeX, sizeY;
    private Grid grid;
    private DisplayGridActivity displayGridActivity;
    public SoloGameController(int sizeX, int sizeY, DisplayGridActivity displayGridActivity)
    {
        this.grid = new Grid(sizeX, sizeY);
        this.displayGridActivity = displayGridActivity;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        MazeGenerator mazeGenerator = new MazeGenerator(this.grid);
        mazeGenerator.runGenerator();
        currentCase = this.grid.get(0,0);
        System.out.println(this.grid);
    }

    public String[][] getByteRepresentation()
    {
        String[][] returnvalue = new String[sizeX][sizeY];
        for(int j = 0 ; j < sizeY ; ++j)
        {
            for(int i = 0 ; i < sizeX ; ++i)
            {
                StringBuilder sb = new StringBuilder();
                for(int direction = 0 ; direction < 4 ; direction++)
                {
                    if(this.grid.get(i,j).getWalls()[direction])
                    {
                        sb.append("1");
                    }
                    else
                    {
                        sb.append("0");
                    }
                }
                returnvalue[i][j] = sb.toString();
            }
        }
        return returnvalue;
    }


    public String getCurrentCoord()
    {
        return currentCase.getCoord_x()+"--"+currentCase.getCoord_y();
    }

    public boolean askToMove(int premaryDirection, int secondaryDirection)
    {
        if(currentCase.getVoisin(premaryDirection) != null && currentCase.getWalls()[premaryDirection] == false)
        {
            currentCase = currentCase.getVoisin(premaryDirection);
            displayGridActivity.update(currentCase.getCoord_x(), currentCase.getCoord_y());
            return true;
        }
        else
        {
            if(currentCase.getVoisin(secondaryDirection) != null && currentCase.getWalls()[secondaryDirection] == false)
            {
                currentCase = currentCase.getVoisin(secondaryDirection);
                displayGridActivity.update(currentCase.getCoord_x(), currentCase.getCoord_y());
                return true;
            }
        }

        return false;
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }
}
