package com.alx.abr.appforespresso;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static com.alx.abr.appforespresso.CustomMatchers.withAdaptedData;
import static com.alx.abr.appforespresso.CustomMatchers.withItemContent;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;

@RunWith(AndroidJUnit4.class)
public class SpinnerTest {
    private static final String INVALID_CITY = "Челябинск";
    private static final String VALID_CITY = "Новосибирск";
    private static final String FIRST_ITEM = "Выберите ваш город…";

    @Rule
    public ActivityScenarioRule<BasicActivity> activityScenarioRule
            = new ActivityScenarioRule<>(BasicActivity.class);

    @Test
    public void testCityNotInList() {
        onView(withId(R.id.spinner)).check(matches(not(withAdaptedData(withItemContent(INVALID_CITY)))));
    }

    @Test
    public void testActionFirstItemSelected() {
        onView(withId(R.id.spinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)))).atPosition(0).perform(click());
        onView(withId(R.id.textView_city)).check(matches(not(withText("Ваш город " + FIRST_ITEM + "!"))));
    }

    public void testTextViewUpdatesIfValidCitySelected() {
        onView(withId(R.id.spinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is(VALID_CITY))).perform(click());
        onView(withId(R.id.textView_city)).check(matches(withText("Ваш город " + VALID_CITY + "!")));
    }
}
