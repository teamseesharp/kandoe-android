package com.example.kandoe.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kandoe.Model.Session;
import com.example.kandoe.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Michelle on 22-2-2016.
 * Review 1 session
 */
public class SessionFragment extends Fragment implements Callback<Session> {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_session,container,false);
    }

    @Override
    public void onResponse(Call<Session> call, Response<Session> response) {

    }

    @Override
    public void onFailure(Call<Session> call, Throwable t) {

    }
}
