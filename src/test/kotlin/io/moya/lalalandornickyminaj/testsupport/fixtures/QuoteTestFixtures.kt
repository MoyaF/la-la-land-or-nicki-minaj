package io.moya.lalalandornickyminaj.testsupport.fixtures

import io.moya.lalalandornickyminaj.repositories.artist.model.ArtistEntity
import io.moya.lalalandornickyminaj.repositories.quote.model.QuoteEntity
import java.util.UUID

interface QuoteTestFixtures : ArtistTestFixtures {

    fun createQuoteEntity(
        id: UUID = UUID.randomUUID(),
        text: String = "This is a quote",
        artist: ArtistEntity? = null,
    ): QuoteEntity {
        return QuoteEntity(id, text, artist)
    }
}
