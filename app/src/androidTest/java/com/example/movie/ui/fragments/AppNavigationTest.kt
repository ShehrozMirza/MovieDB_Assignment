package com.example.movie.ui.fragments

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.movie.R
import com.example.movie.ui.MainActivity
import com.example.movie.EspressoUriIdlingResource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Large instrumented test for navigation between screens.
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
@HiltAndroidTest
class AppNavigationTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoUriIdlingResource.uriIdlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoUriIdlingResource.uriIdlingResource)
    }

    /**
     * Test, to check when the the TrendingMovies on the list
     * is clicked, then we navigate to MovieDetailScreen.
     */
    @Test
    fun trendingMoviesScreen_clickOnRecyclerView_navigateTo_MovieDetailScreen() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        Thread.sleep(3000)
        onView(withId(R.id.recyclerTrendingMovies))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        onView(withId(R.id.recyclerTrendingMovies)).check(doesNotExist())
        onView(withId(R.id.ivCover)).check(matches(isDisplayed()))
        activityScenario.close()
    }

    /**
     * Test, to check when the the TrendingMovies on the list
     * is clicked, then we navigate to MovieDetailScreen and again pressed back
     * to navigate to Trending movies Screen
     */
    @Test
    fun movieDetailScreen_PressBackButton_navigateTo_TrendingMoviesScreen() {
        //Start Up Trending Movies screen.
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        Thread.sleep(3000)
        //Navigate to MovieDetailScreen and click UpButton.
        onView(withId(R.id.recyclerTrendingMovies))
            .perform(actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withContentDescription("Navigate up")).perform(click())
        //Verify navigation to Trending Movies Screen
        onView(withId(R.id.tvCategory)).check(matches(isDisplayed()))
        activityScenario.close()
    }
}