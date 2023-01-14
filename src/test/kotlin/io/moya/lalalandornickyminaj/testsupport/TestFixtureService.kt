package io.moya.lalalandornickyminaj.testsupport

import io.moya.lalalandornickyminaj.repositories.artist.ArtistEntityRepository
import io.moya.lalalandornickyminaj.repositories.artist.model.ArtistEntity
import io.moya.lalalandornickyminaj.repositories.quote.QuoteEntityRepository
import io.moya.lalalandornickyminaj.repositories.quote.model.QuoteEntity
import io.moya.lalalandornickyminaj.testsupport.fixtures.ArtistTestFixtures
import io.moya.lalalandornickyminaj.testsupport.fixtures.QuoteTestFixtures
import org.springframework.stereotype.Component

@Component
class TestFixtureService(
    private val quoteEntityRepository: QuoteEntityRepository,
    private val artistEntityRepository: ArtistEntityRepository,
) : ArtistTestFixtures, QuoteTestFixtures {

    fun clearData() {
        quoteEntityRepository.deleteAll()
        artistEntityRepository.deleteAll()
    }

    fun setUpPersistedArtist(
        artist: ArtistEntity = createArtistEntity(),
    ): ArtistEntity {
        return artistEntityRepository.save(artist)
    }

    fun setUpPersistedQuote(
        quote: QuoteEntity = createQuoteEntity(artist = createArtistEntity()),
    ) = quoteEntityRepository.save(quote)

    fun setUpPersistedMultipleQuotes(
        size: Int,
    ): List<QuoteEntity> = generateSequence { createQuoteEntity(artist = createArtistEntity()) }
        .take(size)
        .map { quoteEntityRepository.save(it) }
        .toList()
}
