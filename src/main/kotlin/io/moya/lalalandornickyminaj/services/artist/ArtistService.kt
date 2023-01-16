package io.moya.lalalandornickyminaj.services.artist

import io.moya.lalalandornickyminaj.mappers.ArtistMapper
import io.moya.lalalandornickyminaj.model.Artist
import io.moya.lalalandornickyminaj.repositories.artist.ArtistEntityRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ArtistService(private val artistEntityRepository: ArtistEntityRepository) {

    fun findAll(): List<Artist> {
        return artistEntityRepository.findAll().asSequence()
            .map { ArtistMapper.mapToArtist(it) }
            .toList()
    }

    fun findById(artistId: UUID): Artist? {
        return artistEntityRepository.findByIdOrNull(artistId)
            ?.let { ArtistMapper.mapToArtist(it) }
    }
}
