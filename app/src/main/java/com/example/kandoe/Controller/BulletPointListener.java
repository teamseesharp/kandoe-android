package com.example.kandoe.Controller;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Thomas on 2016-02-23.
 */
public class BulletPointListener implements View.OnClickListener {

    public BulletPointListener() {
    }

    @Override
    public void onClick(View v) {
                Toast.makeText(v.getContext(),"Test",Toast.LENGTH_SHORT);
    }
}
