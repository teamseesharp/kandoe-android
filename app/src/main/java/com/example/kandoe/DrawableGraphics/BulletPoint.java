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
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Thomas on 2016-02-22.
 */
public class BulletPoint extends View {
    private Paint paint;
    int left;
    int top;
    int right;
    int bottom;
    int color;
    private RoundedRectangle trede;
    private RectF rectF;

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
        this.trede = (RoundedRectangle) trede;


        initValues();

    }

    private void initValues() {

        //Paint init, layertype is needed for shadow
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.setLayerType(LAYER_TYPE_SOFTWARE, paint);
        paint.setColor(Color.BLUE);
        paint.setShadowLayer(4.0f, 0.0f, 2.0f, Color.BLACK);

        int size = 40;

        Random random = new Random();

        //Position bullet
        //int yCenter = this.trede.getBottom2() - this.trede.getTop2();

        int temp = trede.getRight2() - trede.getLeft2() - size * 2;
        int xCenter = random.nextInt(temp) + trede.getLeft2()+50;
      //  int xCenter = 450;
        int yCenter = random.nextInt(trede.getBottom2()-trede.getTop2())+trede.getTop2();


        Rect rect = new Rect(xCenter - size, yCenter - size, xCenter + size, yCenter + size);

        //RectF for rounded corners
        rectF = new RectF(rect);
    }

    @Override
    protected void onDraw(Canvas canvas) {




        canvas.drawRoundRect(rectF, 100, 100, paint);


    }


}
