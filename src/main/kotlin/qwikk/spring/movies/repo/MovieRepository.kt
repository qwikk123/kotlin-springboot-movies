package qwikk.spring.movies.repo

import org.springframework.data.repository.CrudRepository
import qwikk.spring.movies.model.Movie

interface MovieRepository : CrudRepository<Movie, Long> {
    fun findBySeriesTitle(seriesTitle:String) : Iterable<Movie>
}