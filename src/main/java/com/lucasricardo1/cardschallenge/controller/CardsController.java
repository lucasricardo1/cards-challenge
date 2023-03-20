package com.lucasricardo1.cardschallenge.controller;

import com.lucasricardo1.cardschallenge.dto.ShuffleDTO;
import com.lucasricardo1.cardschallenge.services.CardsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cards-challenge")
@Tag(name = "Cards Challenge", description = "Cards Challenge API")
public class CardsController {

    @Autowired
    CardsService cardsService;

    @PostMapping("/shuffle")
    @Operation(summary = "Shuffle and save a new deck.")
    public ResponseEntity<ShuffleDTO> shuffleDeck() {
        return new ResponseEntity<>(cardsService.shuffleDeck(), HttpStatus.OK);
    }

    @PostMapping("/decks")
    @Operation(summary = "Save decks of all players.")
    public ResponseEntity<String> savePlayersDecks(
            @RequestParam String deckId
    ) {
        return new ResponseEntity<>(cardsService.saveDecks(deckId), HttpStatus.OK);
    }

    @GetMapping("/pickWinner")
    @Operation(summary = "Pick the winner.")
    public ResponseEntity<String> pickWinner() {
        return new ResponseEntity<>(cardsService.pickWinner(), HttpStatus.OK);
    }

}
