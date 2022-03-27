package com.example.movie.data.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.movie.LiveDataUtils.getOrAwaitValueTest
import com.example.movie.data.DataState
import com.example.movie.data.MainCoroutinesRule
import com.example.movie.data.usecases.MoviesDetailsUsecase
import com.example.movie.models.MovieModel
import com.example.movie.ui.detail.ContentState
import com.example.movie.ui.detail.ErrorState
import com.example.movie.ui.detail.MoviesDetailViewModel
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
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
class MoviesDetailViewModelTest {

    // Subject under test
    private lateinit var viewModel: MoviesDetailViewModel
    private val testDispatcher = TestCoroutineDispatcher()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @kotlinx.coroutines.ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @MockK
    lateinit var usecase: MoviesDetailsUsecase

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
    fun `fetchMovieDetail()_show_success_response`() = runBlocking {

        coEvery { usecase.fetchMovieDetail(any()) }.returns(flowOf(DataState.success(MovieModel(id = 3))))
        viewModel = MoviesDetailViewModel(usecase)
        viewModel.getMovieDetail(3)

        val uiState = viewModel.uiStateLiveData.getOrAwaitValueTest()
        val liveData = viewModel.movieDetailLiveData.getOrAwaitValueTest()

        MatcherAssert.assertThat(uiState, CoreMatchers.instanceOf(ContentState::class.java))
        MatcherAssert.assertThat(uiState, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(liveData, CoreMatchers.notNullValue())
    }

    @Test
    fun `fetchMovieDetail()_show_error_response`() {

        coEvery { usecase.fetchMovieDetail(any()) }.returns(flowOf(DataState.error("No Data Found")))
        viewModel = MoviesDetailViewModel(usecase)
        viewModel.getMovieDetail(3)

        val uiState = viewModel.uiStateLiveData.getOrAwaitValueTest()

        MatcherAssert.assertThat(uiState, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(uiState, CoreMatchers.instanceOf(ErrorState::class.java))
        MatcherAssert.assertThat("No Data Found", CoreMatchers.`is`((uiState as ErrorState).message))
    }
}

