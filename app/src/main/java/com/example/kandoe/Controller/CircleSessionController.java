package com.example.kandoe.Controller;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kandoe.Activity.Adapters.CardAdapter;
import com.example.kandoe.Model.Card;
import com.example.kandoe.Model.Session;
import com.example.kandoe.Model.UserAccount;
import com.example.kandoe.Utilities.API.KandoeBackendAPI;
import com.example.kandoe.Utilities.DrawableGraphics.Ladder;
import com.example.kandoe.Utilities.DrawableGraphics.SurfacePanel;

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
    private CardAdapter adapter;
    private Session session;
    private ChatController chatController;
    private TextView currentPlayerTxt;
    private SurfacePanel panel;


    private KandoeBackendAPI service;
    private Context context;


    public CircleSessionController(Context context, Session session, KandoeBackendAPI service) {
        this.context = context;
        this.session = session;
        this.service = service;
        init();
    }

    private void init() {
        bulletPoints = new ArrayList<>();
        participants = new ArrayList<>();
        cards = new ArrayList<>();
        getVerboseSession();

        //chatController = new ChatController(session.getId());
    }


    public void play() {

        String chosenCard = adapter.getChosenCardToUpvote();

        for (Card card : cards) {
            if (card.getId() == Integer.parseInt(chosenCard)) {

                if (card.getSessionLevel()!= 1){

                  //vervangen door call
                    int currentlvl = card.getSessionLevel();
                    currentlvl--;
                    card.setSessionLevel(currentlvl);


                }else {
                    Toast.makeText(getContext(),"Deze kaart kan je niet meer upvoten, kies een ander :)",Toast.LENGTH_LONG).show();
                }

            }
        }
        adapter.sortCards();
        panel.setIsDrawing(true);

   ;



    }

    //region API-Calls

    private void voteCardUp() {
        Call<Void> call = service.levelUpCard(1);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccess()) {
                    Toast.makeText(getContext(), "Kaart 1 omhoog :)",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getContext(), "kaarten verhogen mislukt",
                            Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(getContext(), "Oeps er is iets misgelopen",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    public void getVerboseSession() {
        Call<Session> call = service.getVerboseSessionById(session.getId());

        call.enqueue(new Callback<Session>() {
            @Override
            public void onResponse(Call<Session> call, Response<Session> response) {
                session = response.body();
                cards.addAll(session.getSessionCards());
                adapter.notifyDataSetChanged();
                updateCurrentPlayer();
            }

            @Override
            public void onFailure(Call<Session> call, Throwable t) {
                Toast.makeText(getContext(), "Het ophalen van de kaarten is mislukt", Toast.LENGTH_LONG).show();
            }
        });


    }
    //endregion

    //region UI methods
    public void createLadder(Canvas container) {

        Ladder ladder = new Ladder(this);
        ladder.createLadder(container);

    }

    public void updateCurrentPlayer() {
        UserAccount currentPlayer = getCurrentPlayer();
        String player = currentPlayer.getName() + " is aan beurt. ";
        currentPlayerTxt.setText(player);
    }
    //endregion

    //region Getters and Setters

    public void setPanel(SurfacePanel panel) {
        this.panel = panel;
    }

    public ArrayList<UserAccount> getParticipants() {
        return session.getParticipants();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public UserAccount getCurrentPlayer() {

        try {
            return session.getParticipants().get(session.getCurrentPlayerIndex());
        } catch (IndexOutOfBoundsException e) {
            UserAccount userAccount = new UserAccount();
            userAccount.setName("Niemand");
            return userAccount;
        }
    }

    public void setCurrentPlayerTxt(TextView currentPlayerTxt) {
        this.currentPlayerTxt = currentPlayerTxt;
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


    public void setAdapter(CardAdapter adapter) {
        this.adapter = adapter;
    }

    public boolean amICurrentPlayer(UserAccount userAccount) {

        if (getCurrentPlayer().getId() == userAccount.getId()) {
            return true;
        }
        return false;


    }
    //endregion
}
