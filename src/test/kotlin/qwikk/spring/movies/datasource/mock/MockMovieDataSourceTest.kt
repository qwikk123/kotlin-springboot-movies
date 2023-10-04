package qwikk.spring.movies.datasource.mock

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

class MockMovieDataSourceTest {

    private val mockDataSource = MockMovieDataSource()

    @Nested
    @DisplayName("retrieveMovies()")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class RetrieveMovies {
        @Test
        fun `Should provide a collection of movies`() {
            val movies = mockDataSource.retrieveMovies()

            assertThat(movies).isNotEmpty()
        }

        @Test
        fun `Should provide some mock data`() {
            val movies = mockDataSource.retrieveMovies()

            assertThat(movies).allMatch { it.seriesTitle.isNotBlank() }
            assertThat(movies).allMatch { it.runtime.isNotBlank() }
            assertThat(movies).allMatch { it.releasedYear > 0 }
        }
    }
}