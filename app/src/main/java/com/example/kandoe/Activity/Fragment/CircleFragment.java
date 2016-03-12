package com.example.kandoe.Activity.Fragment;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.kandoe.Activity.Adapters.CardAdapter;
import com.example.kandoe.Controller.CircleSessionController;
import com.example.kandoe.Model.Session;
import com.example.kandoe.Model.UserAccount;
import com.example.kandoe.R;
import com.example.kandoe.Utilities.API.KandoeBackendAPI;
import com.example.kandoe.Utilities.DrawableGraphics.SurfacePanel;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CircleFragment extends Fragment {
    private static final String EXTRA_SERVICE = "Service";
    private static final String EXTRA_SESSION = "Session";

    private CircleSessionController controller;
    private OnFragmentInteractionListener mListener;
    private KandoeBackendAPI service;
    private Session session;
    private ArrayList<String> participants = new ArrayList<>();

    //TIJDELIJK
    public void addSpelers(){
        participants.add("Michelle");
        participants.add("Joachim");
        participants.add("Thomas");
        participants.add("Cas");
        participants.add("Bennie");
        participants.add("Olivier");
        participants.add("Paljas");
        participants.add("dingske");
    }

    public CircleFragment() {
    }

    public CircleFragment(KandoeBackendAPI service) {
        this.service = service;
    }

    public static CircleFragment newInstance(KandoeBackendAPI service, Session session) {
        CircleFragment fragment = new CircleFragment();
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_SERVICE, (Serializable) service);
        args.putSerializable(EXTRA_SESSION, (Serializable) session);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            service = (KandoeBackendAPI) getArguments().getSerializable(EXTRA_SERVICE);
            session = (Session) getArguments().getSerializable(EXTRA_SESSION);
        }

        //TODO Add session parameter
        controller = new CircleSessionController(getContext(), session);

        addSpelers();
        //getSessionInfo();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_circlesession, container, false);


        // controller.createLadder(container);
//container.addView(new SurfacePanel(getContext(),controller));


        SurfacePanel panel = (SurfacePanel) view.findViewById(R.id.view);
        panel.setController(controller);
        // panel.invalidate();

        System.out.println(panel.getBottom());

        ListView listView = (ListView) view.findViewById(R.id.lvCards);
        CardAdapter cardAdapter = new CardAdapter(getContext(), false, controller.getCards());
        controller.setAdapter(cardAdapter);
        listView.setAdapter(cardAdapter);

        TextView playerName = (TextView) view.findViewById(R.id.playersTurn);
        //TODO: current player ophalen en tonen
        String player = "Michelle" + " " + playerName.getText().toString();
        playerName.setText(player);

        //BUTTONS
        ImageButton showPersons = (ImageButton) view.findViewById(R.id.button_players);
        showPersons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v);
            }
        });

        Button voteUp = (Button) view.findViewById(R.id.votebutton);
        voteUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: VOTEUP
            }
        });

        //  listView.setPadding(0, (int) controller.getBottomboundLadder(), 0, 0);

        return view;
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void showPopup(View anchorView) {

        final View popupView =  getActivity().getLayoutInflater().inflate(R.layout.popup, null);

        final PopupWindow popupWindow = new PopupWindow(popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        // TODO: spelers inladen
        TextView tv = (TextView) popupView.findViewById(R.id.popup_tv);
        tv.setText(participantsOnNewLine());

        // If the PopupWindow should be focusable
        popupWindow.setFocusable(true);

        // If you need the PopupWindow to dismiss when touched outside
        popupWindow.setBackgroundDrawable(new ColorDrawable());

        int location[] = new int[2];

        // Get the View's(the one that was clicked in the Fragment) location
        anchorView.getLocationOnScreen(location);

        // Using location, the PopupWindow will be displayed right under anchorView
        popupWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY,
                location[0], location[1] + anchorView.getHeight());

    }

    public String participantsOnNewLine(){
        StringBuffer names = new StringBuffer();
        for (UserAccount u : session.getParticipants()) {
            names.append(u.getName()).append('\n');
        }
        return names.toString();
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void getSessionInfo(){
        Call<Session> call = service.getVerboseSessionById(session.getId());
        call.enqueue(new Callback<Session>() {
            @Override
            public void onResponse(Call<Session> call, Response<Session> response) {
                session = response.body();
            }

            @Override
            public void onFailure(Call<Session> call, Throwable t) {

            }
        });
    }
}
