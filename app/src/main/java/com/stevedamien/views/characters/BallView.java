package com.stevedamien.views.characters;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

/**
 * Created by Damien on 19/02/14.
 */
public class BallView extends View {

    private float coordX;
    private float coordY;

    public BallView(Context context, float coordX , float coordY)
    {
        super(context);
        this.coordX = coordX;
        this.coordY = coordY;
    }

    protected void onDraw(Canvas canvas){
        Log.d("DEBUGDAMIEN","ONDRAWBALL");
        super.onDraw(canvas);
        Paint paint = new Paint();

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        canvas.drawCircle(coordX, coordY, 8, paint);

    }
    public void setCurrentX(float currentX) {
        this.coordX = currentX;
    }

    public void setCurrentY(float currentY) {
        this.coordY = currentY;
    }
}
