package com.lucasricardo1.cardschallenge.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DrawHandsResponse {
    String nome;
    List<Long> values;
}
