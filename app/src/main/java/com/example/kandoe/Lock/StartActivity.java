package com.example.kandoe.Lock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.auth0.lock.LockActivity;
import com.example.kandoe.Activity.MainActivity;
import com.example.kandoe.R;

import static com.auth0.lock.Lock.AUTHENTICATION_ACTION;

public class StartActivity extends AppCompatActivity {

    private LocalBroadcastManager broadcastManager;

    private BroadcastReceiver authenticationReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final Intent newIntent = new Intent(StartActivity.this, MainActivity.class);
            newIntent.putExtras(intent);
            startActivity(newIntent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ActionBar bar = getSupportActionBar();
        assert bar != null;
        bar.hide();


        Button signInButton = (Button) findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this, LockActivity.class));
            }
        });
        broadcastManager = LocalBroadcastManager.getInstance(this);
        broadcastManager.registerReceiver(authenticationReceiver, new IntentFilter(AUTHENTICATION_ACTION));
    }
}