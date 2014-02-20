package com.stevedamien.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

import com.stevedamien.gamecontroller.SoloGameController;
import com.stevedamien.model.Grid;

/**
 * Created by Damien on 18/02/14.
 */
public class MazeGridView extends View {
    private Paint paint;
    private SoloGameController soloGameController;
    public MazeGridView(Context context, SoloGameController soloGameController) {
        super(context);
        this.paint = new Paint();
        this.soloGameController = soloGameController;
        paint.setColor(Color.BLACK);
    }

    @Override
    public void onDraw(Canvas canvas) {
        Log.d("DEBUGDAMIEN", "ONDRAWGRID");

        System.out.println(this.soloGameController.getGrid());
        for(int j = 0 ; j < soloGameController.getGrid().getSizeY() ; ++j)
        {
            for(int i = 0 ; i < soloGameController.getGrid().getSizeX() ; ++i)
            {
                if(soloGameController.getGrid().get(i,j).getWalls()[0]) //TOP
                {
                    canvas.drawLine(20*i, 20*j, 20*(i+1), 20*j, paint);
                }
                if(soloGameController.getGrid().get(i,j).getWalls()[1]) //RIGHT
                {
                    canvas.drawLine(20*(i+1), 20*j, 20*(i+1), 20*(j+1), paint);
                }
                if(soloGameController.getGrid().get(i,j).getWalls()[2]) //BOTTOM
                {
                    canvas.drawLine(20*i, 20*(j+1), 20*(i+1), 20*(j+1), paint);
                }
                if(soloGameController.getGrid().get(i,j).getWalls()[3]) //LEFT
                {
                    canvas.drawLine(20*i, 20*j, 20*(i), 20*(j+1), paint);
                }
            }
        }
    }
}
