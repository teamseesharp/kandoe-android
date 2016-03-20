package com.example.kandoe.Utilities.Lock;

import android.app.Application;

import com.auth0.lock.Lock;
import com.auth0.lock.LockProvider;

/**
 * class for Lock from Auth0
 */
public class KandoeApplication extends Application implements LockProvider {

    private Lock lock;

    public void onCreate() {
        super.onCreate();
        lock = new Lock.Builder()
                .loadFromApplication(this)
                        /** Other configuration goes here */
                //.withIdentityProvider(Strategies.GooglePlus, new GooglePlusIdentityProvider(this))
               // .withIdentityProvider(Strategies.Facebook, new FacebookIdentityProvider(this))

                .closable(true)
                .build();
    }

    @Override
    public Lock getLock() {
        return lock;
    }

}
