package com.example.kandoe.Activity.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.kandoe.Activity.MainActivity;
import com.example.kandoe.Model.UserAccount;
import com.example.kandoe.R;
import com.example.kandoe.Utilities.API.KandoeBackendAPI;
import com.example.kandoe.Utilities.ImageLoadTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Shows user account information + user can edit his personal info
 */
public class AccountFragment extends Fragment {
    private static final String TAG = "Accountfragment";

    private EditText fName, lName, email;
    private Button saveButton;
    private Switch aSwitch;
    private ImageView imageView;

    private UserAccount userAccount;
    private KandoeBackendAPI service;

    public AccountFragment(KandoeBackendAPI service) {
        this.service = service;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getUserAccount();
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_account, container, false);
        fName = (EditText) view.findViewById(R.id.account_fname);
        lName = (EditText) view.findViewById(R.id.account_lname);
        email = (EditText) view.findViewById(R.id.account_email);
        imageView = (ImageView) view.findViewById(R.id.account_avatar);
        aSwitch = (Switch) view.findViewById(R.id.switch1);
        saveButton = (Button) view.findViewById(R.id.account_saveButton);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    fName.setFocusable(false);
                    fName.setFocusableInTouchMode(false);
                    lName.setFocusable(false);
                    lName.setFocusableInTouchMode(false);
                    email.setFocusable(false);
                    email.setFocusableInTouchMode(false);
                    saveButton.setVisibility(View.INVISIBLE);

                } else {
                    fName.setFocusable(true);
                    fName.setFocusableInTouchMode(true);
                    lName.setFocusable(true);
                    lName.setFocusableInTouchMode(true);
                    email.setFocusable(true);
                    email.setFocusableInTouchMode(true);

                    saveButton.setVisibility(View.VISIBLE);

                    email.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            String newEmail = email.getText().toString();
                            userAccount.setEmail(newEmail);

                        }
                    });

                    fName.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            String newFirstName = fName.getText().toString();
                            userAccount.setName(newFirstName);

                        }
                    });

                    lName.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            String newLastName = lName.getText().toString();
                            userAccount.setSurname(newLastName);
                        }
                    });

                    saveButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            updateUser(userAccount);
                            aSwitch.setChecked(false);
                        }
                    });
                }
            }
        });
        return view;
    }

    //region calls
    private void getUserAccount() {
        MainActivity activity = (MainActivity) getActivity();
        Call<UserAccount> call = service.getUserId(activity.getUserProfile().getId());
        call.enqueue(new Callback<UserAccount>() {
            @Override
            public void onResponse(Call<UserAccount> call, Response<UserAccount> response) {
                if (response.body() != null) {
                    userAccount = response.body();
                    fName.setText(userAccount.getName());
                    email.setText(userAccount.getEmail());
                    lName.setText(userAccount.getSurname());

                    new ImageLoadTask(userAccount.getPicture(), imageView).execute();
                }
            }

            @Override
            public void onFailure(Call<UserAccount> call, Throwable t) {
                Toast.makeText(getActivity(), "Oeps er is iets misgelopen met het ophalen van je gegevens.Probeer later opnieuw", Toast.LENGTH_LONG).show();
                Log.d(TAG, "Fail accountfragment: get account info");
            }
        });
    }

    private void updateUser(UserAccount account) {
        Call<Void> call = service.changeAccount(account);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccess()) {
                    Toast.makeText(getActivity(), "Gegevens zijn opgeslagen", Toast.LENGTH_LONG).show();
                } else {
                    Log.d(TAG, response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.d(TAG, "Fail update user");
            }
        });
    }
    //endregion
}
