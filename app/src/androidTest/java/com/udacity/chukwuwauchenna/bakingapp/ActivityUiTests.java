package com.udacity.chukwuwauchenna.bakingapp;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.udacity.chukwuwauchenna.bakingapp.ui.main.MainActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;

/**
 * Created by ChukwuwaUchenna
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ActivityUiTests {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    private IdlingResource mIdlingResource;

    @Before
    public void registerIdlingResource() {
        mIdlingResource = mActivityTestRule.getActivity().getIdlingResource();
        IdlingRegistry.getInstance().register(mIdlingResource);
    }

    @Test
    public void checkRecyclerViewItemTest() {
        onView(ViewMatchers.withId(R.id.recipe_recyclerView)).perform(RecyclerViewActions.scrollToPosition(2));
    }

    @Test
    public void test_checkIfRecipeRecyclerViewIsVisible() {
        onView(ViewMatchers.withId(R.id.recipe_recyclerView)).perform(RecyclerViewActions.scrollToPosition(1)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(mIdlingResource);
        }
    }
}
