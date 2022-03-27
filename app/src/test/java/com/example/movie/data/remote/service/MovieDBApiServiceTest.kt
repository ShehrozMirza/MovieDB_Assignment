package com.example.movie.data.remote.service

import com.example.movie.data.MainCoroutinesRule
import com.example.movie.data.remote.ApiResponse
import com.example.movie.data.remote.MovieDBApiService
import com.example.movie.data.remote.api.ApiAbstract
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.IOException

class MovieDBApiServiceTest : ApiAbstract<MovieDBApiService>() {

    private lateinit var apiService: MovieDBApiService

    @get:Rule
    var coroutineRule = MainCoroutinesRule()

    @Before
    fun setUp() {
        apiService = createService(MovieDBApiService::class.java)
    }

    @After
    fun tearDown() {
    }

    @Throws(IOException::class)
    @Test
    fun `test getMoviesList() returns list of Movies`() = runBlocking {
        // Given
        enqueueResponse("/movie_list_response.json")

        // Invoke
        val response = apiService.getMoviesList(page = 1)
        val responseBody = requireNotNull((response as ApiResponse.ApiSuccessResponse).data)
        mockWebServer.takeRequest()

        // Then
        assertThat(responseBody.movies[0].id, `is`(508947))
        assertThat(responseBody.movies[0].title, `is`("Turning Red"))
        assertThat(responseBody.movies[0].originalLanguage, `is`("en"))
        assertThat(responseBody.movies[0].originalTitle, `is`("Turning Red"))
        assertThat(
            responseBody.movies[0].overview,
            `is`("Thirteen-year-old Mei is experiencing the awkwardness of being a teenager with a twist – when she gets too excited, she transforms into a giant red panda.")
        )
        assertThat(responseBody.movies[0].posterPath, `is`("/qsdjk9oAKSQMWs0Vt5Pyfh6O4GZ.jpg"))
        assertThat(responseBody.movies[0].voteCount, `is`(1045))
    }

    @Throws(IOException::class)
    @Test
    fun `test getMovieDetail() return Movie Detail`() = runBlocking {
        // Given
        enqueueResponse("/movie_detail_response.json")

        // Invoke
        val response = apiService.getMovieDetail(movieId = 3)
        val responseBody = requireNotNull((response as ApiResponse.ApiSuccessResponse).data)
        mockWebServer.takeRequest()

        // Then
        assertThat(responseBody.id, `is`(3))
        assertThat(responseBody.title, `is`("Shadows in Paradise"))
        assertThat(responseBody.originalLanguage, `is`("fi"))
        assertThat(responseBody.originalTitle, `is`("Varjoja paratiisissa"))
        assertThat(
            responseBody.overview,
            `is`("An episode in the life of Nikander, a garbage man, involving the death of a co-worker, an affair and much more.")
        )
        assertThat(responseBody.posterPath, `is`("/nj01hspawPof0mJmlgfjuLyJuRN.jpg"))
        assertThat(responseBody.voteCount, `is`(158))
    }
}
