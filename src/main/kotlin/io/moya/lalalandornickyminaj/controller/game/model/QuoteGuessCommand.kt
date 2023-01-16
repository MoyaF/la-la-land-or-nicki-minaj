package io.moya.lalalandornickyminaj.controller.game.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.UUID

data class QuoteGuessCommand(
    @field:JsonProperty("artist_id")
    val artistId: UUID,
    @field:JsonProperty("quote_id")
    val quoteId: UUID,
)
