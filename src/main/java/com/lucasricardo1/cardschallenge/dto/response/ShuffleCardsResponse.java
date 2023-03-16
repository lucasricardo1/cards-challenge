package com.lucasricardo1.cardschallenge.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShuffleCardsResponse {

    boolean success;
    String deck_id;
    boolean shuffled;
    int remaining;

}
