package com.udacity.chukwuwauchenna.bakingapp;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.ViewInteraction;
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
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

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
        ViewInteraction recyclerView = onView(withId(R.id.recipe_recyclerView));
        recyclerView.perform(actionOnItemAtPosition(0, click()));}

    @Test
    public void test_checkIfRecipeRecyclerViewIsVisible() {

        onView(ViewMatchers.withId(R.id.recipe_recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
    }

    @Test
    public void is_recyclerViewDisplayed(){
        onView(withId(R.id.recipe_recyclerView)).check(matches(isDisplayed()));
    }

    @After
    public void unregisterIdlingResource() {
        if (mIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(mIdlingResource);
        }
    }
}
