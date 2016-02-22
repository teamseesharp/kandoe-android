package com.example.kandoe;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.example.kandoe.Activity.LoginActivity;
import com.example.kandoe.Activity.RegisterActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by Thomas on 2016-02-16.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RegistratieTest {

    String username,paswoord,firstname,lastname;

    @Rule
    public ActivityTestRule<RegisterActivity> mActivityRule = new ActivityTestRule<>(
            RegisterActivity.class);

    @Before
    public void initValidString() {
        // Specify a valid string.
        username= "test@test.be";
        paswoord = "test123";
        firstname = "Bennie";
        lastname ="Bax";
    }

    @Test
    public void register() {
        // Type text and then press the button.
        onView(withId(R.id.txtregister_email)).perform(typeText(username), closeSoftKeyboard());
        onView(withId(R.id.txtregister_password)).perform(typeText(paswoord), closeSoftKeyboard());
        onView(withId(R.id.txtregister_confirmpassword)).perform(typeText(paswoord), closeSoftKeyboard());
        onView(withId(R.id.txtregiser_firstname)).perform(typeText(firstname), closeSoftKeyboard());
        onView(withId(R.id.txtregister_last_name)).perform(typeText(lastname), closeSoftKeyboard());
        onView(withId(R.id.btn_createAccount)).perform(click());


    }
}
