package com.example.kandoe.Utilities.DrawableGraphics;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;

import com.example.kandoe.Controller.CircleSessionController;
import com.example.kandoe.Model.Card;

import java.util.ArrayList;

/**
 * Created by Thomas on 2016-02-22.
 */
public class Ladder extends View {


    private CircleSessionController controller;
    private ArrayList<View> steps;
    private ArrayList<RoundedRectangle> legs;
    private double bottembound;

    int clLeft, clTop, clRight, clBottom, offset, width, height, heightleg;


    public Ladder(Context context) {
        super(context);
    }

   /* public Ladder(Context context, int numberOfSteps, ArrayList<Card> cards, ArrayList<View> bulletPoints) {
        super(context);
        steps = new ArrayList<>();
        bullets = bulletPoints;
        this.cards = cards;
        this.numberOfSteps = numberOfSteps;
    }*/

    public Ladder(CircleSessionController circleSessionController) {
        super(circleSessionController.getContext());
        this.controller = circleSessionController;
        steps = new ArrayList<>();
        legs = new ArrayList<>();
    }

    public void createLadder(Canvas container) {
        init();
        createBackGround(container);

        createSteps(container);
        createLegs(container);
        createBullets(container);

    }

    private void init() {
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

        System.out.println("Drawing Background");

        bottembound = (heightleg + heightleg * 0.3);
        controller.setBottomboundLadder(bottembound);

       View background = new Background(getContext(), (int) bottembound,container);

        //container.addView(background);



    }


    private void createBullets(Canvas container) {

        System.out.println("Drawing Bullets");

        for (Card card : controller.getCards()) {


            View bullet = new BulletPoint(getContext(), steps, card, legs, controller,container);

          /*  LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, WRAP_CONTENT);
            myImageView .setLayoutParams(params);*/


            controller.getBulletPoints().add(bullet);
          //  container.addView(bullet);

        }
    }


    private void createSteps( Canvas container) {


        System.out.println("Drawing Steps");
         int numberOfSteps = controller.getSession().getNumberOfSteps();

        //init values
         int ctLeft = clLeft + 30, ctTop = clTop + heightleg / numberOfSteps / 2, ctRight = clLeft + offset + 5, ctBottom = ctTop + 15;

         int topOffset = 0; //space between 2 steps

        //create steps
        for (int i = 0; i < numberOfSteps; i++) {
            RoundedRectangle trede = new RoundedRectangle(getContext(), ctLeft, ctTop + topOffset, ctRight, ctBottom + topOffset, Color.rgb(101, 67, 33), i,container);
            topOffset += heightleg / numberOfSteps;

            steps.add(trede);
            //container.addView(trede);


        }


    }


    private void createLegs(Canvas container) {
        //init values

        // int clLeft = 200, clTop = 150, clRight = clLeft + 50, clBottom = 800;
        //   int offset = 600;


        //left leg
        RoundedRectangle leftleg = new RoundedRectangle(getContext(), clLeft, clTop, clRight, clBottom, Color.rgb(102, 51, 0),container);

        //right leg
        RoundedRectangle rightLeg = new RoundedRectangle(getContext(), clLeft + offset, clTop, clRight + offset, clBottom, Color.rgb(102, 51, 0),container);

        legs.add(leftleg);
        legs.add(rightLeg);
       /* container.addView(leftleg);
        container.addView(rightLeg);*/
    }


    public ArrayList<View> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<View> steps) {
        this.steps = steps;
    }
}
