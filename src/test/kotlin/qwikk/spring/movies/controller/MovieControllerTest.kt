package qwikk.spring.movies.controller

import org.junit.jupiter.api.Assertions.*
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

@SpringBootTest
@AutoConfigureMockMvc
class MovieControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    val url = "/api/movies"

    @Nested
    @DisplayName("getMovies()")
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
    @DisplayName("getMovie()")
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
        fun `Should return Not Found if the account a number does not exist`() {
            val movieId = -1

            mockMvc.get("$url/$movieId")
                .andDo { print() }
                .andExpect {
                    status { isNotFound() }
                }
        }
    }
}