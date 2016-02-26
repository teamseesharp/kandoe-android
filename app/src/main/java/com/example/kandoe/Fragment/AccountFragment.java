package com.example.kandoe.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kandoe.API.APIServiceGenerator;
import com.example.kandoe.API.KandoeBackendAPI;
import com.example.kandoe.Model.UserAccount;
import com.example.kandoe.R;

import retrofit2.Call;

/**
 * Created by Michelle on 16-2-2016.
 * Shows user account information
 */
public class AccountFragment extends Fragment {
    KandoeBackendAPI service = APIServiceGenerator.createService(KandoeBackendAPI.class);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_account,container,false);
    }

    Call<UserAccount> user = service.getUserInfo();


}
