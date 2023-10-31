package qwikk.spring.movies.repo

import org.springframework.data.jpa.repository.JpaRepository
import qwikk.spring.movies.model.MovieGenre

interface MovieGenreRepository : JpaRepository<MovieGenre, Long> {
}