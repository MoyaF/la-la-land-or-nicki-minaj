package io.moya.lalalandornickyminaj.mappers

import io.moya.lalalandornickyminaj.controller.quote.model.RandomQuoteQuery
import io.moya.lalalandornickyminaj.model.Quote
import io.moya.lalalandornickyminaj.repositories.quote.model.QuoteEntity

class QuoteMapper {

    companion object {
        fun mapToQuote(quoteEntity: QuoteEntity): Quote = with(quoteEntity) {
            Quote(id = id, text = text)
        }

        fun mapToRandomQuoteQuery(quote: Quote): RandomQuoteQuery = with(quote) {
            RandomQuoteQuery(id, text)
        }
    }
}
