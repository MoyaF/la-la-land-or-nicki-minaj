package io.moya.lalalandornickyminaj.controller.game

import io.moya.lalalandornickyminaj.controller.game.model.Looser
import io.moya.lalalandornickyminaj.controller.game.model.QuoteGuessCommand
import io.moya.lalalandornickyminaj.controller.game.model.QuoteGuessCommandResponse
import io.moya.lalalandornickyminaj.controller.game.model.Winner
import io.moya.lalalandornickyminaj.services.game.GameService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/game")
class GameController(private val gameService: GameService) {

    @PostMapping
    fun game(@RequestBody quoteGuessCommand: QuoteGuessCommand): ResponseEntity<QuoteGuessCommandResponse> =
        with(quoteGuessCommand) {
            if (gameService.quoteBelongsToArtist(quoteId, artistId)) {
                Winner()
            } else {
                Looser()
            }.let {
                ResponseEntity.ok(it)
            }
        }
}
