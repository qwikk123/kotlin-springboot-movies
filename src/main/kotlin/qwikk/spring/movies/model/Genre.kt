package qwikk.spring.movies.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import jakarta.persistence.*

@Entity
@Table(name = "genre")
data class Genre(
    @Column(name = "genre_id") @Id var genreID: Int = -1,
    @Column(name = "genre_name") var name: String = "",

    @ManyToMany(mappedBy = "genre")
    @JsonIgnoreProperties("genre")
    val movies: Set<Movie> = emptySet()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Genre

        return genreID == other.genreID
    }

    override fun hashCode(): Int {
        return genreID
    }
}