package io.moya.lalalandornickyminaj.testsupport.fixtures

import io.moya.lalalandornickyminaj.repositories.artist.model.ArtistEntity
import io.moya.lalalandornickyminaj.repositories.quote.model.QuoteEntity
import java.util.UUID

interface ArtistTestFixtures {

    fun createArtistEntity(
        id: UUID = UUID.randomUUID(),
        name: String = "An Artist",
        vararg quotes: QuoteEntity = arrayOf(),
    ): ArtistEntity {
        return ArtistEntity(id, name, quotes.toMutableSet())
    }
}
