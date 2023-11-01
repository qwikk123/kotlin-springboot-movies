package qwikk.spring.movies.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import qwikk.spring.movies.model.Movie
import qwikk.spring.movies.service.MovieService
import java.util.*
import kotlin.NoSuchElementException

@RestController
@RequestMapping("api/movies")
@CrossOrigin
class MovieController(private val service: MovieService) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e: IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @GetMapping("/all")
    fun findAll() = service.findAll()

    @GetMapping("/top10")
    fun findTop10() = service.findTop10()

    @GetMapping("/id/{movieID}")
    fun findById(@PathVariable movieID: Long): Optional<Movie> = service.findMovieId(movieID)

    @GetMapping("/title/{seriesTitle}")
    fun findByTitle(@PathVariable seriesTitle:String) = service.findMovieTitle(seriesTitle)

    @GetMapping("/genre/{genre}")
    fun findByGenre(@PathVariable genre: String) = service.findByGenre(genre)

    @GetMapping("/genre/all")
    fun findAllGenres() = service.findAllGenres()
}