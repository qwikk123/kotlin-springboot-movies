package qwikk.spring.movies.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.IdClass
import jakarta.persistence.Table
import java.io.Serializable

@Entity
@IdClass(CompositeKey::class)
@Table(name = "movie_genre")
data class MovieGenre(
    @Column(name = "movieid") @Id var movieId: Int = -1,
    @Column(name = "genreid") @Id var genreId: Int = -1
)

class CompositeKey(val movieId: Int = -1, val genreId: Int = -1) : Serializable