package com.stevedamien.views.joystick;

import android.util.Log;

import com.stevedamien.gamecontroller.SoloGameController;

import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Integer.parseInt;

/**
 * Created by Damien on 20/02/14.
 */
public class CustomJoystickMovedListener implements JoystickMovedListener {

    private SoloGameController soloGameController;
    private Timer timer;
    private boolean canIMove;

    private float currentX;
    private float currentY;

    private float destX;
    private float destY;

    public CustomJoystickMovedListener(SoloGameController controller)
    {
        this.soloGameController = controller;
        this.canIMove = true;
        this.timer = new Timer();

        String current = soloGameController.getCurrentCoord();
        int _curx = parseInt(current.split("--")[0]);
        int _cury = parseInt(current.split("--")[1]);

        this.currentX = _curx +10.0f;
        this.currentY = _cury + 10.0f;
        this.destX = this.currentX;
        this.destY = this.currentY;
    }



    @Override
    public void OnMoved(int pan, int tilt) {
        if(canIMove)
        {
            int primaryDirection = -1;
            int secondaryDirection = -1;
            if(destX == currentX && destY == currentY)
            {
                StringBuilder sb = new StringBuilder();
                if(Math.abs(pan) > 80 || Math.abs(tilt) > 80)
                {
                    if(Math.abs(pan) > Math.abs(tilt))
                    {
                        if(tilt > 0)
                        {
                            secondaryDirection = 2;
                            sb.append(" SEC : BOT");

                        }
                        else
                        {
                            secondaryDirection = 0;
                            sb.append(" SEC : TOP");
                        }

                        if(pan > 0)
                        {
                            primaryDirection = 1;
                            sb.append(" PRIM : RIGHT " + sb.toString());
                        }
                        else
                        {
                            primaryDirection = 3;
                            sb.append(" PRIM : LEFT " + sb.toString());
                        }
                    }
                    else
                    {
                        if(pan > 0)
                        {
                            secondaryDirection = 1;
                            sb.append(" SEC : RIGHT");
                        }
                        else
                        {
                            secondaryDirection = 3;
                            sb.append(" SEC : LEFT");
                        }

                        if(tilt > 0)
                        {
                            primaryDirection = 2;
                            sb.append(" PRIM : BOT " + sb.toString());
                        }
                        else
                        {
                            primaryDirection = 0;
                            sb.append(" PRIM : TOP " + sb.toString());
                        }
                    }
                }
                Log.d("DEBUGDAMIEN", sb.toString());
                if(primaryDirection != -1 && secondaryDirection != -1 && soloGameController.askToMove(primaryDirection, secondaryDirection) )
                {
                    canIMove = false;
                    runTimer();
                }

            }
        }
    }

    @Override
    public void OnReleased() {

    }

    @Override
    public void OnReturnedToCenter() {

    }

    private void runTimer()
    {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                canIMove = true;
            }
        }, 333);
    }
}
