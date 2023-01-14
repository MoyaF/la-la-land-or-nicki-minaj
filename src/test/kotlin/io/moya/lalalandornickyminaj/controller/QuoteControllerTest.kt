package io.moya.lalalandornickyminaj.controller

import io.moya.lalalandornickyminaj.testsupport.AbstractIntegrationTest
import io.moya.lalalandornickyminaj.testsupport.fixtures.ArtistTestFixtures
import io.moya.lalalandornickyminaj.testsupport.fixtures.QuoteTestFixtures
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.oneOf
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.get

class QuoteControllerTest : AbstractIntegrationTest(), ArtistTestFixtures, QuoteTestFixtures {

    @Test
    fun `GIVEN an empty database THEN status code is 204`() {
        mockMvc.get("/api/quote/random")
            .andExpect {
                status { isNoContent() }
            }
    }

    @Test
    fun `GIVEN a quote present in database THEN status code is 200`() {
        tfs.setUpPersistedQuote()
        mockMvc.get("/api/quote/random")
            .andExpect {
                status { isOk() }
            }
    }

    @Test
    fun `GIVEN a quote present in database THEN response body has same values as it has in database`() {
        val quoteEntity = tfs.setUpPersistedQuote()
        mockMvc.get("/api/quote/random")
            .andExpect {
                jsonPath("$.id", equalTo("${quoteEntity.id}"))
                jsonPath("$.text", equalTo(quoteEntity.text))
            }
    }

    @Test
    fun `GIVEN multiple quotes present in database THEN response body has the information of one of the quotes persisted`() {
        val quoteEntities = tfs.setUpPersistedMultipleQuotes(5)
        mockMvc.get("/api/quote/random")
            .andExpect {
                jsonPath("$.id", oneOf(*quoteEntities.map { "${it.id}" }.toTypedArray()))
                jsonPath("$.text", oneOf(*quoteEntities.map { it.text }.toTypedArray()))
            }
    }
}
