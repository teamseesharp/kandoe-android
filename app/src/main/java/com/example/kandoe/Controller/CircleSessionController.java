package com.example.kandoe.Controller;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.kandoe.Model.Card;
import com.example.kandoe.Model.Session;
import com.example.kandoe.Model.UserAccount;
import com.example.kandoe.Utilities.API.KandoeBackendAPI;
import com.example.kandoe.Utilities.DrawableGraphics.Ladder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Thomas on 2016-02-23.
 */
public class CircleSessionController {
    private ArrayList<Card> cards;
    private ArrayList<View> bulletPoints;


    private ArrayList<UserAccount> participants;
    private ArrayAdapter adapter;
    private Session session;
    private ChatController chatController;

    private KandoeBackendAPI service;
    private Context context;
    private double bottomboundLadder;


    public CircleSessionController(Context context, Session session, KandoeBackendAPI service) {
        this.context = context;
        this.session = session;
        this.service = service;
        init();
    }

    private void init() {
        cards = new ArrayList<>();
        bulletPoints = new ArrayList<>();
        participants = new ArrayList<>();
        getVerboseSession();
        //chatController = new ChatController(session.getId());
    }




    public void getVerboseSession() {
        Call<Session> call = service.getVerboseSessionById(session.getId());

        call.enqueue(new Callback<Session>() {
            @Override
            public void onResponse(Call<Session> call, Response<Session> response) {
                session = response.body();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Session> call, Throwable t) {

            }
        });
    }


    public ArrayList<Card> getCards() {
        return session.getCards();
    }

    public void createLadder(Canvas container) {

        Ladder ladder = new Ladder(this);
        ladder.createLadder(container);
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

    public ArrayList<UserAccount> getParticipants() {
        return participants;
    }
}
