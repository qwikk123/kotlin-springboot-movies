package qwikk.kotlin.spring.helloworld.datasource.mock

import org.springframework.stereotype.Repository
import qwikk.kotlin.spring.helloworld.datasource.MovieDataSource
import qwikk.kotlin.spring.helloworld.model.Movie

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