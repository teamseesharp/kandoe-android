package com.example.kandoe.Activity.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.kandoe.Utilities.API.KandoeBackendAPI;
import com.example.kandoe.Activity.Adapters.CardAdapter;
import com.example.kandoe.Model.Card;
import com.example.kandoe.Model.Session;
import com.example.kandoe.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SetupFragment extends ListFragment implements OnItemClickListener {
    private static final String EXTRA_SERVICE =   "Service" ;
    private static final String EXTRA_SESSION =   "Session" ;
    private ArrayList<Card> cards;
    private ArrayList<Card> myCards;
    private GridView grdMyCards;
    private GridView grdCards;
    private CardAdapter cardAdapter;
    private CardAdapter myCardAdapter;
    private ProgressBar progressBar;
    private Button playButton;
    private TextView numberOfCards;

    KandoeBackendAPI service;
    private Session session;



    public SetupFragment() {
    }

    public static SetupFragment newInstance(KandoeBackendAPI service, Session session)
    {
        SetupFragment f = new SetupFragment();
        Bundle bdl = new Bundle(2);
        bdl.putSerializable(EXTRA_SERVICE, (Serializable) service);
        bdl.putSerializable(EXTRA_SESSION, (Serializable) session);
        f.setArguments(bdl);
        return f;
    }


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        service = (KandoeBackendAPI) getArguments().getSerializable(EXTRA_SERVICE);
        session = (Session) getArguments().getSerializable(EXTRA_SESSION);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setup, container, false);



        myCards = new ArrayList<>();
        cards = new ArrayList<>();
        cards = getCardData();

        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        progressBar.setProgress(0);

        numberOfCards = (TextView) view.findViewById(R.id.txtNumberOfCards);

        grdMyCards = (GridView) view.findViewById(R.id.grdmycards);
        myCardAdapter = new CardAdapter(view.getContext(), true, myCards);
        grdMyCards.setAdapter(myCardAdapter);
        grdMyCards.setOnItemClickListener(this);

        grdCards = (GridView) view.findViewById(R.id.grdcards);
        cardAdapter = new CardAdapter(view.getContext(), true, cards);

        grdCards.setAdapter(cardAdapter);
        grdCards.setOnItemClickListener(this);

        playButton = (Button) view.findViewById(R.id.btnPlay);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                CircleFragment fragment = CircleFragment.newInstance(service, session);


                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_main, fragment).commit();

            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String cardid = (String) ((TextView) view.findViewById(R.id.txtId)).getText();
        //String descrption = (String) ((TextView) view.findViewById(R.id.txtCardDescription)).getText();
        ArrayList<Card> from = new ArrayList<>();
        ArrayList<Card> too = new ArrayList<>();
        Card cardToMove = new Card(-1, "", "", "");

        switch (parent.getId()) {
            case R.id.grdmycards:
                from = myCards;
                too = cards;
                break;
            case R.id.grdcards:
                from = cards;
                too = myCards;
                break;
        }

        for (Card card : from) {
            if (cardid.equals(String.valueOf(card.getId()))) {
                cardToMove = card;
            }
        }

        from.remove(cardToMove);
        too.add(0, cardToMove);

        setProgress();

        cardAdapter.notifyDataSetChanged();
        myCardAdapter.notifyDataSetChanged();
    }

    private void setProgress(){
        session.setNumberOfCards(3);
        double max = session.getNumberOfCards();
        double currentNumber = myCards.size();
        double progress = (currentNumber/max)*100;
        progressBar.setProgress((int) progress);
        numberOfCards.setText(String.valueOf(cards.size()));


        if (progressBar.getProgress() == 100){
            playButton.setEnabled(true);
            playButton.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
        } else {
            playButton.setEnabled(false);
            playButton.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
        }


    }
    public ArrayList<Card> getCardData() {

        Call<List<Card>> callList = service.getCards();

        callList.enqueue(new Callback<List<Card>>() {
            @Override
            public void onResponse(Call<List<Card>> call, Response<List<Card>> response) {
                cards.addAll(response.body());
                cardAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<Card>> call, Throwable t) {
                System.out.println(t.toString());
            }
        });



        return cards;
    }
}
