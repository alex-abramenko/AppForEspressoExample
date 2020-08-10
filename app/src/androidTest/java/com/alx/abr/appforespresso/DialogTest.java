package com.alx.abr.appforespresso;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class DialogTest {
    @Rule
    public ActivityScenarioRule<BasicActivity> activityScenarioRule
            = new ActivityScenarioRule<>(BasicActivity.class);

    @Test
    public void testCheckDialogDisplayed() {
        onView(withId(R.id.button_dialog)).perform(click());
        onView(withText(R.string.dialog_title)).check(matches(isDisplayed()));
    }

    @Test
    public void testClickFirstButton() {
        onView(withId(R.id.button_dialog)).perform(click());
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.textView_userChoice)).check(matches(
                withText("Ты выбрал черный цвет!")));
    }

    @Test
    public void testClickSecondButton() {
        onView(withId(R.id.button_dialog)).perform(click());
        onView(withId(android.R.id.button2)).perform(click());
        onView(withId(R.id.textView_userChoice)).check(matches(
                withText("Ты выбрал зеленый цвет!")));
    }
}
