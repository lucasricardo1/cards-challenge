package com.lucasricardo1.cardschallenge.client;

import com.lucasricardo1.cardschallenge.dto.response.ShuffleCardsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(url = "https://deckofcardsapi.com/api/deck/new/shuffle/?deck_count=1", name = "shuffle")
public interface ShuffleCardsClient {

    @GetMapping
    ShuffleCardsResponse shuffleCards();

}
