package com.example.kandoe;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.example.kandoe.Activity.LoginActivity;

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
public class LoginTest {

    String username,paswoord;

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule = new ActivityTestRule<>(
            LoginActivity.class);

    @Before
    public void initValidString() {
        // Specify a valid string.
        username= "test@test.be";
        paswoord = "test";
    }

    @Test
    public void changeText_sameActivity() {
        // Type text and then press the button.
        onView(withId(R.id.txtEmail))
                .perform(typeText(username), closeSoftKeyboard());
        onView(withId(R.id.txtPaswoord)).perform(typeText(paswoord), closeSoftKeyboard());


        onView(withId(R.id.btn_login)).perform(click());


    }

}
