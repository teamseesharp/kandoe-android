package com.example.kandoe.Activity.Fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.kandoe.Utilities.API.KandoeBackendAPI;
import com.example.kandoe.Activity.Adapaters.SessionAdapter;
import com.example.kandoe.Model.Organisation;
import com.example.kandoe.Model.Session;
import com.example.kandoe.R;

import java.util.ArrayList;

/**
 * Created by JoachimDs on 19/02/2016.
 * Shows all sessions in listview
 */
public class SessionListFragment extends android.support.v4.app.Fragment {
    KandoeBackendAPI service;

    private ArrayList<Organisation> organisations;
    private SessionAdapter adapter;


    public SessionListFragment(KandoeBackendAPI service) {
        this.service = service;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_session_list, container, false);

        ExpandableListView expandableListView = (ExpandableListView) view.findViewById(R.id.listview);
        adapter = new SessionAdapter(getContext(), organisations);

        expandableListView.setAdapter(adapter);
        for (int i = 0; i < adapter.getGroupCount(); i++)
            expandableListView.expandGroup(i);


        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Organisation organisation = organisations.get(groupPosition);
                //Session session = organisation.getSessions().get(childPosition);
                boolean firstTime = true;

                android.support.v4.app.Fragment fragment;

                if (firstTime){
                     fragment = SetupFragment.newInstance(service,new Session(null,1,4,1));
                }else {
                     fragment = new CircleFragment(service);
                }




                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_main, fragment).commit();
                return true;
            }
        });
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //Todo parameter data voorzien met juiste data
        ArrayList<Session> sessions = new ArrayList<>();
        organisations = new ArrayList<>();

        sessions.add(new Session(null, 2, 3, 1));
        sessions.add(new Session(null, 2, 3, 2));
        sessions.add(new Session(null, 2, 3, 3));
        sessions.add(new Session(null, 2, 3, 4));
        Organisation organisation = new Organisation("KdG", sessions, "1");
        Organisation organisation2 = new Organisation("Antwerpen", sessions, "2");
        Organisation organisation3 = new Organisation("Paljaskes Unite", sessions, "3");

        organisations.add(organisation);
        organisations.add(organisation2);
        organisations.add(organisation3);


    }




}
