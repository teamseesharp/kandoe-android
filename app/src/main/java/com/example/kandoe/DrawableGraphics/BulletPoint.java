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

import com.example.kandoe.Controller.CircleSessionController;
import com.example.kandoe.Model.Card;
import com.example.kandoe.Utilities.Utilities;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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
    int position;

    private RectF rectF;

    Rect rect;


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


    public BulletPoint(Context context, ArrayList<View> steps, Card card, ArrayList<RoundedRectangle> legs, CircleSessionController controller) {
        super(context);
        initValues(card, steps, legs, controller);

    }


    private void initValues(Card card, ArrayList steps, ArrayList<RoundedRectangle> legs, CircleSessionController controller) {

        position = getPosition(card);


        RoundedRectangle trede = getStepOnPosition(position, steps);

        //Paint init, layertype is needed for shadow
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.setLayerType(LAYER_TYPE_SOFTWARE, paint);
        paint.setColor(Color.rgb(34, 139, 34));
        paint.setShadowLayer(4.0f, 0.0f, 2.0f, Color.BLACK);


        //Position bullet
        initValuesForPositon(trede);

       /* boolean noCollision = false;


        while (!noCollision) {
            // for (RoundedRectangle leg : legs) {

            //  if (!Utilities.CheckCollision(leg, rect)){
            if (controller.getBulletPoints().isEmpty()){noCollision=true;}
            for (View bullet : controller.getBulletPoints()) {

                if (Utilities.CheckCollision(bullet, rect)) {
                    noCollision = true;
                } else {
                    initValuesForPositon(trede);
                    break;
                }
                // }
                //  }

            }


        }*/


        //RectF for rounded corners
        rectF = new RectF(rect);


    }

    private void initValuesForPositon(RoundedRectangle trede) {

        int size = 40;


       // int temp = trede.getRight2() - 50 - trede.getLeft2();


        int xCenter = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            xCenter = ThreadLocalRandom.current().nextInt(trede.getLeft2() + 50, trede.getRight2() - 50);
        }

        int yCenter = (trede.getBottom2() + trede.getTop2()) / 2;
        rect = new Rect(xCenter - size, yCenter - size, xCenter + size, yCenter + size);
    }


    private RoundedRectangle getStepOnPosition(int position, ArrayList<RoundedRectangle> steps) {

        for (RoundedRectangle step : steps) {
            if (step.getStepNumber() == position) return step;
        }

        return null;

    }

    @Override
    protected void onDraw(Canvas canvas) {


        canvas.drawRoundRect(rectF, 100, 100, paint);
        drawRectText(String.valueOf(position + 1), canvas, rectF);


    }


    private void drawRectText(String text, Canvas canvas, RectF r) {

        paint.setColor(Color.WHITE);
        paint.setTextSize(50);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setShadowLayer(0f, 0.0f, 0f, Color.BLACK);
        float width = r.width();

        int numOfChars = paint.breakText(text, true, width, null);
        int start = (text.length() - numOfChars) / 2;
        canvas.drawText(text, start, start + numOfChars, r.centerX(), r.centerY() + 20, paint);
    }

    private int getPosition(Card card) {

        return new Random().nextInt(4);

    }


}
