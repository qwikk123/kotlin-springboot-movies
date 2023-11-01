package qwikk.spring.movies.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import qwikk.spring.movies.repo.GenreRepository
import qwikk.spring.movies.repo.MovieRepository

@Service
class MovieService() {

    @Autowired
    lateinit var repository: MovieRepository
    @Autowired
    lateinit var genreRepository : GenreRepository

    fun findAll() = repository.findAllByOrderByImdbRatingDesc()
    fun findTop10() = repository.findTop10Rated()
    fun findMovieId(id: Long) = repository.findById(id)
    fun findMovieTitle(title: String) = repository.findBySeriesTitle(title)
    fun findAllGenres() = genreRepository.findAllGenres()
    fun findByGenre(name: String) = repository.findByGenreName(name)
}