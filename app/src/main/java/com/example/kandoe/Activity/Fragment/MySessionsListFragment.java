package com.example.kandoe.Activity.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
 * Show all sessions where user is participant or user is invited for
 */
public class MySessionsListFragment extends Fragment {
    private final String TAG = "MySessionListFragment";

    private KandoeBackendAPI service;
    private SessionAdapter adapter;

    private UserAccount account;
    private Session sessionVerbose;

    private ArrayList<Organisation> organisations;
    private ArrayList<SubTheme> subThemes;

    private boolean isSessionlistFragment;

    public MySessionsListFragment(KandoeBackendAPI service, UserAccount userAccount) {
        this.service = service;
        this.account = userAccount;
        isSessionlistFragment = false;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        organisations = new ArrayList<>();
        subThemes = new ArrayList<>();
        adapter = new SessionAdapter(getContext(), organisations, subThemes, account, isSessionlistFragment);
        adapter.setIsInviteOnly(true);
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
                final Organisation organisation = organisations.get(groupPosition);
                final Session session = organisation.getSessions().get(childPosition);
                final boolean[] firstTime = {true};

                Call<Session> sessionCall = service.getVerboseSessionById(session.getId());
                sessionCall.enqueue(new Callback<Session>() {
                    @Override
                    public void onResponse(Call<Session> call, Response<Session> response) {
                        if (response.isSuccess()) {
                            sessionVerbose = response.body();

                            ArrayList<UserAccount> participants = sessionVerbose.getParticipants();
                            if (!participants.isEmpty()) {
                                for (UserAccount u : participants) {
                                    if (u.getId() == (account.getId())) {
                                        firstTime[0] = false;
                                        break;
                                    }
                                }
                            }

                            SubTheme currentsubtheme = null;
                            Theme currentTheme = null;

                            for (SubTheme subtheme : subThemes) {
                                if (subtheme.getId() == session.getSubThemeId()) {
                                    currentsubtheme = subtheme;
                                }
                            }

                            for (Theme theme : organisation.getThemes()) {

                                if (theme.getId() == (currentsubtheme != null ? currentsubtheme.getThemaId() : 0)) {
                                    currentTheme = theme;
                                }
                            }

                            android.support.v4.app.Fragment fragment;
                            if (firstTime[0]) {
                                fragment = SetupFragment.newInstance(service, session, currentTheme, currentsubtheme);

                            } else {
                                fragment = CircleFragment.newInstance(service, session, currentsubtheme);
                                FragmentManager fragmentManager = getFragmentManager();
                                fragmentManager.beginTransaction().replace(R.id.fragment_main, fragment).commit();
                            }

                            FragmentManager fragmentManager = getFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.fragment_main, fragment).addToBackStack(TAG).commit();
                            System.out.println(response.code());

                        } else {
                            Toast.makeText(getActivity(), "Verbose fail", Toast.LENGTH_LONG).show();
                            Log.d(TAG, "Session verbose fail");
                        }
                    }

                    @Override
                    public void onFailure(Call<Session> call, Throwable t) {
                        Log.d(TAG, "Session verbose onfailure");
                    }
                });
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
                final ArrayList<Organisation> toDeleteOrganisations = new ArrayList<Organisation>();

                try {
                    for (final Organisation org : organisationsTemp) {
                        Collections.sort(org.getSessions(), new Comparator<Session>() {
                            @Override
                            public int compare(Session lhs, Session rhs) {
                                if (lhs.getSubthemeId() == rhs.getSubthemeId()) return 0;
                                if (lhs.getSubthemeId() > rhs.getSubthemeId()) return -1;
                                if (lhs.getSubthemeId() < rhs.getSubthemeId()) return 1;
                                return 0;
                            }
                        });

                        final ArrayList<Session> validSessions = new ArrayList<Session>();


                        for (final Session s : org.getSessions()) {
                            Call<Session> sessionCall = service.getVerboseSessionById(s.getId());
                            sessionCall.enqueue(new Callback<Session>() {
                                @Override
                                public void onResponse(Call<Session> call, Response<Session> response) {
                                    Session sessionVerbose = response.body();
                                    ArrayList<UserAccount> invitees;

                                    if (sessionVerbose != null) {
                                        invitees = sessionVerbose.getInvitees();
                                        for (UserAccount invitee : invitees) {
                                            if (invitee.getId() == account.getId()) {
                                                validSessions.add(s);
                                            }
                                        }
                                        org.setSessions(validSessions);

                                      /* if (org.getSessions().isEmpty()) {
                                            toDeleteOrganisations.add(org);
                                        }*/
                                    }
                                }

                                @Override
                                public void onFailure(Call<Session> call, Throwable t) {
                                    Log.d(TAG, "Sessionverbose onFailure ");
                                }
                            });
                        }

                    }
                    organisationsTemp.removeAll(toDeleteOrganisations);

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
    //endregion
}
