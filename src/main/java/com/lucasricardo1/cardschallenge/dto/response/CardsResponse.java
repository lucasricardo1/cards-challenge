package com.lucasricardo1.cardschallenge.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardsResponse {

    String code;

    String  value;

    String suit;
}
