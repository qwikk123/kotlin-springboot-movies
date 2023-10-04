package qwikk.spring.movies.datasource.mock

import org.springframework.stereotype.Repository
import qwikk.spring.movies.datasource.MovieDataSource
import qwikk.spring.movies.model.Movie
import java.lang.IllegalArgumentException

@Repository
class MockMovieDataSource : MovieDataSource {
    val movies = mutableListOf(
        Movie(1003,"link","Title",1000,"cert","runtime","genre","imdbrating","overview",90,"director","star1","star2","star3","star4","votes","gross"),
        Movie(1004,"link","Title",1000,"cert","runtime","genre","imdbrating","overview",90,"director","star1","star2","star3","star4","votes","gross"),
        Movie(1005,"link","Title",1000,"cert","runtime","genre","imdbrating","overview",90,"director","star1","star2","star3","star4","votes","gross")
        )

    override fun retrieveMovies() = movies
    override fun retrieveMovie(movieId: Int) =
        movies.firstOrNull { it.movieID == movieId }
            ?: throw NoSuchElementException("Could not find movie with id: $movieId")

    override fun createMovie(movie: Movie): Movie {
        if (movies.any{ it.movieID == movie.movieID} ) {
            throw IllegalArgumentException("MovieId: ${movie.movieID} already exists")
        }
        movies.add(movie)
        return movie
    }

    override fun updateMovie(movie: Movie) {
        val movieToReplace = movies.firstOrNull { it.movieID == movie.movieID }
            ?: throw NoSuchElementException("Could not find movie with id: ${movie.movieID}")
        movies.remove(movieToReplace)
        movies.add(movie)
    }

    override fun delete(movieId: Int) {
        if (!movies.removeIf { it.movieID == movieId }) {
            throw NoSuchElementException("Could not find movie with id: $movieId")
        }
    }
}