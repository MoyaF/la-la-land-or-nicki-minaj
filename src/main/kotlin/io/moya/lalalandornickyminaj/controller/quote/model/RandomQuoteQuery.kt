package io.moya.lalalandornickyminaj.controller.quote.model

import java.util.UUID

data class RandomQuoteQuery(
    val id: UUID,
    val text: String,
)
