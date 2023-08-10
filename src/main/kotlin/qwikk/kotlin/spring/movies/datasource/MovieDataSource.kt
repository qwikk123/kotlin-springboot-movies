package qwikk.kotlin.spring.movies.datasource

import qwikk.kotlin.spring.movies.model.Movie

interface MovieDataSource {
    fun retrieveMovies(): Collection<Movie>
    fun retrieveMovie(movieId: Int): Movie
}