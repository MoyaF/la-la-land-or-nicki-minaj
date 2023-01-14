package io.moya.lalalandornickyminaj.controller.quote

import io.moya.lalalandornickyminaj.controller.quote.model.RandomQuoteQuery
import io.moya.lalalandornickyminaj.mappers.QuoteMapper
import io.moya.lalalandornickyminaj.services.quote.QuoteService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/quote")
@RestController
class QuoteController(
    private val quoteService: QuoteService,
) {

    @GetMapping("/random")
    fun random(): ResponseEntity<RandomQuoteQuery> {
        val quote = quoteService.getRandomQuote()
        return quote?.let { QuoteMapper.mapToRandomQuoteQuery(it) }
            ?.let { ResponseEntity.ok(it) } ?: ResponseEntity.noContent().build()
    }
}
