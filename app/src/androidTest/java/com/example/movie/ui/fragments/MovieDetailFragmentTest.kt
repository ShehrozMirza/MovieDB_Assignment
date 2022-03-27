package com.example.movie.ui.fragments

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.MediumTest
import com.example.movie.EspressoUriIdlingResource
import com.example.movie.R
import com.example.movie.models.MovieModel
import com.example.movie.ui.detail.MoviesDetailFragment
import com.example.movie.utils.factory.DataFactoryImpl
import com.example.movie.utils.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.*

@HiltAndroidTest
@MediumTest
@ExperimentalCoroutinesApi
class MovieDetailFragmentTest {

    private lateinit var movieModel: MovieModel
    private lateinit var fragmentArgsBundle: Bundle

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Before
    fun setup() {
        movieModel = DataFactoryImpl.getMovieModel()
        fragmentArgsBundle = bundleOf("movieDetails" to movieModel)
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
    is Fragment Displayed
     */

    @Test
    fun isFragmentDisplayed() {
        launchFragmentInHiltContainer<MoviesDetailFragment>(fragmentArgs = fragmentArgsBundle) {
        }
        onView(withId(R.id.ivCover))
            .check(matches(ViewMatchers.isDisplayed()))
    }

    /**
     *
    is Movie Detail 'object' rendered to all views properly'
    has to match exactly with this data because of api calling get movie details
    {
    "id": 3,
    "title": "Shadows in Paradise"
    "genres": Drama,Comedy,
    "overview": "An episode in the life of Nikander, a garbage man, involving the death of a co-worker, an affair and much more.",
    "run_time":"74 mins"
    }
     */

    @Test
    fun isAllDataRenderedToViews() {
        launchFragmentInHiltContainer<MoviesDetailFragment>(fragmentArgs = fragmentArgsBundle) {
            Thread.sleep(3000)
        }
        onView(withId(R.id.tvTitle)).check(matches(withText("Shadows in Paradise")))
        onView(withId(R.id.tvGenre)).check(matches(withText("Drama, Comedy")))
        onView(withId(R.id.tvRunningTime)).check(matches(withText("74 mins")))
        onView(withId(R.id.tvDescription)).check(matches(withText("An episode in the life of Nikander, a garbage man, involving the death of a co-worker, an affair and much more.")))
    }
}