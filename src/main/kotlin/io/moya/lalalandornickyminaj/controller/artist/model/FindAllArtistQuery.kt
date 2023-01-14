package io.moya.lalalandornickyminaj.controller.artist.model

data class FindAllArtistQuery(
    val results: List<ArtistQuery> = listOf(),
)
