package com.example.kandoe.DrawableGraphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.Shape;
import android.view.View;

import java.util.Random;

/**
 * Created by Thomas on 2016-02-22.
 */
public class BulletPoint extends View {
    Paint paint = new Paint();
    int left;int top; int right; int bottom;int color;
    private View view;

    public BulletPoint(Context context) {
        super(context);

    }

    public BulletPoint(Context context, int left, int top, int right, int bottom, int color) {
        super(context);
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.color = color;
    }

    public BulletPoint(Context context, View trede) {
        super(context);
        this.view = trede;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        paint.setColor(Color.BLACK);


//verplaatsen naar andere methode
        int x = view.getLeft()+50;
        int y = view.getTop()-view.getBottom();


        Rect rect = new Rect(x-40,y-40,x+50,y+60);
        //Rect rect = new Rect(getLeft(),top,right,bottom);
        RectF rectF = new RectF(rect);
       canvas.drawRoundRect(rectF, 2, 2, paint);


    }
}
