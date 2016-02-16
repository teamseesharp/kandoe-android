package com.example.kandoe.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kandoe.Model.CreatedUser;
import com.example.kandoe.R;

import java.util.regex.Pattern;


/**
 * Created by Michelle on 16-5-2015.
 */
public class RegisterActivity extends Activity {

    private Button btnCreate;
    private EditText email,password,confirmpassword,first_name,last_name;
    private TextView txtError;

    private CreatedUser UserToCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        UserToCreate = new CreatedUser();
        makeItems();
        makeListener();
    }

    private void makeItems() {
        email = (EditText) findViewById(R.id.register_email);
        password = (EditText) findViewById(R.id.register_password);
        first_name = (EditText) findViewById(R.id.register_first_name);
        last_name = (EditText) findViewById(R.id.register_last_name);
        confirmpassword = (EditText) findViewById(R.id.register_confirmpassword);
        txtError = (TextView) findViewById(R.id.register_txtError);

        btnCreate = (Button) findViewById(R.id.btn_createAccount);
    }

    private void makeListener() {
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Pattern pattern = Pattern.compile(
                        "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$");

                if (isEmpty(email) || !pattern.matcher((email.getText())).matches()) {
                    changeTextColor("red", "email");
                } else {
                    changeTextColor("black", "email");
                    UserToCreate.setEmail(email.getText().toString());
                }
            }
        });

        first_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (isEmpty(first_name)) {
                    changeTextColor("red", "first_name");
                    txtError.setText("Vul je voornaam in !");
                } else {
                    changeTextColor("black", "first_name");
                    UserToCreate.setFirstname(first_name.getText().toString());
                }
            }
        });

        last_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (isEmpty(last_name)) {
                    changeTextColor("red", "last_name");
                } else {
                    changeTextColor("black", "last_name");
                    UserToCreate.setLastname(last_name.getText().toString());
                }
            }
        });

        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (isEmpty(password) || password.getText().length() < 6) {
                    changeTextColor("red", "password");
                    txtError.setText("Wachtwoord moet minstens 6 karaters lang zijn!");
                } else {
                    changeTextColor("black", "password");
                    UserToCreate.setPassword(password.getText().toString());
                }
            }
        });

        confirmpassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (isEmpty(confirmpassword) || !password.getText().toString().equals(confirmpassword.getText().toString())) {
                    changeTextColor("red", "confirmpassword");
                    txtError.setText("Niet identiek aan wachtwoord!");
                } else {
                    changeTextColor("black", "confirmpassword");
                    UserToCreate.setConfirmpassword(confirmpassword.getText().toString());
                }
            }
        });
    }

    private void register() {
        //TODO: callback uitwerken
        startActivity(new Intent(getApplication(), MainActivity.class));
    }

    private boolean isEmpty(EditText eText) {
        return (eText.getText().toString() == null || eText.getText().toString().trim().length() == 0) ? true : false;
    }

    private void changeTextColor(String newColor, String editTextToChange) {
        int colorToUse = android.R.color.black;
        int idToFind = R.id.register_first_name;

        switch (newColor) {
            case "red":
                colorToUse = android.R.color.holo_red_dark;
                break;
            case "black":
                colorToUse = android.R.color.black;
                break;
        }
        switch (editTextToChange) {
            case "first_name":
                idToFind = R.id.register_first_name;
                break;
            case "last_name":
                idToFind = R.id.register_last_name;
                break;
            case "email":
                idToFind = R.id.register_email;
                break;
            case "password":
                idToFind = R.id.register_password;
                break;
            case "confirmpassword":
                idToFind = R.id.register_confirmpassword;
                break;
            default:
                idToFind = R.id.register_first_name;
                break;
        }

        ((EditText) findViewById(idToFind)).setTextColor(getResources().getColor(colorToUse));
    }
}
