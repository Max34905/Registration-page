package com.example.registrationpage;

import android.view.View;

import androidx.test.espresso.matcher.BoundedMatcher;

import com.google.android.material.textfield.TextInputLayout;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

public class ErrorMatcher {
    public static Matcher<View> withErrorText(final String expectedError) {
        return new BoundedMatcher<View, TextInputLayout>(TextInputLayout.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText(expectedError);
            }

            @Override
            protected boolean matchesSafely(TextInputLayout textInputLayout) {
                return expectedError.equals(textInputLayout.getError().toString());
            }
        };
    }
}
