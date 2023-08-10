package qwikk.kotlin.spring.helloworld.datasource

import qwikk.kotlin.spring.helloworld.model.Movie

interface MovieDataSource {
    fun retrieveMovies(): Collection<Movie>
    fun retrieveMovie(movieId: Int): Movie
}