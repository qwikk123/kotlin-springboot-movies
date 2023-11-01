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

@SpringBootTest
@AutoConfigureMockMvc
class MovieControllerTest @Autowired constructor(
    val mockMvc: MockMvc,
    val objectMapper: ObjectMapper
) {

    val url = "/api/movies"

    @Nested
    @DisplayName("GET /api/movies/dbmovies/top10")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class GetTop10 {
        @Test
        fun `Should return all movies`() {
            mockMvc.get("$url/top10")
                .andDo { print() }
                .andExpect {
                    status { isOk()}
                    content { contentType(MediaType.APPLICATION_JSON) }
                    jsonPath("$[0].seriesTitle") { value("The Shawshank Redemption")}
                }
        }
    }
}