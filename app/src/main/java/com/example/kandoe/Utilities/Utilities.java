package com.example.kandoe.Utilities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Thomas on 2016-02-23.
 */
public class Utilities {

    public static String stringFormatter(String string) {
        String buffer = string.substring(0, 16);
        String jaar = buffer.substring(0, 4);
        String maand = buffer.substring(5, 7);
        String dag = buffer.substring(8, 10);
        String uur = buffer.substring(11, 13);
        String minuut = buffer.substring(14, 16);

        StringBuilder longMonth = new StringBuilder("0");


        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        month++;
        int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        if (maand.substring(0, 1).equals("0")) {
            longMonth.append(month);
        }


        if (year == Integer.parseInt(jaar)) {
            if (longMonth.toString().equals(maand)) {
                if (day == Integer.parseInt(dag)) {
                    return uur + ":" + minuut;
                }
            }
        }
        return dag + "-" + maand + "-" + jaar;

    }


}
