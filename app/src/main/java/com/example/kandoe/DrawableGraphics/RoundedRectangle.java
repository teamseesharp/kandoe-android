package com.example.kandoe.DrawableGraphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by Thomas on 2016-02-22.
 * Class to create rounded rectangles with help of paint
 */
public class RoundedRectangle extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    int left;
    int top;
    int right;
    int bottom;
    int color;
    int stepNumber;
    RectF rectF;
    ArrayList<Rect> bulletPoints;

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
        this.setLayerType(LAYER_TYPE_SOFTWARE, paint);



        Rect rect = new Rect(left, top, right, bottom);

        //RectF for rounded corners
        rectF = new RectF(rect);
        paint.setColor(color);

        paint.setShadowLayer(4.0f, 0.0f, 2.0f, Color.BLACK);
    }

    public RoundedRectangle(Context context, int left, int top, int right, int bottom, int color, int stepNumber) {
        super(context);
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.color = color;
        this.stepNumber = stepNumber;
        this.setLayerType(LAYER_TYPE_SOFTWARE, paint);
        bulletPoints = new ArrayList<>();


        Rect rect = new Rect(left, top, right, bottom);

        //RectF for rounded corners
        rectF = new RectF(rect);
        paint.setColor(color);

        paint.setShadowLayer(4.0f, 0.0f, 2.0f, Color.BLACK);
    }

    @Override
    protected void onDraw(Canvas canvas) {




        canvas.drawRoundRect(rectF, 15, 15, paint);


    }


    public int getLeft2() {
        return left;
    }


    public int getTop2() {
        return top;
    }


    public int getRight2() {
        return right;
    }

    public int getBottom2() {
        return bottom;
    }

    public int getStepNumber() {
        return stepNumber;
    }

    public ArrayList<Rect> getBulletPoints() {
        return bulletPoints;
    }


}
