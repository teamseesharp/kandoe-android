package com.example.kandoe.Activity.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;

import com.auth0.core.UserProfile;
import com.example.kandoe.Model.UserAccount;
import com.example.kandoe.R;
import com.example.kandoe.Utilities.ImageLoadTask;

/**
 * Created by Michelle on 16-2-2016.
 * Shows user account information
 */
public class AccountFragment extends Fragment {

    private EditText fName,email;
    private Switch aSwitch;
    private ImageView imageView;

    private UserAccount userProfile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userProfile = (UserAccount) getArguments().getSerializable("profile");

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account,container,false);
        fName = (EditText) view.findViewById(R.id.account_fname);
        email = (EditText) view.findViewById(R.id.account_email);
        imageView =(ImageView) view.findViewById(R.id.account_avatar);
        aSwitch = (Switch) view.findViewById(R.id.switch1);



        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            Editable tempEmail;
            Editable tempName;
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked){
                    fName.setFocusable(false);
                    fName.setFocusableInTouchMode(false);
                    email.setFocusable(false);
                    email.setFocusableInTouchMode(false);


                    if (!fName.getText().equals(tempName)){
                        // DO UPDATE NAME
                    }

                    if (!email.getText().equals(tempEmail)){
                        // DO UPDATE EMAIL
                    }

                }else {
                    fName.setFocusable(true);
                    fName.setFocusableInTouchMode(true);
                    email.setFocusable(true);
                    email.setFocusableInTouchMode(true);
                    tempEmail = email.getText();
                    tempName = fName.getText();
                }
            }
        });

        setTexts();
        return view;
    }

    private void setTexts(){


        fName.setText(userProfile.getName());

        email.setText(userProfile.getEmail());

        new ImageLoadTask(userProfile.getPicture(), imageView).execute();
    }






}
