package com.alx.abr.appforespresso;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

@RunWith(AndroidJUnit4.class)
public class ActionBarTest {
    @Rule
    public ActivityScenarioRule<BasicActivity> activityScenarioRule
            = new ActivityScenarioRule<>(BasicActivity.class);

    @Test
    public void testClickOnMenuItem() {
        onView(withId(R.id.action_settings)).perform(click());
        onView(withId(R.id.textView_action_mode)).check(matches(withText(R.string.action_settings)));
    }

    @Test
    public void testOverflowMenuOrOptionsMenu() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getContext());
        onView(withText(R.string.action_about)).perform(click());
        onView(withId(R.id.textView_action_mode)).check(matches(withText(R.string.action_about)));
    }

    @Test
    public void testToggleActionMode() {
        onView(withId(R.id.button_toggle_actionmode)).perform(click());
        onView(withId(R.id.action_one)).perform(click());
        onView(withId(R.id.textView_action_mode)).check(matches(withText(R.string.action_mode_one)));
    }
}
