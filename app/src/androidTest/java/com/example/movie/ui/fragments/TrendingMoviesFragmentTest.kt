package com.example.movie.ui.fragments

import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.example.movie.R
import com.example.movie.adapters.MoviesAdapter
import com.example.movie.ui.trending.TrendingMoviesFragment
import com.example.movie.utils.EspressoUriIdlingResource
import com.example.movie.utils.clickChildViewWithId
import com.example.movie.utils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

@HiltAndroidTest
@MediumTest
@ExperimentalCoroutinesApi
class TrendingMoviesFragmentTest {

    private val mockNavController = mock(NavController::class.java)

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
    Checking is fragment is Displayed or not
     **/
    @Test
    fun isFragmentDisplayed() {
        launchFragmentInHiltContainer<TrendingMoviesFragment> {
        }
        Espresso.onView(withId(R.id.tvCategory))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    /**
    Checking is fragment all views fully rendered or not
     **/
    @Test
    fun isFragmentViewsFullyRendered() {

        launchFragmentInHiltContainer<TrendingMoviesFragment> {
            Thread.sleep(3000)
        }

        Espresso.onView(withId(R.id.progressBar))
            .check(ViewAssertions.matches(not(isDisplayed())))

        Espresso.onView(withId(R.id.tvCategory))
            .check(ViewAssertions.matches(isDisplayed()))

        Espresso.onView(withId(R.id.layoutError))
            .check(ViewAssertions.matches(not(isDisplayed())))

        Espresso.onView(withId(R.id.reloadPostsBtn))
            .check(ViewAssertions.matches(not(isDisplayed())))

        Espresso.onView(withId(R.id.recyclerTrendingMovies))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    /**
    Perform Action : Click on the first item of the recyclerView
     **/
    @Test
    fun clickFirstItemInList() {
        launchFragmentInHiltContainer<TrendingMoviesFragment> {
            Navigation.setViewNavController(
                this.requireView(),
                mockNavController
            )
            Thread.sleep(3000)
        }

        Espresso.onView(withId(R.id.recyclerTrendingMovies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<MoviesAdapter.MovieViewHolder>(
                0,
                clickChildViewWithId(R.id.imgMovieBanner)
            )
        )
    }

    /**
     * Check recyclerView Scrolling to random Position
     **/
    @Test
    fun checkRecyclerViewScrolling() {
        val scrollPosition = 20
        launchFragmentInHiltContainer<TrendingMoviesFragment> {
            Thread.sleep(2000)
        }
        Espresso.onView(withId(R.id.recyclerTrendingMovies))
        RecyclerViewActions.scrollToPosition<MoviesAdapter.MovieViewHolder>(scrollPosition - 5)
    }

    /**
     * Check recyclerView Item Count
     **/
    @Test
    fun matchItemCount() {
        var itemCount: Int? = 0
        launchFragmentInHiltContainer<TrendingMoviesFragment> {
            Thread.sleep(3000)
            itemCount = this.moviesAdapter.itemCount
        }
        Espresso.onView(withId(R.id.recyclerTrendingMovies))
            .perform(itemCount?.let { count ->
                RecyclerViewActions.scrollToPosition<MoviesAdapter.MovieViewHolder>(count - 1)
            })
    }
}