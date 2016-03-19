package com.example.kandoe.Activity.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.kandoe.Activity.MainActivity;
import com.example.kandoe.Controller.Adapters.SessionAdapter;
import com.example.kandoe.Model.Organisation;
import com.example.kandoe.Model.Session;
import com.example.kandoe.Model.SubTheme;
import com.example.kandoe.Model.UserAccount;
import com.example.kandoe.R;
import com.example.kandoe.Utilities.API.KandoeBackendAPI;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * List of all organisations with corresponding themes, subthemes and sessions
 */
public class SessionListFragment extends android.support.v4.app.Fragment {
    private final String TAG = "SessionListFragment";
    KandoeBackendAPI service;

    private ArrayList<Organisation> organisations;
    private SessionAdapter adapter;
    private ArrayList<SubTheme> subThemes;
    private UserAccount userAccount;
    private Session sessionVerbose;

    private boolean isSessionListFragment;

    public SessionListFragment(KandoeBackendAPI service) {
        this.service = service;
        isSessionListFragment = true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        organisations = new ArrayList<>();
        subThemes = new ArrayList<>();
        adapter = new SessionAdapter(getContext(), organisations, subThemes, userAccount, isSessionListFragment);

        getUserAccount();
        getOrganisationsData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_session_list, container, false);

        ExpandableListView expandableListView = (ExpandableListView) view.findViewById(R.id.explv);
        expandableListView.setAdapter(adapter);
        return view;
    }

    private void getUserAccount() {
        MainActivity activity = (MainActivity) getActivity();
        Call<UserAccount> call = service.getUserId(activity.getUserProfile().getId());

        call.enqueue(new Callback<UserAccount>() {
            @Override
            public void onResponse(Call<UserAccount> call, Response<UserAccount> response) {
                userAccount = response.body();
                Log.d(TAG, "USer call succes");
            }

            @Override
            public void onFailure(Call<UserAccount> call, Throwable t) {
                Log.d(TAG, "User call not succes");
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void getOrganisationsData() {
        Call<List<Organisation>> callList = service.getOrganisationsVerbose();
        callList.enqueue(new Callback<List<Organisation>>() {
            @Override
            public void onResponse(Call<List<Organisation>> call, Response<List<Organisation>> response) {

                ArrayList<Organisation> organisationsTemp = (ArrayList<Organisation>) response.body();
                ArrayList<Session> toDelete = new ArrayList<Session>();
                try {
                    for (Organisation org : organisationsTemp) {

                        Collections.sort(org.getSessions(), new Comparator<Session>() {
                            @Override
                            public int compare(Session lhs, Session rhs) {
                                if (lhs.getSubthemeId() == rhs.getSubthemeId()) return 0;
                                if (lhs.getSubthemeId() > rhs.getSubthemeId()) return -1;
                                if (lhs.getSubthemeId() < rhs.getSubthemeId()) return 1;
                                return 0;
                            }
                        });

                        for (Session sess : org.getSessions()) {
                            if (sess.isFinished()) {
                                toDelete.add(sess);
                            }
                        }
                        org.getSessions().removeAll(toDelete);
                    }

                    organisations.addAll(organisationsTemp);
                    adapter.notifyDataSetChanged();
                    getSubThemesData();

                } catch (NullPointerException e) {
                    Log.d(TAG, "onResponse: organisations" + e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<List<Organisation>> call, Throwable t) {
                System.out.println(t.toString());
            }
        });
    }

    private void getSubThemesData() {
        Call<List<SubTheme>> call = service.getSubThemes();
        call.enqueue(new Callback<List<SubTheme>>() {
            @Override
            public void onResponse(Call<List<SubTheme>> call, Response<List<SubTheme>> response) {
                try {
                    subThemes.addAll(response.body());
                    adapter.notifyDataSetChanged();

                } catch (NullPointerException e) {
                    Toast.makeText(getActivity(), "Spijtig, er is iets misgegaan met ophalen van de themas. Probeer in enkele ogenblikken terug", Toast.LENGTH_LONG).show();
                    Log.d(TAG, "onResponse: subthemes" + e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<List<SubTheme>> call, Throwable t) {
                System.out.println(t.toString());
            }
        });
    }
}

