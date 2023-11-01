package qwikk.spring.movies.repo

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import qwikk.spring.movies.model.Genre

interface GenreRepository : JpaRepository<Genre, Long> {

    @Query("SELECT name FROM Genre")
    fun findAllGenres(): List<String>


}