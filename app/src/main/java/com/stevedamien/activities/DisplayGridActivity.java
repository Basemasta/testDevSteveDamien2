package com.stevedamien.activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.stevedamien.R;
import com.stevedamien.gamecontroller.SoloGameController;
import com.stevedamien.views.MazeGridView;
import com.stevedamien.views.characters.BallView;
import com.stevedamien.views.joystick.CustomJoystickMovedListener;
import com.stevedamien.views.joystick.JoystickClickedListener;
import com.stevedamien.views.joystick.JoystickMovedListener;
import com.stevedamien.views.joystick.JoystickView;

import static java.lang.Integer.parseInt;

public class DisplayGridActivity extends Activity  {

    private SoloGameController soloGameController;

    //Game Part
    private int sizeX;
    private int sizeY;
    private MazeGridView mazeGridView;

    private TextView textViewTilt, textViewPan;

    private RelativeLayout gridLayout;
    private JoystickView joystickView;
    private BallView ballView;

    private float currentX;
    private float currentY;

    private float destX;
    private float destY;

    //JoyStick
    private LinearLayout buttonLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_grid);

        this.sizeX = parseInt(this.getIntent().getStringExtra("SIZEX"));
        this.sizeY = parseInt(this.getIntent().getStringExtra("SIZEY"));
        this.soloGameController = new SoloGameController(sizeX, sizeY,this);
        String current = soloGameController.getCurrentCoord();
        int _curx = parseInt(current.split("--")[0]);
        int _cury = parseInt(current.split("--")[1]);

        this.currentX = _curx +10.0f;
        this.currentY = _cury + 10.0f;
        this.destX = this.currentX;
        this.destY = this.currentY;

        this.gridLayout = (RelativeLayout) findViewById(R.id.gridArea);
        this.mazeGridView = new MazeGridView(this, soloGameController);
        Log.d("DEBUGDAMIEN" , soloGameController.getGrid().toString());
        this.ballView = new BallView(this,currentX, currentY );
        Log.d("DEBUGDAMIEN", "_curx = " + _curx + " _cury = " + _cury);

        this.gridLayout.addView(this.mazeGridView);
        this.gridLayout.addView(this.ballView,1);

        this.buttonLayout = (LinearLayout) findViewById(R.id.buttonArea);
        this.joystickView = (JoystickView) findViewById(R.id.joystick);
        this.textViewTilt = (TextView) findViewById(R.id.textViewTilt);
        this.textViewPan = (TextView) findViewById(R.id.textViewPan);

        this.joystickView.setOnJostickMovedListener(new CustomJoystickMovedListener(this.soloGameController));

        Thread myThread = new Thread(new UpdateThread());
        myThread.start();
    }

    public Handler updateHandler = new Handler(){
        /** Gets called on every message that is received */
        @Override
        public void handleMessage(Message msg) {
            ballView.invalidate();
            super.handleMessage(msg);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.display_grid, menu);
        return true;
    }

    public void update(int coord_x, int coord_y) {
        this.destX = 20*coord_x +10;
        this.destY = 20*coord_y +10;
    }


    public class UpdateThread implements Runnable {

        @Override
        public void run() {
            while(true){

                if(destY != currentY || destX != currentX)
                {
                    Log.d("DEBUGDAMIEN", " CX : " + currentX+" DX : " + destX+"CY : " +currentY+ "DY : " + destY);
                    if(destY > currentY)
                    {
                        //monte
                        ballView.setCurrentY(currentY+1.0f);
                        currentY+=1.0f;
                    }
                    else
                    {
                        if(destY < currentY)
                        {
                            ballView.setCurrentY(currentY-1.0f);
                            currentY-=1.0f;
                        }
                    }

                    if(destX > currentX)
                    {
                        //monte
                        ballView.setCurrentX(currentX + 1.0f);
                        currentX+=1.0f;
                    }
                    else
                    {
                        if(destX < currentX)
                        {
                            ballView.setCurrentX(currentX-1.0f);
                            currentX-=1.0f;
                        }
                    }

                    DisplayGridActivity.this.updateHandler.sendEmptyMessage(0);
                }
            }
        }

    }
}
