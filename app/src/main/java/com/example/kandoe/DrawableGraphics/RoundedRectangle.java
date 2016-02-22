package com.example.kandoe.DrawableGraphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

/**
 * Created by Thomas on 2016-02-22.
 */


public class RoundedRectangle extends View {
    Paint paint = new Paint();
    int left;int top; int right; int bottom;int color;

    public RoundedRectangle(Context context) {
        super(context);

    }

    public RoundedRectangle(Context context, int left, int top, int right, int bottom, int color) {
        super(context);
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.color = color;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        paint.setColor(color);


        Rect rect = new Rect(left,top,right,bottom);
        RectF rectF = new RectF(rect);
        canvas.drawRoundRect(rectF, 15, 15, paint);

    }
}
