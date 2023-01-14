package io.moya.lalalandornickyminaj.services.quote

import io.moya.lalalandornickyminaj.mappers.QuoteMapper
import io.moya.lalalandornickyminaj.model.Quote
import io.moya.lalalandornickyminaj.repositories.quote.QuoteEntityRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class QuoteService(private val quoteEntityRepository: QuoteEntityRepository) {

    fun getRandomQuote(): Quote? {
        val count = quoteEntityRepository.count()
        val index = (Math.random() * count).toInt()
        val quoteEntity = quoteEntityRepository.findAll(PageRequest.of(index, 1)).firstOrNull()
        return quoteEntity?.let { QuoteMapper.mapToQuote(it) }
    }
}
