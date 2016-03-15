package com.example.kandoe.Controller;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Thomas on 2016-02-23.
 */
public class CircleSessionController {
    private final String TAG = "CircleSessionController";
    private ArrayList<Card> cards;
    private ArrayList<View> bulletPoints;

    private ArrayList<UserAccount> participants;
    private CardAdapter adapter;
    private Session session;
    private ChatController chatController;
    private TextView currentPlayerTxt;
    private SurfacePanel panel;
    private Button btnUpVote;
    private UserAccount userAccount;

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

                    playCard(card);

                    card.setSessionLevel(currentlvl);

                }else {
                    Toast.makeText(getContext(),"Deze kaart kan je niet meer upvoten, kies een ander :)",Toast.LENGTH_LONG).show();
                }
            }
        }
        adapter.sortCards();
        panel.setIsDrawing(true);
    }

    //region API-Calls
    public void getVerboseSession() {
        Call<Session> call = service.getVerboseSessionById(session.getId());

        call.enqueue(new Callback<Session>() {
            @Override
            public void onResponse(Call<Session> call, Response<Session> response) {
                session = response.body();
                cards.addAll(session.getSessionCards());
                adapter.notifyDataSetChanged();
                updateCurrentPlayer();
                amICurrentPlayer();
            }

            @Override
            public void onFailure(Call<Session> call, Throwable t) {
                Toast.makeText(getContext(), "Het ophalen van de kaarten is mislukt", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void playCard(Card card){
        Call<Void> call = service.levelUpCard(session.getId(), card.getId());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccess()) {
                    System.out.println("Play card SUCCES");
                    Toast.makeText(getContext(), "SPELEN KAART SUCCES", Toast.LENGTH_LONG).show();

                } else {
                    Log.d(TAG, "Play card FAIL. ERROR: " + response.errorBody());
                    Toast.makeText(getContext(), "SPELEN KAART ONRESP FAIL", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                System.out.println("Play card onfailure !!");
                Toast.makeText(getContext(), "SPELEN KAART ONFAILURE", Toast.LENGTH_LONG).show();
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
        if (session.isFinished()){
            String sessionfinished = "Deze sessie is gestopt.";
            currentPlayerTxt.setText(sessionfinished);
        }else {
            if(amICurrentPlayer()){
                String player = "Jij bent aan de beurt... ";
                currentPlayerTxt.setText(player);
            }else{
                String player = currentPlayer.getName() + " is aan beurt... ";
                currentPlayerTxt.setText(player);
            }
        }
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
            userAccount.setName("Nog niemand");
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

    public boolean amICurrentPlayer() {
        if (getCurrentPlayer().getId() == userAccount.getId()) {
            btnUpVote.setVisibility(View.VISIBLE);
            return true;
        } else {
            btnUpVote.setVisibility(View.INVISIBLE);
            return false;
        }
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public void setBtnUpVote(Button btnUpVote) {
        this.btnUpVote = btnUpVote;
    }
    //endregion
}
