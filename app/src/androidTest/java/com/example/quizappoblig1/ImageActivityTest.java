package com.example.quizappoblig1;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.CoreMatchers.containsString;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ImageActivityTest {
    @Rule
    public ActivityScenarioRule<ImageActivity> activityScenarioRule
            = new ActivityScenarioRule<>(ImageActivity.class);

     @Test
        public void testImage() {
            onView(withId(R.id.baseimage)).check(matches(isDisplayed()));
        }
     @Test
    public void testInput() {
        onView(withId(R.id.textInputEditText))
                .perform(typeText("Golden Retriever"), ViewActions.closeSoftKeyboard());
         onView(withId(R.id.textInputEditText))
                 .check(matches(withText(containsString("Golden Retriever"))));

    }
    @Test
    public void testChooseButton() {
        onView(withId(R.id.choose)).check(matches(isDisplayed()));
        onView(withId(R.id.choose)).perform(ViewActions.click());
    }
    @Test
    public void testSubmitButton() {
        onView(withId(R.id.submit)).check(matches(isDisplayed()));
        onView(withId(R.id.submit)).perform(ViewActions.click());
    }


}
