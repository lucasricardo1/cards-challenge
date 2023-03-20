package com.lucasricardo1.cardschallenge.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrawCardsResponse {

        private boolean success;

        @JsonProperty("deck_id")
        private String deckId;

        private List<CardsResponse> cards;

        private int remaining;
}
