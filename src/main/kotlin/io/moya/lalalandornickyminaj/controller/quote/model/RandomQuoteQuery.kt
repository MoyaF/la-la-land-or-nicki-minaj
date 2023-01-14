package io.moya.lalalandornickyminaj.controller.quote.model

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.util.UUID

@JsonNaming(value = SnakeCaseStrategy::class)
data class RandomQuoteQuery(
    val id: UUID,
    val text: String,
)
