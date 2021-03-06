package com.example.kandoe.Utilities.DrawableGraphics;

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
 * Creates the background with help of paint
 */
public class Background extends View {
    Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Rect border;
    Drawable d;

    public Background(Context context, int height, Canvas container) {
        super(context);

        paint.setColor(Color.LTGRAY);

        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;

        d = context.getResources().getDrawable(R.drawable.bgcartoon);

        if (d != null) {
            d.setBounds(0, 0, width, (int) (height));
        }

        border = new Rect(0, height - 2, width, height + 2);

        d.draw(container);
        container.drawRect(border, paint);

    }

    public Background(Context context, int height) {
        super(context);
        paint.setColor(Color.LTGRAY);

        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;

        d = context.getResources().getDrawable(R.drawable.bgcartoon);

        if (d != null) {
            d.setBounds(0, 0, width, (int) (height));
        }

        border = new Rect(0, height - 2, width, height + 2);
    }



    @Override
    public void onDraw(Canvas canvas) {
        d.draw(canvas);
        canvas.drawRect(border, paint);
    }
}
