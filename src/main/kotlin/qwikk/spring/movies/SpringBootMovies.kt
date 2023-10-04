package qwikk.spring.movies

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MovieSpringApplication

fun main(args: Array<String>) {
	runApplication<MovieSpringApplication>(*args)
}
