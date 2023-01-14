package io.moya.lalalandornickyminaj.controller.artist

import io.moya.lalalandornickyminaj.mappers.ArtistMapper
import io.moya.lalalandornickyminaj.services.artist.ArtistService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/artist")
class ArtistController(private val artistService: ArtistService) {

    @GetMapping
    fun findAll() = artistService.findAll()
        .let { ArtistMapper.mapToFindAllArtistQuery(it) }
        .let { ResponseEntity.ok(it) }
}
