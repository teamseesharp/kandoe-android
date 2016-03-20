package com.example.kandoe.Utilities.Lock;

import android.app.Application;
import android.support.test.espresso.core.deps.guava.eventbus.EventBus;

import com.auth0.core.Auth0;
import com.auth0.core.Strategies;
import com.auth0.facebook.FacebookIdentityProvider;
import com.auth0.identity.IdentityProvider;
import com.auth0.identity.WebIdentityProvider;
import com.auth0.identity.web.CallbackParser;
import com.auth0.lock.Lock;
import com.auth0.lock.LockProvider;
import com.example.kandoe.R;

/**
 * Created by Thomas on 28-2-2016.
 */
public class KandoeApplication extends Application implements LockProvider {

    private Lock lock;


    public void onCreate() {
        super.onCreate();

       // Auth0 auth0 = new Auth0(getString(R.string.auth0_client_id), getString(R.string.auth0_domain_name));

        lock = new Lock.Builder()
                .loadFromApplication(this)
                        /** Other configuration goes here */
                //.withIdentityProvider(Strategies.GooglePlus, new GooglePlusIdentityProvider(this))
                .withIdentityProvider(Strategies.Facebook, new FacebookIdentityProvider(this))
             //   .withIdentityProvider(Strategies.Twitter, new WebIdentityProvider(new CallbackParser(),auth0.getClientId(), auth0.getAuthorizeUrl()))

                .closable(true)
                .build();
    }

    @Override
    public Lock getLock() {
        return lock;
    }

}
