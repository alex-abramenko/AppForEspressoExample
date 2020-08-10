package com.alx.abr.appforespresso;

import android.widget.TimePicker;

import androidx.test.espresso.contrib.PickerActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class TimePickerTest {
    @Rule
    public ActivityScenarioRule<BasicActivity> activityScenarioRule
            = new ActivityScenarioRule<>(BasicActivity.class);

    @Test
    public void testSetTime() {
        int hour = 10;
        int minutes = 59;

        onView(withId(R.id.button_timepicker)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(hour, minutes));
        onView(withId(android.R.id.button1)).perform(click());
        onView(withId(R.id.textView_user_date_time)).check(matches(
                withText("Ваше время: " + hour + ":" + minutes)));
    }
}
