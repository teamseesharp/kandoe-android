package com.example.kandoe.Fragment;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.kandoe.R;

import java.util.ArrayList;

/**
 * Created by JoachimDs on 19/02/2016.
 */
public class SessionListFragment extends ListFragment implements OnItemClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_session_list, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Todo parameter data voorzien met juiste data
        ArrayList<String> data = new ArrayList<>();
        data.add("Eerste item");
        data.add(("Tweede item"));
        //ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), data, android.R.layout.simple_list_item_1);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, data);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Todo naar volgend activity of fragment gaan en id van sessie meegeven
        int sessionId;
        sessionId = 1;



        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
    }
}
