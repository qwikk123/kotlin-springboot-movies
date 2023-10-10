package qwikk.spring.movies.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table


@Entity
@Table(name = "movies")
class Movie(
    @Id var movieID: Int,
    @Column(name = "poster_link") var posterLink:String,
    @Column(name = "series_title") val seriesTitle:String,
    @Column(name = "released_year") val releasedYear:Int,
    val certificate:String,
    val runtime:String,
    val genre:String,
    @Column(name = "imdb_rating")val imdbRating:Double,
    val overview:String,
    @Column(name = "meta_score")val metaScore:Int?,
    val director:String,
    val star1:String,
    val star2:String,
    val star3:String,
    val star4:String,
    @Column(name = "no_of_votes")val noOfVotes:Int,
    val gross:String,
) {
    private constructor() : this(-1,"", "",0,"","","",-1.0,"",0,"","","","","",-1,"")
}