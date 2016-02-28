package com.example.kandoe;

import android.app.Application;

import com.auth0.core.Strategies;
import com.auth0.facebook.FacebookIdentityProvider;
import com.auth0.googleplus.GooglePlusIdentityProvider;
import com.auth0.lock.Lock;
import com.auth0.lock.LockProvider;

/**
 * Created by Thomas on 28-2-2016.
 */
public class KandoeApplication extends Application implements LockProvider {

    private Lock lock;

    public void onCreate() {
        super.onCreate();
        lock = new Lock.Builder()
                .loadFromApplication(this)
                        /** Other configuration goes here */
                .withIdentityProvider(Strategies.GooglePlus, new GooglePlusIdentityProvider(this))
                .withIdentityProvider(Strategies.Facebook, new FacebookIdentityProvider(this))
                .closable(true)
                .build();
    }

    @Override
    public Lock getLock() {
        return lock;
    }

}
