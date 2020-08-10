package com.alx.abr.appforespresso;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.not;

@RunWith(AndroidJUnit4.class)
public class EnterNameTest {
    private static final String TEST_NAME = "Alexandr";
    private static final String DISPLAY_TEST_NAME = "Привет, " + TEST_NAME + "!";

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testHintDisplayed() {
        onView(withId(R.id.editText_name)).check(matches(withHint(R.string.enter_name)));
    }

    @Test
    public void testErrorMessageDisplayed() {
        onView(withId(R.id.textView_error)).check(matches(not(isDisplayed())));
        onView(withId(R.id.button_ok)).perform(click());
        onView(withId(R.id.textView_error)).check(matches(isDisplayed()));
    }

    @Test
    public void testDisplayUserName() {
        onView(withId(R.id.editText_name)).perform(typeText(TEST_NAME));
        onView(withId(R.id.button_ok)).perform(click());
        onView(withId(R.id.textView_user_name)).check(matches(withText(DISPLAY_TEST_NAME)));
    }
}
