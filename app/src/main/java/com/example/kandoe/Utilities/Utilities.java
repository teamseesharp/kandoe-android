package com.example.kandoe.Utilities;

import android.graphics.Rect;
import android.view.View;

/**
 * Created by Thomas on 2016-02-23.
 */
public class Utilities {


    public static boolean CheckCollision(View v1,Rect v2) {
        Rect R1=new Rect(v1.getLeft(), v1.getTop(), v1.getRight(), v1.getBottom());
     //   Rect R2=new Rect(v2.getLeft(), v2.getTop(), v2.getRight(), v2.getBottom());
        return R1.intersect(v2);
    }
}
