package com.example.kandoe.Activity.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.kandoe.Activity.Adapters.SessionAdapter;
import com.example.kandoe.Model.Organisation;
import com.example.kandoe.Model.Session;
import com.example.kandoe.R;
import com.example.kandoe.Utilities.API.KandoeBackendAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        organisations = new ArrayList<>();
        getOrganisationsData();
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
                Session session = organisation.getSessions().get(childPosition);
                boolean firstTime = true;

                android.support.v4.app.Fragment fragment;

                if (firstTime) {
                    fragment = SetupFragment.newInstance(service, session);

                    //TODO
                } else {
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


    }

    public void getOrganisationsData() {

        Call<List<Organisation>> callList = service.getOrganisationsVerbose();

        callList.enqueue(new Callback<List<Organisation>>() {
            @Override
            public void onResponse(Call<List<Organisation>> call, Response<List<Organisation>> response) {
                organisations.addAll(response.body());
                System.out.println(organisations);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Organisation>> call, Throwable t) {
                System.out.println(t.toString());
            }
        });


    }


}

