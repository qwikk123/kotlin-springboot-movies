package qwikk.spring.movies.service

import org.springframework.stereotype.Service
import qwikk.spring.movies.datasource.MovieDataSource

@Service
class MovieService(private val dataSource: MovieDataSource) {

    fun getMovies() = dataSource.retrieveMovies()
    fun getMovie(movieId: Int) = dataSource.retrieveMovie(movieId)

}