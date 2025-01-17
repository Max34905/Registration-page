package com.example.registrationpage;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static androidx.test.espresso.assertion.ViewAssertions.matches;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.registrationpage.ui.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RegistrationEspressoUITest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void under18RegistrationProcess() {
        onView(withId(R.id.name_input)).perform(typeText("Maksym"), closeSoftKeyboard());
        onView(withId(R.id.surname_input)).perform(typeText("Petrov"), closeSoftKeyboard());
        onView(withId(R.id.password_input)).perform(typeText("Strongggg1"), closeSoftKeyboard());
        onView(withId(R.id.sign_up_button)).perform(click());

        onView(withId(R.id.parent_name_input)).perform(typeText("Oleksandr"), closeSoftKeyboard());
        onView(withId(R.id.parent_continue_button)).perform(click());

        onView(withId(R.id.name_surname_text)).check(matches(withText("Maksym Petrov")));
        onView(withId(R.id.parent_name_text)).check(matches(withText("Oleksandr")));
    }

    @Test
    public void over18RegistrationProcess() {
        onView(withId(R.id.name_input)).perform(typeText("Maksym"), closeSoftKeyboard());
        onView(withId(R.id.surname_input)).perform(typeText("Petrov"), closeSoftKeyboard());
        onView(withId(R.id.password_input)).perform(typeText("Strongggg1"), closeSoftKeyboard());
        onView(withId(R.id.adult_switch)).perform(click());
        onView(withId(R.id.sign_up_button)).perform(click());

        onView(withId(R.id.name_surname_text)).check(matches(withText("Maksym Petrov")));
        onView(withId(R.id.parent_name_text)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.INVISIBLE)));
    }

    @Test
    public void emptyNameField() {
        onView(withId(R.id.surname_input)).perform(typeText("Petrov"), closeSoftKeyboard());
        onView(withId(R.id.password_input)).perform(typeText("Strongggg1"), closeSoftKeyboard());
        onView(withId(R.id.sign_up_button)).perform(click());

        String errorString = InstrumentationRegistry.getInstrumentation().
                getTargetContext().getString(R.string.empty_field_error);
        onView(withId(R.id.name_input_layout)).check(matches(ErrorMatcher.withErrorText(errorString)));
    }

    @Test
    public void nonAlphabeticalSurname() {
        onView(withId(R.id.name_input)).perform(typeText("Maksym"), closeSoftKeyboard());
        onView(withId(R.id.surname_input)).perform(typeText("Petr  ov"), closeSoftKeyboard());
        onView(withId(R.id.password_input)).perform(typeText("Strongggg1"), closeSoftKeyboard());
        onView(withId(R.id.sign_up_button)).perform(click());

        String errorString = InstrumentationRegistry.getInstrumentation().
                getTargetContext().getString(R.string.invalid_name_error);
        onView(withId(R.id.surname_input_layout)).check(matches(ErrorMatcher.withErrorText(errorString)));
    }

    @Test
    public void unreliablePassword() {
        onView(withId(R.id.name_input)).perform(typeText("Maksym"), closeSoftKeyboard());
        onView(withId(R.id.surname_input)).perform(typeText("Petrov"), closeSoftKeyboard());
        onView(withId(R.id.password_input)).perform(typeText("weak"), closeSoftKeyboard());
        onView(withId(R.id.sign_up_button)).perform(click());

        String errorString = InstrumentationRegistry.getInstrumentation().
                getTargetContext().getString(R.string.unreliable_password_error);
        onView(withId(R.id.password_input_layout)).check(matches(ErrorMatcher.withErrorText(errorString)));
    }

    @Test
    public void emptyParentNameField() {
        onView(withId(R.id.name_input)).perform(typeText("Maksym"), closeSoftKeyboard());
        onView(withId(R.id.surname_input)).perform(typeText("Petrov"), closeSoftKeyboard());
        onView(withId(R.id.password_input)).perform(typeText("Strongggg1"), closeSoftKeyboard());
        onView(withId(R.id.sign_up_button)).perform(click());

        onView(withId(R.id.parent_continue_button)).perform(click());
        String errorString = InstrumentationRegistry.getInstrumentation().
                getTargetContext().getString(R.string.empty_field_error);
        onView(withId(R.id.parent_name_input_layout)).check(matches(ErrorMatcher.withErrorText(errorString)));
    }

    @Test
    public void nonAlphabeticalParentName() {
        onView(withId(R.id.name_input)).perform(typeText("Maksym"), closeSoftKeyboard());
        onView(withId(R.id.surname_input)).perform(typeText("Petrov"), closeSoftKeyboard());
        onView(withId(R.id.password_input)).perform(typeText("Strongggg1"), closeSoftKeyboard());
        onView(withId(R.id.sign_up_button)).perform(click());

        onView(withId(R.id.parent_name_input)).perform(typeText("Oleks1ndr"), closeSoftKeyboard());
        onView(withId(R.id.parent_continue_button)).perform(click());
        String errorString = InstrumentationRegistry.getInstrumentation().
                getTargetContext().getString(R.string.invalid_name_error);
        onView(withId(R.id.parent_name_input_layout)).check(matches(ErrorMatcher.withErrorText(errorString)));
    }
}
