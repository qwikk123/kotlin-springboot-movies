package qwikk.kotlin.spring.movies.service

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import qwikk.kotlin.spring.movies.datasource.MovieDataSource

class MovieServiceTest {

    private val dataSource: MovieDataSource = mockk(relaxed = true)

    private val movieService = MovieService(dataSource)

    @Nested
    @DisplayName("getMovies() calls retrieveMovies()")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetMovies {
        @Test
        fun `Should call its data source to retrieve movies`() {
            movieService.getMovies()
            verify(exactly = 1) { dataSource.retrieveMovies() }
        }
    }
}