package io.moya.lalalandornickyminaj.model

import java.util.UUID

data class Artist(
    val id: UUID,
    val name: String,
    val quotes: Set<Quote>,
)
