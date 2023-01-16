package io.moya.lalalandornickyminaj.controller.game.model

sealed class QuoteGuessCommandResponse(
    val winner: Boolean,
    val message: String,
)

class Winner : QuoteGuessCommandResponse(true, "Winner winner chicken dinner")

class Looser : QuoteGuessCommandResponse(false, "You loose, better luck next time")
