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
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
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
                    jsonPath("$[0].name") { value("TestName1")}
                }
        }
    }

    @Nested
    @DisplayName("GET /api/movies/{movieId}")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetMovie {
        @Test
        fun `Should return movie with the given id`() {
            val id = 1

            mockMvc.get("$url/$id")
                .andDo { print() }
                .andExpect {
                    status { isOk()}
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$.id") { value(1)}
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
            val movie = Movie(1234,"AddedMovie",2023,120)

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
        }

        @Test
        fun `Should return BAD REQUEST if movie id already exists`() {
            val movie = Movie(1234,"AddedMovie",2023,120)

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
}