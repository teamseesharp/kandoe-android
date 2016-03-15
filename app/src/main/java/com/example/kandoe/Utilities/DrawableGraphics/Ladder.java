package com.example.kandoe.Utilities.DrawableGraphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.View;

import com.example.kandoe.Controller.CircleSessionController;
import com.example.kandoe.Model.Card;

import java.util.ArrayList;

/**
 * Created by Thomas on 2016-02-22.
 */
public class Ladder extends View {

    private final String DEBUGTAG = "LADDER";
    private CircleSessionController controller;
    private ArrayList<RoundedRectangle> steps;
    private ArrayList<RoundedRectangle> legs;
    private double bottembound;
    private boolean firstTime;

    int clLeft, clTop, clRight, clBottom, offset, width, height, heightleg;

    public Ladder(Context context) {
        super(context);
    }

    public Ladder(CircleSessionController circleSessionController) {
        super(circleSessionController.getContext());
        this.controller = circleSessionController;
    }

    public void createLadder(Canvas container) {
        init();
          createBackGround(container);
          createSteps(container);
          createLegs(container);

        if (controller.getSession().getRound() != 0){
            createBullets(container);
        }

        createBullets(container);
    }

    private void init() {
        steps = new ArrayList<>();
        legs = new ArrayList<>();
        DisplayMetrics displayMetrics = controller.getContext().getResources().getDisplayMetrics();
        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;

        clLeft = width / 4;
        clTop = (int) (height * 0.05);
        clRight = clLeft + 40;
        clBottom = (int) (height / 2.5);
        offset = width / 2;

        heightleg = clBottom - clTop;
    }

    private void createBackGround(Canvas container) {
       // Log.d(DEBUGTAG, "creating Background");

        bottembound = (heightleg + heightleg * 0.3);

        View background = new Background(getContext(), (int) bottembound,container);
    }

    private void createBullets(Canvas container) {
      //  Log.d(DEBUGTAG, "Creating bullets");

        for (Card card : controller.getCards()) {
            View bullet = new BulletPoint(getContext(), steps, card, legs, controller, container);
            controller.getBulletPoints().add(bullet);
        }
    }


    private void createSteps( Canvas container) {
        //Log.d(DEBUGTAG,"Creating steps");
      //  int numberOfSteps = controller.getSession().getNumberOfSteps();
        int numberOfSteps = 10;
        //init values
         int ctLeft = clLeft + 30, ctTop = clTop + heightleg / numberOfSteps / 2, ctRight = clLeft + offset + 5, ctBottom = ctTop + 15;

         int topOffset = 0; //space between 2 steps

        //create steps
        for (int i = 0; i < numberOfSteps; i++) {
            RoundedRectangle trede = new RoundedRectangle(getContext(), ctLeft, ctTop + topOffset, ctRight, ctBottom + topOffset, Color.rgb(101, 67, 33), i,container);
            topOffset += heightleg / numberOfSteps;

            steps.add(trede);
        }


    }

    private void createLegs(Canvas container) {
       // Log.d(DEBUGTAG,"Creating legs");

        //left leg
        RoundedRectangle leftleg = new RoundedRectangle(getContext(), clLeft, clTop, clRight, clBottom, Color.rgb(102, 51, 0),container);

        //right leg
        RoundedRectangle rightLeg = new RoundedRectangle(getContext(), clLeft + offset, clTop, clRight + offset, clBottom, Color.rgb(102, 51, 0),container);

        legs.add(leftleg);
        legs.add(rightLeg);
    }
}
