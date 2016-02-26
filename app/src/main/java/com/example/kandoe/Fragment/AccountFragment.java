package com.example.kandoe.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.kandoe.API.APIServiceGenerator;
import com.example.kandoe.API.KandoeBackendAPI;
import com.example.kandoe.Model.UserAccount;
import com.example.kandoe.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Michelle on 16-2-2016.
 * Shows user account information
 */
public class AccountFragment extends Fragment {
    KandoeBackendAPI service = APIServiceGenerator.createService(KandoeBackendAPI.class);
    int id;

    private EditText fName,lName,email;
    private ImageView imageView;
    private UserAccount userInfo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account,container,false);
        fName = (EditText) view.findViewById(R.id.account_firstName);
        lName = (EditText) view.findViewById(R.id.account_lastName);
        email = (EditText) view.findViewById(R.id.account_email);

        return view;
    }

    private void setTexts(){
                                    //TODO: naam checken
      if(userInfo.getAvatar().equals("avatar.png")){
          imageView.setImageResource(R.drawable.avatar);
      }else{
          try{
              //TODO uitwerken
          }catch (Exception e){

          }
      }
        fName.setText(userInfo.getFirstName());
        lName.setText(userInfo.getLastName());
        email.setText(userInfo.getEmail());
    }


    public void retrieveUserInfo(){
        Call<UserAccount> user = service.getUserInfo(id);
        user.enqueue(new Callback<UserAccount>() {
            @Override
            public void onResponse(Call<UserAccount> call, Response<UserAccount> response) {
            userInfo = response.body();
            }

            @Override
            public void onFailure(Call<UserAccount> call, Throwable t) {
                Toast.makeText(getActivity(), R.string.retrofit_error, Toast.LENGTH_LONG).show();
            }
        });


    }




}
