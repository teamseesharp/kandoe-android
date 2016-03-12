package com.example.kandoe.Activity.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.auth0.core.UserProfile;
import com.example.kandoe.Activity.Adapters.SessionAdapter;
import com.example.kandoe.Model.Organisation;
import com.example.kandoe.Model.Session;
import com.example.kandoe.Model.SubTheme;
import com.example.kandoe.R;
import com.example.kandoe.Utilities.API.KandoeBackendAPI;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by JoachimDs on 19/02/2016.
 * Shows all sessions in listview
 */
public class SessionListFragment extends android.support.v4.app.Fragment {

    private final String TAG = "SessionListFragment";
    KandoeBackendAPI service;

    private ArrayList<Organisation> organisations;
    private SessionAdapter adapter;
    private ArrayList<SubTheme> subThemes;


    public SessionListFragment(KandoeBackendAPI service, UserProfile userProfile) {
        this.service = service;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_session_list, container, false);

        ExpandableListView expandableListView = (ExpandableListView) view.findViewById(R.id.listview);


        expandableListView.setAdapter(adapter);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Organisation organisation = organisations.get(groupPosition);
                Session session = organisation.getSessions().get(childPosition);
                TextView textView = (TextView) v.findViewById(R.id.txtSubthemeName);
                boolean firstTime = true;

                android.support.v4.app.Fragment fragment;

                if (firstTime) {
                    fragment = SetupFragment.newInstance(service, session,textView.getText().toString());

                    //TODO
                } else {
                    fragment = new CircleFragment(service);
                }


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
        getSubThemesData();

    }

    public void getOrganisationsData() {

        Call<List<Organisation>> callList = service.getOrganisationsVerbose();

        callList.enqueue(new Callback<List<Organisation>>() {
            @Override
            public void onResponse(Call<List<Organisation>> call, Response<List<Organisation>> response) {

                ArrayList<Organisation> organisationsTemp = (ArrayList<Organisation>) response.body();
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
                                org.getSessions().remove(sess);
                            }
                        }

                    }


                    organisations.addAll(organisationsTemp);
                    adapter.notifyDataSetChanged();
                }catch (NullPointerException e){

                    Toast.makeText(getActivity(),"Spijtig, er is iets misgegaan. Probeer in enkele ogenblikken terug",Toast.LENGTH_LONG).show();
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
               }catch (NullPointerException e){
                   Toast.makeText(getActivity(),"Spijtig, er is iets misgegaan. Probeer in enkele ogenblikken terug",Toast.LENGTH_LONG).show();
               }

            }

            @Override
            public void onFailure(Call<List<SubTheme>> call, Throwable t) {
                System.out.println(t.toString());
            }
        });

    }


}

