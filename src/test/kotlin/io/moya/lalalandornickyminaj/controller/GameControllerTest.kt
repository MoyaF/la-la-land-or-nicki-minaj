package io.moya.lalalandornickyminaj.controller

import io.moya.lalalandornickyminaj.testsupport.AbstractIntegrationTest
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.post
import java.util.UUID

class GameControllerTest : AbstractIntegrationTest() {

    @Test
    fun `GIVEN a request body with quote_id matching an artist_id THEN status code is 200`() {
        val quoteEntity = tfs.setUpPersistedQuote()

        mockMvc.post("/api/game") {
            contentType = MediaType.APPLICATION_JSON
            content = """
                {
                    "quote_id": "${quoteEntity.id}",
                    "artist_id": "${quoteEntity.artistEntity?.id}"
                }
            """
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
        }
    }

    @Test
    fun `GIVEN a request body with quote_id matching an artist_id THEN response body contains winner message`() {
        val quoteEntity = tfs.setUpPersistedQuote()

        mockMvc.post("/api/game") {
            contentType = MediaType.APPLICATION_JSON
            content = """
                {
                    "quote_id": "${quoteEntity.id}",
                    "artist_id": "${quoteEntity.artistEntity?.id}"
                }
            """
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            jsonPath("$.winner", equalTo(true))
            jsonPath("$.message", equalTo("Winner winner chicken dinner"))
        }
    }

    @Test
    fun `GIVEN a request body with quote_id not matching an artist_id THEN response body contains winner message`() {
        val quoteEntity = tfs.setUpPersistedQuote()

        mockMvc.post("/api/game") {
            contentType = MediaType.APPLICATION_JSON
            content = """
                {
                    "quote_id": "${UUID.randomUUID()}",
                    "artist_id": "${quoteEntity.artistEntity?.id}"
                }
            """
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            jsonPath("$.winner", equalTo(false))
            jsonPath("$.message", equalTo("You loose, better luck next time"))
        }
    }
}
