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
