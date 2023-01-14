package io.moya.lalalandornickyminaj.repositories.artist.model

import io.moya.lalalandornickyminaj.repositories.quote.model.QuoteEntity
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.Hibernate
import java.util.UUID

@Entity
@Table(name = "artist")
data class ArtistEntity(

    @Id
    val id: UUID,

    val name: String,

    @OneToMany(mappedBy = "artistEntity", cascade = [CascadeType.ALL])
    val quoteEntities: Set<QuoteEntity>,

) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as ArtistEntity

        return id == other.id
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , name = $name )"
    }
}
