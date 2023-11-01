package qwikk.spring.movies.repo

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import qwikk.spring.movies.model.Movie

interface MovieRepository : JpaRepository<Movie, Long> {

    fun findAllByOrderByImdbRatingDesc() : Iterable<Movie>
    fun findBySeriesTitle(seriesTitle:String) : Iterable<Movie>

    fun findByGenreName(name: String) : Iterable<Movie>

    @Query("FROM Movie ORDER BY imdbRating DESC LIMIT 10")
    fun findTop10Rated() : List<Movie>
}