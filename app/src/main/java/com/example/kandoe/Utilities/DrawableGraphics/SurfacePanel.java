package com.example.kandoe.Utilities.DrawableGraphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.kandoe.Controller.CircleSessionController;

/**
 * Created by Thomas on 3-3-2016.
 */
public class SurfacePanel extends SurfaceView implements SurfaceHolder.Callback{

    // Global variables
    private Boolean _run;
    public boolean isDrawing = true;
    protected DrawThread thread;
    private Bitmap mBitmap;

    private CircleSessionController controller;

    public SurfacePanel(Context context) {
        super(context);getHolder().addCallback(this);
        thread = new DrawThread(getHolder());
    }

    public SurfacePanel(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
        thread = new DrawThread(getHolder());
    }

    // Constructor
    public SurfacePanel(Context context, CircleSessionController attrs) {
        super(context);
        this.controller = attrs;
        getHolder().addCallback(this);
        thread = new DrawThread(getHolder());

    }

    class DrawThread extends  Thread {
        private SurfaceHolder mSurfaceHolder;

        public DrawThread (SurfaceHolder surfaceHolder){
            mSurfaceHolder = surfaceHolder;
        }

        public void setRunning(boolean run) {
            _run = run;
        }

        @Override
        public void run() {
            Canvas canvas = null;



            while (_run){
                if(isDrawing == true){
                    try{
                        canvas = mSurfaceHolder.lockCanvas(null);
                        if(mBitmap == null){
                            mBitmap =  Bitmap.createBitmap (1, 1, Bitmap.Config.ARGB_8888);
                        }
                        final Canvas c = new Canvas (mBitmap);

                        controller.createLadder(c);
                      canvas.drawBitmap(mBitmap, 0, 0, null);




                    } finally {
                        mSurfaceHolder.unlockCanvasAndPost(canvas);
setRunning(false);

                    }
                }
            }
        }
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width,  int height) {
        // Create a Bitmap with the dimensions of the View
        mBitmap =  Bitmap.createBitmap (width, height, Bitmap.Config.ARGB_8888);;
    }

    public void surfaceCreated(SurfaceHolder holder) {
        // Starts thread execution
        thread.setRunning(true);
        thread.start();
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // Finish thread execution
        boolean retry = true;
        thread.setRunning(false);
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
                // we will try it again and again...
            }
        }
    }


    public void setController(CircleSessionController controller) {
        this.controller = controller;
    }
}