package com.example.kandoe;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.PerformException;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;

import com.auth0.core.Token;
import com.auth0.core.UserProfile;
import com.example.kandoe.Activity.MainActivity;
import com.example.kandoe.Controller.Adapters.SessionAdapter;
import com.example.kandoe.Model.Card;
import com.example.kandoe.Model.Organisation;
import com.example.kandoe.Model.Session;

import junit.framework.AssertionFailedError;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.core.deps.guava.base.Preconditions.checkNotNull;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.fail;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by Thomas on 19-3-2016.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    private static final String TAG = "SessionTest";
    private MainActivity mActivity;


    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Before
    public void initValidString() {
        mActivityRule.getActivity().setTesting(true);
    }


    @Test
    public void testSessionDisplay() {
        // aversionsListView is the id of the ExpandableListView containing the expandable list

        boolean succes = true;

        //loop because delay of API call, can return an Exception when not fully loaded.
        while (succes) {
            //region Sessionlist test
            try {

                Espresso.onData(allOf(is(instanceOf(Organisation.class))))
                        .inAdapterView(withId(R.id.explv)).atPosition(0)
                        .perform(click());

                Espresso.onData(allOf(is(instanceOf(Session.class))))
                        .inAdapterView(withId(R.id.explv)).atPosition(0)
                        .perform(click());

                succes = true;
            } catch (PerformException e) {
                succes = false;
                Log.d(TAG, "data not loaded");

            }
            //endregion

            //Code for setup, handled when no setup needed
            //region Setup test
            try {

                onData(anything()).inAdapterView(withId(R.id.grdcards)).atPosition(0)
                        .perform(click());

                onData(anything()).inAdapterView(withId(R.id.grdcards)).atPosition(0)
                        .perform(click());
                onData(anything()).inAdapterView(withId(R.id.grdcards)).atPosition(0)
                        .perform(click());

                onView(withId(R.id.btnPlay)).perform(click());

                succes = true;
            } catch (NoMatchingViewException e) {
                succes = false;
                Log.d(TAG, "no setup needed");


            }
            //endregion


        }
    }


    @Test
    public void testVote() {
        // aversionsListView is the id of the ExpandableListView containing the expandable list

        boolean succes = true;

        //loop because delay of API call, can return an Exception when not fully loaded.
        while (succes) {
            //region Sessionlist test
            try {

                Espresso.onData(allOf(is(instanceOf(Organisation.class))))
                        .inAdapterView(withId(R.id.explv)).atPosition(0)
                        .perform(click());

                Espresso.onData(allOf(is(instanceOf(Session.class))))
                        .inAdapterView(withId(R.id.explv)).atPosition(0)
                        .perform(click());

                succes = true;
            } catch (PerformException e) {
                succes = false;
                Log.d(TAG, "data not loaded");


            }
            //endregion

            //Code for setup, handled when no setup needed
            //region Setup test
            try {

                onData(anything()).inAdapterView(withId(R.id.grdcards)).atPosition(0)
                        .perform(click());

                onData(anything()).inAdapterView(withId(R.id.grdcards)).atPosition(0)
                        .perform(click());
                onData(anything()).inAdapterView(withId(R.id.grdcards)).atPosition(0)
                        .perform(click());

                onView(withId(R.id.btnPlay)).perform(click());

                succes = true;
            } catch (NoMatchingViewException e) {
                succes = false;
                Log.d(TAG, "no setup needed");


            }
            //endregion


            onData(anything()).inAdapterView(withId(R.id.lvCards)).atPosition(0)
                    .perform(click());

            try {

                onView(withId(R.id.votebutton)).check(matches(isDisplayed())).perform(click());

            } catch (AssertionFailedError e) {
                fail("Not our turn to vote. Expected to fail in this case");
            }
        }
    }

    @Test
    public void testSessionChat() {
        // aversionsListView is the id of the ExpandableListView containing the expandable list

        boolean succes = true;

        //loop because delay of API call, can return an Exception when not fully loaded.
        while (succes) {
            //region Sessionlist test
            try {

                Espresso.onData(allOf(is(instanceOf(Organisation.class))))
                        .inAdapterView(withId(R.id.explv)).atPosition(0)
                        .perform(click());

                Espresso.onData(allOf(is(instanceOf(Session.class))))
                        .inAdapterView(withId(R.id.explv)).atPosition(0)
                        .perform(click());

                succes = true;
            } catch (PerformException e) {
                succes = false;
                Log.d(TAG, "data not loaded");

            }
            //endregion

            //Code for setup, handled when no setup needed
            //region Setup test
            try {

                onData(anything()).inAdapterView(withId(R.id.grdcards)).atPosition(0)
                        .perform(click());

                onData(anything()).inAdapterView(withId(R.id.grdcards)).atPosition(0)
                        .perform(click());
                onData(anything()).inAdapterView(withId(R.id.grdcards)).atPosition(0)
                        .perform(click());

                onView(withId(R.id.btnPlay)).perform(click());

                succes = true;
            } catch (NoMatchingViewException e) {
                succes = false;
                Log.d(TAG, "no setup needed");


            }
            //endregion

            try {


                onView(withId(R.id.action_chat)).perform(click());

                onView(withId(R.id.txtMsg)).perform(typeText("Hej, ik ben een geautomatiseerde tester! Groetjes!"));
                onView(withId(R.id.btnSend)).perform(click());

            } catch (NoMatchingViewException e) {

                Log.d(TAG, "testSessions: " + e.getViewMatcherDescription());
            }


            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }

}