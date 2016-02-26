package com.example.kandoe.DrawableGraphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

/**
 * Created by Thomas on 2016-02-22.
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


        Rect rect = new Rect(left, top, right, bottom);

        //RectF for rounded corners
        rectF = new RectF(rect);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        paint.setColor(color);

        paint.setShadowLayer(4.0f, 0.0f, 2.0f, Color.BLACK);


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
}
