package com.example.quizappoblig1;

import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import junit.framework.AssertionFailedError;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class QuizActivityTest {
    @Rule
    public ActivityScenarioRule<QuizActivity> activityScenarioRule
            = new ActivityScenarioRule<>(QuizActivity.class);

    @Test
    public void testScoreUpdate() {
        Espresso.onView(withId(R.id.option1)).perform(ViewActions.click());

        ViewInteraction interaction = Espresso.onView(withId(R.id.answer));
        try {
        interaction.check(ViewAssertions.matches(ViewMatchers.withText("Correct!")));
            Espresso.onView(withId(R.id.points)).check(ViewAssertions.matches(withText("1")));

        } catch (AssertionFailedError e) {
            Espresso.onView(withId(R.id.points)).check(ViewAssertions.matches(withText("0")));

        }
    }

    }

