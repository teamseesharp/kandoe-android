package com.example.kandoe.Controller;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.kandoe.Model.Card;
import com.example.kandoe.Model.Session;
import com.example.kandoe.Utilities.DrawableGraphics.Ladder;

import java.util.ArrayList;

/**
 * Created by Thomas on 2016-02-23.
 */
public class CircleSessionController {
    private ArrayList<Card> cards;
    private ArrayList<View> bulletPoints;
    private ArrayAdapter adapter;
    private Session session;
    private ChatController chatController;

    private Context context;
    private double bottomboundLadder;


    public CircleSessionController(Context context, Session session) {
        init();
        this.context = context;
        this.session = session;
    }

    private void init() {

        cards = new ArrayList<>();
        bulletPoints = new ArrayList<>();


        session = new Session();
        chatController = new ChatController(session.getId());

        cards.add(new Card(1, "Jeugd"));
        cards.add(new Card(2, "Armoede "));
        cards.add(new Card(3, "Racisme"));
        cards.add(new Card(4, "Inspiratieloosheid"));
        cards.add(new Card(5, "Milieu"));
        cards.add(new Card(6, "Feest"));

    }

    public void createLadder(Canvas container) {

        Ladder ladder = new Ladder(this);
        ladder.createLadder(container);
    }



    public ArrayList<Card> getCards() {
        return cards;
    }

    public ArrayList<View> getBulletPoints() {
        return bulletPoints;
    }

    public Session getSession() {
        return session;
    }

    public Context getContext() {
        return context;
    }

    public double getBottomboundLadder() {
        return bottomboundLadder;
    }

    public void setBottomboundLadder(double bottomboundLadder) {
        this.bottomboundLadder = bottomboundLadder;
    }

    public ArrayAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(ArrayAdapter adapter) {
        this.adapter = adapter;
    }
}
