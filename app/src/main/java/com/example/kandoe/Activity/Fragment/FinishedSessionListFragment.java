package com.example.kandoe.Activity.Fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

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
 * Shows a list off all finished sessions.
 */
public class FinishedSessionListFragment extends  android.support.v4.app.Fragment {
    private final String TAG = "SessionListFragment";

    private KandoeBackendAPI service;
    private SessionAdapter adapter;
    private UserAccount account;

    private ArrayList<Organisation> organisations;
    private ArrayList<SubTheme> subThemes;

    private boolean isSessionListFragment;
    private boolean mIsReview;

    public FinishedSessionListFragment(KandoeBackendAPI service,UserAccount userAccount) {
        this.service = service;
        this.account = userAccount;
        isSessionListFragment = false;
        mIsReview = true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        organisations = new ArrayList<>();
        subThemes = new ArrayList<>();
        adapter = new SessionAdapter(getContext(), organisations, subThemes,account,isSessionListFragment);

        getOrganisationsData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_session_list, container, false);

        ExpandableListView expandableListView = (ExpandableListView) view.findViewById(R.id.explv);
        expandableListView.setAdapter(adapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Organisation organisation = organisations.get(groupPosition);
                Session session = organisation.getSessions().get(childPosition);

                SubTheme currentsubtheme = null;

                for (SubTheme subtheme : subThemes) {
                    if (subtheme.getId() == session.getSubThemeId()) {
                        currentsubtheme = subtheme;
                    }
                }

                android.support.v4.app.Fragment fragment;
                fragment = CircleFragment.newInstance(service, session, currentsubtheme);
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_main, fragment).commit();
                return true;
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    //region calls
    public void getOrganisationsData() {
        Call<List<Organisation>> callList = service.getOrganisationsVerbose();
        callList.enqueue(new Callback<List<Organisation>>() {

            @Override
            public void onResponse(Call<List<Organisation>> call, Response<List<Organisation>> response) {
                ArrayList<Organisation> organisationsTemp = (ArrayList<Organisation>) response.body();
                ArrayList<Session> toDeleteSessions = new ArrayList<Session>();
                ArrayList<Organisation> toDeleteOrganisations = new ArrayList<Organisation>();

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
                            if (!sess.isFinished()) {
                                toDeleteSessions.add(sess);
                            }
                        }
                        org.getSessions().removeAll(toDeleteSessions);

                        if (org.getSessions().isEmpty()) {
                            toDeleteOrganisations.add(org);
                        }
                    }

                    organisationsTemp.removeAll(toDeleteOrganisations);

                    organisations.addAll(organisationsTemp);
                    adapter.notifyDataSetChanged();
                    getSubThemesData();

                } catch (NullPointerException e) {
                    Toast.makeText(getActivity(), "Spijtig, er is iets misgegaan. Probeer in enkele ogenblikken terug", Toast.LENGTH_LONG).show();
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
    //endregion
}
