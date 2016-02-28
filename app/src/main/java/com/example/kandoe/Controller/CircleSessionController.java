package com.example.kandoe.Controller;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

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
    private ArrayAdapter adapter;
    private Session session;
    private ChatController chatController;
    private Context context;
    private double bottomboundLadder;




    public CircleSessionController(Context context) {
        init();
        this.context = context;

    }

    private void init() {

        cards = new ArrayList<>();
        bulletPoints = new ArrayList<>();


        session = new Session(null,1,4,1);
        chatController = new ChatController(session.getId());

        cards.add(new Card(1, "", "Jeugd", "info over Jeugd "));
        cards.add(new Card(2, "", "Armoede ", "info over paupers"));
        cards.add(new Card(3, "", "Racisme", "info over racisme en andere zaken met discriminatie"));
        cards.add(new Card(4, "", "Inspiratieloosheid", "info over niks"));
        cards.add(new Card(5, "", "Inspiratieloosheid", "info over niks"));
        cards.add(new Card(6, "", "Inspiratieloosheid", "info over niks"));
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
