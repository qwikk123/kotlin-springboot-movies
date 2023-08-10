package qwikk.spring.movies.datasource.mock

import org.springframework.stereotype.Repository
import qwikk.spring.movies.datasource.MovieDataSource
import qwikk.spring.movies.model.Movie

@Repository
class MockMovieDataSource : MovieDataSource {
    val movies = listOf(
        Movie(1,"TestName1",2000,123),
        Movie(2,"TestName2",2012,160),
        Movie(3,"TestName3",1995,60)
        )

    override fun retrieveMovies() = movies
    override fun retrieveMovie(movieId: Int) =
        movies.firstOrNull { it.id == movieId }
            ?: throw NoSuchElementException("Could not find movie with id: $movieId")
}