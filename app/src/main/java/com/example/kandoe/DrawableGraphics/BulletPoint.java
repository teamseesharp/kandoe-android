package com.example.kandoe.DrawableGraphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

import com.example.kandoe.Controller.CircleSessionController;
import com.example.kandoe.Model.Card;
import com.example.kandoe.Utilities.Utilities;

import java.util.ArrayList;

/**
 * Created by Thomas on 2016-02-22.
 */
public class BulletPoint extends View {
    private Paint paint;
    int id;

    private RectF rectF;
    private Card card;

    Rect rect;


    public BulletPoint(Context context) {
        super(context);

    }


    public BulletPoint(Context context, ArrayList<View> steps, Card card, ArrayList<RoundedRectangle> legs, CircleSessionController controller) {
        super(context);
        initValues(card, steps, legs, controller);

    }


    private void initValues(Card card, ArrayList steps, ArrayList<RoundedRectangle> legs, CircleSessionController controller) {

        id = getId(card);
        this.card = card;

        RoundedRectangle trede = getStepOnPosition(id, steps);

        //Paint init, layertype is needed for shadow
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.setLayerType(LAYER_TYPE_SOFTWARE, paint);

        paint.setColor(Color.parseColor(BulletColor.getColor(id).getHexCode()));

        //paint.setColor(Color.rgb(34, 139, 34));


        paint.setShadowLayer(4.0f, 0.0f, 2.0f, Color.BLACK);


        //Position bullet
        initValuesForPositon(trede);


        //RectF for rounded corners
        rectF = new RectF(rect);


    }

    private void initValuesForPositon(RoundedRectangle trede) {

        int size = 40;


        int xCenter = Utilities.randInt(trede.getLeft2() + 50, trede.getRight2() - 50);


        int yCenter = (trede.getBottom2() + trede.getTop2()) / 2;
        rect = new Rect(xCenter - size, yCenter - size, xCenter + size, yCenter + size);
    }


    private RoundedRectangle getStepOnPosition(int position, ArrayList<RoundedRectangle> steps) {

        for (RoundedRectangle step : steps) {
            if (step.getStepNumber() == position - 1) return step;
        }

        return null;

    }

    @Override
    protected void onDraw(Canvas canvas) {


        canvas.drawRoundRect(rectF, 100, 100, paint);
        drawRectText(String.valueOf(card.getId()), canvas, rectF);


    }


    private void drawRectText(String text, Canvas canvas, RectF r) {

        Paint paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint2.setColor(Color.WHITE);
        paint2.setTextSize(50);
        paint2.setTextAlign(Paint.Align.CENTER);
        paint2.setShadowLayer(0f, 0.0f, 0f, Color.BLACK);
        float width = r.width();

        int numOfChars = paint2.breakText(text, true, width, null);
        int start = (text.length() - numOfChars) / 2;
        canvas.drawText(text, start, start + numOfChars, r.centerX(), r.centerY() + 20, paint2);
    }

    private int getId(Card card) {

        return card.getId();

    }


}
