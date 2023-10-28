package qwikk.spring.movies.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import qwikk.spring.movies.model.Movie
import qwikk.spring.movies.repo.MovieRepository
import qwikk.spring.movies.service.MovieService
import java.util.*
import kotlin.NoSuchElementException

@RestController
@RequestMapping("api/movies")
@CrossOrigin(origins = ["http://localhost:3000"])
class MovieController(private val service: MovieService) {

    @Autowired
    lateinit var repository: MovieRepository

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e: IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @GetMapping("/all")
    fun getMovies(): Collection<Movie> = service.getMovies()

    @GetMapping("/{movieId}")
    fun getMovie(@PathVariable movieId :Int) = service.getMovie(movieId)

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    fun addMovie(@RequestBody movie: Movie) = service.addMovie(movie)

    @PatchMapping("/patch")
    fun updateMovie(@RequestBody movie: Movie) = service.updateMovie(movie)

    @DeleteMapping("/delete/{movieID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteMovie(@PathVariable movieID: Int) {
        service.delete(movieID)
    }

    @GetMapping("/dbmovies/all")
    fun findAll() = repository.findAllByOrderByImdbRatingDesc()

    @GetMapping("/dbmovies/top10")
    fun findTop10() = repository.findTop10Rated()

    @GetMapping("/dbmovies/id/{movieID}")
    fun findAllById(@PathVariable movieID: Long): Optional<Movie> = repository.findById(movieID)

    @GetMapping("/dbmovies/title/{seriesTitle}")
    fun findByTitle(@PathVariable seriesTitle:String) = repository.findBySeriesTitle(seriesTitle)

    @GetMapping("dbmovies/genre/{genre}")
    fun findByGenre(@PathVariable genre: String) = repository.findByGenreContains(genre)

    @RequestMapping("/dbmovies/fix")
    fun fixMovies() {
        val movies = repository.findAll()
        movies.forEach {
            it.posterLink = it.posterLink.split("_V1_")[0] + "_V1_.jpg"
            repository.save(it)
        }
    }
}