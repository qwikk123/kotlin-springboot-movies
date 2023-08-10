package qwikk.kotlin.spring.helloworld.service

import org.springframework.stereotype.Service
import qwikk.kotlin.spring.helloworld.datasource.MovieDataSource
import qwikk.kotlin.spring.helloworld.model.Movie

@Service
class MovieService(private val dataSource: MovieDataSource) {

    fun getMovies() = dataSource.retrieveMovies()
    fun getMovie(movieId: Int) = dataSource.retrieveMovie(movieId)

}