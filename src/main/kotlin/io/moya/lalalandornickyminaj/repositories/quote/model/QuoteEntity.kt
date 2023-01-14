package io.moya.lalalandornickyminaj.repositories.quote.model

import io.moya.lalalandornickyminaj.repositories.artist.model.ArtistEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table
import org.hibernate.Hibernate
import java.util.UUID

@Entity
@Table(name = "quote")
data class QuoteEntity(

    @Id
    val id: UUID,

    val text: String,

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "artist_entity_id")
    val artistEntity: ArtistEntity?,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as QuoteEntity

        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , text = $text )"
    }
}
