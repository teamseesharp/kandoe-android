package com.example.kandoe;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;

import com.example.kandoe.Activity.MainActivity;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class NavigationTest {
    @Rule
    public ActivityTestRule<MainActivity> mActivity = new ActivityTestRule<>(
            MainActivity.class);

    private static ViewAction actionOpenDrawer() {
    return new ViewAction() {
        @Override
        public Matcher<View> getConstraints() {
            return isAssignableFrom(DrawerLayout.class);
        }

        @Override
        public String getDescription() {
            return "open drawer";
        }

        @Override
        public void perform(UiController uiController, View view) {
            ((DrawerLayout) view).openDrawer(GravityCompat.START);
        }
    };
}
    private static ViewAction actionCloseDrawer() {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isAssignableFrom(DrawerLayout.class);
            }

            @Override
            public String getDescription() {
                return "close drawer";
            }

            @Override
            public void perform(UiController uiController, View view) {
                ((DrawerLayout) view).closeDrawer(GravityCompat.START);
            }
        };
    }

    @Test
    public void testDrawer(){
        onView(withId(R.id.drawer_layout)).perform(actionOpenDrawer());
        onView(withId(R.id.drawer_layout)).perform(actionCloseDrawer());

    }

}
