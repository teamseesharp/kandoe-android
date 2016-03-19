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


public class CircleFragment extends Fragment {
    private static final String EXTRA_SERVICE = "Service";
    private static final String EXTRA_SESSION = "Session";
    private static final String EXTRA_SUBTHEME = "Subtheme";

    private CircleSessionController controller;
    private OnFragmentInteractionListener mListener;
    private KandoeBackendAPI service;
    private Session session;
    private SubTheme subtheme;
    private MainActivity mainActivity;
    private UserAccount account;
    private TextView txtCurrentPlayer;
    private Button voteUp;

    private boolean mIsReview;

    public CircleFragment() {
    }

    public CircleFragment(KandoeBackendAPI service) {
        this.service = service;
    }

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

                System.out.println("Action Clicked");

                mIsReview = false;

                Fragment fragment = ChatFragment.newInstance(service, controller.getSession(), controller.getUserAccount(),mIsReview);
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
            mainActivity.getActionBar().setTitle(subtheme.getName());
        }
        if (mainActivity.getSupportActionBar() != null) {
            mainActivity.getSupportActionBar().setTitle(subtheme.getName());
        }

        txtCurrentPlayer = (TextView) view.findViewById(R.id.playersTurn);
        voteUp = (Button) view.findViewById(R.id.votebutton);

        SurfacePanel panel = (SurfacePanel) view.findViewById(R.id.view);
        ImageButton showPersons = (ImageButton) view.findViewById(R.id.button_players);
        ListView listView = (ListView) view.findViewById(R.id.lvCards);

        controller.setCurrentPlayerTxt(txtCurrentPlayer);
        controller.setBtnUpVote(voteUp);
        controller.setUserAccount(mainActivity.getUserAccount());
        panel.setController(controller);
        controller.setPanel(panel);

        CardAdapter cardAdapter = new CardAdapter(getContext(), false, controller.getCards());
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
                System.out.println("KLIK");
                controller.play();
            }
        });

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

        final View popupView = getActivity().getLayoutInflater().inflate(R.layout.popup, null);

        final PopupWindow popupWindow = new PopupWindow(popupView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

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

    public String participantsOnNewLine() {
        StringBuffer names = new StringBuffer();
        for (UserAccount s : controller.getParticipants()) {
            names.append(s.getName()).append('\n');
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
        void onFragmentInteraction(Uri uri);
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


}
