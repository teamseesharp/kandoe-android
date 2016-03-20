package com.example.kandoe.Activity.Fragment;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.kandoe.Controller.Adapters.CardAdapter;
import com.example.kandoe.Activity.MainActivity;
import com.example.kandoe.Controller.CircleSessionController;
import com.example.kandoe.Model.Session;
import com.example.kandoe.Model.SubTheme;
import com.example.kandoe.Model.UserAccount;
import com.example.kandoe.R;
import com.example.kandoe.Utilities.API.KandoeBackendAPI;
import com.example.kandoe.Utilities.DrawableGraphics.SurfacePanel;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * In this fragment users can play kandoe
 */
public class CircleFragment extends Fragment {
    private static final String EXTRA_SERVICE = "Service";
    private static final String EXTRA_SESSION = "Session";
    private static final String EXTRA_SUBTHEME = "Subtheme";

    private CircleSessionController controller;
    private OnFragmentInteractionListener mListener;

    private KandoeBackendAPI service;
    private Session session;
    private SubTheme subtheme;
    private UserAccount account;

    private MainActivity mainActivity;

    public CircleFragment() {}

    public static CircleFragment newInstance(KandoeBackendAPI service, Session session,SubTheme subTheme) {
        CircleFragment fragment = new CircleFragment();
        Bundle args = new Bundle();
        args.putSerializable(EXTRA_SERVICE, (Serializable) service);
        args.putSerializable(EXTRA_SESSION, (Serializable) session);
        args.putSerializable(EXTRA_SUBTHEME, (Serializable) subTheme);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            service = (KandoeBackendAPI) getArguments().getSerializable(EXTRA_SERVICE);
            session = (Session) getArguments().getSerializable(EXTRA_SESSION);
            subtheme = (SubTheme) getArguments().getSerializable(EXTRA_SUBTHEME);
        }

        mainActivity = (MainActivity) getActivity();
        controller = new CircleSessionController(getActivity(), session, service);
        getUserAccountInfo();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.findItem(R.id.action_chat).setVisible(true);
        menu.findItem(R.id.action_chat).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                Fragment fragment = ChatFragment.newInstance(service, controller.getSession(), controller.getUserAccount());
                android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_main, fragment).addToBackStack(null).commit();

                return false;
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_circlesession, container, false);
        setHasOptionsMenu(true);

        if (mainActivity.getActionBar() != null) {
            mainActivity.setmTitle(session.getDescription());
            mainActivity.restoreActionBar();
        }
        if (mainActivity.getSupportActionBar() != null) {
            mainActivity.setmTitle(session.getDescription());
            mainActivity.restoreActionBar();
        }

        TextView txtCurrentPlayer = (TextView) view.findViewById(R.id.playersTurn);
        Button voteUp = (Button) view.findViewById(R.id.votebutton);

        SurfacePanel panel = (SurfacePanel) view.findViewById(R.id.view);
        ImageButton showPersons = (ImageButton) view.findViewById(R.id.button_players);
        ListView listView = (ListView) view.findViewById(R.id.lvCards);

        controller.setCurrentPlayerTxt(txtCurrentPlayer);
        controller.setBtnUpVote(voteUp);
        controller.setUserAccount(mainActivity.getUserAccount());
        panel.setController(controller);
        controller.setPanel(panel);

        CardAdapter cardAdapter = new CardAdapter(getContext(), false, controller.getCards(),session);
        controller.setAdapter(cardAdapter);
        listView.setAdapter(cardAdapter);

        //BUTTONS
        showPersons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v);
            }
        });

        voteUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.play();
            }
        });
        return view;
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
        final View popupView = getActivity().getLayoutInflater().inflate(R.layout.popup, null);
        final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView tv = (TextView) popupView.findViewById(R.id.popup_tv);
        tv.setText(participantsOnNewLine());

        popupWindow.setFocusable(true);

        //to dismiss popup window when touched outside
        popupWindow.setBackgroundDrawable(new ColorDrawable());

        int location[] = new int[2];

        // Get the View's(the one that was clicked in the Fragment) location
        anchorView.getLocationOnScreen(location);

        // PopupWindow will be displayed right under anchorView
        popupWindow.showAtLocation(anchorView, Gravity.NO_GRAVITY, location[0], location[1] + anchorView.getHeight());
    }

    public String participantsOnNewLine() {
        StringBuffer names = new StringBuffer();
        for (UserAccount s : controller.getParticipants()) {
            names.append(s.getName()).append('\n');
        }
        return names.toString();
    }

    private void getUserAccountInfo() {
        String secret = mainActivity.getUserProfile().getId();

        Call<UserAccount> call = service.getUserId(secret);
        call.enqueue(new Callback<UserAccount>() {
            @Override
            public void onResponse(Call<UserAccount> call, Response<UserAccount> response) {
                account = response.body();
            }

            @Override
            public void onFailure(Call<UserAccount> call, Throwable t) {
            }
        });
    }

    /**
     * This interface must be implemented by activities that contain this fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that activity.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
