package io.moya.lalalandornickyminaj.services.game

import io.moya.lalalandornickyminaj.services.artist.ArtistService
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class GameService(private val artistService: ArtistService) {

    fun quoteBelongsToArtist(quoteId: UUID, artistId: UUID): Boolean {
        return artistService.findById(artistId)
            ?.quotes
            ?.map { it.id }
            ?.contains(quoteId)
            ?: false
    }
}
