package com.example.quizappoblig1;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule
            = new ActivityScenarioRule<>(MainActivity.class);
    @Test
    public void testGalleryButton() {
        Intents.init();

        onView(withId(R.id.gallery)).check(matches(isDisplayed()));
        onView(withId(R.id.gallery)).check(matches(isClickable()));
        onView(withId(R.id.gallery)).perform(click());

        Intents.intended(hasComponent(GalleryActivity.class.getName()));
        Intents.release();
    }
    @Test
    public void testQuizButton() {
        Intents.init();

        onView(withId(R.id.quiz)).check(matches(isDisplayed()));
        onView(withId(R.id.quiz)).check(matches(isClickable()));
        onView(withId(R.id.quiz)).perform(click());

        Intents.intended(hasComponent(QuizActivity.class.getName()));
        Intents.release();
    }
}