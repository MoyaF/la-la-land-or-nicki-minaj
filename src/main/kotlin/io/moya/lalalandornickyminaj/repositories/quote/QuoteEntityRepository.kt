package io.moya.lalalandornickyminaj.repositories.quote

import io.moya.lalalandornickyminaj.repositories.quote.model.QuoteEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface QuoteEntityRepository : JpaRepository<QuoteEntity, UUID>
