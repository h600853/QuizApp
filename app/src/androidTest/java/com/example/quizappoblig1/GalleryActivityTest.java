package com.example.quizappoblig1;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.doubleClick;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.CoreMatchers.not;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class GalleryActivityTest {
    @Rule
    public ActivityScenarioRule<GalleryActivity> activityScenarioRule
            = new ActivityScenarioRule<>(GalleryActivity.class);
    @Test
    public void delteImage() {
        onView(withId(R.id.recyclerView)).check(matches(hasMinimumChildCount(3)));
        deleteImage();
        onView(withId(R.id.recyclerView)).check(matches(hasChildCount(2)));


    }
    private void deleteImage() {

        onView(withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

    }
}
