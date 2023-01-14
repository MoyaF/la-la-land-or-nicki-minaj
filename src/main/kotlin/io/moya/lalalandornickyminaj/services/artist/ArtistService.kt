package io.moya.lalalandornickyminaj.services.artist

import io.moya.lalalandornickyminaj.mappers.ArtistMapper
import io.moya.lalalandornickyminaj.model.Artist
import io.moya.lalalandornickyminaj.repositories.artist.ArtistEntityRepository
import org.springframework.stereotype.Service

@Service
class ArtistService(private val artistEntityRepository: ArtistEntityRepository) {

    fun findAll(): List<Artist> {
        return artistEntityRepository.findAll().asSequence()
            .map { ArtistMapper.mapToArtist(it) }
            .toList()
    }
}
