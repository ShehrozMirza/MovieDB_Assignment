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
class MoviesDetailUsecaseTest {

    @MockK
    private lateinit var repository: MovieDBRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun `test invoking MovieDbUsecaseTest gives detail of Movie`() = runBlocking {
        // Given
        val usecase = MoviesDetailsUsecase(repository)
        val givenMovie = MockTestUtil.createMovieDetail()

        // When
        coEvery { repository.getMovieDetail(any()) }
            .returns(flowOf(DataState.success(givenMovie)))

        // Invoke
        val moviesDetailFlow = usecase.fetchMovieDetail(3)

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