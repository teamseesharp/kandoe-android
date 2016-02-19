package com.example.kandoe.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.kandoe.Fragment.SessionListFragment;
import com.example.kandoe.R;

/**
 * Created by JoachimDs on 19/02/2016.
 */
public class SessionActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sessiondetail);

        Intent intent = getIntent();
        int id = intent.getExtras().getInt("SESSIONID");
        //Todo ik weet niet welke werkt.
        //int id = Integer.parseInt(intent.getExtras().getString("SESSIONID"));

        //Todo sessiongegevens ophalen via de sessionId

        //Todo sessiongegevens tonen
    }




}
