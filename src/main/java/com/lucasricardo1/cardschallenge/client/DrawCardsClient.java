package com.lucasricardo1.cardschallenge.client;

import com.lucasricardo1.cardschallenge.dto.response.DrawCardsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "https://deckofcardsapi.com/api/deck", name = "draw")
public interface DrawCardsClient {

    @GetMapping("/{deck_id}/draw/")
    DrawCardsResponse drawCards(@PathVariable String deck_id,
                                @RequestParam int count);
}
