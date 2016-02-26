package com.example.kandoe.DrawableGraphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.View;

import com.example.kandoe.R;

/**
 * Created by Thomas on 2016-02-23.
 */
public class Background extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Rect rect;
    Drawable d;

    public Background(Context context) {
        super(context);
        paint.setColor(Color.LTGRAY);

        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        d = getResources().getDrawable(R.drawable.bgcartoon);


        d.setBounds(0, 0, width,(int) (height/2));


         rect = new Rect(0,0,width,height/2);
    }

    @Override
    protected void onDraw(Canvas canvas) {



d.draw(canvas);
       // canvas.drawRect(rect, paint);


    }
}
