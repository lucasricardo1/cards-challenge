package com.lucasricardo1.cardschallenge.dto;


import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShuffleDTO {

    private boolean success;

    private String deckId;

    private boolean shuffle;

    private int remaining;
}
