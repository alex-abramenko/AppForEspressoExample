package com.alx.abr.appforespresso;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class RecyclerViewTest {
    private static final String TEST_BRAND = "Chery";
    private static final String TEST_MODEL = "Amulet";
    private static final int TEST_POS = 7;

    @Rule
    public ActivityScenarioRule<BasicActivity> activityScenarioRule
            = new ActivityScenarioRule<>(BasicActivity.class);

    @Test
    public void testClickAtPosition() {
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(TEST_POS, click()));
        onView(withId(R.id.textView_car)).check(matches(withText(
                String.format("Вы выбрали %s %s, хороший выбор!", TEST_BRAND, TEST_MODEL))));
    }

    @Test
    public void testClickOnViewInRow() {
        onView(withId(R.id.recyclerView)).perform(RecyclerViewActions.actionOnItem(
                hasDescendant(withText(TEST_MODEL)), click()));
        onView(withId(R.id.textView_car)).check(matches(withText(
                String.format("Вы выбрали %s %s, хороший выбор!", TEST_BRAND, TEST_MODEL))));
    }
}
