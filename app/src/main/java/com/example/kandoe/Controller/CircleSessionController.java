package com.example.kandoe.Controller;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.example.kandoe.DrawableGraphics.BulletPoint;
import com.example.kandoe.DrawableGraphics.Ladder;
import com.example.kandoe.Model.Card;
import com.example.kandoe.Model.Session;

import java.util.ArrayList;

/**
 * Created by Thomas on 2016-02-23.
 */
public class CircleSessionController {
    private ArrayList<Card> cards;
    private ArrayList<View> bulletPoints;
    private Session session;
    private Context context;

    public CircleSessionController(Context context) {
        init();
        this.context = context;
    }

    private void init() {

        cards = new ArrayList<>();
        bulletPoints = new ArrayList<>();

        session = new Session(null,1,4,1);

        cards.add(new Card(1, "", "Test"));
        cards.add(new Card(2, "", "Test"));
        cards.add(new Card(3, "", "Test"));
        cards.add(new Card(4, "", "Test"));

    }

    public void createLadder(ViewGroup container) {

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
}
