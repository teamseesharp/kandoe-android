package com.example.kandoe.Utilities.DrawableGraphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

import com.example.kandoe.Controller.CircleSessionController;
import com.example.kandoe.Model.Card;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Thomas on 2016-02-22.
 */
public class BulletPoint extends View {
    private Paint paint, paint2;
    private RectF rectF;
    private Card card;
    Bitmap bmDest;

    Rect rect;


    public BulletPoint(Context context) {
        super(context);

    }


    public BulletPoint(Context context, ArrayList<View> steps, Card card, ArrayList<RoundedRectangle> legs, CircleSessionController controller) {
        super(context);
        initValues(card, steps, legs, controller);

    }


    private void initValues(Card card, ArrayList steps, ArrayList<RoundedRectangle> legs, CircleSessionController controller) {

        this.card = card;

        //Paint init, layertype is needed for shadow
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.setLayerType(LAYER_TYPE_SOFTWARE, paint);
        paint.setColor(Color.parseColor(BulletColor.getColor(card.getId()).getHexCode()));
        paint.setShadowLayer(4.0f, 0.0f, 2.0f, Color.BLACK);

        paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint2.setColor(Color.WHITE);
        paint2.setTextSize(50);
        paint2.setTextAlign(Paint.Align.CENTER);
        paint2.setShadowLayer(0f, 0.0f, 0f, Color.BLACK);


        //Position bullet
        RoundedRectangle step = getStepOnPosition(card.getId(), steps);
        initValuesForPositon(step);


        //RectF for rounded corners
        rectF = new RectF(rect);

        bmDest = Bitmap.createBitmap(rect.width(), rect.height(), Bitmap.Config.RGB_565);


        Canvas mycanvas = new Canvas(bmDest);

        mycanvas.drawRoundRect(rectF, 100, 100, paint);
        drawRectText(String.valueOf(card.getId()), mycanvas, rectF);


    }

    private void initValuesForPositon(RoundedRectangle trede) {

        int size = 40;
        int yCenter = (trede.getBottom2() + trede.getTop2()) / 2;
        int xCenter = trede.getLeft2()+75+(100*trede.getBulletPoints().size());
        rect = new Rect(xCenter - size, yCenter - size, xCenter + size, yCenter + size);
        trede.getBulletPoints().add(rect);
    }


    private RoundedRectangle getStepOnPosition(int cardId, ArrayList<RoundedRectangle> steps) {

        //TODO Call naar positie card
        int position = new Random().nextInt(steps.size());


        for (RoundedRectangle step : steps) {
            if (step.getStepNumber() == position) return step;
        }

        return null;

    }

    @Override
    protected void onDraw(Canvas canvas) {

        //canvas.setBitmap(bmDest);

        //  canvas.drawBitmap(bmDest, bmDest.getWidth(), bmDest.getHeight(), null);

        canvas.drawRoundRect(rectF, 100, 100, paint);
        drawRectText(String.valueOf(card.getId()), canvas, rectF);


    }


    private void drawRectText(String text, Canvas canvas, RectF r) {


        float width = r.width();

        int numOfChars = paint2.breakText(text, true, width, null);
        int start = (text.length() - numOfChars) / 2;
        canvas.drawText(text, start, start + numOfChars, r.centerX(), r.centerY() + 20, paint2);
    }


}