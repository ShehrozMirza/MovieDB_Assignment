package com.example.movie.data.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import com.example.movie.LiveDataUtils.getOrAwaitValueTest
import com.example.movie.data.MainCoroutinesRule
import com.example.movie.data.usecases.MovieDbUsecase
import com.example.movie.ui.trending.TrendingMoviesViewModel
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class TrendingMoviesViewModelTest {

    // Subject under test
    private lateinit var viewModel: TrendingMoviesViewModel
    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @kotlinx.coroutines.ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @MockK
    lateinit var usecase: MovieDbUsecase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchMoviesList()_shows_success_response`() = runBlocking {
        viewModel = TrendingMoviesViewModel(usecase)
        viewModel.fetchMoviesList()
        val liveData = viewModel.trendingMovies.getOrAwaitValueTest()
        MatcherAssert.assertThat(liveData, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(liveData, CoreMatchers.instanceOf(PagingData::class.java))
    }
}