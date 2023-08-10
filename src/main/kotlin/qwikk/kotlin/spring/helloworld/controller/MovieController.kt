package qwikk.kotlin.spring.helloworld.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import qwikk.kotlin.spring.helloworld.model.Movie
import qwikk.kotlin.spring.helloworld.service.MovieService

@RestController()
@RequestMapping("api/movies")
class MovieController(private val service:MovieService) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @GetMapping("/all")
    fun getMovies(): Collection<Movie> = service.getMovies()

    @GetMapping("{movieId}")
    fun getMovie(@PathVariable movieId :Int) = service.getMovie(movieId)
}