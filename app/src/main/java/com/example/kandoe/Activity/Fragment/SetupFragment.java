package com.example.kandoe.Activity.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.text.Editable;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kandoe.Controller.Adapters.CardAdapter;
import com.example.kandoe.Activity.MainActivity;
import com.example.kandoe.Model.Card;
import com.example.kandoe.Model.Session;
import com.example.kandoe.Model.SubTheme;
import com.example.kandoe.Model.Theme;
import com.example.kandoe.Model.UserAccount;
import com.example.kandoe.R;
import com.example.kandoe.Utilities.API.KandoeBackendAPI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SetupFragment extends ListFragment implements OnItemClickListener {
    private static final String EXTRA_SERVICE = "Service";
    private static final String EXTRA_SESSION = "Session";
    private static final String EXTRA_THEMA = "Theme";
    private static final String EXTRA_SUBTHEME = "Subtheme";
    private static final String TAG = "Setupfragment";

    private ArrayList<Card> cards, myCards;
    private GridView grdMyCards, grdCards;
    private CardAdapter cardAdapter, myCardAdapter;
    private ProgressBar progressBar;
    private Button playButton;
    private TextView numberOfCards;
    private ImageButton addButton;

    private KandoeBackendAPI service;
    private Session session;
    private MainActivity mainActivity;
    private UserAccount account;
    private SubTheme subtheme;
    private Theme theme;

    public SetupFragment() {
    }

    public static SetupFragment newInstance(KandoeBackendAPI service, Session session, Theme theme, SubTheme subTheme) {
        SetupFragment f = new SetupFragment();
        Bundle bdl = new Bundle(2);
        bdl.putSerializable(EXTRA_SERVICE, (Serializable) service);
        bdl.putSerializable(EXTRA_SESSION, session);
        bdl.putSerializable(EXTRA_THEMA, theme);
        bdl.putSerializable(EXTRA_SUBTHEME, subTheme);
        f.setArguments(bdl);
        return f;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            service = (KandoeBackendAPI) getArguments().getSerializable(EXTRA_SERVICE);
            session = (Session) getArguments().getSerializable(EXTRA_SESSION);
            theme = (Theme) getArguments().getSerializable(EXTRA_THEMA);
            subtheme = (SubTheme) getArguments().getSerializable(EXTRA_SUBTHEME);
        }

        mainActivity = (MainActivity) getActivity();
        myCards = new ArrayList<>();
        cards = new ArrayList<>();
        getCardData();
    }


    private void addUserToSession() {
        Call<Void> call = service.addPlayerToSession(session.getId());
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                System.out.println(response.code());
                if (response.isSuccess()) {
                    System.out.println("Toevoegen speler aan sessie: GELUKT!");
                } else {
                    Log.d(TAG,"addUserToSession: FAIL. ERROR: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d(TAG, "addUserToSession: ONFAILURE");
            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.setup, container, false);

        if (mainActivity.getActionBar() != null) {
            mainActivity.getActionBar().setTitle(subtheme.getName());
        }
        if (mainActivity.getSupportActionBar() != null) {
            mainActivity.getSupportActionBar().setTitle(subtheme.getName());
        }


        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        progressBar.setProgress(0);

        //numberOfCards = (TextView) view.findViewById(R.id.txtNumberOfCards);

        grdMyCards = (GridView) view.findViewById(R.id.grdmycards);
        myCardAdapter = new CardAdapter(view.getContext(), true, myCards);
        grdMyCards.setAdapter(myCardAdapter);
        grdMyCards.setOnItemClickListener(this);

        grdCards = (GridView) view.findViewById(R.id.grdcards);
        cardAdapter = new CardAdapter(view.getContext(), true, cards);

        grdCards.setAdapter(cardAdapter);
        grdCards.setOnItemClickListener(this);

        playButton = (Button) view.findViewById(R.id.btnPlay);
        playButton.setText("Speel");
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCardsToSession();
                addUserToSession();
                CircleFragment fragment = CircleFragment.newInstance(service, session,subtheme);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_main, fragment).commit();
            }
        });


        addButton = (ImageButton) view.findViewById(R.id.addCardButton);
        if (session.isCardCreationAllowed()) {
            addButton.setVisibility(View.VISIBLE);
        } else {
            addButton.setVisibility(View.INVISIBLE);
        }

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCard();
            }
        });

        return view;
    }

    private void addCardsToSession() {
        Call<Void> call = service.addCardsToSession(session.getId(), myCards);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccess()) {
                    System.out.println("Add cards to session: SUCCES");
                } else {
                    Log.d(TAG,"add cards to session: NOT SUCCES. ERROR: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d(TAG, "add cards to session: ON FAILURE ");
            }
        });
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String cardId = (String) ((TextView) view.findViewById(R.id.txtId)).getText();
        ArrayList<Card> from = new ArrayList<>();
        ArrayList<Card> too = new ArrayList<>();
        Card cardToMove = new Card(-1, "");

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
            if (cardId.equals(String.valueOf(card.getId()))) {
                cardToMove = card;
            }
        }

        from.remove(cardToMove);
        too.add(0, cardToMove);

        setProgress();

        cardAdapter.notifyDataSetChanged();
        myCardAdapter.notifyDataSetChanged();
    }


    private void setProgress() {
        session.setNumberOfCards(3);
        double max = session.getNumberOfCards();
        double currentNumber = myCards.size();
        double progress = (currentNumber / max) * 100;
        progressBar.setProgress((int) progress);
        progressBar.setProgress((int) progress);

        if (progressBar.getProgress() == 100 && myCards.size() >= 1 && myCards.size() <= 3) {
            playButton.setEnabled(true);
            playButton.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
        } else if (progressBar.getProgress() == 100 && myCards.size() >= 3) {
            Toast.makeText(getContext(), "Je hebt teveel kaarten geselecteerd!", Toast.LENGTH_LONG).show();
            playButton.setEnabled(false);
            playButton.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            playButton.setEnabled(false);
            playButton.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    private void getCardData() {
        Call<List<Card>> callList = service.getSelectionCardsBySubthemeId(session.getSubThemeId());

        callList.enqueue(new Callback<List<Card>>() {
            @Override
            public void onResponse(Call<List<Card>> call, Response<List<Card>> response) {
                try {
                    cards.addAll(response.body());
                    Collections.shuffle(cards);
                    cardAdapter.notifyDataSetChanged();
                } catch (NullPointerException e){
                    Toast.makeText(getActivity(), "Spijtig, er is iets misgegaan met ophalen van de kaarten. Probeer in enkele ogenblikken terug", Toast.LENGTH_LONG).show();
                    Log.d(TAG, "onResponse: subthemes" + e.getMessage());
                    Log.d(TAG, "onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Card>> call, Throwable t) {
                System.out.println(t.toString());
            }
        });
    }

    private void addCard() {
        final EditText input = new EditText(getActivity());
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setLines(1);

        new AlertDialog.Builder(getActivity())
                .setTitle("Voeg een kaart toe:")
                .setView(input)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Editable kaartnaam = input.getText();
                        Card newCard = new Card();

                        newCard.setText(String.valueOf(kaartnaam));
                        newCard.setSubthemeId(session.getSubThemeId());

                        newCard.setThemeId(theme.getId());
                        createCard(newCard);

                        myCards.add(0, newCard);
                        myCardAdapter.notifyDataSetChanged();

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }

    private void createCard(Card card) {
        Call<Card> call = service.addCard(card);
        call.enqueue(new Callback<Card>() {
            @Override
            public void onResponse(Call<Card> call, Response<Card> response) {
                System.out.println(response);
                if (response.isSuccess()) {
                    System.out.println("CAll createCard Succes");
                } else {
                    Log.d(TAG,"createCard FAIL. Error: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<Card> call, Throwable t) {
                System.out.println("createCard faill");
            }
        });
    }
}
