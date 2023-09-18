package qwikk.spring.movies.datasource.mock

import org.springframework.stereotype.Repository
import qwikk.spring.movies.datasource.MovieDataSource
import qwikk.spring.movies.model.Movie
import java.lang.IllegalArgumentException

@Repository
class MockMovieDataSource : MovieDataSource {
    val movies = mutableListOf(
        Movie(1,"TestName1",2000,123),
        Movie(2,"TestName2",2012,160),
        Movie(3,"TestName3",1995,60)
        )

    override fun retrieveMovies() = movies
    override fun retrieveMovie(movieId: Int) =
        movies.firstOrNull { it.id == movieId }
            ?: throw NoSuchElementException("Could not find movie with id: $movieId")

    override fun createMovie(movie: Movie): Movie {
        if (movies.any{ it.id == movie.id} ) {
            throw IllegalArgumentException("MovieId: ${movie.id} already exists")
        }
        movies.add(movie)
        return movie
    }

    override fun updateMovie(movie: Movie) {
        val movieToReplace = movies.firstOrNull { it.id == movie.id }
            ?: throw NoSuchElementException("Could not find movie with id: ${movie.id}")
        movies.remove(movieToReplace)
        movies.add(movie)
    }

    override fun delete(movieId: Int) {
        if (!movies.removeIf { it.id == movieId }) {
            throw NoSuchElementException("Could not find movie with id: $movieId")
        }
    }
}