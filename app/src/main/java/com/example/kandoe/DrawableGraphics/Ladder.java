package com.example.kandoe.DrawableGraphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Thomas on 2016-02-22.
 */
public class Ladder extends View {

    private ArrayList<View> steps;
    private ArrayList<View> bullets;
    int numberOfSteps;

    public Ladder(Context context) {
        super(context);
    }

    public Ladder(Context context, int numberOfSteps) {
        super(context);
        steps = new ArrayList<>();
        bullets = new ArrayList<>();
        this.numberOfSteps = numberOfSteps;
    }

    public void createLadder(ViewGroup container) {
        createBackGround(container);
        createSteps(container);
        createLegs(container);

        createBullets(container);


    }

    private void createBackGround(ViewGroup container) {

        View background = new Background(getContext());

        container.addView(background);



    }

    private void createBullets(ViewGroup container) {

        for (View step : steps){


            View bullet = new BulletPoint(getContext(), step);



            bullets.add(bullet);
            container.addView(bullet);

        }
    }

    private void createSteps(ViewGroup container) {

        //init values
        int ctLeft = 210, ctTop = 225, ctRight = ctLeft+600, ctBottom = ctTop + 15;

        int topOffset = 0; //space between 2 steps

        //create steps
        for (int i = 0; i < numberOfSteps; i++) {
            RoundedRectangle trede = new RoundedRectangle(getContext(), ctLeft, ctTop + topOffset, ctRight, ctBottom + topOffset, Color.rgb(101, 67, 33));
            topOffset += 125;
            steps.add(trede);
            container.addView(trede);

        }
    }

    private void createLegs(ViewGroup container) {
        //init values
        int clLeft = 200, clTop = 150, clRight = clLeft + 50, clBottom = 800;
        int offset = 600;

        //left leg
        View leftleg = new RoundedRectangle(getContext(), clLeft, clTop, clRight, clBottom, Color.rgb(102, 51, 0));

        //right leg
        View rightLeg = new RoundedRectangle(getContext(), clLeft + offset, clTop, clRight + offset, clBottom, Color.rgb(102, 51, 0));


        container.addView(leftleg);
        container.addView(rightLeg);
    }

    public ArrayList<View> getBullets() {
        return bullets;
    }

    public void setBullets(ArrayList<View> bullets) {
        this.bullets = bullets;
    }

    public ArrayList<View> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<View> steps) {
        this.steps = steps;
    }
}
