package com.example.kandoe.Activity.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kandoe.Utilities.API.APIServiceGenerator;
import com.example.kandoe.Utilities.API.KandoeBackendAPI;
import com.example.kandoe.Model.Session;
import com.example.kandoe.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Michelle on 22-2-2016.
 * Review 1 session
 */
public class SessionFragment extends Fragment {
    KandoeBackendAPI service = APIServiceGenerator.createService(KandoeBackendAPI.class);
    int id;

    private Session session;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_session, container, false);
        return view;
    }

    public void retrieveSession(){
        Call<Session> session = service.getSessionDetail(id);
        session.enqueue(new Callback<Session>() {
            @Override
            public void onResponse(Call<Session> call, Response<Session> response) {
               // session = response.body();
            }

            @Override
            public void onFailure(Call<Session> call, Throwable t) {
                Toast.makeText(getActivity(), R.string.retrofit_error, Toast.LENGTH_LONG).show();
            }
        });
    }

}
