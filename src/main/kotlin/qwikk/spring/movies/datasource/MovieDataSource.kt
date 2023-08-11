package qwikk.spring.movies.datasource

import qwikk.spring.movies.model.Movie

interface MovieDataSource {
    fun retrieveMovies(): Collection<Movie>
    fun retrieveMovie(movieId: Int): Movie
    fun createMovie(movie: Movie): Movie
}