package com.example.movie.data.usecases

import com.example.movie.MockTestUtil
import com.example.movie.data.DataState
import com.example.movie.data.repository.MovieDBRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MovieDbUsecaseTest {

    @MockK
    private lateinit var repository: MovieDBRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test invoking MovieDbUsecaseTest gives list of Movies`() = runBlocking {
        // Given
        val usecase = MovieDbUsecase(repository)
        val givenMoviesList = MockTestUtil.createMovieListResponse(3)

        // When
        coEvery { repository.fetchTrendingMoviesList(any()) }
            .returns(flowOf(DataState.success(givenMoviesList)))

        // Invoke
        val moviesListFlow = usecase.fetchTrendingMovies(1)

        // Then
        MatcherAssert.assertThat(moviesListFlow, CoreMatchers.notNullValue())

        val movieListDataState = moviesListFlow.first()
        MatcherAssert.assertThat(movieListDataState, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(movieListDataState, CoreMatchers.instanceOf(DataState.Success::class.java))

        val moviesList = (movieListDataState as DataState.Success).data
        MatcherAssert.assertThat(moviesList, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(moviesList.movies.size, `is`(givenMoviesList.movies.size))
    }

    @Test
    fun `test invoking MovieDbUsecaseTest gives detail of Movie`() = runBlocking {
        // Given
        val usecase = MovieDbUsecase(repository)
        val givenMovie = MockTestUtil.createMovieDetail()

        // When
        coEvery { repository.getMovieDetail(any()) }
            .returns(flowOf(DataState.success(givenMovie)))

        // Invoke
        val moviesDetailFlow = usecase.fetchMovieDetail(1)

        // Then
        MatcherAssert.assertThat(moviesDetailFlow, CoreMatchers.notNullValue())

        val movieDetailDataState = moviesDetailFlow.first()
        MatcherAssert.assertThat(movieDetailDataState, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(movieDetailDataState, CoreMatchers.instanceOf(DataState.Success::class.java))

        val movieDetail = (movieDetailDataState as DataState.Success).data
        MatcherAssert.assertThat(movieDetail, CoreMatchers.notNullValue())
        MatcherAssert.assertThat(movieDetail, `is`(givenMovie))
    }
}