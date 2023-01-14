package io.moya.lalalandornickyminaj.controller

import io.moya.lalalandornickyminaj.testsupport.AbstractIntegrationTest
import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.get

class ArtistControllerTest : AbstractIntegrationTest() {

    @Test
    fun `GIVEN an empty database THEN status code is 200`() {
        mockMvc.get("/api/artist")
            .andExpect {
                status { isOk() }
            }
    }

    @Test
    fun `GIVEN an empty database THEN response body has an empty array`() {
        mockMvc.get("/api/artist")
            .andExpect {
                jsonPath("$.results.length()", equalTo(0))
            }
    }

    @Test
    fun `GIVEN a quote present in database THEN status code is 200`() {
        tfs.setUpPersistedQuote()
        mockMvc.get("/api/artist")
            .andExpect {
                status { isOk() }
            }
    }

    @Test
    fun `GIVEN a quote present in database THEN response body has same values as it has in database`() {
        val quoteEntity = tfs.setUpPersistedArtist()
        mockMvc.get("/api/artist")
            .andExpect {
                jsonPath("$.results.length()", equalTo(1))
                jsonPath("$.results[0].id", equalTo("${quoteEntity.id}"))
                jsonPath("$.results[0].name", equalTo(quoteEntity.name))
            }
    }
}
