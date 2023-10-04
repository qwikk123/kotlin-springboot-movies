package qwikk.spring.movies.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.*
import qwikk.spring.movies.model.Movie

@SpringBootTest
@AutoConfigureMockMvc
class MovieControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {

    val url = "/api/movies"

    @Nested
    @DisplayName("GET /api/movies/all")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetMovies {
        @Test
        fun `Should return all movies`() {
            mockMvc.get("$url/all")
                .andDo { print() }
                .andExpect {
                    status { isOk()}
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[0].seriesTitle") { value("Title")}
                }
        }
    }

    @Nested
    @DisplayName("GET /api/movies/{movieId}")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetMovie {
        @Test
        fun `Should return movie with the given id`() {
            val id = 1001

            mockMvc.get("$url/$id")
                .andDo { print() }
                .andExpect {
                    status { isOk()}
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.movieID") { value(1001)}
                }
        }

        @Test
        fun `Should return Not Found if the movieId does not exist`() {
            val movieId = -1

            mockMvc.get("$url/$movieId")
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }
        }
    }

    @Nested
    @DisplayName("POST /api/movies/add")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class AddMovie {

        @Test
        fun `Should add a new movie`() {
            val movie = Movie(1234,"link","Title",1000,"cert","runtime","genre","imdbrating","overview",90,"director","star1","star2","star3","star4","votes","gross")

            mockMvc.post("$url/add") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(movie)
            }
                .andDo { print() }
                .andExpect {
                    status { isCreated() }
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.id") { value(1234) }
                }

            mockMvc.get("$url/${movie.movieID}")
                .andExpect { content {
                    json(objectMapper.writeValueAsString(movie))
                } }
        }

        @Test
        fun `Should return BAD REQUEST if movie id already exists`() {
            val movie = Movie(1234,"link","Title",1000,"cert","runtime","genre","imdbrating","overview",90,"director","star1","star2","star3","star4","votes","gross")

            mockMvc.post("$url/add") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(movie)
            }
                .andDo { print() }
                .andExpect {
                    status { isBadRequest() }
                }
        }
    }

    @Nested
    @DisplayName("PATCH /api/movies/patch")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class PatchMovie {
        @Test
        fun `Should update movie`() {
            val updatedMovie = Movie(1234,"link","Title",1000,"cert","runtime","genre","imdbrating","overview",90,"director","star1","star2","star3","star4","votes","gross")

            mockMvc.patch("$url/patch") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(updatedMovie)
            }
                .andDo { print() }
                .andExpect {
                    status { isOk() }
                }

            mockMvc.get("$url/${updatedMovie.movieID}")
                .andExpect { content {
                    json(objectMapper.writeValueAsString(updatedMovie))
                } }
        }

        @Test
        fun `patch should return NOT FOUND if no movie with id exists`() {
            val badMovie = Movie(-50,"link","Title",1000,"cert","runtime","genre","imdbrating","overview",90,"director","star1","star2","star3","star4","votes","gross")

            mockMvc.patch("$url/patch") {
                contentType = MediaType.APPLICATION_JSON
                content = objectMapper.writeValueAsString(badMovie)
            }
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }


        }
    }

    @Nested
    @DisplayName("DELETE /api/movies/delete/{movieId}")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class DeleteMovie {
        @Test
        fun `Should delete movie with movieId`() {
            val movieId = 1003

            mockMvc.delete("$url/delete/$movieId")
                .andDo { print() }
                .andExpect {
                    status { isNoContent() }
                }

            mockMvc.get("$url/$movieId")
                .andExpect { status { isNotFound() } }
        }

        @Test
        fun `Should return NOT FOUND if movie does not exist`() {
            val movieId = -123

            mockMvc.delete("$url/delete/$movieId")
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }
        }
    }
}