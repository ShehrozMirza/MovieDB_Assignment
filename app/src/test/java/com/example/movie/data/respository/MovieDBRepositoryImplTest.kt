package com.example.movie.data.respository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.movie.MockTestUtil
import com.example.movie.data.DataState
import com.example.movie.data.MainCoroutinesRule
import com.example.movie.data.remote.ApiResponse
import com.example.movie.data.remote.MovieDBApiService
import com.example.movie.data.remote.api.ApiUtil.successCall
import com.example.movie.data.repository.MovieDBRepositoryImpl
import com.example.movie.models.MovieModel
import com.example.movie.utils.StringUtils
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MovieDBRepositoryImplTest {

    private lateinit var repository: MovieDBRepositoryImpl

    @MockK
    private lateinit var apiService: MovieDBApiService

    @MockK
    private lateinit var stringUtils: StringUtils

    @kotlinx.coroutines.ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesRule = MainCoroutinesRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `test fetchTrendingMovies() gives list of Movies`() = runBlocking {

        // Given
        repository = MovieDBRepositoryImpl(stringUtils, apiService)
        val givenMoviesList = MockTestUtil.createMovieListResponse()
        val apiCall = successCall(givenMoviesList)

        // When
        coEvery { apiService.getMoviesList(page = any()) }
            .returns(apiCall)

        // Invoke
        val apiResponseFlow = repository.fetchTrendingMoviesList(pageNumber = 1)

        // Then
        MatcherAssert.assertThat(apiResponseFlow, CoreMatchers.notNullValue())

        val movieListDataState = apiResponseFlow.first()
        MatcherAssert.assertThat(movieListDataState, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(
            movieListDataState,
            CoreMatchers.instanceOf(DataState.Success::class.java)
        )

        val movieList = (movieListDataState as DataState.Success).data.movies
        MatcherAssert.assertThat(movieList, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(movieList.size, CoreMatchers.`is`(givenMoviesList.movies.size))

        coVerify(exactly = 1) { apiService.getMoviesList(page = any()) }
        confirmVerified(apiService)
    }

    @Test
    fun `test fetchTrendingMovies() gives empty movies list`() = runBlocking {

        // Given
        repository = MovieDBRepositoryImpl(stringUtils, apiService)
        val givenMoviesList = MockTestUtil.createMovieListResponse(0)
        val apiCall = successCall(givenMoviesList)

        // When
        coEvery { apiService.getMoviesList(page = any()) }
            .returns(apiCall)

        // Invoke
        val apiResponseFlow = repository.fetchTrendingMoviesList(pageNumber = 1)

        // Then
        MatcherAssert.assertThat(apiResponseFlow, CoreMatchers.notNullValue())

        val movieListDataState = apiResponseFlow.first()
        MatcherAssert.assertThat(movieListDataState, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(
            movieListDataState,
            CoreMatchers.instanceOf(DataState.Success::class.java)
        )

        val movieList = (movieListDataState as DataState.Success).data.movies
        MatcherAssert.assertThat(movieList, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(movieList.size, CoreMatchers.`is`(givenMoviesList.movies.size))

        coVerify(exactly = 1) { apiService.getMoviesList(page = any()) }
        confirmVerified(apiService)
    }

    @Test
    fun `test getMovieDetail() gives details of Movie`() = runBlocking {

        // Given
        repository = MovieDBRepositoryImpl(stringUtils, apiService)
        val giveMovieDetail = MockTestUtil.createMovieDetail()
        val apiCall = successCall(giveMovieDetail)

        // When
        coEvery { apiService.getMovieDetail(movieId = any()) }
            .returns(apiCall)

        // Invoke
        val apiResponseFlow = repository.getMovieDetail(id = 1)

        // Then
        MatcherAssert.assertThat(apiResponseFlow, CoreMatchers.notNullValue())

        val movieDetailState = apiResponseFlow.first()
        MatcherAssert.assertThat(movieDetailState, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(movieDetailState, CoreMatchers.instanceOf(DataState.Success::class.java)
        )

        val movie = (movieDetailState as DataState.Success).data
        MatcherAssert.assertThat(movie, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(movie.id, CoreMatchers.`is`(giveMovieDetail.id))

        coVerify(exactly = 1) { apiService.getMovieDetail(movieId = any()) }
        confirmVerified(apiService)
    }

    @Test
    fun `test getMovieDetail() throws exception`() = runBlocking {

        // Given
        repository = MovieDBRepositoryImpl(stringUtils, apiService)
        val givenMessage = "Test Error Message"
        val exception = Exception(givenMessage)
        val apiResponse = ApiResponse.exception<MovieModel>(exception)

        // When
        coEvery { apiService.getMovieDetail(any()) }
            .returns(apiResponse)
        coEvery { stringUtils.somethingWentWrong() }
            .returns(givenMessage)

        // Invoke
        val apiResponseFlow = repository.getMovieDetail(1)

        // Then
        MatcherAssert.assertThat(apiResponseFlow, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(apiResponseFlow.count(), CoreMatchers.`is`(1))

        val apiResponseDataState = apiResponseFlow.first()
        MatcherAssert.assertThat(apiResponseDataState, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(apiResponseDataState, CoreMatchers.instanceOf(DataState.Error::class.java))

        val errorMessage = (apiResponseDataState as DataState.Error).message
        MatcherAssert.assertThat(errorMessage, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(errorMessage, CoreMatchers.equalTo(givenMessage))

        coVerify(atLeast = 1) { apiService.getMovieDetail(any()) }
        confirmVerified(apiService)
    }

}