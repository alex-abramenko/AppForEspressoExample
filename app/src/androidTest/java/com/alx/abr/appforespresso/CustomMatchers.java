package com.alx.abr.appforespresso;

import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;

import androidx.test.espresso.matcher.BoundedMatcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import static androidx.test.espresso.core.internal.deps.guava.base.Preconditions.checkNotNull;
import static org.hamcrest.Matchers.equalTo;

public class CustomMatchers {

    public static Matcher<View> withAdaptedData(final Matcher<Object> dataMatcher) {
        return new TypeSafeMatcher<View>() {

            @Override
            public void describeTo(Description description) {
                description.appendText("with class name: ");
                dataMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof AdapterView)) {
                    return false;
                }
                @SuppressWarnings("rawtypes")
                Adapter adapter = ((AdapterView) view).getAdapter();
                for (int i = 0; i < adapter.getCount(); i++) {
                    if (dataMatcher.matches(adapter.getItem(i))) {
                        return true;
                    }
                }
                return false;
            }
        };
    }

    public static Matcher<Object> withItemContent(String expectedText) {
        checkNotNull(expectedText);
        return withItemContent(equalTo(expectedText));
    }

    public static Matcher<Object> withItemContent(final Matcher<String> itemTextMatcher) {
        checkNotNull(itemTextMatcher);
        return new BoundedMatcher<Object, String>(String.class) {
            @Override
            public boolean matchesSafely(String text) {
                return itemTextMatcher.matches(text);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with item content: ");
                itemTextMatcher.describeTo(description);
            }
        };
    }
}
