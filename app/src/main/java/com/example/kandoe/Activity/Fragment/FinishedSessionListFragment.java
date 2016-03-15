package com.example.kandoe.Activity.Fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.auth0.core.UserProfile;
import com.example.kandoe.Activity.Adapters.SessionAdapter;
import com.example.kandoe.Model.Organisation;
import com.example.kandoe.Model.Session;
import com.example.kandoe.Model.SubTheme;
import com.example.kandoe.Model.Theme;
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
 * Created by Michelle on 12-3-2016.
 */
public class FinishedSessionListFragment extends  android.support.v4.app.Fragment {

    private final String TAG = "SessionListFragment";
    KandoeBackendAPI service;

    private ArrayList<Organisation> organisations;
    private SessionAdapter adapter;
    private ArrayList<SubTheme> subThemes;
    boolean getDataSucces = false;
    private UserAccount userAccount;

    public FinishedSessionListFragment(KandoeBackendAPI service, UserProfile userProfile) {
        this.service = service;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_session_list, container, false);

        ExpandableListView expandableListView = (ExpandableListView) view.findViewById(R.id.listview);
        Button refresh = (Button) view.findViewById(R.id.btnRefresh);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subThemes = new ArrayList<SubTheme>();
                organisations = new ArrayList<Organisation>();
                getOrganisationsData();

            }
        });

        expandableListView.setAdapter(adapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Organisation organisation = organisations.get(groupPosition);
                Session session = organisation.getSessions().get(childPosition);

                boolean firstTime = true;

                SubTheme currentsubtheme = null;
                Theme currenttheme = null;
              /*  for (UserAccount accounts : session.getParticipants()) {
                    if (accounts.getId() == userAccount.getId()) {
                        firstTime = false;
                    }
                }


                for (SubTheme subtheme : subThemes) {
                    if (subtheme.getId() == session.getSubThemeId()) {
                        currentsubtheme = subtheme;
                    }

                }
                for (Theme theme : organisation.getThemes()) {

                    if (theme.getId() == (currentsubtheme != null ? currentsubtheme.getThemaId() : 0)) {
                        currenttheme = theme;
                    }
                }*/


                android.support.v4.app.Fragment fragment;
                //todo snapshot
                fragment = new CircleFragment(service);


                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_main, fragment).addToBackStack(TAG).commit();
                return true;
            }
        });
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        organisations = new ArrayList<>();
        subThemes = new ArrayList<>();
        adapter = new SessionAdapter(getContext(), organisations, subThemes);

        getOrganisationsData();
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
                              //  org.getSessions().remove(sess);
                                toDeleteSessions.add(sess);
                            }
                        }
                        org.getSessions().removeAll(toDeleteSessions);

                        if (org.getSessions().isEmpty()){
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


}
