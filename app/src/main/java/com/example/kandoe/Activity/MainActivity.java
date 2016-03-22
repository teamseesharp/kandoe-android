package com.example.kandoe.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.auth0.core.Token;
import com.auth0.core.UserProfile;
import com.example.kandoe.Activity.Fragment.AccountFragment;
import com.example.kandoe.Activity.Fragment.CircleFragment;
import com.example.kandoe.Activity.Fragment.FinishedSessionListFragment;
import com.example.kandoe.Activity.Fragment.HelpFragment;
import com.example.kandoe.Activity.Fragment.MainFragment;
import com.example.kandoe.Activity.Fragment.MySessionsListFragment;
import com.example.kandoe.Activity.Fragment.NavigationDrawerFragment;
import com.example.kandoe.Activity.Fragment.SessionListFragment;
import com.example.kandoe.Model.UserAccount;
import com.example.kandoe.R;
import com.example.kandoe.Utilities.API.APIServiceGenerator;
import com.example.kandoe.Utilities.API.KandoeBackendAPI;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends ActionBarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks, CircleFragment.OnFragmentInteractionListener {
    private static final String TAG = "Mainactivity";

    // Fragment managing the behaviors, interactions and presentation of the navigation drawer.
    private NavigationDrawerFragment mNavigationDrawerFragment;

    private CharSequence mTitle;
    private UserProfile userProfile;
    private UserAccount userAccount;
    private UserAccount postAccount;
    private Token token;
    private KandoeBackendAPI service;
    private boolean testing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        postAccount = new UserAccount();

        Intent intent = getIntent();
        if (intent != null) {
            if (intent.getBooleanExtra("testing",false)) {
                token = new Token("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2thbmRvZS5ldS5hdXRoMC5jb20vIiwic3ViIjoiYXV0aDB8NTZkNDU5MTMxN2FjYTkxZjFhZmY1ZGZiIiwiYXVkIjoib0ZnUUJtZnNsSHFlYWhZazJpdk5OQXprZ2NQZ3dUYTgiLCJleHAiOjE0NTg0Mjc2NDIsImlhdCI6MTQ1ODM5MTY0Mn0.1WrQleFrOys5H3nwfWftxv8lBovWsMsGH7sVpWYY0fg",
                        "3MLwR1Yt7rPn50pF",
                        "bearer",
                        "Vu3ZM67oczh8Xo46sTGPwLH79vE3swAPpmoiCBp4FuvAU");

                HashMap<String, Object> values = new HashMap<>();
                values.put("user_id", "auth0|56d4591317aca91f1aff5dfb");
                values.put("nickname", "thomastvd");
                values.put("email", "thomastvd@gmail.com");
                userProfile = new UserProfile(values);

            } else {
                userProfile = intent.getParcelableExtra("profile");
                token = intent.getParcelableExtra("token");
            }

                System.out.println(token.getIdToken());
                System.out.println(userProfile.getId());
                postAccount.setName(userProfile.getNickname());
            if(userProfile.getEmail() == null){
                postAccount.setEmail("");
            } else {
                postAccount.setEmail(userProfile.getEmail());
                postAccount.setSecret(userProfile.getId());
            }
        }

        service = APIServiceGenerator.createService(KandoeBackendAPI.class, token.getIdToken());

        createNewUser(postAccount);
        getUserAccount(userProfile);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#5f995f")));
        }

        mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.container, PlaceholderFragment.newInstance(position + 1)).commit();
    }

    public void onSectionAttached(int number) {
        Fragment fragment = null;
        android.support.v4.app.ListFragment listfragment = null;

        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                fragment = new SessionListFragment(service);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                fragment = new FinishedSessionListFragment(service,userAccount);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                fragment = new MySessionsListFragment(service, userAccount);
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                fragment = new AccountFragment(service);
                break;
            default:
                fragment = new MainFragment();
                break;
        }

        if (fragment != null) {
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_main, fragment).commit();

        } else if (listfragment != null) {
            android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_main, listfragment).commit();

        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setTitle(mTitle);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            restoreActionBar();
            return true;
        }

        menu.clear();
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_login);
        item.setVisible(true);
        MenuItem item2 = menu.findItem(R.id.action_help);
        item2.setVisible(true);
        MenuItem item3 = menu.findItem(R.id.action_chat);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_login) {
            //restart app
            Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
            System.exit(0);
        }

        if( id == R.id.action_help){
            mTitle = getString(R.string.title_section5);
            restoreActionBar();

            Fragment help = new HelpFragment();
            if (help != null) {
                android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.fragment_main, help).commit();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setmTitle(CharSequence mTitle) {
        this.mTitle = mTitle;
    }

    //region calls
    public void getUserAccount(UserProfile profile) {
        Call<UserAccount> call = service.getUserId(profile.getId());

        call.enqueue(new Callback<UserAccount>() {
            @Override
            public void onResponse(Call<UserAccount> call, Response<UserAccount> response) {
                userAccount = response.body();
            }

            @Override
            public void onFailure(Call<UserAccount> call, Throwable t) {
                System.out.println("Get user faill");
            }
        });
    }

    public void createNewUser(UserAccount account) {
        Call<UserAccount> call = service.postUser(account);
        call.enqueue(new Callback<UserAccount>() {
            @Override
            public void onResponse(Call<UserAccount> call, Response<UserAccount> response) {
                if (response.isSuccess()) {
                    System.out.println("post user succes ");
                } else {
                    System.out.println("post user onreponse failed. Code: "+ response.code());
                }
            }

            @Override
            public void onFailure(Call<UserAccount> call, Throwable t) {
                Log.d(TAG,"post user onfailre " + t.getMessage());
            }
        });
    }
    //endregion calls

    public void setTesting(boolean testing) {
        this.testing = testing;
    }
}
