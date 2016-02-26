package com.example.kandoe.Utilities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Thomas on 2016-02-23.
 */
public class Utilities {

  public static Bitmap getBitmapFromView(View view) {
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null)
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        else
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }


    public static boolean CheckCollision(View v1, Rect v2) {
        Rect R1 = new Rect(v1.getLeft(), v1.getTop(), v1.getRight(), v1.getBottom());
        //   Rect R2=new Rect(v2.getLeft(), v2.getTop(), v2.getRight(), v2.getBottom());
        return R1.intersect(v2);
    }

    public static int randInt(int min, int max) {

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            return ThreadLocalRandom.current().nextInt(min, max);
        } else {
            Random rand = new Random();

            // nextInt is normally exclusive of the top value,
            // so add 1 to make it inclusive

            return rand.nextInt((max - min) + 1) + min;
        }

    }
}
