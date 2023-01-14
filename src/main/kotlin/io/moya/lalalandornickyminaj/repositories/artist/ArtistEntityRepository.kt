package io.moya.lalalandornickyminaj.repositories.artist

import io.moya.lalalandornickyminaj.repositories.artist.model.ArtistEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface ArtistEntityRepository : JpaRepository<ArtistEntity, UUID>
