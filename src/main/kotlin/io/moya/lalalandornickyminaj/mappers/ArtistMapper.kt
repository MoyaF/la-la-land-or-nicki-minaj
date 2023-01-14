package io.moya.lalalandornickyminaj.mappers

import io.moya.lalalandornickyminaj.controller.artist.model.ArtistQuery
import io.moya.lalalandornickyminaj.controller.artist.model.FindAllArtistQuery
import io.moya.lalalandornickyminaj.model.Artist
import io.moya.lalalandornickyminaj.repositories.artist.model.ArtistEntity

class ArtistMapper {
    companion object {
        fun mapToArtist(artistEntity: ArtistEntity) = with(artistEntity) {
            val quotes = quoteEntities.asSequence().map { QuoteMapper.mapToQuote(it) }.toSet()
            Artist(id, name, quotes)
        }

        fun mapToFindAllArtistQuery(artists: List<Artist>) = artists.asSequence()
            .map { mapToArtistQuery(it) }
            .toList()
            .let { FindAllArtistQuery(it) }

        private fun mapToArtistQuery(artist: Artist) = with(artist) {
            ArtistQuery(id, name)
        }
    }
}
